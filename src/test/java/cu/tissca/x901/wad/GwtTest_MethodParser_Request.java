package cu.tissca.x901.wad;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import cu.tissca.x901.wad.model.RequestDescriptor;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
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


    private static final String SAMPLE_REQUEST_WITH_REPRESENTATION = "<ns2:request xmlns:ns2=\"http://wadl.dev.java.net/2009/02\">\n" +
            "                        <ns2:representation\n" +
            "                                element=\"worklogIdsRequest\"\n" +
            "                                mediaType=\"application/json\">\n" +
            "                            <ns2:doc>\n" +
            "                                <ns3:p\n" +
            "                                        xmlns:ns3=\"http://www.w3.org/1999/xhtml\">\n" +
            "                                    <ns3:h6>Example</ns3:h6>\n" +
            "                                    <ns3:pre>\n" +
            "                                        <ns3:code>{\"ids\":[1,2,5,10]}</ns3:code>\n" +
            "                                    </ns3:pre>\n" +
            "                                </ns3:p>\n" +
            "                            </ns2:doc>\n" +
            "                            <ns2:doc>\n" +
            "                                <ns3:p\n" +
            "                                        xmlns:ns3=\"http://www.w3.org/1999/xhtml\">\n" +
            "                                    <ns3:h6>Schema</ns3:h6>\n" +
            "                                    <ns3:pre>\n" +
            "                                        <ns3:code>\n" +
            "                                            {\"id\":\"https://docs.atlassian.com/jira/REST/schema/worklog-ids-request#\",\"title\":\"Worklog\n" +
            "                                            Ids\n" +
            "                                            Request\",\"type\":\"object\",\"properties\":{\"ids\":{\"type\":\"array\",\"items\":{\"type\":\"integer\"}}},\"additionalProperties\":false}\n" +
            "                                        </ns3:code>\n" +
            "                                    </ns3:pre>\n" +
            "                                </ns3:p>\n" +
            "                            </ns2:doc>\n" +
            "                        </ns2:representation>\n" +
            "                    </ns2:request>\n";

    @Test
    public void test_parse_param() throws MalformedWadlException {
        MethodParser parser = new MethodParser("ns2");
        Document document = XMLParser.parse(SAMPLE_REQUEST);
        RequestDescriptor paramDescriptor = parser.parseRequest(document.getDocumentElement());
        assertFalse(paramDescriptor.getParams().isEmpty());
    }

    @Test
    public void test_parse_request_with_representation() throws MalformedWadlException {
        MethodParser parser = new MethodParser("ns2");
        RequestDescriptor requestDescriptor = parser.parseRequest(XMLParser.parse(SAMPLE_REQUEST_WITH_REPRESENTATION).getDocumentElement());
        MatcherAssert.assertThat(requestDescriptor.getRepresentation(), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Override
    public String getModuleName() {
        return "cu.tissca.x901.wad.WadlParserTest";
    }
}
