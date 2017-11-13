package tn.esprit.RefugeeCampProject.Entities.AdoptionManagment;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;

@Entity
public class Orphan implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private String nationality;
	private String educationLevel;
	private String activities;
	
	
	@ManyToOne
	private Member member;
	
	

	public Orphan() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public Orphan(String firstName, String lastName, String gender, int age, String nationality, String educationLevel,
			String activities) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.nationality = nationality;
		this.educationLevel = educationLevel;
		this.activities = activities;
	}

	public Orphan(int orphanIdToBeUpdated, String firstName, String lastName, String gender, int age, String nationality, String educationLevel,
			String activities) {
		super();
		this.id=orphanIdToBeUpdated;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.nationality = nationality;
		this.educationLevel = educationLevel;
		this.activities = activities;
	}
	
	

}
