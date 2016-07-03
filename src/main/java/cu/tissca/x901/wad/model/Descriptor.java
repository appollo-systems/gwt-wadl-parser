package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public interface Descriptor extends HasErrorAnnotations, HasExtendedProperties {
    void accept(Visitor visitor);
}
