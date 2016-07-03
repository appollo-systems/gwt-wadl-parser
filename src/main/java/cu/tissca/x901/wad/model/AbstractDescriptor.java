package cu.tissca.x901.wad.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public abstract class AbstractDescriptor implements Descriptor, HasErrorAnnotations {

    private List<Object> errors = new ArrayList<>();
    private Map<String, Object> extendedProperties = new HashMap<>();

    @Override
    public List<Object> getErrors() {
        return errors;
    }

    @Override
    public void addError(Object error) {
        this.errors.add(errors);
    }

    @Override
    public Map<String, Object> getExtendedProperties() {
        return this.extendedProperties;
    }

    @Override
    public void setExtendedProperties(Map<String, Object> value) {
        this.extendedProperties = value;
    }
}
