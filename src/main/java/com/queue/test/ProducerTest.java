package com.queue.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.queue.ProducerService;

public class ProducerTest {
	private static final int SEND_NUMBER = 5;

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-jms-queue.xml");        
		ProducerService producer = (ProducerService) context.getBean("producerServiceImpl");
		for(int i = 0; i < SEND_NUMBER; i ++){
			producer.sendMessage("Activemq消息" + i);
		}
	}
}
