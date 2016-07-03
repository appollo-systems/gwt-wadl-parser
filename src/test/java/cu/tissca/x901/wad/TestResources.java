package cu.tissca.x901.wad;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public interface TestResources extends ClientBundle {

    TestResources INSTANCE = GWT.create(TestResources.class);

    @Source("jira-rest-plugin.wadl.xml")
    TextResource jiraRestPluginWadl();



}
