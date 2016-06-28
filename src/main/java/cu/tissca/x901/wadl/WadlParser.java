package cu.tissca.x901.wadl;

import com.google.common.base.Verify;
import com.google.gwt.xml.client.*;
import cu.tissca.x901.wadl.model.MethodElement;
import cu.tissca.x901.wadl.model.RepresentationElement;
import cu.tissca.x901.wadl.model.ResourceElement;
import cu.tissca.x901.wadl.model.WebApplicationElement;
import cu.tissca.x901.wadl.xmlutils.NsHelper;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class WadlParser {

    private String wadlPrefix;

    public WadlParser(String wadlPrefix) {
        this.wadlPrefix = wadlPrefix;
    }

    public static WebApplicationElement parse(String xml) throws MalformedWadlException {
        Document document = XMLParser.parse(xml);
        String wadlPrefix = null;
        if(NsHelper.hasNamespaces(document.getDocumentElement())) {
            wadlPrefix = NsHelper.getNamespacePrefix(document.getDocumentElement(), "http://wadl.dev.java.net/2009/02");
        }
        WadlParser parser = new WadlParser(wadlPrefix);
        parser.setWadlPrefix(wadlPrefix);
        return parser.parseApplication(document.getDocumentElement());
    }

    private WebApplicationElement parseApplication(Element documentElement) throws MalformedWadlException {
        WebApplicationElement result = new WebApplicationElement();
        Verify.verify(false, "Should not use getElementsByTagName because it's recursive");
        NodeList resourcessNodeList = documentElement.getElementsByTagName(wadlPrefix + ":" + "resources");
        if(resourcessNodeList.getLength()==1){
            Element resourcesNode = (Element) resourcessNodeList.item(0);
            Verify.verify(false, "Should not use getElementsByTagName because it's recursive");
            NodeList resourcesNodeList = resourcesNode.getElementsByTagName(wadlPrefix + ":" + "resource");
            for(int i=0;i<resourcesNodeList.getLength();i++) {
                Element resourceElement = (Element) resourcesNodeList.item(i);
                ResourceElement resource = parseResource(resourceElement);
                result.addResource(resource);
            }
            return result;
        } else {
            throw new MalformedWadlException("No resources found");
        }
    }

    private ResourceElement parseResource(Element resourceNode) {
        assert resourceNode instanceof Element;
        ResourceElement result = new ResourceElement();

        String base = resourceNode.getAttribute("base");
        NodeList resourcesNodeList = resourceNode.getElementsByTagName(wadlPrefix + ":" + "resource");
        for(int i=0;i<resourcesNodeList.getLength();i++) {
            Element childResourceNode = (Element) resourcesNodeList.item(i);
            ResourceElement resource = parseResource(childResourceNode);
            result.addResource(resource);
        }

        NodeList methodNodeList = resourceNode.getElementsByTagName(wadlPrefix + ":" + "method");
        for(int i=0;i<methodNodeList.getLength();i++) {
            Element methodNode = (Element) methodNodeList.item(i);
            MethodElement method = parseMethod(methodNode);
            result.addMethod(method);
        }

        result.setBase(base);
        return result;
    }

    MethodElement parseMethod(Element methodNode) {
        MethodElement result = new MethodElement();
        result.setId(methodNode.getAttribute("id"));
        result.setName(methodNode.getAttribute("name"));
        return result;
    }

    public void setWadlPrefix(String wadlPrefix) {
        this.wadlPrefix = wadlPrefix;
    }

    public String getWadlPrefix() {
        return wadlPrefix;
    }

    public RepresentationElement parseRepresentation(Element documentElement) {
        RepresentationElement representationElement = new RepresentationElement();
        representationElement.setMediaType(documentElement.getAttribute("mediaType"));
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            documentElement.getElementsByTagName()
        }
        return representationElement;
    }
}
