package com.dev3.app.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by A022713 on 2017/4/24.
 */
public interface IMessageProcessor {
    void process(HttpServletRequest request, HttpServletResponse response, MessageProcessorIndicator indicator);
}
