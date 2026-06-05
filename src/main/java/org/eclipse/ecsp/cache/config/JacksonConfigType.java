package org.eclipse.ecsp.cache.config;

/**
 * Enum to define the serializer and deserializer enum to be used with jackson.
 */
public enum JacksonConfigType {
    
    /** The serializer. */
    SERIALIZER,
    
    /** The deserializer. */
    DESERIALIZER,
    
    /** The subtype. */
    SUBTYPE
}