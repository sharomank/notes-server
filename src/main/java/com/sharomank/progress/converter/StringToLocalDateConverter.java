package com.sharomank.progress.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String source) {
        return source == null ? null : LocalDate.parse(source);
    }
}
