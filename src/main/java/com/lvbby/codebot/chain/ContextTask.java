package com.lvbby.codebot.chain;

/**
 * Created by peng on 16/7/27.
 */
public interface ContextTask<T, R> {
    R process(T context);
}
