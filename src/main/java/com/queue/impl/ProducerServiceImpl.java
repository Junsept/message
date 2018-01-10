package com.queue.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.queue.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService{
    @Resource
    private JmsTemplate jmsTemplate;
    
	public void sendMessage(Destination destination, final String msg) {
		System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);
		jmsTemplate.send(destination, new MessageCreator(){
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}		
		});
	}

	public void sendMessage(final String msg) {
		String destination =  jmsTemplate.getDefaultDestination().toString();
		System.out.println("生产者发送了消息：" + msg);
		jmsTemplate.send(new MessageCreator(){
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}		
		});
	}
	
}
