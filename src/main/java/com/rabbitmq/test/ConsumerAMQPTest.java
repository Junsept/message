package com.rabbitmq.test;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import com.rabbitmq.ConsumerAMQPService;


public class ConsumerAMQPTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-amqp-rabbitmq.xml");
		ConsumerAMQPService consumer = (ConsumerAMQPService) context.getBean("consumerAMQPServiceImpl");
		consumer.receiveMessage();
	}
}
