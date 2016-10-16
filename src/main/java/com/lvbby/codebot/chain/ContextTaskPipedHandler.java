package com.lvbby.codebot.chain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by peng on 16/7/30.
 */
public class ContextTaskPipedHandler<T, R> implements ContextHandler<T> {
    private List<PipeLine<R, T>> pipeLines = new LinkedList<>();
    private ContextTask<T, R> contextTask;
    private R result;

    public ContextTaskPipedHandler(ContextTask<T, R> contextTask) {
        this.contextTask = contextTask;
    }

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
    public void handle(T context) {
        R result = contextTask.process(context);
        for (PipeLine<R, T> pipeLine : pipeLines) {
            try {
                pipeLine.handle(result, context);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        this.result = result;
    }
}
