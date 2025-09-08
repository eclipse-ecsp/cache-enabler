package org.eclipse.ecsp.cache.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Test class.
 */

@RunWith(Parameterized.class)
public class JacksonMapperConfigInvalidParamsSubTypeTest {

    String customSubType;

    public JacksonMapperConfigInvalidParamsSubTypeTest(String customSubType) {
        this.customSubType = customSubType;
    }

    /**
     * Helper method to pass each item in the array as an argument to the test method.
     */
    @Parameterized.Parameters
    public static Collection<?> customSubTypeList() {
        return Arrays.asList(new Object[] { "org.eclipse.ecsp.cache.config.ItemToUserNameMapping:",
                                            ":newType",
                                            "newType" });
    }

    /**
     * Testing insufficient params in sub type.
     */
    @Test(expected = IllegalStateException.class)
    public void testInsufficientParamsSubtype() {

        JacksonMapperConfig config = new JacksonMapperConfig();
        config.setCustomSubtypes(customSubType);

        // get the object mapper
        config.jsonObjectMapper();
    }
}
