package com.dev3.app.validator;

import java.util.List;

/**
 * Created by A022713 on 2017/4/25.
 */
public interface IMessageValidator<M> {
    boolean validate(M message, List<Exception> exceptionList);
}
