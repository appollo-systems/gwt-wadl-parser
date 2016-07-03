package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a method inside a resource.
 *
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class MethodDescriptor extends AbstractDescriptor {

    private String id;
    private String name;
    private List<DocElement> docs = new ArrayList<>();
    private RequestDescriptor requestDescriptor;
    private ResponseDescriptor responseDescriptor;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<DocElement> getDocs() {
        return docs;
    }


    public RequestDescriptor getRequestDescriptor() {
        return requestDescriptor;
    }

    public void setRequestDescriptor(RequestDescriptor requestDescriptor) {
        this.requestDescriptor = requestDescriptor;
    }

    public ResponseDescriptor getResponseDescriptor() {
        return responseDescriptor;
    }

    public void setResponseDescriptor(ResponseDescriptor responseDescriptor) {
        this.responseDescriptor = responseDescriptor;
    }

    @Override
    public void accept(Visitor visitor) {
        try {
            visitor.visitMethodDescriptor(this);
            requestDescriptor.accept(visitor);
            responseDescriptor.accept(visitor);
            for(DocElement doc:docs){
                doc.accept(visitor);
            }
        } finally {
            visitor.endVisitMethodDescriptor(this);
        }
    }

}
