package com.back.practique.springboot.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.practique.springboot.interfaces.ServiceBasic;
import com.back.practique.springboot.models.User;
import com.back.practique.springboot.repositories.UserRepository;
import com.back.practique.springboot.responses.Response;
import com.back.practique.springboot.responses.ResponseList;

@Service
public class UserService extends ServiceBasic<Long, User> {

	@Autowired
	private UserRepository repository;

	@Override
	protected Response<User> getOne(Long id) {
		Response<User> response = new Response<User>();
		if (id == 0) {
			response.setMessage("Bad Request");
			response.setSucces(false);
			return response;
		}
		User user = null;
		try {
			user = this.repository.findById(id).get();
		} catch (Exception e) {
			response.setMessage("No se encontro ningun usuario");
			response.setSucces(false);
			return response;
		}
		if (user == null) {
			response.setMessage("No se encontro ningun usuario");
			response.setSucces(false);
			return response;
		}
		response.setSucces(true);
		response.setData(user);
		return response;
	}

	@Override
	protected ResponseList<User> getList() {
		ResponseList<User> response = new ResponseList<User>();
		List<User> users = null;
		try {
			users = StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toList());
		} catch (Exception e) {
			response.setMessage("No se encontro ningun usuario");
			response.setSucces(false);
			return response;
		}
		if (users == null || users.size() == 0) {
			response.setMessage("No se encontro ningun usuario");
			response.setSucces(false);
			return response;
		}
		response.setSucces(true);
		response.setData(users);
		return response;
	}

	@Override
	protected Response<User> deleteObject(Long id) {
		Response<User> response = new Response<User>();
		if (id == 0) {
			response.setMessage("Bad Request");
			response.setSucces(false);
			return response;
		}
		Response<User> find = this.getOne(id);
		if (!find.isSucces()) {
			return find;
		}
		try {
			this.repository.deleteById(id);

		} catch (Exception e) {
			response.setMessage("No se pudo eliminar");
			response.setSucces(false);
			return response;
		}
		response.setSucces(true);
		return response;
	}

	@Override
	protected Response<User> updateObject(User object) {
		Response<User> response = new Response<User>();
		if (object == null) {
			response.setMessage("Bad Request");
			response.setSucces(false);
			return response;
		}
		User validaName = this.repository.findByName(object.getName());
		if (validaName != null) {
			if (!validaName.getId().equals(object.getId())) {
				response.setMessage("Usuario con mismo nombre creado");
				response.setSucces(false);
				return response;
			}

		}
		User user = this.repository.save(object);
		if (user == null) {
			response.setSucces(false);
			response.setMessage("Error al Actualizar el usuario");
			return response;
		}
		response.setSucces(true);
		return response;
	}

	@Override
	protected Response<User> createObject(User object) {
		Response<User> response = new Response<User>();
		if (object == null) {
			response.setMessage("Bad Request");
			response.setSucces(false);
			return response;
		}
		object.setId(null);
		User validaName = this.repository.findByName(object.getName());
		if (validaName != null) {
			response.setMessage("Usuario con mismo nombre creado");
			response.setSucces(false);
			return response;
		}
		object.setCreationDate(new Date());
		User user = this.repository.save(object);
		response.setData(user);
		if (user == null) {
			response.setSucces(false);
			response.setMessage("Error al crear el usuario");
			return response;
		}
		response.setSucces(true);
		return response;
	}

	@Override
	protected ResponseList<User> findByAnyField(String text) {
		ResponseList<User> response = new ResponseList<User>();
		if (text == null) {
			return this.getList();
		}
		List<User> users = null;
		try {
			users = StreamSupport.stream(this.repository.findByPorcentualName(text).spliterator(), false)
					.collect(Collectors.toList());
		} catch (Exception e) {
			response.setMessage("No se encontro ningun usuario");
			response.setSucces(false);
			return response;
		}
		if (users == null || users.size() == 0) {
			response.setMessage("No se encontro ningun usuario");
			response.setSucces(false);
			return response;
		}
		response.setSucces(true);
		response.setData(users);
		return response;
	}

}
