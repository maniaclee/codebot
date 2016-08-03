package com.lvbby.codebot.lang;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Created by peng on 16/8/3.
 */
public class ClassFieldVisitor {
    public void visitField(Object obj, ClassFieldHandler... handlers) throws Exception {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            if (type.isAssignableFrom(Collection.class)) {
                Collection o = (Collection) field.get(obj);
                for (Object s : o) {
                    visitField(s, handlers);
                }
            } else {
                for (ClassFieldHandler h : handlers) h.visit(field, obj);
            }
        }
    }
}
