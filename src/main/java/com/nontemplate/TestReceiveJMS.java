package com.nontemplate;

import java.net.URI;
import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class TestReceiveJMS {
	public static void main(String[] args) {
		BrokerService broker;
		try {
			broker = BrokerFactory.createBroker(new URI("broker:tcp://localhost:61616"));
			broker.start(); 
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    // ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection conn = null;
		// Session： 一个发送或接收消息的线程
		Session session = null;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // MessageConsumer：消息接收者
        MessageConsumer consumer;
        
        try{
            // 构造从工厂得到连接对象
            conn = cf.createConnection();
            // 启动
            conn.start();
            // 获取操作连接
            session = conn.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            
            consumer = session.createConsumer(destination);
            
            while (true) {
                //设置接收者接收消息的时间,即接受者启动后100s内可实时接收
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message) {
                    System.out.println("收到消息" + message.getText());
                } else {
                    break;
                }
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
}
