package com.asgarov.postprocessor;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (hasAnnotatedMethods(bean, PostProcessable.class)) {
            var enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback(createCustomCallback());
            return enhancer.create();
        }
        return bean;
    }

    private boolean hasAnnotatedMethods(Object bean, Class<? extends Annotation> clazz) {
        return Arrays.stream(bean.getClass().getMethods())
                .map(method -> method.getAnnotation(clazz))
                .anyMatch(Objects::nonNull);
    }

    public MethodInterceptor createCustomCallback() {
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
