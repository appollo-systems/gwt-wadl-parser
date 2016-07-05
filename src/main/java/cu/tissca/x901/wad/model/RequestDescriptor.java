package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class RequestDescriptor extends AbstractDescriptor implements HasDocs, HasRepresentation {
    private List<ParamDescriptor> params = new ArrayList<>();
    private List<DocElement> docs = new ArrayList<>();
    private RepresentationDescriptor representation;

    @Override
    public RepresentationDescriptor getRepresentation() {
        return representation;
    }

    @Override
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
