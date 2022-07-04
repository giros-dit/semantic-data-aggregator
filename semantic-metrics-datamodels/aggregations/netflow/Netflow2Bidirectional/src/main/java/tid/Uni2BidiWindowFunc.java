package tid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.NetflowBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacketBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecordBuilder;


public class Uni2BidiWindowFunc
    extends ProcessWindowFunction<String, String, String, GlobalWindow> {


		private static Netflow NetflowUni2Bidi(Netflow netflow1, Netflow netflow2){
			FlowDataRecordBuilder flow = new FlowDataRecordBuilder(netflow1.getExportPacket().getFlowDataRecord().get(0));
			FlowDataRecord outvalues = netflow2.getExportPacket().getFlowDataRecord().get(0);

			flow.setBytesOut(outvalues.getBytesIn());
			flow.setPktsOut(outvalues.getPktsIn());

			List<FlowDataRecord> newlist = new ArrayList<>();
			newlist.add(flow.build());

			ExportPacketBuilder expacketb = new ExportPacketBuilder(netflow1.getExportPacket());
			expacketb.setFlowDataRecord(newlist);

			NetflowBuilder nfbuilder = new NetflowBuilder(netflow1);
			nfbuilder.setExportPacket(expacketb.build());

			return nfbuilder.build();
		}

		private static int size(Iterable data) {

			if (data instanceof Collection) {
				return ((Collection<?>) data).size();
			}
			int counter = 0;
			for (Object i : data) {
				counter++;
			}
			return counter;
		}

		@Override
		public void process(String key, Context context, Iterable<String> input, Collector<String> out) {
			
			int windowL = size(input);
			if(windowL==2){
				
				Iterator<String> it = input.iterator();
				Netflow netflow1 = StringNetflowSerialization.String2Netflow(it.next());
				Netflow netflow2 = StringNetflowSerialization.String2Netflow(it.next());

				Netflow nfinal;
				// Take the lower time-received of both flows
				if(netflow1.getCollectorGoflow2().getTimeReceived().getValue().compareTo(netflow2.getCollectorGoflow2().getTimeReceived().getValue()) <= 0){
					// netflow1 has lower or equal time-received
					nfinal = NetflowUni2Bidi(netflow1, netflow2);
				}else{
					// netflow2 has lower time-received
					nfinal = NetflowUni2Bidi(netflow2, netflow1);
				}

				String nfinalS =  StringNetflowSerialization.Netflow2String(nfinal);
				

				out.collect(nfinalS);
			}else if(windowL == 1){
				out.collect(input.iterator().next());
			}else{
				System.out.println("\n\nERROR: window length different from 1 or 2,");
				System.out.println("this might be a problem with the custom trigger implementation\n\n");
			}
			
			// this is to try to clean the state (key) when the window is processed
			// I could not conclude if this is done automatically for this specific case.
			try {
				this.clear(context);
			} catch (Exception e) {
				// context could not be cleaned?
				e.printStackTrace();
			}
		}
	}

