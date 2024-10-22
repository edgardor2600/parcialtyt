package com.pruebastyt.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pruebastyt.app.entity.Admin;


public interface AdminRepository extends MongoRepository<Admin, String> {

	public Admin findByEmail(String email);

}
