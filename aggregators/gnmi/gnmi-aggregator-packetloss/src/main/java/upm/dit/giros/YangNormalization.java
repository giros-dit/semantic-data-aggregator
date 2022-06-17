package upm.dit.giros;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map.Entry;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.MapEntryNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactory;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactorySupplier;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonParserStream;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonWriterFactory;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.Notification;

public class YangNormalization {
    
    // VARIABLES TO SERIALIZE AND DESERIALIZE
	private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.RFC7951;
	private static final BindingNormalizedNodeSerializer op_if_notification_agg_codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext());
	private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos());
	private static final JSONCodecFactory codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
	private static final JsonParser PARSER = new JsonParser();
    private static final Logger LOG = LogManager.getLogger(YangNormalization.class);
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";

    // CONVERSIRONS FROM STRING TO NOTIFICATION CLASS

    /**
     * Receives the String by the topic and returns a Notification Class or null if the String is not correct.
     * @param notification
     * @return
     */
    public static Notification input2OpenconfigInterfacesNotificationClass(String notification){
        // Get Object of class openconfig-interfaces-notification-wrapper from the input (JSON-IETF)
		JsonReader reader = new JsonReader(new StringReader(notification));
		Notification openconfig_interfaces_notification  = null;

		try{
            NormalizedNodeResult result = new NormalizedNodeResult();
            NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);
            JsonParserStream jsonParser = JsonParserStream.create(streamWriter, codecFactory);
            jsonParser.parse(reader);
            NormalizedNode<?, ?> transformedInput = result.getResult();
            InstanceIdentifier<Notification> op_if_notification_iid = InstanceIdentifier.create(Notification.class);
            YangInstanceIdentifier op_if_notification_yiid = op_if_notification_agg_codec.toYangInstanceIdentifier(op_if_notification_iid);
            openconfig_interfaces_notification = (Notification) op_if_notification_agg_codec.fromNormalizedNode(op_if_notification_yiid, transformedInput).getValue();
        } catch(IllegalStateException | JsonSyntaxException | IllegalArgumentException e){
			LOG.info("EXCEPTION: Malformed INPUT (JSON-IETF)");
			e.printStackTrace();
		} catch(Exception e){
			LOG.info("Unknown exception:");
			e.printStackTrace();
		}

        return openconfig_interfaces_notification;
	}
 

    // CONVERSIONS FROM INTERFACES CLASS TO JSON-IETF STRING

	/**
	 * Performs the actual data conversion.
	 *
	 * @param schemaPath - schema path for data
	 * @param data - Normalized Node
	 * @return data converted as a JsonObject
	 */
	private static JsonObject doConvert(SchemaPath schemaPath, NormalizedNode<?, ?> data) {
		try (StringWriter writer = new StringWriter();
			 JsonWriter jsonWriter = JsonWriterFactory.createJsonWriter(writer)) {
			final NormalizedNodeStreamWriter jsonStream = (data instanceof MapEntryNode)
					? JSONNormalizedNodeStreamWriter.createNestedWriter(codecFactory, schemaPath, null, jsonWriter)
					: JSONNormalizedNodeStreamWriter.createExclusiveWriter(codecFactory, schemaPath, null, jsonWriter);
			try (NormalizedNodeWriter nodeWriter = NormalizedNodeWriter.forStreamWriter(jsonStream)) {
				nodeWriter.write(data);
				nodeWriter.flush();
			}
			return PARSER.parse(writer.toString()).getAsJsonObject();
		} catch (IOException e) {
            LOG.error(JSON_IO_ERROR, e);
			return null;
		}
	}

    /**
     * Given an Interfaces class, the method seriealizeds it into a String with JSON-IETF format
     * @param interfaces_packetlossKpi
     * @return
     */
    public static String serialize2JSONstring(Interfaces interfaces_packetlossKpi){
		InstanceIdentifier<Interfaces> iid = InstanceIdentifier.create(Interfaces.class);
		JsonObject json_ietf_obj = new JsonObject();

		try {
			Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = op_if_notification_agg_codec.toNormalizedNode(iid, interfaces_packetlossKpi);
			json_ietf_obj = doConvert(schemaContext.getPath(), normalized.getValue());
		} catch (Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
		}

		return json_ietf_obj.toString();
	}
}
