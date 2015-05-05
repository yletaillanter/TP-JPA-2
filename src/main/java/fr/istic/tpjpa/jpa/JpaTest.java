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

import fr.istic.tpjpa.domain.ElectronicDevice;
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
		
		// Creation des personnes
		List<Person> persons = new ArrayList<Person>();
		Person yoann = new Person("Le Taillanter","Yoann",Gender.male,new Date()); 
		persons.add(yoann);
		Person faustine = new Person("Beaumont","Faustine",Gender.female,new Date());
		persons.add(faustine);
		Person person1 = new Person("Forrest","Fry",Gender.male,new Date());
		persons.add(person1);
		Person person2 = new Person("Sebastian","Hahn",Gender.male,new Date());
		persons.add(person2);
		Person person3 = new Person("Todd","Baird",Gender.male,new Date());
		persons.add(person3);
		Person person4 = new Person("Beck","Aguirre",Gender.female,new Date());
		persons.add(person4);
		Person person5 = new Person("Herrod","Mueller",Gender.female,new Date());
		persons.add(person5);
		
		// Liste d'amis
		yoann.setFriends(new ArrayList<Person>(Arrays.asList(faustine,person1,person2)));
		faustine.setFriends(new ArrayList<Person>(Arrays.asList(yoann,person2,person3,person4)));
		person1.setFriends(new ArrayList<Person>(Arrays.asList(yoann)));
		person2.setFriends(new ArrayList<Person>(Arrays.asList(faustine,person4,person5)));
		person3.setFriends(new ArrayList<Person>(Arrays.asList(person4)));
		person4.setFriends(new ArrayList<Person>(Arrays.asList(person3,person5)));
		person5.setFriends(new ArrayList<Person>(Arrays.asList(person4)));

		
		// Ajout des maisons
		Home home;
		Person person;
		List homes = new ArrayList<Home>();
		for (int i = 0; i<7;i++){
			home = new Home(
					new Address(i,"Avenue du General Leclerc", 35700, "Rennes"),
					100,
					"192.168.0.10",
					persons.get(i)
			);
			homes.add(home);
		}
		
		//Creation des devices
		SmartDevice chauffage;
		SmartDevice tv;
		Home h;
		for (int i = 0; i<7;i++){
			//chauffage = new Heater(2000,(Home)homes.get(i));
			chauffage = new Heater();
			tv = new ElectronicDevice();
			h = (Home) homes.get(i);
			chauffage.setHome(h);
			tv.setHome(h);
			chauffage.setConso(2000);
			tv.setConso(60);
			h.setDevices(new ArrayList<SmartDevice>(Arrays.asList(chauffage)));
		}
		
		// PERSIST ENTITY
		for (int i = 0; i<7;i++){
			h = (Home) homes.get(i);
			manager.persist(h);
		}
		
		Person p;
		for (int i = 0; i<7;i++){
			p = (Person) persons.get(i);
			manager.persist(p);
		}

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
	
	//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	//simpleDateFormat.parse("06/12/1965")
	
}
