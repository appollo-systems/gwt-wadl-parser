package cu.tissca.x901.wadl.model;

import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public interface HasErrorAnnotations {
    List<Object> getErrors();
    void addError(Object error);
}
