package com.sharomank.progress.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public class LocalDateTimeToStringConverter implements Converter<LocalDateTime, String> {
    @Override
    public String convert(LocalDateTime source) {
        return source == null ? null : source.toString();
    }
}
