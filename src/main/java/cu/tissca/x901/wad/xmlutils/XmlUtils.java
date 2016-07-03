package cu.tissca.x901.wad.xmlutils;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class XmlUtils {
    private static XmlUtilsImpl instance = XmlUtilsImpl.getInstance();

    public static NodeList getChildElementsByTagName(Element node, String tagName) {
        return instance.getChildElementsByTagName(node, tagName);
    }
}
