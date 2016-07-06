package com.lab.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.lab.demo.model.Person;

public interface PersonMongoRepository extends CrudRepository<Person, String>
{}
