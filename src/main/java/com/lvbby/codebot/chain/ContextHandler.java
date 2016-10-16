package com.lvbby.codebot.chain;

/**
 * Created by peng on 16/7/27.
 * the basic handler interface
 */
public interface ContextHandler<T> {
    void handle(T context);
}
