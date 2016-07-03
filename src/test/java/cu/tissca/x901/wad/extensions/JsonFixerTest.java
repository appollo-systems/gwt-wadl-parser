package cu.tissca.x901.wad.extensions;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by avd on 2016-06-29.
 */
public class JsonFixerTest {
    private static String BROKEN_JSON = "{\n" +
            "    \"asdfs\" : \"asdf\n" +
            "    asdf\n" +
            "    asdf\"\n" +
            "}";

    private static String FIXED_JSON = "{\n" +
            "    \"asdfs\" : \"asdf" +
            "    asdf" +
            "    asdf\"\n" +
            "}";


    @Test
    public void testFixJson(){
        MatcherAssert.assertThat(JsonFixer.fixJson(BROKEN_JSON),CoreMatchers.is(FIXED_JSON));
    }

}