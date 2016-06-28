package cu.tissca.x901.wadl.extensions;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.Text;
import cu.tissca.commons.jsonschema.model.JsonObjectSchema;
import cu.tissca.commons.jsonschema.model.JsonSchema;
import cu.tissca.commons.jsonschema.parser.JsonSchemaParser;
import cu.tissca.commons.jsonschema.parser.JsonSchemaParserException;
import cu.tissca.x901.wadl.VisitorAdapter;
import cu.tissca.x901.wadl.model.DocElement;
import cu.tissca.x901.wadl.model.RepresentationElement;
import cu.tissca.x901.wadl.model.WadlElement;

import java.util.logging.Logger;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class JsonSchemaScanner {

    public static final Logger LOGGER = Logger.getLogger(JsonSchemaScanner.class.getName());

    public static void scanAndPrepareJsonSchemas(final WadlElement wadlElement) {
        wadlElement.accept(new VisitorAdapter() {
                               public RepresentationElement currentRepresentation;

                               @Override
                               public void visitRepresentation(RepresentationElement representationElement) {
                                   currentRepresentation = representationElement;
                               }

                               @Override
                               public void visitDocElement(DocElement docElement) {
                                   if (currentRepresentation != null) {
                                       lookForJSONTextNode(docElement.getElement(), new JsonSchemaConsumer() {
                                           @Override
                                           public void accept(JsonObjectSchema jsonObjectSchema) {
                                               currentRepresentation.getExtendedProperties().put(ExtendedProperties.JSON_SCHEMA, jsonObjectSchema);
                                           }
                                       });
                                   }
                               }

                               @Override
                               public void endVisitRepresentation(RepresentationElement representationElement) {
                                   currentRepresentation = null;
                               }
                           }
        );
    }

    private interface JsonSchemaConsumer {
        void accept(JsonObjectSchema jsonObjectSchema);
    }

    static void lookForJSONTextNode(Element element, JsonSchemaConsumer consumer) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            switch (item.getNodeType()) {
                case Node.TEXT_NODE:
                    Text text = (Text) item;
                    try {
                        JsonSchemaParser parser = new JsonSchemaParser();
                        JsonSchema jsonSchema = parser.parse(text.getData());
                        JsonObjectSchema result = jsonSchema.asObjectSchema();
                        if (result != null) { // found
                            consumer.accept(result);
                        } else {
                            LOGGER.info("JsonSchema was found but was not convertible to JsonObjectSchema");
                        }
                    } catch (JsonSchemaParserException e) {
                        LOGGER.info("Could not parse json schema in text element "+text.toString());
                    }
                    break;
                case Node.ELEMENT_NODE:
                    lookForJSONTextNode((Element) item, consumer);
                    break;
                default:
                    break;
            }
        }
    }
}
