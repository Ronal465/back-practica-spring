package com.back.practique.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.practique.springboot.interfaces.ControllerBasic;
import com.back.practique.springboot.interfaces.ServiceBasic;
import com.back.practique.springboot.models.User;

@RestController
@RequestMapping(path = "api/user")
public class UserController extends ControllerBasic<Long, User> {

	@Autowired
	private ServiceBasic<Long, User> service;

	@Override
	protected ServiceBasic<Long, User> getService() {
		return this.service;
	}

}
