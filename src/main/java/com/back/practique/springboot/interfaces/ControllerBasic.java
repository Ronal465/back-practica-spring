package com.back.practique.springboot.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.back.practique.springboot.responses.Response;
import com.back.practique.springboot.responses.ResponseList;

public abstract class ControllerBasic<ID, O> {

	protected abstract ServiceBasic<ID, O> getService();

	@RequestMapping(method = RequestMethod.GET)
	public ResponseList<O> fetch() {
		return getService().getList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response<O> findOne(@PathVariable("id") ID id) {
		return getService().getOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Response<O> create(@RequestBody() O object) {
		return getService().createObject(object);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Response<O> update(@RequestBody O object) {
		return getService().updateObject(object);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Response<O> delete(@PathVariable("id") ID id) {
		return getService().deleteObject(id);
	}

	@RequestMapping(value = "/field/{field}", method = RequestMethod.GET)
	public ResponseList<O> findByAnyField(@PathVariable(required = false, value = "field") String field) {
		return getService().findByAnyField(field);
	}

}
