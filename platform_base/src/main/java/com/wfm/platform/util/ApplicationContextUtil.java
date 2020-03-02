package com.wfm.platform.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Weifengming
 * @description 获取ApplicationContext上下文
 * @date 2020/2/18
 */
@Component

public class ApplicationContextUtil implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class);

    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext _context)
            throws BeansException {
        context = _context;
    }

    public static ApplicationContext getApplicaitonContext() {
        return context;
    }

    public static Object getBean(String beanId) {
        try {
            return context.getBean(beanId);
        } catch (Exception ex) {
            logger.debug("getBean:" + beanId + "," + ex.getMessage());
        }
        return null;
    }

    public static <T> T getBean(Class<T> beanClass) {
        Object bean = null;
        try {
            bean = context.getBean(beanClass);
        } catch (Exception ex) {
            try {
                if (beanClass != null) {
                    String beanName = beanClass.getSimpleName();
                    String beanId = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                    bean = context.getBean(beanId);
                }
            } catch (BeansException e) {
                logger.debug("getBean:" + beanClass + "," + ex.getMessage());
            }
        }
        return (T) bean;
    }

    public static List<Class> getImplClass(Class clazz)
            throws ClassNotFoundException {
        List list = new ArrayList();

        Map map = context.getBeansOfType(clazz);
        for (Iterator localIterator = map.values().iterator(); localIterator.hasNext(); ) {
            Object obj = localIterator.next();
            String name = obj.getClass().getName();
            int pos = name.indexOf("$$");
            if (pos > 0) {
                name = name.substring(0, name.indexOf("$$"));
            }
            Class cls = Class.forName(name);

            list.add(cls);
        }
        return list;
    }

    public static Map<String, Object> getImplInstance(Class clazz)
            throws ClassNotFoundException {
        Map map = context.getBeansOfType(clazz);
        return map;
    }

    public static void publishEvent(ApplicationEvent event) {
        if (context != null)
            context.publishEvent(event);
    }

    public static void publishEvent(Object var) {
        if (context != null)
            context.publishEvent(var);
    }

    public static String getClasspath() {
        String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String rootPath = "";

        if ("\\".equals(File.separator)) {
            rootPath = classPath.substring(1);
            rootPath = rootPath.replace("/", "\\");
        }

        if ("/".equals(File.separator)) {
            rootPath = classPath.substring(1);
            rootPath = rootPath.replace("\\", "/");
        }
        return rootPath;
    }
}
