package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class RequestDescriptor extends AbstractDescriptor implements HasDocs {
    private List<ParamDescriptor> params;
    private List<DocElement> docs;
    private RepresentationDescriptor representation;

    public RepresentationDescriptor getRepresentation() {
        return representation;
    }

    public void setRepresentation(RepresentationDescriptor representation) {
        this.representation = representation;
    }

    @Override
    public List<DocElement> getDocs() {
        return docs;
    }

    @Override
    public void setDocs(List<DocElement> docs) {
        this.docs = docs;
    }

    public List<ParamDescriptor> getParams() {
        return params;
    }

    public void setParams(List<ParamDescriptor> params) {
        this.params = params;
    }

    @Override
    public void accept(Visitor visitor) {
        try {
            visitor.visitRequestDescriptor(this);
            for(ParamDescriptor paramDescriptor : params){
                paramDescriptor.accept(visitor);
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
