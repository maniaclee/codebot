package com.lvbby.codebot.chain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by peng on 16/7/30.
 */
public abstract class ContextTaskPipedHandler<T, R> extends ContextTaskHandler<T, R> {
    private List<PipeLine<R, T>> pipeLines = new LinkedList<>();

    public ContextTaskPipedHandler<T, R> addPipeLine(PipeLine<R, T> pipeLine) {
        pipeLines.add(pipeLine);
        return this;
    }

    public void setPipeLines(List<PipeLine<R, T>> pipeLines) {
        this.pipeLines = pipeLines;
    }

    public List<PipeLine<R, T>> getPipeLines() {
        return pipeLines;
    }

    @Override
    public R process(T context) {
        R t = doProcess(context);
        for (PipeLine<R, T> pipeLine : pipeLines) {
            try {
                pipeLine.handle(t, context);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }

    public abstract R doProcess(T context);

}
