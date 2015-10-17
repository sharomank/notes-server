package com.sharomank.notes.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ReflectionUtils;

public final class JavaBeanUtils {
    private JavaBeanUtils() {
    }

    public static void copyNotNullProperties(final Object source, final Object target) {
        if (source != null && target != null) {
            final BeanWrapper src = new BeanWrapperImpl(source);
            final BeanWrapper trg = new BeanWrapperImpl(target);
            ReflectionUtils.doWithFields(source.getClass(), field -> {
                String propName = field.getName();
                if (trg.isWritableProperty(propName)) {
                    Object srcPropValue = src.getPropertyValue(propName);
                    if (srcPropValue != null) {
                        trg.setPropertyValue(propName, srcPropValue);
                    }
                }
            });
        }
    }
}