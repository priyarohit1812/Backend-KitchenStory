package com.kitchenstory.model.responses;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = -871368303082362495L;
	private Boolean isError;
	private String message;
	private T response;

	public BaseResponse() {
		this(false, "", null);
	}

	public BaseResponse(Boolean isError, String message, T response) {
		this.isError = isError;
		this.message = message;
		this.response = response;
	}

	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "BaseResponse [isError=" + isError + ", message=" + message + ", response=" + response + "]";
	}

}
