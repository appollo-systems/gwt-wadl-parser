package cu.tissca.x901.wadl.model;

import cu.tissca.x901.wadl.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class RepresentationElement implements WadlElement {

    private List<DocElement> docs = new ArrayList<>();
    /**
     * This is used to store properties that are calculated from this
     * object state but that are not part of this object's formal schema.
     *
     * Example of this a Representation's JsonSchema.
     * This JsonSchema is usually specified in a documentation element
     * and has to be looked up after the object already exists. So after the parser
     * runs there should be additional processing to lookup this information.
     */
    private Map<String, Object> extendedProperties = new HashMap<>();
    /**
     * Optional
     */
    private String element;
    /**
     * Optional mime type. e.g. application/json
     */
    private String mediaType;

    public List<DocElement> getDocs() {
        return docs;
    }

    public void setDocs(List<DocElement> docs) {
        this.docs = docs;
    }

    public Map<String, Object> getExtendedProperties() {
        return extendedProperties;
    }

    public void setExtendedProperties(Map<String, Object> extendedProperties) {
        this.extendedProperties = extendedProperties;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public void accept(Visitor visitor) {
        try {
            visitor.visitRepresentation(this);
            for (DocElement doc : docs) {
                doc.accept(visitor);
            }
        } finally {
            visitor.endVisitRepresentation(this);
        }
    }
}
