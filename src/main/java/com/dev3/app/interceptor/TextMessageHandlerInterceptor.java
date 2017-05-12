package com.dev3.app.interceptor;

import com.dev3.app.entity.TextMessage;
import com.dev3.app.repositoriy.TextMessageRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by phoenix on 2017/5/12.
 */
@Aspect
@Component
public class TextMessageHandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TextMessageHandlerInterceptor.class);

    @Autowired
    private TextMessageRepository textMessageRepository;

    @Value("${spring.profiles}")
    private String env;

    @Pointcut("execution(* com.dev3.app.handler.AbsMessageHandler.handleMessage(..))")
    public void handlerMethodPointcut() {
    }

    @Around("handlerMethodPointcut()")
    public Object handlerInterceptor(ProceedingJoinPoint pjp) {
        Object result = null;

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getMethod().getName();

        logger.info(String.format("Intercept method: %1$s.%2$s", pjp.getThis().getClass().getName(), methodName));

        Class[] parameters = signature.getParameterTypes();

        if (parameters != null && parameters.length > 0 && parameters[0].isAssignableFrom(TextMessage.class)) {
            TextMessage message = (TextMessage) pjp.getArgs()[0];

            this.textMessageRepository.save(message);
        }

        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            logger.error(String.format("Exception: %1$s\n", e.getLocalizedMessage()));
            e.printStackTrace();
        }

        if (result != null && result instanceof TextMessage) {
            TextMessage message = (TextMessage) result;

            this.textMessageRepository.save(message);
        }

        return result;
    }
}
