package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by igouz on 2017/4/28.
 */
public interface IMessageSerializer<M extends Serializable> {
    void serialize(M message, HttpServletResponse response);

    M deserialize(HttpServletRequest request);
}
