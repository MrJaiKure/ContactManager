package com.smart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.smart.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

//	Optional<User>findEmailAndPassword(String email,String password);
//	
//	Optional<User> findByEmailToken(String id);

}
