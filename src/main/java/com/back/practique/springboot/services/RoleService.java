package com.back.practique.springboot.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.practique.springboot.interfaces.ServiceBasic;
import com.back.practique.springboot.models.Role;
import com.back.practique.springboot.repositories.RoleRepository;
import com.back.practique.springboot.responses.Response;
import com.back.practique.springboot.responses.ResponseList;

@Service
public class RoleService extends ServiceBasic<Long, Role> {

	@Autowired
	private RoleRepository repository;

	@Override
	protected Response<Role> getOne(Long id) {
		Response<Role> response = new Response<Role>();
		if (id == 0) {
			response.setMessage("Bad Request");
			response.setSucces(false);
			return response;
		}
		Role role = null;
		try {
			role = this.repository.findById(id).get();
			response.setData(role);
		} catch (Exception e) {
			response.setMessage("No se encontro ningun rol");
			response.setSucces(false);
			return response;
		}
		if (role == null) {
			response.setMessage("No se encontro ningun rol");
			response.setSucces(false);
			return response;
		}
		response.setSucces(true);

		return response;
	}

	@Override
	protected ResponseList<Role> getList() {
		ResponseList<Role> response = new ResponseList<Role>();
		List<Role> roles = null;
		try {
			roles = StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toList());
			response.setData(roles);
		} catch (Exception e) {
			response.setMessage("No se encontro ningun rol");
			response.setSucces(false);
			return response;
		}
		if (roles == null || roles.size() == 0) {
			response.setMessage("No se encontro ningun rol");
			response.setSucces(false);
			return response;
		}
		response.setSucces(true);
		return response;
	}

	@Override
	protected Response<Role> deleteObject(Long id) {
		Response<Role> response = new Response<Role>();
		if (id == 0) {
			response.setMessage("Bad Request");
			response.setSucces(false);
			return response;
		}
		Response<Role> find = this.getOne(id);
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
	protected Response<Role> updateObject(Role object) {
		Response<Role> response = new Response<Role>();
		if (object == null) {
			response.setMessage("Bad Request");
			response.setSucces(false);
			return response;
		}
		Role validaRole = this.repository.findByName(object.getName());
		if (validaRole != null) {
			if (!validaRole.getId().equals(object.getId())) {
				response.setMessage("Rol con mismo nombre creado");
				response.setSucces(false);
				return response;
			}
		}
		Role role = this.repository.save(object);
		if (role == null) {
			response.setSucces(false);
			response.setMessage("Error al Actualizar el rol");
			return response;
		}
		response.setData(role);
		response.setSucces(true);
		return response;
	}

	@Override
	protected Response<Role> createObject(Role object) {
		Response<Role> response = new Response<Role>();
		if (object == null) {
			response.setMessage("Bad Request");
			response.setSucces(false);
			return response;
		}
		object.setId(null);
		Role validaRole = this.repository.findByName(object.getName());
		if (validaRole != null) {
			response.setMessage("Rol con mismo nombre creado");
			response.setSucces(false);
			return response;
		}

		Role role = this.repository.save(object);
		if (role == null) {
			response.setSucces(false);
			response.setMessage("Error al crear el rol");
			return response;
		}
		response.setData(role);
		response.setSucces(true);
		return response;
	}

	@Override
	protected ResponseList<Role> findByAnyField(String text) {
		ResponseList<Role> response = new ResponseList<Role>();
		if (text == null) {
			return this.getList();
		}
		List<Role> roles = null;
		try {
			roles = StreamSupport.stream(this.repository.findByPorcentualName(text).spliterator(), false)
					.collect(Collectors.toList());
		} catch (Exception e) {
			response.setMessage("No se encontro ningun rol");
			response.setSucces(false);
			return response;
		}
		if (roles == null || roles.size() == 0) {
			response.setMessage("No se encontro ningun rol");
			response.setSucces(false);
			return response;
		}
		response.setSucces(true);
		response.setData(roles);
		return response;
	}

}
