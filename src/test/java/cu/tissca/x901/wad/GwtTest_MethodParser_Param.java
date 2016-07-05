package cu.tissca.x901.wad;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import cu.tissca.x901.wad.model.ParamDescriptor;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class GwtTest_MethodParser_Param extends GWTTestCase {


    private static String SAMPLE_PARAM =
            "                        <ns2:param xmlns:ns2=\"http://wadl.dev.java.net/2009/02\"\n" +
                    "                                name=\"since\" style=\"query\" type=\"xs:long\"\n" +
                    "                                default=\"0\"\n" +
                    "                                xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
                    "                            <ns2:doc>\n" +
                    "                                <![CDATA[a date time in unix timestamp format since when updated worklogs will be returned.]]></ns2:doc>\n" +
                    "                        </ns2:param>\n";

    @Test
    public void test_parse_param() {
        MethodParser parser = new MethodParser("ns2");
        Document document = XMLParser.parse(SAMPLE_PARAM);
        ParamDescriptor paramDescriptor = parser.parseParam(document.getDocumentElement());
        assertThat(paramDescriptor.getType(), is(equalTo("xs:long")));
        assertThat(paramDescriptor.getStyle(), is(equalTo("query")));
        assertThat(paramDescriptor.getName(), is(equalTo("since")));
        assertThat(paramDescriptor.getDefault(), is(equalTo("0")));
        assertFalse(paramDescriptor.getDocs().isEmpty());
    }


    @Override
    public String getModuleName() {
        return "cu.tissca.x901.wad.WadlParserTest";
    }
}
