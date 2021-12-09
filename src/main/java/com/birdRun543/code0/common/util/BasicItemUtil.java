package com.birdRun543.code0.common.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanbing
 */
@Slf4j
public class BasicItemUtil {

    private static final Pattern UNDERLINE_PATTERN = Pattern.compile("_(\\w)");

    /**
     * fetch properties from .yml file
     *
     * @param propertyName property name
     * @return Properties
     */
    public static Properties initProperties(String propertyName) {
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            ClassPathResource resource = new ClassPathResource(propertyName);
            yamlFactory.setResources(resource);
            return yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    static String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //对private的属性的访问
            field.setAccessible(true);
            return String.valueOf(field.get(object));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String lineToHump(String text) {
        text = text.toLowerCase();
        Matcher matcher = UNDERLINE_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
