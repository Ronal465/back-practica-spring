package com.back.practique.springboot.responses;

public class Response<D> implements com.back.practique.springboot.responses.IResponse<D> {

	private D data;

	private boolean succes;

	private String message;

	public Response() {
		super();
	}

	@Override
	public D getData() {
		return data;
	}

	@Override
	public void setData(D data) {
		this.data = data;
	}

	@Override
	public boolean isSucces() {
		return succes;
	}

	@Override
	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
