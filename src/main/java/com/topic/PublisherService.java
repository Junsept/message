package com.topic;

import javax.jms.Destination;

public interface PublisherService {
	void publishMessage(String msg);
	
	void publishMessage(Destination destination, String msg);
}
