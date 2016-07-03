package cu.tissca.x901.wadl.model;

import cu.tissca.x901.wadl.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class ResponseElement extends AbstractWadlElement {
    private RepresentationElement representation;
    private List<DocElement> docs = new ArrayList<>();

    public RepresentationElement getRepresentation() {
        return representation;
    }

    public void setRepresentation(RepresentationElement representation) {
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
