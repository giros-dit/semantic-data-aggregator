package tid;

// GOOGLE JSON imports
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonSyntaxException;

// GENERATED-SOURCES imports
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;

// YANG-TOOLS imports
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.QName;
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
import org.opendaylight.yangtools.yang.data.codec.xml.XmlParserStream;
import org.opendaylight.yangtools.yang.data.codec.xml.XmlCodecFactory;
import org.opendaylight.yangtools.yang.data.codec.xml.XMLStreamNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.model.api.DataSchemaNode;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.SchemaNode;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.util.Optional;
// JAVA imports
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.IllegalArgumentException;

public class StringNetflowSerialization {

	// VARIABLES TO SERIALIZE AND DESERIALIZE
	private static final BindingNormalizedNodeSerializer netflow_agg_codec = new BindingCodecContext(
			BindingRuntimeHelpers.createRuntimeContext(
					Netflow.class));
	private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers
			.createEffectiveModel(BindingReflections.loadModuleInfos());;

	// CONVERSIONS FROM STRING TO NETFLOW CLASS --->
	/**
	 * Receives the String by the topic and returns a Netflow Class or null if the String is not correct.
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws FactoryConfigurationError
	 * @throws XMLStreamException
	 */
	public static Netflow String2Netflow(String netflowString) throws SAXException, IOException, ParserConfigurationException, XMLStreamException, FactoryConfigurationError{

		StringReader stringReader = new StringReader(netflowString);
		XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(stringReader);

		Netflow netflowv9 = null; 
		try{
			NormalizedNodeResult result = new NormalizedNodeResult();
			NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);

			QName qname = QName.create("http://data-aggregator.com/ns/netflow", "2021-10-08", "netflow");

			Optional<org.opendaylight.yangtools.yang.model.api.Module> moduleOptional = schemaContext.findModule(qname.getModule());

			if (moduleOptional.isPresent()) {
				org.opendaylight.yangtools.yang.model.api.Module module = moduleOptional.get();

				Optional<? extends SchemaNode> nodeOptional = module.findDataChildByName(qname);

				if (nodeOptional.isPresent()) {
					SchemaNode parentNode = nodeOptional.get();
					XmlParserStream xmlParser = XmlParserStream.create(streamWriter, schemaContext, parentNode);
					xmlParser.parse(xmlReader);
					NormalizedNode<?, ?> transformedInput = result.getResult();
					InstanceIdentifier<Netflow> netflow_iid = InstanceIdentifier.create(Netflow.class);
					YangInstanceIdentifier netflow_yiid = netflow_agg_codec.toYangInstanceIdentifier(netflow_iid);
					netflowv9 = (Netflow) netflow_agg_codec.fromNormalizedNode(netflow_yiid, transformedInput).getValue();
				}
			}


		} catch(IllegalStateException | XMLStreamException | IllegalArgumentException e){
			System.out.println("EXCEPTION: Malformed INPUT (XML)");
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("Unknown exception:");
			e.printStackTrace();
		}
		
		return netflowv9;

	}

	// <--- CONVERSIONS FROM STRING TO NETFLOW CLASS

	// CONVERSIONS FROM NETFLOW CLASS TO XML STRING --->


	/**
	 * Performs the actual data conversion.
	 * @param schemaPath
	 * @param data
	 * @return
	 * @throws XMLStreamException
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	private static String doConvert(SchemaPath schemaPath, NormalizedNode<?, ?> data)
			throws XMLStreamException, FactoryConfigurationError, ParserConfigurationException, SAXException {
		try (StringWriter writer = new StringWriter()) {
			XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(writer);
			final NormalizedNodeStreamWriter xmlStream = XMLStreamNormalizedNodeStreamWriter.create(xmlWriter,
					schemaContext, schemaPath);
			try (NormalizedNodeWriter nodeWriter = NormalizedNodeWriter.forStreamWriter(xmlStream)) {
				nodeWriter.write(data);
				nodeWriter.flush();
			}
			return writer.toString();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Given a Netflow class serialize it into a String
	 */
	public static String Netflow2String(Netflow netflow) {

		String serialized = "";
		if (netflow != null) {

			InstanceIdentifier<Netflow> iid = InstanceIdentifier.create(Netflow.class);;
			String xml_obj = new String();

			try {
				Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = netflow_agg_codec.toNormalizedNode(iid,
						netflow);
				xml_obj = doConvert(schemaContext.getPath(), normalized.getValue());
			} catch (Exception ex) {
				ex.printStackTrace();
				StringWriter errors = new StringWriter();
				ex.printStackTrace(new PrintWriter(errors));
			}
			serialized = xml_obj;
			System.out.println(serialized);
		}

		return serialized;

	}

	// <--- CONVERSIONS FROM NETFLOW CLASS TO JSON STRING
}
