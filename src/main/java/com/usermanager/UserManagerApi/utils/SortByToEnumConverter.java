package com.usermanager.UserManagerApi.utils;

import org.springframework.core.convert.converter.Converter;


public class SortByToEnumConverter implements Converter<String, SortedBy> {

    @Override
    public SortedBy convert(String source) {
        try {
            return SortedBy.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
