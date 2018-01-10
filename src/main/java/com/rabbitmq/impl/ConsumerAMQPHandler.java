package com.rabbitmq.impl;

public class ConsumerAMQPHandler {
	public void handlerMessage(String message){
		System.out.println("监听器监听到消息：\t" + message);
	}
}
