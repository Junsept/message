package com.queue.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.queue.ConsumerService;


@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Resource
    private JmsTemplate jmsTemplate;
	public void receiveMessage(Destination destination) {
		TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
		try{
            System.out.println("消费者收到了消息：\t"+ tm.getText());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
