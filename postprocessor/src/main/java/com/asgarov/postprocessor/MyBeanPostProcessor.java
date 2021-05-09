package com.asgarov.postprocessor;

import com.asgarov.event.annotation.Loggable;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (hasLoggableMethods(bean)) {
            var enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback(getMethodInterception());
            return enhancer.create();
        }
        return bean;
    }

    private boolean hasLoggableMethods(Object bean) {
        return Arrays.stream(bean.getClass().getMethods())
                .anyMatch(method -> method.getAnnotation(PostProcessable.class) != null);
    }

    public MethodInterceptor getMethodInterception() {
        return (bean, method, objects, methodProxy) -> {
            var annotation = Optional.ofNullable(method.getAnnotation(PostProcessable.class));
            if (annotation.isPresent()) {
                LoggerFactory.getLogger(method.getDeclaringClass()).info("BEFORE: " + annotation.get().value());
                return methodProxy.invokeSuper(bean, objects);
            }
            return methodProxy.invokeSuper(bean, objects);
        };
    }
}
