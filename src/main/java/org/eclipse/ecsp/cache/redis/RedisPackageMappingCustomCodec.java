package org.eclipse.ecsp.cache.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import org.apache.commons.io.IOUtils;
import org.eclipse.ecsp.entities.dma.RetryRecord;
import org.eclipse.ecsp.entities.dma.RetryRecordIds;
import org.eclipse.ecsp.entities.dma.VehicleIdDeviceIdMapping;
import org.eclipse.ecsp.utils.logger.IgniteLogger;
import org.eclipse.ecsp.utils.logger.IgniteLoggerFactory;
import org.redisson.client.codec.Codec;
import org.redisson.client.handler.State;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * Custom decoder for Redis that maps old package names to new ones for specific entities.
 * This is used to ensure compatibility with the new package structure in the ECSP project.
 *
 * @author HBadshah
 */
public class RedisPackageMappingCustomCodec implements Codec {
    
    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(RedisPackageMappingCustomCodec.class);
    
    private ObjectMapper objectMapper;

    private final Encoder encoder = new Encoder() {

        @Override
        public ByteBuf encode(Object in) throws IOException {
            LOGGER.info("Encoding object : {} as ByteBuf type in custom RedisPackageMappingCustomCodec.", in);
            ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
            try (ByteBufOutputStream os = new ByteBufOutputStream(out)) {
                byte[] b = objectMapper.writeValueAsBytes(in);
                os.write(b);
                return os.buffer();
            }
        }
    };

    /**
     * Constructor for RedisPackageMappingCustomCodec.
     *
     * @param objectMapper the ObjectMapper to use for serialization/deserialization
     */
    public RedisPackageMappingCustomCodec(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private final Decoder<Object> valueDecoder = new Decoder<Object>() {

        @Override
        public Object decode(ByteBuf buf, State state) {
            try (InputStream stream = new ByteBufInputStream(buf)) { 
                StringWriter writer = new StringWriter();
                IOUtils.copy(stream, writer, StandardCharsets.UTF_8);
                String value = writer.toString();
                String className = value.substring(value.indexOf(":") + RedisConstants.TWO.getValue(), 
                    value.indexOf(",") - 1);
                LOGGER.info("Fetched data of type: {} from Redis to be decoded.", className);
            
                if (className.equals(RedisProperty.OLD_PACKAGE_NAME_ENTITIES + ".dma.VehicleIdDeviceIdMapping")) {
                    return decodeVehicleIdDeviceIdMapping(value);
                } else if (className.equals(RedisProperty.OLD_PACKAGE_NAME_ENTITIES + ".dma.RetryRecord")) {
                    return decodeRetryRecord(value);
                } else if (className.equals(RedisProperty.OLD_PACKAGE_NAME_ENTITIES + ".dma.RetryRecordIds")) {
                    return decodeRetryRecordIds(value);
                }
            } catch (IOException e) {
                LOGGER.error("Error decoding value from Redis: {}", e.getMessage(), e);
            }
            LOGGER.warn("No matching class found for decoding. Returning null.");
            return null;
        }
        
        private VehicleIdDeviceIdMapping decodeVehicleIdDeviceIdMapping(String value) throws IOException {
            LOGGER.info("Decoding VehicleIdDeviceIdMapping object.");
            value = value.replace(RedisProperty.OLD_PACKAGE_NAME_ENTITIES, RedisProperty.NEW_PACKAGE_NAME_ENTITIES);
            value = value.replace(RedisProperty.CONCURRECT_HASH_SET_OLD, RedisProperty.CONCURRECT_HASH_SET_NEW);
            return objectMapper.readValue(value, VehicleIdDeviceIdMapping.class);
        }

        private RetryRecord decodeRetryRecord(String value) {
            try {
                LOGGER.info("Decoding RetryRecord object.");
                if (value.contains(RedisProperty.OLD_PACKAGE_NAME_ENTITIES)) {
                    value = value.replace(RedisProperty.OLD_PACKAGE_NAME_ENTITIES, 
                            RedisProperty.NEW_PACKAGE_NAME_ENTITIES);
                }
                if (value.contains(RedisProperty.IGNITE_STRING_KEY_OLD)) {
                    value = value.replace(RedisProperty.IGNITE_STRING_KEY_OLD, RedisProperty.IGNITE_STRING_KEY_NEW);
                }
                return objectMapper.readValue(value, RetryRecord.class);
            } catch (IOException e) {
                LOGGER.error("Error decoding RetryRecord: {}", e.getMessage(), e);
                return null;
            }
        }
        
        private RetryRecordIds decodeRetryRecordIds(String value) throws IOException {
            LOGGER.info("Decoding RetryRecordIds object.");
            value = value.replace(RedisProperty.OLD_PACKAGE_NAME_ENTITIES, RedisProperty.NEW_PACKAGE_NAME_ENTITIES)
                         .replace(RedisProperty.CONCURRECT_HASH_SET_OLD, RedisProperty.CONCURRECT_HASH_SET_NEW);
            return objectMapper.readValue(value, RetryRecordIds.class);
        }
    };
    
    private final Decoder<Object> keyDecoder = new Decoder<Object>() {

        @Override
        public Object decode(ByteBuf buf, State state) throws IOException {
            try (InputStream stream = new ByteBufInputStream(buf)) {
                StringWriter writer = new StringWriter();
                IOUtils.copy(stream, writer, StandardCharsets.UTF_8);
                String value = writer.toString();        
                LOGGER.debug("Decoding key :{}", value);
                return objectMapper.readValue(value, String.class);
            }
        }
    };

    @Override
    public Decoder<Object> getMapValueDecoder() {
        return valueDecoder;
    }

    @Override
    public Encoder getMapValueEncoder() {
        return encoder;
    }

    @Override
    public Decoder<Object> getMapKeyDecoder() {
        return keyDecoder;
    }

    @Override
    public Encoder getMapKeyEncoder() {
        return encoder;
    }

    @Override
    public Decoder<Object> getValueDecoder() {
        return valueDecoder;
    }

    @Override
    public Encoder getValueEncoder() {
        return encoder;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

}