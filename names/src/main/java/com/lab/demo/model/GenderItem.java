package com.lab.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenderItem
{
	private String name;
	private String gender;
	private int samples;
	private String accuracy;
	private String duration;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getSamples() {
		return samples;
	}
	public void setSamples(int samples) {
		this.samples = samples;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String toString()
	{
		String genderItem = "GenderItem{" +
				"name='" + name + '\'' +
				", gender='" + gender + '\'' +
				", samples='" + samples +
				", accuracy='" + accuracy + '\'' +
				", duration='" + duration + '\'' +
				'}';
		
		return genderItem;
	}
	
}
