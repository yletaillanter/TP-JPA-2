package fr.istic.tpjpa.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Person {
	private Long id;
	private String name;
	private String firstName;
	private Gender gender;
	private String email;
	private Date birthDate;
	private String facebookProfil;
	
	private List<Home> homes;
	private List<Person> friends; 
	
	public Person(){		
	}
	
	public Person(String name, String firstName, Gender gender, Date birthdate){
		this.name = name;
		this.firstName = firstName;
		this.gender = gender;
		this.email = name + firstName + "@opower.com";
		this.birthDate = birthdate;
		this.facebookProfil = name + firstName;
		
		this.homes = new ArrayList<Home>();
		this.friends = new ArrayList<Person>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFacebookProfil() {
		return facebookProfil;
	}

	public void setFacebookProfil(String facebookProfil) {
		this.facebookProfil = facebookProfil;
	}

	@OneToMany( mappedBy = "owner", cascade = CascadeType.PERSIST)
	public List<Home> getHomes() {
		return homes;
	}

	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}

	@ManyToMany
	@JoinTable(
			name="Friends"
	)
	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	
	@Override
	public String toString(){
		return firstName +" "+ name;
	}
	
}
