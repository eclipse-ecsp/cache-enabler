package org.eclipse.ecsp.cache.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Jackson ObjectMapper.
 * This class provides a default ObjectMapper bean that can be used throughout the application.
 * If another ObjectMapper bean is defined, this one will not override it.
 */
@Configuration
public class JacksonConfig {

    /**
     * Provides a default ObjectMapper bean if no other ObjectMapper bean is defined.
     * This allows for customization of the ObjectMapper in other configurations if needed.
     *
     * @return a new instance of ObjectMapper
     */
    @ConditionalOnMissingBean
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
