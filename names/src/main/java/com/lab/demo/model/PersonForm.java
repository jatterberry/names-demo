package com.lab.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class PersonForm
{
	@Valid
	private List<Person> personList = new ArrayList<Person>();
	
	public PersonForm() {}
	
	public PersonForm(List<Person> personList)
	{
		this.personList = personList;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	
}
