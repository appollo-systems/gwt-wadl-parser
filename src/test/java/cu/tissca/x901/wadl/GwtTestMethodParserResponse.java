package cu.tissca.x901.wadl;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;
import cu.tissca.x901.wadl.model.ResponseElement;
import org.junit.Test;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class GwtTestMethodParserResponse extends GWTTestCase {

    static String SAMPLE_RESPONSE = "<ns2:response\n" +
            "        status=\"200\">\n" +
            "    <ns2:doc><![CDATA[a set of worklogs id and update time.]]></ns2:doc>\n" +
            "    <ns2:representation\n" +
            "            mediaType=\"application/json\">\n" +
            "        <ns2:doc>\n" +
            "            <ns3:p\n" +
            "                    xmlns:ns3=\"http://www.w3.org/1999/xhtml\">\n" +
            "                <ns3:h6>Example</ns3:h6>\n" +
            "                <ns3:pre>\n" +
            "                    <ns3:code>\n" +
            "                        {\"values\":[{\"worklogId\":103,\"updatedTime\":1438013671562},{\"worklogId\":104,\"updatedTime\":1438013672165},{\"worklogId\":105,\"updatedTime\":1438013693136}],\"since\":1438013671562,\"until\":1438013693136,\"self\":\"http://www.example.com/jira/worklog/updated?since=1438013671136\",\"nextPage\":\"http://www.example.com/jira/worklog/updated/updated?since=1438013671136&amp;since=1438013693136\",\"lastPage\":true}\n" +
            "                    </ns3:code>\n" +
            "                </ns3:pre>\n" +
            "            </ns3:p>\n" +
            "        </ns2:doc>\n" +
            "        <ns2:doc><![CDATA[Returns a JSON representation of the worklog changes.]]></ns2:doc>\n" +
            "        <ns2:doc>\n" +
            "            <ns3:p\n" +
            "                    xmlns:ns3=\"http://www.w3.org/1999/xhtml\">\n" +
            "                <ns3:h6>Schema</ns3:h6>\n" +
            "                <ns3:pre>\n" +
            "                    <ns3:code>\n" +
            "                        {\"id\":\"https://docs.atlassian.com/jira/REST/schema/worklog-changed-since#\",\"title\":\"Worklog\n" +
            "                        Changed\n" +
            "                        Since\",\"type\":\"object\",\"properties\":{\"values\":{\"type\":\"array\",\"items\":{\"title\":\"Worklog\n" +
            "                        Change\",\"type\":\"object\",\"properties\":{\"worklogId\":{\"type\":\"integer\"},\"updatedTime\":{\"type\":\"integer\"}},\"additionalProperties\":false}},\"since\":{\"type\":\"integer\"},\"until\":{\"type\":\"integer\"},\"isLastPage\":{\"type\":\"boolean\"},\"self\":{\"type\":\"string\",\"format\":\"uri\"},\"nextPage\":{\"type\":\"string\",\"format\":\"uri\"}},\"additionalProperties\":false,\"required\":[\"isLastPage\"]}\n" +
            "                    </ns3:code>\n" +
            "                </ns3:pre>\n" +
            "            </ns3:p>\n" +
            "        </ns2:doc>\n" +
            "    </ns2:representation>\n" +
            "</ns2:response>\n";

    @Test
    public void test_response_docs() {
        WadlParser parser = new WadlParser("ns2");
        Element responseElement = XMLParser.parse(SAMPLE_RESPONSE).getDocumentElement();
        throw new RuntimeException("not implemented");
    }

    @Test
    public void test_response_status() {
        WadlParser parser = new WadlParser("ns2");
        Element responseElement = XMLParser.parse(SAMPLE_RESPONSE).getDocumentElement();
        throw new RuntimeException("not implemented");
    }



    @Test
    public void test_response_representation() {
        Document responseDocument = XMLParser.parse(SAMPLE_RESPONSE);
        MethodParser wadlParser = new MethodParser("ns2");
        ResponseElement responseElement = wadlParser.parseResponse(responseDocument.getDocumentElement());
    }

    @Test
    public void test_response_representation_docs() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getModuleName() {
        return null;
    }
}
