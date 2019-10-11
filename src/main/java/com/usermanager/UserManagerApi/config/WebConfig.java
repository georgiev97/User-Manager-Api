package com.usermanager.UserManagerApi.config;

import com.usermanager.UserManagerApi.utils.OrderByToEnumConverter;
import com.usermanager.UserManagerApi.utils.SortByToEnumConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SortByToEnumConverter());
        registry.addConverter(new OrderByToEnumConverter());
    }
}
