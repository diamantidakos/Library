package com.mgiandia.library.util;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HttpError {

	int errorCode;
	String message;

	public HttpError() {
		
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static HttpError httpConflictError(String msg) {
		return new HttpError(Status.CONFLICT.getStatusCode(), msg);
	}

	public static HttpError httpNotFoundError(String msg) {
		return new HttpError(Status.NOT_FOUND.getStatusCode(), msg);
	}

	private HttpError(int errorCode,
			String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public static HttpError httpForbiddenError(String msg) {
		return new HttpError(Status.FORBIDDEN.getStatusCode(), msg);
	}

}
