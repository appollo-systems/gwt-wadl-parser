package cu.tissca.x901.wad.model;

import java.util.List;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public interface HasDocs {
    List<DocElement> getDocs();

    void setDocs(List<DocElement> docs);
}
