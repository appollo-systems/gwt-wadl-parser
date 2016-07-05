package cu.tissca.x901.wad.xmlutils;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import cu.tissca.x901.wad.TestResources;
import org.junit.Test;

/**
 * Created by avd on 2016-06-17.
 */
public class GwtTestNsHelper extends GWTTestCase {

    @Test
    public void testSplitTag() throws Exception {
        NsHelper.NsAndTag nsAndTag = NsHelper.splitName("ns3:application");
        GWTTestCase.assertEquals("ns3", nsAndTag.getPrefix());
        GWTTestCase.assertEquals("application", nsAndTag.getName());
    }

    @Test
    public void testNoNs() throws Exception {
        NsHelper.NsAndTag nsAndTag = NsHelper.splitName("application");
        GWTTestCase.assertEquals("", nsAndTag.getPrefix());
        GWTTestCase.assertEquals("application", nsAndTag.getName());
    }

    @Test
    public void test_XmlUtils_detect_namespaces_on_sample_wadl() {
        Document document = XMLParser.parse(TestResources.INSTANCE.jiraRestPluginWadl().getText());
        assertTrue(NsHelper.hasNamespaces(document.getDocumentElement()));
    }

    @Test
    public void test_XmlUtils_extracts_prefix_correctly() {
        Document document = XMLParser.parse(TestResources.INSTANCE.jiraRestPluginWadl().getText());
        assertEquals(NsHelper.getNamespacePrefix(document.getDocumentElement(), "http://wadl.dev.java.net/2009/02"), "ns2");
    }

    @Override
    public String getModuleName() {
        return "cu.tissca.x901.wadl.WadlParser";
    }
}