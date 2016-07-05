package cu.tissca.x901.wad;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import cu.tissca.x901.wad.model.*;
import cu.tissca.x901.wad.xmlutils.XmlUtils;

/**
 * Method parser encloses the parsing of all entities beneath the Method AST.
 *
 * Any failure behind any of the methods in this parser stops the parser and invalidates
 * the parsing completely.
 *
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class MethodParser extends AbstractParser {

    public MethodParser(String wadlPrefix) {
        super(wadlPrefix);
    }

    public <T extends Throwable> void verifyOrThrow(boolean condition, T instance) throws T {
        if(!condition) throw instance;
    }

    @Strict
    MethodDescriptor parseMethod(Element methodNode) throws MalformedWadlException {
        MethodDescriptor result = new MethodDescriptor();
        result.setId(methodNode.getAttribute("id"));
        result.setName(methodNode.getAttribute("name"));
        NodeList requestList = methodNode.getElementsByTagName(tagName("request"));
        verifyOrThrow(requestList.getLength() == 1, new MalformedWadlException("Method element must have exactly one child request element"));
        Element item1 = (Element) requestList.item(0);
        result.setRequestDescriptor(parseRequest(item1));
        NodeList responseList = methodNode.getElementsByTagName(tagName("response"));
        verifyOrThrow(responseList.getLength() == 1, new MalformedWadlException("Method element must have exactly one child response element"));
        Element item2 = (Element)responseList.item(0);
        result.setResponseDescriptor(parseResponse(item2));
        readDocsFromElementInto(methodNode, result);
        return result;
    }

    @Strict
    RequestDescriptor parseRequest(Element item1) throws MalformedWadlException {
        RequestDescriptor requestDescriptor = new RequestDescriptor();
        readRepresentationFromElementInto(item1, requestDescriptor);
        NodeList paramNodeList = item1.getElementsByTagName(tagName("param"));
        for(int i=0;i<paramNodeList.getLength();i++){
            Element paramElement = (Element) paramNodeList.item(i);
            requestDescriptor.getParams().add(parseParam(paramElement));
        }
        return requestDescriptor;
    }

    private void readRepresentationFromElementInto(Element item1, HasRepresentation requestDescriptor) throws MalformedWadlException {
        NodeList nodeList = item1.getElementsByTagName("representation");
        if(nodeList.getLength()>0){
            verifyOrThrow(nodeList.getLength() == 1, new MalformedWadlException("Request element can have at most one child representation element"));
            Element item = (Element) nodeList.item(0);
            requestDescriptor.setRepresentation(parseRepresentation(item));
        }
    }

    /**
     * If it fails it's fatal for the method
     * @param rootElement
     * @return
     */
    @Strict
    public RepresentationDescriptor parseRepresentation(Element rootElement) {
        RepresentationDescriptor representationDescriptor = new RepresentationDescriptor();
        representationDescriptor.setMediaType(rootElement.getAttribute("mediaType"));
        readDocsFromElementInto(rootElement, representationDescriptor);
        return representationDescriptor;
    }

    private void readDocsFromElementInto(Element rootElement, HasDocs hasDocs) {
        NodeList doc = XmlUtils.getChildElementsByTagName(rootElement, tagName("doc"));
        for(int i=0;i<doc.getLength();i++){
            Element docNode = (Element) doc.item(i);
            hasDocs.getDocs().add(parseDocElement(docNode));
        }
    }

    /**
     * If it fails it's fatal for the method
     * @param rootElement
     * @return
     */
    @Strict
    public ResponseDescriptor parseResponse(Element rootElement) throws MalformedWadlException {
        ResponseDescriptor result = new ResponseDescriptor();
        readDocsFromElementInto(rootElement, result);
        readRepresentationFromElementInto(rootElement, result);
        result.setStatus(Integer.valueOf(rootElement.getAttribute("status")));
        return result;
    }

    public ParamDescriptor parseParam(Element documentElement) {
        ParamDescriptor result = new ParamDescriptor();
        String name = documentElement.getAttribute("name");
        String style = documentElement.getAttribute("style");
        String type = documentElement.getAttribute("type");
        String _default = documentElement.getAttribute("default");
        result.setName(name);
        result.setType(type);
        result.setStyle(style);
        result.setDefault(_default);
        readDocsFromElementInto(documentElement, result);
        return result;
    }
}
