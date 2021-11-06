package com.birdRun543.code0.common.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author hanbing
 */
@Slf4j
public class BasicItemUtil {

    /**
     * fetch properties from .yml file
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
}
