package com.queue;

import javax.jms.Destination;

public interface ConsumerService {
	
	void receiveMessage(Destination destination);
}
