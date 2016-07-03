package cu.tissca.x901.wadl.model;

import cu.tissca.x901.wadl.Visitor;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public interface WadlElement extends HasErrorAnnotations, HasExtendedProperties {
    void accept(Visitor visitor);
}
