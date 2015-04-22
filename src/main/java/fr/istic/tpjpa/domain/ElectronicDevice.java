package fr.istic.tpjpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ElectronicDevice")
public class ElectronicDevice extends SmartDevice {

	public ElectronicDevice(int conso, Home home) {
		super(conso, home);
		// TODO Auto-generated constructor stub
	}

}
