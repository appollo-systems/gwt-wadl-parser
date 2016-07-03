package cu.tissca.x901.wadl.model;

import com.google.gwt.xml.client.Element;
import cu.tissca.x901.wadl.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the documentation element inside the representation, method descriptor, etc.
 *
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class DocElement implements WadlElement, HasExtendedProperties, HasErrorAnnotations {
    private Element element;


    private Map<String, Object> extendedProperties = new HashMap<>();
    private List<Object> errors = new ArrayList<>();

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public void accept(Visitor visitor){
        try {
            visitor.visitDocElement(this);
        } finally {
            visitor.endVisitDocElement(this);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Map<String, Object> getExtendedProperties() {
        return this.extendedProperties;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void setExtendedProperties(Map<String, Object> value) {
        this.extendedProperties = value;
    }

    @Override
    public List<Object> getErrors() {
        return this.errors;
    }

    @Override
    public void addError(Object error) {
        this.errors.add(error);
    }
}
