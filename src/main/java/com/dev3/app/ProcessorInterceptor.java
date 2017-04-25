package com.dev3.app;

import com.dev3.app.validator.IMessageValidator;
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

import java.lang.reflect.Method;

/**
 * Created by A022713 on 2017/4/25.
 */
@Aspect
@Component
public class ProcessorInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ProcessorInterceptor.class);

    @Autowired
    private IMessageValidator messageValidator;

    @Value("${spring.profiles}")
    private String env;

    @Pointcut("execution(* com.dev3.app.processor.IMessageProcessor.process(..))")
    public void processorMethodPointcut() {
    }

    @Around("processorMethodPointcut()")
    public Object ProcessInterceptor(ProceedingJoinPoint pjp) {
        Object result = null;

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();

        logger.info("Intercept method: %1$s.%2$s", pjp.getThis().getClass().getName(), methodName);

        try {
            result = pjp.proceed();
        } catch (Throwable e) {
        }

        return result;
    }
}
