package cu.tissca.x901.wadl.xmlutils;

import com.google.gwt.thirdparty.guava.common.annotations.VisibleForTesting;
import com.google.gwt.xml.client.Attr;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NamedNodeMap;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class NsHelper {
    public static boolean hasNamespaces(Element document) {
        NamedNodeMap attributes = document.getAttributes();
        for(int i=0;i<attributes.getLength();i++){
            if(attributes.item(i).getNodeName().contains("xmlns"))
                return true;
        }
        return false;
    }

    public static String getNamespacePrefix(Element document, String url) {
        NamedNodeMap attributes = document.getAttributes();
        for(int i=0;i<attributes.getLength();i++){
            Attr item = (Attr) attributes.item(i);
            if(item.getNodeName().startsWith("xmlns:") && item.getValue().equals(url))
                return splitName(item.getNodeName()).getName();
        }
        return null;
    }

    @VisibleForTesting
    static class NsAndTag {
        private String ns;
        private String tag;

        public String getPrefix() {
            return ns;
        }

        public String getName() {
            return tag;
        }

        public NsAndTag(String ns, String tag) {
            this.ns = ns;
            this.tag = tag;
        }
    }

    @VisibleForTesting
    static NsAndTag splitName(String tag){
        String[] split = tag.split(":",2);
        if(split.length==2)
            return new NsAndTag(split[0], split[1]);
        else
            return new NsAndTag("", split[0]);
    }
}
