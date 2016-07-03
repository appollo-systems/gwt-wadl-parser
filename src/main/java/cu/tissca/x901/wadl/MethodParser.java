package cu.tissca.x901.wadl;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import cu.tissca.x901.wadl.model.*;
import cu.tissca.x901.wadl.xmlutils.XmlUtils;

import java.util.ArrayList;
import java.util.List;

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
    MethodElement parseMethod(Element methodNode) throws MalformedWadlException {
        MethodElement result = new MethodElement();
        result.setId(methodNode.getAttribute("id"));
        result.setName(methodNode.getAttribute("name"));
        NodeList requestList = methodNode.getElementsByTagName(tagName("request"));
        verifyOrThrow(requestList.getLength() == 1, new MalformedWadlException("Method element must have exactly one child request element"));
        Element item1 = (Element) requestList.item(0);
        result.setRequestDescriptor(parseRequest(item1));
        NodeList responseList = methodNode.getElementsByTagName(tagName("response"));
        verifyOrThrow(responseList.getLength() == 1, new MalformedWadlException("Method element must have exactly one child response element"));
        Element item2 = (Element)requestList.item(0);
        result.setResponseElement(parseResponse(item2));
        return result;
    }

    private RequestElement parseRequest(Element item1) {
        return null;
    }

    /**
     * If it fails it's fatal for the method
     * @param rootElement
     * @return
     */
    @Strict
    public RepresentationElement parseRepresentation(Element rootElement) {
        RepresentationElement representationElement = new RepresentationElement();
        representationElement.setMediaType(rootElement.getAttribute("mediaType"));
        NodeList doc = XmlUtils.getChildElementsByTagName(rootElement, tagName("doc"));
        List<DocElement> list = new ArrayList<>();
        for(int i=0;i<doc.getLength();i++){
            Element docNode = (Element) doc.item(i);
            list.add(parseDocElement(docNode));
        }
        representationElement.setDocs(list);
        return representationElement;
    }

    /**
     * If it fails it's fatal for the method
     * @param rootElement
     * @return
     */
    @Strict
    public ResponseElement parseResponse(Element rootElement) {
        ResponseElement result = new ResponseElement();
        NodeList doc = XmlUtils.getChildElementsByTagName(rootElement, tagName("doc"));
        for(int i=0;i<doc.getLength();i++){
            Element item = (Element) doc.item(i);
            result.getDocs().add(parseDocElement(item));
        }
        NodeList representation = XmlUtils.getChildElementsByTagName(rootElement, tagName("representation"));
        if(representation.getLength()!=0){
            Element element = (Element) representation.item(0);
            result.setRepresentation(parseRepresentation(element));
        }
        return result;
    }
}
