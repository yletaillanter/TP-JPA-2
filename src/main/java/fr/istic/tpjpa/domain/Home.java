package fr.istic.tpjpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Home {
	private Long id;
	private Address address;
	private float area;
	private String ip;
	private Person owner;
	
	private List<Device> devices;
	
	
	
	public Home(){
		
	}
	
	public Home(Address address, float area, String ip, Person owner){
		this.address = address;
		this.area = area;
		this.ip = ip;
		this.owner = owner;
		this.devices = new ArrayList<Device>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Embedded
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	@OneToMany(mappedBy = "home", cascade = CascadeType.PERSIST)
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
	@Override
	public String toString(){
		return address.getNumber()
				+" "+address.getStreet()
			    +" "+address.getZipCode()
			    +" "+address.getCity();
	}
	
}
