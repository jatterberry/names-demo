/**
 * 
 */
package com.lab.demo.model;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author joe
 * This person just has a name and a gender
 */
@Document(collection = "names")
public class Person
{
	private String personId;
	
    @Transient
    private UUID uuid = UUID.randomUUID();
    
    @NotNull
    @Size(min=1)
	private String firstName;
    
	private String middleName;
	
	@NotNull
	@Size(min=1)
	private String lastName;
	
	private String suffix;
	private GenderType genderType;
	private String genderConfidence;
	private Boolean deleteChecked = new Boolean("false");
	
	public Person()
	{
		//personId = Long.toHexString(uuid.getMostSignificantBits())
		//	+ Long.toHexString(uuid.getLeastSignificantBits());
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public GenderType getGender() {
		return genderType;
	}
	public void setGender(GenderType genderType) {
		this.genderType = genderType;
	}
	public String getPersonId() {
		return personId;
	}
	
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getGenderConfidence() {
		return genderConfidence;
	}
	public void setGenderConfidence(String genderConfidence) {
		this.genderConfidence = genderConfidence;
	}

	public Boolean getDeleteChecked() {
		return deleteChecked;
	}

	public void setDeleteChecked(Boolean deleteChecked) {
		this.deleteChecked = deleteChecked;
	}

}
