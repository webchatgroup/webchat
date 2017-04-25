package com.dev3.app.processor;

import com.dev3.app.entity.AbstractMessage;

import java.io.Serializable;

/**
 * Created by A022713 on 2017/4/24.
 */
public class MessageProcessorIndicator {
    private boolean success;
    private Serializable state;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Serializable getState() {
        return state;
    }

    public void setState(Serializable state) {
        this.state = state;
    }
}
