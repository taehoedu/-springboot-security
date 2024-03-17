package com.office.security.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyBeanNameGenerator implements BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		log.info("Bean name: {}", definition.getBeanClassName());
		
		return definition.getBeanClassName();
		
	}

}
