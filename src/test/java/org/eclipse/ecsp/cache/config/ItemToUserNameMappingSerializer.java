package org.eclipse.ecsp.cache.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * sample serializer class for ItemToUserNameMapping.
 */
public class ItemToUserNameMappingSerializer extends JsonSerializer<ItemToUserNameMapping> {

    @Override
    public void serialize(ItemToUserNameMapping value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("itemName", value.getItemName());
        jgen.writeStringField("owner", value.getUserName() + "-new");
        jgen.writeEndObject();
    }
}
