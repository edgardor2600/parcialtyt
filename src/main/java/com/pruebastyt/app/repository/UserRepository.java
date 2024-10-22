package com.pruebastyt.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pruebastyt.app.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmail(String email);
}
