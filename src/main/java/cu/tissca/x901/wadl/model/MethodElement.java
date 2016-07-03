package cu.tissca.x901.wadl.model;

import cu.tissca.x901.wadl.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a method inside a resource.
 *
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class MethodElement extends AbstractWadlElement  {

    private String id;
    private String name;
    private List<DocElement> docs = new ArrayList<>();
    private RequestElement requestDescriptor;
    private ResponseElement responseElement;

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


    public RequestElement getRequestDescriptor() {
        return requestDescriptor;
    }

    public void setRequestDescriptor(RequestElement requestDescriptor) {
        this.requestDescriptor = requestDescriptor;
    }

    public ResponseElement getResponseElement() {
        return responseElement;
    }

    public void setResponseElement(ResponseElement responseElement) {
        this.responseElement = responseElement;
    }

    @Override
    public void accept(Visitor visitor) {
        try {
            visitor.visitMethodDescriptor(this);
            requestDescriptor.accept(visitor);
            responseElement.accept(visitor);
            for(DocElement doc:docs){
                doc.accept(visitor);
            }
        } finally {
            visitor.endVisitMethodDescriptor(this);
        }
    }

}
