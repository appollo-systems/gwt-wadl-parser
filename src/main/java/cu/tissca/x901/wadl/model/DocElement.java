package cu.tissca.x901.wadl.model;

import com.google.gwt.xml.client.Element;
import cu.tissca.x901.wadl.Visitor;

/**
 * This class represents the documentation element inside the representation, method descriptor, etc.
 *
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class DocElement implements WadlElement {
    private Element element;

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
}
