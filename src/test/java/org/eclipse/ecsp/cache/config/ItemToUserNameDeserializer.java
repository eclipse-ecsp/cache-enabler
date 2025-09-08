package org.eclipse.ecsp.cache.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Sample deserializer class.
 */
public class ItemToUserNameDeserializer extends JsonDeserializer<ItemToUserNameMapping> {

    @Override
    public ItemToUserNameMapping deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        final int number = 1234;
        return new ItemToUserNameMapping(number, "pen", "user2");
    }

}
