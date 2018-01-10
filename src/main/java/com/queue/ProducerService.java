package com.queue;

import javax.jms.Destination;

public interface ProducerService {
	
	void sendMessage(Destination destination, String msg);
	
	void sendMessage(String msg);
}
