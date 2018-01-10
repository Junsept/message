package com.rabbitmq.test;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import com.rabbitmq.ProducerAMQPService;

public class ProducerAMQPTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-amqp-rabbitmq.xml");
		ProducerAMQPService producer = (ProducerAMQPService) context.getBean("producerAMQPServiceImpl");
		producer.sendMessage("rabbitMQ");
	}
}
