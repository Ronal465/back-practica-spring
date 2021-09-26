package com.back.practique.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.practique.springboot.interfaces.ControllerBasic;
import com.back.practique.springboot.interfaces.ServiceBasic;
import com.back.practique.springboot.models.Role;

@RestController
@RequestMapping(path = "api/role")
public class RoleController extends ControllerBasic<Long, Role> {

	@Autowired
	private ServiceBasic<Long, Role> service;

	@Override
	protected ServiceBasic<Long, Role> getService() {
		return this.service;
	}

}
