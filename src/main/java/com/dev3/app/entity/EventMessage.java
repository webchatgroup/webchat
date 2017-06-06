package com.dev3.app.entity;

import java.io.Serializable;

public class EventMessage extends AbstractMessage implements Serializable {

	private static final long serialVersionUID = 3261043988061293237L; 
	
	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	
	
}
