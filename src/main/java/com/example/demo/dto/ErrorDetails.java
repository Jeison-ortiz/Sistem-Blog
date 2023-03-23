package com.example.demo.dto;

import java.util.Date;

public class ErrorDetails {
	
	private Date marcOfTime;
	
	private String detail;
	
	private String Message;

	public ErrorDetails(Date marcOfTime, String detail, String message) {
		super();
		this.marcOfTime = marcOfTime;
		this.detail = detail;
		Message = message;
	}

	public Date getMarcOfTime() {
		return marcOfTime;
	}

	public void setMarcOfTime(Date marcOfTime) {
		this.marcOfTime = marcOfTime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
	
}
