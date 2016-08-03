package com.lvbby.codebot.lang;

import java.lang.reflect.Field;

/**
 * Created by peng on 16/8/3.
 */
public interface ClassFieldHandler {
    void visit(Field field, Object obj);
}
