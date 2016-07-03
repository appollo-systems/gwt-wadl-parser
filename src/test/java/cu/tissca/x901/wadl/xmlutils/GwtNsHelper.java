package cu.tissca.x901.wadl.xmlutils;

import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Test;

/**
 * Created by avd on 2016-06-17.
 */
public class GwtNsHelper extends GWTTestCase {

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

    @Override
    public String getModuleName() {
        return "cu.tissca.x901.wadl.WadlParser";
    }
}