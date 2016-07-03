package cu.tissca.x901.wadl.xmlutils;

import com.google.gwt.xml.client.Element;
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

    public NodeList getChildElementsByTagName(final Element node, String tagName){
        final List<Node> nodes = new ArrayList<>();
        NodeList childNodes = node.getChildNodes();
        for(int i=0;i<childNodes.getLength();i++){
            Node item = childNodes.item(i);
            if(item instanceof Element){
                Element elementItem = (Element) item;
                if(elementItem.getTagName().equals(tagName)){
                    nodes.add(elementItem);
                }
            }
        }
        return new NodeList() {
            @Override
            public int getLength() {
                return nodes.size();
            }

            @Override
            public Node item(int index) {
                return nodes.get(index);
            }
        };
    }
}
