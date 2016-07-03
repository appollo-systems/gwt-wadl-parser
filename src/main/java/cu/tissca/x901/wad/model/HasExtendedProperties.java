package cu.tissca.x901.wad.model;

import java.util.Map;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public interface HasExtendedProperties {

    /**
     * This is used to store properties that are calculated from this
     * object state but that are not part of this object's formal schema.
     *
     * Example of this a Representation's JsonSchema.
     * This JsonSchema is usually specified in a documentation element
     * and has to be looked up after the object already exists. So after the parser
     * runs there should be additional processing to lookup this information.
     */
    Map<String, Object> getExtendedProperties();

    void setExtendedProperties(Map<String, Object> extendedProperties);
}
