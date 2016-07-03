package cu.tissca.x901.wad.model;

import com.google.common.base.Verify;
import cu.tissca.x901.wad.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class ResourceDescriptor extends AbstractDescriptor {
    private String base;
    private List<ResourceDescriptor> resources = new ArrayList<>();
    private List<MethodDescriptor> methods = new ArrayList<>();
    private List<Object> errors = new ArrayList<>();

    public void setBase(String base) {
        this.base = base;
    }

    public String getBase() {
        return base;
    }

    public void addResource(ResourceDescriptor resource) {
        this.resources.add(resource);
    }

    public void addMethod(MethodDescriptor method) {
        this.methods.add(method);
    }

    @Override
    public void accept(Visitor visitor) {
        checkConsistency();
        try {
            visitor.visitResourceDescriptor(this);
            for(ResourceDescriptor resourceDescriptor :resources){
                resourceDescriptor.accept(visitor);
            }
            for(MethodDescriptor methodDescriptor :methods){
                methodDescriptor.accept(visitor);
            }
        } finally {
            visitor.endVisitResourceDescriptor(this);
        }
    }

    private void checkConsistency() {
        Verify.verify(!resources.contains(this));
    }

}
