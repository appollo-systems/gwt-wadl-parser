package cu.tissca.x901.wadl.xmlutils;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class XmlUtilsImpl {

    private static XmlUtilsImpl impl = new XmlUtilsImpl();

    public static XmlUtilsImpl getInstance(){
        return impl;
    }

    public NodeList getChildElementsByTagName( String tagName){
        List<Node> nodes = new ArrayList<>();
    }
}
