package com.back.practique.springboot.responses;

public interface IResponse<Data> {

	Data getData();

	boolean isSucces();

	String getMessage();

	void setData(Data data);

	void setSucces(boolean succes);

	void setMessage(String message);

}
