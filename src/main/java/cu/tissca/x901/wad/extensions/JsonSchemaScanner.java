package cu.tissca.x901.wad.extensions;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.Text;
import cu.tissca.commons.jsonschema.model.JsonSchema;
import cu.tissca.commons.jsonschema.parser.JsonSchemaParser;
import cu.tissca.commons.jsonschema.parser.JsonSchemaParserException;
import cu.tissca.x901.wad.VisitorAdapter;
import cu.tissca.x901.wad.model.DocElement;
import cu.tissca.x901.wad.model.RepresentationDescriptor;
import cu.tissca.x901.wad.model.Descriptor;

import java.util.logging.Logger;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class JsonSchemaScanner {

    public static final Logger LOGGER = Logger.getLogger(JsonSchemaScanner.class.getName());

    /**
     * Scans for a JsonSchema and annotates the Representation element with it in its extended properties.
     * @param descriptor
     */
    public static void scanAndPrepareJsonSchemas(final Descriptor descriptor) {
        descriptor.accept(new VisitorAdapter() {
                               public RepresentationDescriptor currentRepresentation;
                               private boolean jsonSchemaFound;

                               @Override
                               public void visitRepresentation(RepresentationDescriptor representationDescriptor) {
                                   currentRepresentation = representationDescriptor;
                                   jsonSchemaFound = false;
                               }

                               @Override
                               public void visitDocElement(final DocElement docElement) {
                                   if (currentRepresentation != null && !jsonSchemaFound) {
                                       lookForTextNode(docElement.getElement(), new TextConsumer() {
                                           @Override
                                           public void accept(Text text) {
                                               try {
                                                   // Fixes Bugs in JSON from JIRA wadl
                                                   String corrected = JsonFixer.fixJson(text.getData());
                                                   JsonSchemaParser parser = new JsonSchemaParser();
                                                   JsonSchema jsonSchema = parser.parse(corrected);
                                                   jsonSchemaFound = true; // skip the rest
                                                   currentRepresentation.getExtendedProperties().put(ExtendedProperties.JSON_SCHEMA, jsonSchema);
                                               } catch (JsonSchemaParserException e) {
                                                   LOGGER.info("Could not parse json schema in text element " + text.toString());
                                                   docElement.addError(e.toString());
                                               }
                                           }
                                       });
                                   }
                               }

                               @Override
                               public void endVisitRepresentation(RepresentationDescriptor representationDescriptor) {
                                   currentRepresentation = null;
                               }
                           }
        );
    }


    private interface TextConsumer {
        void accept(Text text);
    }

    static void lookForTextNode(Element element, TextConsumer consumer) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            switch (item.getNodeType()) {
                case Node.TEXT_NODE:
                    Text text = (Text) item;
                    consumer.accept(text);
                    break;
                case Node.ELEMENT_NODE:
                    lookForTextNode((Element) item, consumer);
                    break;
                default:
                    break;
            }
        }
    }
}
