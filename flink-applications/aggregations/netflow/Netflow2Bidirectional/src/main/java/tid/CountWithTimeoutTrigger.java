package tid;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeutils.base.LongSerializer;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.Window;

import java.io.IOException;

/**
 * A {@link Trigger} that fires once the number of elements in a pane reaches the given count or the timeout expires, whichever happens first.
 *
 * @param <T> The type of elements.
 * @param <W> The type of {@link Window Windows} on which this trigger can operate.
 */
public class CountWithTimeoutTrigger<W extends Window> extends Trigger<Object, W> {

    private static final long serialVersionUID = 1L;

    private final long maxCount;
    private final long timeoutMs;

    private final ValueStateDescriptor<Long> countDesc = new ValueStateDescriptor<>("count", LongSerializer.INSTANCE);
    private final ValueStateDescriptor<Long> deadlineDesc = new ValueStateDescriptor<>("deadline", LongSerializer.INSTANCE);

    CountWithTimeoutTrigger(long maxCount, long timeoutMs) {
        this.maxCount = maxCount;
        this.timeoutMs = timeoutMs;
    }

    // Executes every time an element enters in the window
    @Override
    public TriggerResult onElement(Object element, long timestamp, W window, Trigger.TriggerContext ctx) throws Exception {
        final ValueState<Long> deadline = ctx.getPartitionedState(deadlineDesc);
        final ValueState<Long> count = ctx.getPartitionedState(countDesc);

        // Long currentDeadline = deadline.value();
        final long currentDeadline;
        // If it is not set change default value for LONG.MAX_VALUE
        if (deadline.value()==null) {
            currentDeadline = Long.MAX_VALUE;
        } else {
            currentDeadline = deadline.value();
        }

        final long currentTimeMs = System.currentTimeMillis();

        final long newCount;
        if (count.value()==null){
            newCount = 1;
        } else {
            newCount = count.value() + 1;
        }

        // If the time has passed or the value enter is max of the window process the window
        if (currentTimeMs >= currentDeadline || newCount >= maxCount) {
            clear(window, ctx);
            return fire();
        }

        // if the timer is not setted, set it
        if (currentDeadline == Long.MAX_VALUE) {
            final long nextDeadline = currentTimeMs + timeoutMs;
            deadline.update(nextDeadline);
            ctx.registerProcessingTimeTimer(nextDeadline);
        }

        count.update(newCount);

        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onEventTime(long time, W window, Trigger.TriggerContext ctx) {
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onProcessingTime(long time, W window, Trigger.TriggerContext ctx) throws Exception {
        final ValueState<Long> deadline = ctx.getPartitionedState(deadlineDesc);

        // fire only if the deadline hasn't changed since registering this timer
        if (Long.compare(deadline.value(), (Long)time)==0) {
            clear(window, ctx);
            return fire();
        }
        return TriggerResult.CONTINUE;
    }

    @Override
    public void clear(W window, TriggerContext ctx) throws Exception {
        final ValueState<Long> deadline = ctx.getPartitionedState(deadlineDesc);
        final ValueState<Long> count = ctx.getPartitionedState(countDesc);
        final Long deadlineValue = deadline.value();

        // If the timer is set, delete the timer
        if (deadlineValue != deadlineDesc.getDefaultValue()) {
            ctx.deleteProcessingTimeTimer(deadlineValue);
        }

        // delete state of the window, count and deadline
        deadline.clear();
        count.clear();
    }

    private TriggerResult fire() throws IOException {
        return TriggerResult.FIRE_AND_PURGE;
    }

    @Override
    public String toString() {
        return "CountWithTimeoutTrigger(" + maxCount + "," + timeoutMs + ")";
    }

    public static <T, W extends Window> CountWithTimeoutTrigger<W> of(long maxCount, long intervalMs) {
        return new CountWithTimeoutTrigger<>(maxCount, intervalMs);
    }

}
