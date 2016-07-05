package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class ParamDescriptor extends AbstractDescriptor implements HasDocs {
    private String name;
    private String style;
    private String type;
    private String _default;
    private List<DocElement> docs = new ArrayList<>();

    @Override
    public void accept(Visitor visitor){
        try {
            visitor.visitParamDescriptor(this);
        } finally {
            visitor.endVisitParamDescriptor(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    @Override
    public List<DocElement> getDocs() {
        return this.docs;
    }

    @Override
    public void setDocs(List<DocElement> value) {
        this.docs = value;
    }
}
