package org.eclipse.ecsp.cache.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Testing Jackson Mapper config class.
 */
public class JacksonMapperConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonMapperConfigTest.class);

    /**
     * Test to add custom serializer to the mapper.
     *
     * @throws JsonProcessingException  JsonProcessingException
     */
    @Test
    public void testCustomSerializer() throws JsonProcessingException {
        JacksonMapperConfig config = new JacksonMapperConfig();
        // adding the custom serializer for Sample class
        String customSerializerList =
                "org.eclipse.ecsp.cache.config."
                        + "ItemToUserNameMapping:org.eclipse.ecsp.cache.config.ItemToUserNameMappingSerializer";
        config.setCustomSerializers(customSerializerList);

        // get the object mapper
        ObjectMapper mapper = config.jsonObjectMapper();

        ItemToUserNameMapping s = new ItemToUserNameMapping(1, "soap", "user1");
        String actualString = mapper.writeValueAsString(s);
        // as per the serializer, "-new will be added to the user1 string and
        // the field name will be owner
        LOGGER.info("Modified sample string:{}", actualString);

        String expectedString = "{\"id\":1,\"itemName\":\"soap\",\"owner\":\"user1-new\"}";
        Assert.assertEquals(expectedString, actualString);

    }

    /**
     * Test to add custom deserializer.
     *
     * @throws IOException         I/O Exception
     * @throws JsonMappingException JsonMappingException
     * @throws JsonParseException  JsonParseException
     */
    @Test
    public void testCustomDeserializer() throws JsonParseException, JsonMappingException, IOException {
        JacksonMapperConfig config = new JacksonMapperConfig();
        String customDeserializerList = "org.eclipse.ecsp.cache.config"
                + ".ItemToUserNameMapping:org.eclipse.ecsp.cache.config.ItemToUserNameDeserializer";
        config.setCustomDeserializers(customDeserializerList);

        // get the object mapper
        ObjectMapper mapper = config.jsonObjectMapper();
        String itemString = "{\"id\":1,\"itemName\":\"soap\",\"userName\":\"user1-new\"}";
        ItemToUserNameMapping obj = mapper.readValue(itemString, ItemToUserNameMapping.class);

        // in the deserializer class, we have changed the value of itemnNam to
        // pen and userName to "user2"
        Assert.assertNotNull(obj);

        Assert.assertEquals("Item names must be same", "pen", obj.getItemName());
        Assert.assertEquals("User names must be same", "user2", obj.getUserName());
    }
    
    /**
     * Negative test cases in which we have added deserializer class in case
     * serialization is expected.
     */
    @Test(expected = IllegalStateException.class)
    public void testImproperClassProvidedInSerializer() {

        JacksonMapperConfig config = new JacksonMapperConfig();
        String customSerializerList = "org.eclipse.ecsp.cache.config.ItemToUserNameMapping:"
                + "org.eclipse.ecsp.cache.config.ItemToUserNameDeserializer";
        config.setCustomSerializers(customSerializerList);

        // get the object mapper
        config.jsonObjectMapper();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testInsufficientParamsSubtype() {
        Properties p = new Properties();
        JacksonMapperConfig config = new JacksonMapperConfig(p);
        String customSubtypes = "newType";
        config.setCustomSubtypes(customSubtypes);

        // get the object mapper
        config.jsonObjectMapper();
    }

}
