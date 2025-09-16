package org.eclipse.ecsp.cache.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Test case for mapping class to deserializer.
 */

@RunWith(Parameterized.class)
public class JacksonMapperConfigInvalidDeserializationParamsTest {
    String customDeserializerList;

    public JacksonMapperConfigInvalidDeserializationParamsTest(String customDeserializerList) {
        this.customDeserializerList = customDeserializerList;
    }
    
    /**
     * Helper method to pass each item in the array as an argument to the test method.
     */
    
    @Parameterized.Parameters
    public static Collection<?> customDeserializerList() {
        return Arrays.asList(new Object[] { "org.eclipse.ecsp.cache.config.ItemToUserNameMapping:",
                                            ":org.eclipse.ecsp.cache.config.ItemToUserNameDeserializer", 
                                            "org.eclipse.ecsp.cache.config.ItemToUserNameMapping:"
                                            + "org.eclipse.ecsp.cache.config.ItemToUserNameMappingSerializer" });
    }

    /**
     * Negative scenario where proper pair is not given.
     */
    @Test(expected = IllegalStateException.class)
    public void testInsufficientDeSerializationParams() {

        JacksonMapperConfig config = new JacksonMapperConfig();
        config.setCustomDeserializers(customDeserializerList);

        // get the object mapper
        config.jsonObjectMapper();
    }
}