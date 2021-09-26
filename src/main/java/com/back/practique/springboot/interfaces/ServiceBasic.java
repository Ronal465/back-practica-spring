package com.back.practique.springboot.interfaces;

import com.back.practique.springboot.responses.Response;
import com.back.practique.springboot.responses.ResponseList;

public abstract class ServiceBasic<Id, O> {

	protected abstract Response<O> getOne(Id id);

	protected abstract ResponseList<O> getList();

	protected abstract Response<O> updateObject(O object);

	protected abstract Response<O> deleteObject(Id id);

	protected abstract Response<O> createObject(O object);

	protected abstract ResponseList<O> findByAnyField(String text);

}
