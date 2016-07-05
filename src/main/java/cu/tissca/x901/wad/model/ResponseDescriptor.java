package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class ResponseDescriptor extends AbstractDescriptor implements HasDocs, HasRepresentation {
    private RepresentationDescriptor representation;
    private List<DocElement> docs = new ArrayList<>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;

    public RepresentationDescriptor getRepresentation() {
        return representation;
    }

    public void setRepresentation(RepresentationDescriptor representation) {
        this.representation = representation;
    }

    public List<DocElement> getDocs() {
        return docs;
    }

    public void setDocs(List<DocElement> docs) {
        this.docs = docs;
    }

    @Override
    public void accept(Visitor visitor){
        try {
            visitor.visitResponseDescriptor(this);
            representation.accept(visitor);
            for(DocElement docElement:docs){
                docElement.accept(visitor);
            }
        } finally {
            visitor.endVisitResponseDescriptor(this);
        }
    }
}
