package com.rabbitmq.impl;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.rabbitmq.ConsumerAMQPService;
@Service
public class ConsumerAMQPServiceImpl implements ConsumerAMQPService {
	
	@Resource
	private RabbitTemplate rabbitTemplate;
	public void receiveMessage() {
		Message tm1 = rabbitTemplate.receive("queue1");		
		Message tm2 = rabbitTemplate.receive("queue2");	
		Message tm3 = rabbitTemplate.receive("queue3");	
		try{
            System.out.println("消费者收到了queue1的消息：\t"+ new String(tm1.getBody()));
            System.out.println("消费者收到了queue2的消息：\t"+ new String(tm2.getBody()));
            System.out.println("消费者收到了queue3的消息：\t"+ new String(tm3.getBody()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
