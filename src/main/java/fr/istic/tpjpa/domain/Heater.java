package fr.istic.tpjpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Heater")
public class Heater extends SmartDevice  {

	public Heater(int conso, Home home) {
		super(conso, home);
		// TODO Auto-generated constructor stub
	}

}
