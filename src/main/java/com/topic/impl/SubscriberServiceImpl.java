package com.topic.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

import com.topic.SubscriberService;


@Service
public class SubscriberServiceImpl implements SubscriberService{

	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
        try {
            System.out.println("订阅者1 \t" + tm.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}

}
