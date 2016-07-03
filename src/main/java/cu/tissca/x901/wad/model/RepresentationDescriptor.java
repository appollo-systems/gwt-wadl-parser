package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class RepresentationDescriptor extends AbstractDescriptor implements HasDocs {

    private List<DocElement> docs = new ArrayList<>();

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
