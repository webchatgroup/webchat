package com.dev3.app.processor;

import com.dev3.app.handler.IMessageHandler;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by A022713 on 2017/4/24.
 */
public abstract class AbsMessageProcessor implements IMessageProcessor {
    private String successor;
    private boolean stopOnError;

    public boolean isStopOnError() {
        return stopOnError;
    }

    public void setStopOnError(boolean stopOnError) {
        this.stopOnError = stopOnError;
    }

    public IMessageProcessor getSuccessor() {
        if (this.successor != null) {
            return (IMessageProcessor) ContextLoader.getCurrentWebApplicationContext().getBean(this.successor);
        }

        return null;
    }

    public void setSuccessor(String successor) {
        this.successor = successor;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, MessageProcessorIndicator indicator) {
        if (indicator == null) indicator = new MessageProcessorIndicator();

        this.processRequestResponse(request, response, indicator);

        if (indicator.isSuccess() || !this.stopOnError) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().process(request, response, indicator);
            }
        }
    }

    protected abstract void processRequestResponse(HttpServletRequest request, HttpServletResponse response, MessageProcessorIndicator indicator);
}
