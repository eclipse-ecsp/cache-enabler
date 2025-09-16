package org.eclipse.ecsp.cache.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Test case for mapping class to serializer.
 */

@RunWith(Parameterized.class)
public class JacksonMapperConfigInvalidSerializationParamsTest {
    String customSerializerList;

    public JacksonMapperConfigInvalidSerializationParamsTest(String customSerializerList) {
        this.customSerializerList = customSerializerList;
    }
    
    /**
     * Helper method to pass each item in the array as an argument to the test method.
     */
    @Parameterized.Parameters
    public static Collection<?> customSerializerList() {
        return Arrays.asList(new Object[] { "org.eclipse.ecsp.cache.config.ItemToUserNameMapping:",
                                            ":org.eclipse.ecsp.cache.config.ItemToUserNameMappingSerializer" });
    }

    /**
     * Negative scenario where proper pair is not given.
     */
    
    @Test(expected = IllegalStateException.class)
    public void testInsufficientSerializationParams() {

        JacksonMapperConfig config = new JacksonMapperConfig();
        config.setCustomSerializers(customSerializerList);

        // get the object mapper
        config.jsonObjectMapper();
    }
}
