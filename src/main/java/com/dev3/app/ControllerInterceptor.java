package com.dev3.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by A022713 on 2017/4/25.
 */
@Aspect
@Component
public class ControllerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ProcessorInterceptor.class);

    @Value("${spring.profiles}")
    private String env;

    @Pointcut("execution(* com.dev3.app.web..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {
    }

    @Around("controllerMethodPointcut()")
    public Object controllerMethodInterceptor(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();

        Object result = null;

        try {
            logger.info("Auditing %1$s", methodName);

            Set<Object> allParams = new LinkedHashSet<>();
            Object[] args = pjp.getArgs();

            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) arg;

                    Map<String, String[]> paramMap = request.getParameterMap();
                    if (paramMap != null && paramMap.size() > 0) {
                        allParams.add(paramMap);
                    }
                }
            }

            logger.info("Invoking %1$s", methodName);

            result = pjp.proceed();
        } catch (Throwable e) {
            logger.warn("%1$s exception: %2$s\n", methodName, e.getMessage());
        } finally {
            logger.info("Done %1$s in %2$s ms.", methodName, System.currentTimeMillis() - beginTime);
        }

        return result;
    }
}
