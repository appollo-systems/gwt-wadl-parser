package cu.tissca.x901.wadl.extensions;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.Text;
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
                               private boolean jsonSchemaFound;

                               @Override
                               public void visitRepresentation(RepresentationElement representationElement) {
                                   currentRepresentation = representationElement;
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
                                                   LOGGER.info("Could not parse json schema in text element "+text.toString());
                                                   docElement.addError(e.toString());
                                               }
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
