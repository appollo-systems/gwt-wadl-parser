package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class WebApplicationDescriptor extends AbstractDescriptor {

    private List<ResourceDescriptor> resources = new ArrayList<>();

    public void addResource(ResourceDescriptor resource) {
        this.resources.add(resource);
    }

    @Override
    public void accept(Visitor visitor){
        try {
            visitor.visitWebApplicationDescriptor(this);
            for (ResourceDescriptor resourceDescriptor : resources) {
                resourceDescriptor.accept(visitor);
            }
        }finally {
            visitor.endVisitWebApplicationDescriptor();
        }
    }
}
