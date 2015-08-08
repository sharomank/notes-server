package com.sharomank.notes.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ReflectionUtils;

import java.util.Optional;

public final class JavaBeanUtils {
    private JavaBeanUtils() {
    }

    public static void copyNotNullProperties(Optional<Object> source, Optional<Object> target) {
        if (!source.isPresent() || !target.isPresent()) {
            return;
        }
        final BeanWrapper src = new BeanWrapperImpl(source.get());
        final BeanWrapper trg = new BeanWrapperImpl(target.get());
        ReflectionUtils.doWithFields(source.get().getClass(), field -> {
            String propName = field.getName();
            Object srcPropValue = src.getPropertyValue(propName);
            if (srcPropValue != null) {
                trg.setPropertyValue(propName, srcPropValue);
            }
        });
    }
}