package com.usermanager.UserManagerApi.utils;

import org.springframework.core.convert.converter.Converter;

public class OrderByToEnumConverter implements Converter<String, OrderBy> {
    @Override
    public OrderBy convert(String source) {
        try {
            return OrderBy.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
