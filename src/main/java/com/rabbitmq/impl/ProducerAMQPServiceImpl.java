package com.rabbitmq.impl;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.rabbitmq.ProducerAMQPService;

@Service
public class ProducerAMQPServiceImpl implements ProducerAMQPService{
	
	@Resource
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String msg) {
		System.out.println("生产者发送消息：\t"+msg);
		//测试direct-exchange
		rabbitTemplate.convertAndSend("direct", "1", msg);
		//测试topic-exchange
		//rabbitTemplate.convertAndSend("topic", "test.queue", msg);
		//测试fanout-exchange
		//rabbitTemplate.convertAndSend("fanout", "", msg);
	}

}
