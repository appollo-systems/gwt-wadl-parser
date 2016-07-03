package cu.tissca.x901.wad;

import com.google.gwt.xml.client.Element;
import cu.tissca.x901.wad.model.DocElement;

/**
 * Abstract class for all parsers.
 *
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public abstract class AbstractParser {

    /**
     * the prefix is the xml namespace prefix used to identify the elements specific of the wadl
     */
    private String wadlPrefix;

    public AbstractParser(String wadlPrefix) {
        this.wadlPrefix = wadlPrefix;
    }

    protected String tagName(String tag) {
        return wadlPrefix + ":" + tag;
    }

    public void setWadlPrefix(String wadlPrefix) {
        this.wadlPrefix = wadlPrefix;
    }

    public String getWadlPrefix() {
        return wadlPrefix;
    }

    DocElement parseDocElement(Element rootElement) {
        DocElement result = new DocElement();
        result.setElement(rootElement);
        return result;
    }
}
