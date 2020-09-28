package com.EBookStore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EBookStore.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
	
	User findByEmail(String email);

}
