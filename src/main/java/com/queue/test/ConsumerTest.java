package com.queue.test;

import javax.jms.Destination;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.queue.ConsumerService;


public class ConsumerTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-jms-queue.xml");        
		Destination destination = (Destination) context.getBean("queueDestination");
		ConsumerService consumer = (ConsumerService) context.getBean("consumerServiceImpl");
		while(true){
			consumer.receiveMessage(destination);
		}
	}
}
