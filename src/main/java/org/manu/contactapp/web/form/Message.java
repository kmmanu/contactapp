package org.manu.contactapp.web.form;

public class Message {
	private String type;
	private String message;

	public Message(final String type, final String message) {
		super();
		this.type = type;
		this.message = message;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}
