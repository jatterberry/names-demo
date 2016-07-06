package com.lab.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.lab.demo.model.Person;

@Repository
public class PersonSearchRepository
{
	@Autowired
	MongoTemplate mongoTemplate;

	public Person findByPersonId(String personId)
	{
		Query idQuery = new Query();
		idQuery.addCriteria(Criteria.where("personId").is(personId));
		
		Person foundPerson = mongoTemplate.findOne(idQuery, Person.class);
		return foundPerson;
	}
	
	public Person findByPersonFullName(Person inPerson)
	{
		Query query = new Query();
		query.addCriteria(new Criteria()
							.andOperator(Criteria.where("firstName").is(inPerson.getFirstName()),
									     Criteria.where("middleName").is(inPerson.getMiddleName()),
									     Criteria.where("lastName").is(inPerson.getLastName())));
		
		List<Person> people = mongoTemplate.find(query, Person.class);
		return people.get(0);
	}
	
	public List<Person> searchPeople(String name)
	{
		Query query = new Query();
		query.addCriteria(new Criteria()
				              .orOperator(Criteria.where("firstName").regex(name, "i"),
				            		      Criteria.where("middleName").regex(name, "i"),
				            		      Criteria.where("lastName").regex(name, "i")));
		
		List<Person> people = mongoTemplate.find(query, Person.class);
		return people;
	}
	
	public void removePerson(Person person)
	{
		Query idQuery = new Query();
		idQuery.addCriteria(Criteria.where("personId").is(person.getPersonId()));
		
		mongoTemplate.remove(idQuery, Person.class);
	}
	
	public void removePerson(String personId)
	{
		Query idQuery = new Query();
		idQuery.addCriteria(Criteria.where("personId").is(personId));
		
		mongoTemplate.remove(idQuery, Person.class);
	}
	
	public Person findAndModifyPerson(Person inPerson)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("personId").is(inPerson.getPersonId()));
		
		Update update = new Update();
		update.set("firstName", inPerson.getFirstName());
		update.set("middleName", inPerson.getMiddleName());
		update.set("lastName", inPerson.getLastName());
		update.set("suffix", inPerson.getSuffix());
		
		Person origPerson = mongoTemplate.findAndModify(query, update, Person.class);
		return origPerson;
	}
}
