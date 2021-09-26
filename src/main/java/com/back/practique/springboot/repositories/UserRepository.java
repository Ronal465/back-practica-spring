package com.back.practique.springboot.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.back.practique.springboot.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.name LIKE CONCAT('%',:name,'%')")
	List<User> findByPorcentualName(String name);
	
	User findByName(String name);
}
