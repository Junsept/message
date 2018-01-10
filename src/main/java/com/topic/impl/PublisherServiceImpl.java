package com.topic.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.topic.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{
	@Resource 
	JmsTemplate jmsTemplate ;
	
	public void publishMessage(final String msg) {
		final Destination destination = jmsTemplate.getDefaultDestination();
		jmsTemplate.send(new MessageCreator(){
			public Message createMessage(Session session) throws JMSException {
                System.out.println("topic name 是" + destination.toString()
                + "，发布消息内容为:\t" + msg);
				return session.createTextMessage(msg);
			}			
		});
	}

	public void publishMessage(final Destination destination, final String msg) {
		
		jmsTemplate.send(destination, new MessageCreator(){
			public Message createMessage(Session session) throws JMSException {
                System.out.println("topic name 是" + destination.toString()
                + "，发布消息内容为:\t" + msg);
				return session.createTextMessage(msg);
			}			
		});
	}

}
