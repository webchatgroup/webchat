package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by A022713 on 2017/4/25.
 */
public interface IMessageParser<M extends AbstractMessage> {
    M parse(HttpServletRequest request);
}
