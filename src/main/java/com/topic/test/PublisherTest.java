package com.topic.test;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import com.topic.PublisherService;

public class PublisherTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-jms-topic.xml");
		PublisherService publisher = (PublisherService) context.getBean("publisherServiceImpl");
		publisher.publishMessage("ActiveMQ 发布信息1");
	}
}
