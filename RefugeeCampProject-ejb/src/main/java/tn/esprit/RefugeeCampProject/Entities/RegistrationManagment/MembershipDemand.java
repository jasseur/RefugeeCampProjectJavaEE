package tn.esprit.RefugeeCampProject.Entities.RegistrationManagment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import tn.esprit.RefugeeCampProject.Types.Gender;
import tn.esprit.RefugeeCampProject.Types.Role;

@Entity
public class MembershipDemand implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String login;
	private String email;
	private String password;
	
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Date registrationDate;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String description;
	private boolean accepted;
	
	@ManyToOne
	Member member;



	public MembershipDemand() {
		super();
	}

	public MembershipDemand(String login, String email, String password, String firstName, String lastName,
			Date birthDate, Date registrationDate, Gender gender, Role role, String description, boolean accepted) {
		super();
		this.login = login;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.registrationDate = registrationDate;
		this.gender = gender;
		this.role = role;
		this.description = description;
		this.accepted = accepted;
	}

	public MembershipDemand(int id, String login, String email, String password, String firstName, String lastName,
			Date birthDate, Date registrationDate, Gender gender, Role role, String description, boolean accepted) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.registrationDate = registrationDate;
		this.gender = gender;
		this.role = role;
		this.description = description;
		this.accepted = accepted;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

}
