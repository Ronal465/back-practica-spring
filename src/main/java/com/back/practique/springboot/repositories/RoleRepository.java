package com.back.practique.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.back.practique.springboot.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	@Query("SELECT u FROM Role u WHERE u.name LIKE CONCAT('%',:name,'%')")
	List<Role> findByPorcentualName(String name);

	Role findByName(String name);
}
