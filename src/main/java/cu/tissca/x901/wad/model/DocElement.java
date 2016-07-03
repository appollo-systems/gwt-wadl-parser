package cu.tissca.x901.wad.model;

import com.google.gwt.xml.client.Element;
import cu.tissca.x901.wad.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the documentation element inside the representation, method descriptor, etc.
 *
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class DocElement extends AbstractDescriptor {
    private Element element;
    private List<DocElement> docs = new ArrayList<>();

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
