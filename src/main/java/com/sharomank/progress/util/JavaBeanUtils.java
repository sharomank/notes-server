package com.sharomank.progress.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;

public final class JavaBeanUtils {
    private JavaBeanUtils() {
    }

    public static void copyNotNullProperties(Object source, Object target) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper trg = new BeanWrapperImpl(target);
        for (final Field field : source.getClass().getDeclaredFields()) {
            String propName = field.getName();
            Object srcPropValue = src.getPropertyValue(propName);
            if (srcPropValue != null) {
                trg.setPropertyValue(propName, srcPropValue);
            }
        }
    }
}