package cu.tissca.x901.wad;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import cu.tissca.x901.wad.model.RequestDescriptor;
import org.junit.Test;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class GwtTest_MethodParser_Request extends GWTTestCase {


    private static final String SAMPLE_REQUEST =
            "                    <ns2:request xmlns:ns2=\"http://wadl.dev.java.net/2009/02\">\n" +
                    "                        <ns2:param\n" +
                    "                                name=\"since\" style=\"query\" type=\"xs:long\"\n" +
                    "                                default=\"0\"\n" +
                    "                                xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
                    "                            <ns2:doc>\n" +
                    "                                <![CDATA[a date time in unix timestamp format since when updated worklogs will be returned.]]></ns2:doc>\n" +
                    "                        </ns2:param>\n" +
                    "                    </ns2:request>\n";

    @Test
    public void test_parse_param() {
        MethodParser parser = new MethodParser("ns2");
        Document document = XMLParser.parse(SAMPLE_REQUEST);
        RequestDescriptor paramDescriptor = parser.parseRequest(document.getDocumentElement());
        assertFalse(paramDescriptor.getParams().isEmpty());
    }


    @Override
    public String getModuleName() {
        return "cu.tissca.x901.wad.WadlParserTest";
    }
}
