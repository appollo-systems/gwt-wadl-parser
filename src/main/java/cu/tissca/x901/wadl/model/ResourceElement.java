package cu.tissca.x901.wadl.model;

import com.google.common.base.Verify;
import cu.tissca.x901.wadl.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class ResourceElement extends AbstractWadlElement {
    private String base;
    private List<ResourceElement> resources = new ArrayList<>();
    private List<MethodElement> methods = new ArrayList<>();
    private List<Object> errors = new ArrayList<>();

    public void setBase(String base) {
        this.base = base;
    }

    public String getBase() {
        return base;
    }

    public void addResource(ResourceElement resource) {
        this.resources.add(resource);
    }

    public void addMethod(MethodElement method) {
        this.methods.add(method);
    }

    @Override
    public void accept(Visitor visitor) {
        checkConsistency();
        try {
            visitor.visitResourceDescriptor(this);
            for(ResourceElement resourceElement :resources){
                resourceElement.accept(visitor);
            }
            for(MethodElement methodElement :methods){
                methodElement.accept(visitor);
            }
        } finally {
            visitor.endVisitResourceDescriptor(this);
        }
    }

    private void checkConsistency() {
        Verify.verify(!resources.contains(this));
    }

}
