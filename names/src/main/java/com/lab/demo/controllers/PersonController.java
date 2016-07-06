package com.lab.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lab.demo.model.GenderItem;
import com.lab.demo.model.GenderType;
import com.lab.demo.model.Person;
import com.lab.demo.model.PersonForm;
import com.lab.demo.repository.PersonMongoRepository;
import com.lab.demo.repository.PersonSearchRepository;

@Controller
public class PersonController
{
	@Autowired
	PersonMongoRepository personRepository;
	
	@Autowired
	PersonSearchRepository personSearchRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private GenderRestClient restClient = new GenderRestClient();
	
	@RequestMapping("/home")
	public String home(Model model)
	{
		ArrayList<Person> people = new ArrayList<Person>();
		Iterable<Person> pList = personRepository.findAll();
		for (Person p : pList)
		{
			logger.debug("Id: " + p.getPersonId());
			logger.debug("Last: " + p.getLastName());
			people.add(p);
		}
		
		PersonForm personForm = new PersonForm();
		personForm.setPersonList(people);
		model.addAttribute("personForm", personForm);
		
		return "home";
	}
	
	@RequestMapping(value = "/refresh", method=RequestMethod.POST)
	public String refreshPersons(Model model, @Valid @ModelAttribute PersonForm personForm)
	{
		ArrayList<Person> removedPersons = new ArrayList<Person>();
		
		logger.debug("Person count: " + personForm.getPersonList().size());
		List<Person> personList = personForm.getPersonList();
		
		if (personList != null)
		{
			for (Person p : personList)
			{
				Person existingPerson = personSearchRepository.findByPersonId(p.getPersonId());
				p.setPersonId(existingPerson.getPersonId());
				
				if (p.getDeleteChecked())
				{
					personSearchRepository.removePerson(existingPerson);
					removedPersons.add(p);
				}
				else
				{
					// If the first name changed, may change gender
					if (!existingPerson.getFirstName().equalsIgnoreCase(p.getFirstName()))
					{
						GenderItem item = restClient.getGenderInfo(p.getFirstName());
						p.setGender(GenderType.valueOf(item.getGender().toUpperCase()));
						p.setGenderConfidence(item.getAccuracy());						
					}
					
					Person origPerson = personSearchRepository.findAndModifyPerson(p);
					logger.debug("Orig PersonId: " + origPerson.getPersonId());
				}
			}
			
			// If there were people removed from the db, remove them from the model
			for (Person pe : removedPersons)
			{
				personList.remove(pe);
			}
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/addPerson", method = RequestMethod.POST)
	public String addPerson(@Valid @ModelAttribute Person person)
	{
		// Given the first name, find the probable gender
		GenderItem item = restClient.getGenderInfo(person.getFirstName());
		person.setGender(GenderType.valueOf(item.getGender().toUpperCase()));
		person.setGenderConfidence(item.getAccuracy());
		
		// Generate a new id and persist
		person.setPersonId(this.generatePersonId());		
		personRepository.save(person);
		return "redirect:home";
	}
	
	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam String searchParams)
	{
		List<Person> personMatches = personSearchRepository.searchPeople(searchParams);
		PersonForm personForm = new PersonForm();
		personForm.setPersonList(personMatches);
		
		model.addAttribute("personForm", personForm);
		model.addAttribute("searchParams", searchParams);
		
		return "home";
	}
	
	@RequestMapping(value = "/removePerson/{id}")
	public String removePerson(@PathVariable("id") String id)
	{
		logger.debug("Remove Id: " + id);
		personSearchRepository.removePerson(id);
		return "home";
	}
	
	private String generatePersonId()
	{
		UUID uuid = UUID.randomUUID();
		
		String personId = Long.toHexString(uuid.getMostSignificantBits())
			+ Long.toHexString(uuid.getLeastSignificantBits());
		
		return personId;
	}
	
}
