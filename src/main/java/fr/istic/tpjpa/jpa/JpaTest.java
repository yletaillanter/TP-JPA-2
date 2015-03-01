package fr.istic.tpjpa.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import fr.istic.tpjpa.domain.SmartDevice;
import fr.istic.tpjpa.domain.Gender;
import fr.istic.tpjpa.domain.Heater;
import fr.istic.tpjpa.domain.Person;
import fr.istic.tpjpa.domain.Address;
import fr.istic.tpjpa.domain.Home;

public class JpaTest {

	private EntityManager manager;
	private CriteriaBuilder criteriaBuilder;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		// CREATE ENTITY
		Person yoann = new Person("Le Taillanter","Yoann",Gender.male,new Date());
		Person faustine = new Person("Beaumont","Faustine",Gender.female,new Date());
		Person faustineBol = new Person("Bollaert","Faustine",Gender.female,new Date());
		
		yoann.setFriends(new ArrayList<Person>(Arrays.asList(faustine)));
		
		Home yoannHome = new Home(
				new Address(1,"rue Jean Brulelou", 35700, "Rennes"),
				100,
				"192.168.0.10",
				yoann);
		
		Home yoannHome2 = new Home(
				new Address(2,"rue du Gué renard", 41250, "Bracieux"),
				100,
				"127.0.0.0",
				yoann);
		
		Home faustineHome1 = new Home(
				new Address(100,"Chemin de la matauderie", 86000, "Poitiers"),
				100,
				"172.168.100.0",
				faustine);
		
		SmartDevice chauffage = new Heater();
		chauffage.setConso(1000);
		chauffage.setHome(yoannHome);
		yoannHome.setDevices(new ArrayList<SmartDevice>(Arrays.asList(chauffage)));
		
		// PERSIST ENTITY
		manager.persist(faustineBol);		
		manager.persist(yoannHome);
		manager.persist(yoannHome2);
		manager.persist(faustineHome1);
		
		manager.persist(yoann);
		tx.commit();

		// RUN REQUEST
		
		test.listPerson();	
		test.listHome();
		test.getPersonNameFromFirstName("Faustine");

		System.out.println(".. done");
	}
	
	private void listPerson(){
		List<Person> resultList = manager.createQuery("Select a From Person a", Person.class).getResultList();
		System.out.println("Nombre de Personnes dans la base = " + resultList.size());
		for(Person next : resultList ){
			System.out.println("next person: " + next.toString());
		}
	}
	
	private void listHome(){
		List<Home> resultList = manager.createQuery("Select a From Home a", Home.class).getResultList();
		System.out.println("Nombre de Maisons dans la base = " + resultList.size());
		for(Home next : resultList ){
			System.out.println("next maison: " + next.toString());
		}
	}
	
	private void getPersonNameFromFirstName(String paramName){
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq= cb.createTupleQuery();

		Root<Person> root = cq.from(Person.class);
		Expression<String> name = root.get("name");
		Expression<String> firstName = root.get("firstName");
		
		cq.multiselect(name.alias("name"));
		cq.where(cb.equal(firstName, paramName));

		TypedQuery<Tuple> tq = manager.createQuery(cq);
		for (Tuple t : tq.getResultList()) {
		  System.out.println(t.get("name"));
		}
	}
	
}
