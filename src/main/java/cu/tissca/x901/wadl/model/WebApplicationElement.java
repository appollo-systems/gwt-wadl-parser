package cu.tissca.x901.wadl.model;

import cu.tissca.x901.wadl.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class WebApplicationElement implements WadlElement {

    private List<ResourceElement> resources = new ArrayList<>();

    public void addResource(ResourceElement resource) {
        this.resources.add(resource);
    }

    @Override
    public void accept(Visitor visitor){
        try {
            visitor.visitWebApplicationDescriptor(this);
            for (ResourceElement resourceElement : resources) {
                resourceElement.accept(visitor);
            }
        }finally {
            visitor.endVisitWebApplicationDescriptor();
        }
    }
}
