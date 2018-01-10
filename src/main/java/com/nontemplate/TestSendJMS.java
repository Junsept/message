package com.nontemplate;

import java.net.URI;
import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class TestSendJMS {
	private static final int SEND_NUMBER = 5;

	public static void main(String[] args) {
		
		BrokerService broker;		
	    // ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory cf = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection conn = null;
		// Session： 一个发送或接收消息的线程
		Session session = null;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // MessageProducer：消息发送者
        MessageProducer producer;
        
		try{
			conn = cf.createConnection();
			
			conn.start();		
	         // 获取操作连接
            session = conn.createSession(Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);
            
            destination = session.createQueue("FirstQueue");
            
            producer = session.createProducer(destination);
            
            sendMessage(session, producer);
            
            session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void sendMessage(Session session, MessageProducer producer)
            throws Exception {
        for (int i = 1; i <= SEND_NUMBER; i++) {
            TextMessage message = session
                    .createTextMessage("ActiveMq 发送的消息" + i);
            // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
    }
}
