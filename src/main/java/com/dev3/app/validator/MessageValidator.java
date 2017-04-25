package com.dev3.app.validator;

import com.dev3.app.entity.AbstractMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by A022713 on 2017/4/24.
 */
@Component
public class MessageValidator implements IMessageValidator<AbstractMessage> {

    @Override
    public boolean validate(AbstractMessage message, List<Exception> exceptionList) {
        return true;
    }
}
