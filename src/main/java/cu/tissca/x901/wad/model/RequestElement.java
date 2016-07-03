package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class RequestElement extends AbstractWadlElement {
    private List<ParamElement> params;
    private List<DocElement> docs;
    private RepresentationElement representation;

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

    public List<ParamElement> getParams() {
        return params;
    }

    public void setParams(List<ParamElement> params) {
        this.params = params;
    }

    @Override
    public void accept(Visitor visitor) {
        try {
            visitor.visitRequestDescriptor(this);
            for(ParamElement paramElement : params){
                paramElement.accept(visitor);
            }
            representation.accept(visitor);
            for(DocElement doc:docs){
                doc.accept(visitor);
            }
        }finally {
            visitor.endVisitRequestDescriptor(this);
        }

    }
}
