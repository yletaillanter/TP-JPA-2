package fr.istic.sir.rest;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import fr.istic.tpjpa.domain.Gender;
import fr.istic.tpjpa.domain.Heater;
import fr.istic.tpjpa.domain.Home;
import fr.istic.tpjpa.domain.Person;
import fr.istic.tpjpa.domain.SmartDevice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/hello")
public class SampleWebService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello, how are you?";
    }
    
    @GET
    @Path("/persons")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Person> getPersons(){
    	List<Person> resultList = new ArrayList<Person>();
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Query query = manager.createQuery("select a from Person a");
        tx.commit();
        return resultList = query.getResultList();
    }
    
    
    @GET
    @Path("/person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") Long idPerson) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        
    	tx.begin();
    	Query query = manager.createQuery("select a from Person a where id = :id");
        query.setParameter("id", idPerson);
        List<Person> results = query.getResultList();
        tx.commit();

        return results.get(0);
    }
    
    @GET
    @Path("/HomeByOwner/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Home> getHomeByOwner(@PathParam("id") Long idPerson){
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        
    	tx.begin();
    	Query query = manager.createQuery("select a from Home a where OWNER_ID = :id");
        query.setParameter("id", idPerson);
        List<Home> results = query.getResultList();
        tx.commit();

        return results;
    }
    
    @GET
    @Path("/Homes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Home> getHomes(){
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        
    	tx.begin();
    	Query query = manager.createQuery("select a from Home a");
        tx.commit();

        return query.getResultList();
    }
    
    @POST
    @Path("/newPerson")
    public JSONObject newPerson(@FormParam("name") String name, @FormParam("firstName") String firstName) {
    
    	// test return JSON pour erreur angular
    	JSONObject jsonObj = new JSONObject();
    	
    	if( !name.equals(null) &&  !firstName.equals(null)){
    		try {
    			jsonObj.put("name", name);
    			jsonObj.put("firstName", firstName);
    		} catch (JSONException e) {
    			e.printStackTrace();
    		}

        	// Creation d l'entity manager
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
            EntityManager manager = factory.createEntityManager();
            EntityTransaction tx = manager.getTransaction();
            
        	// Creation de la nouvelle personnne
            Person personToSave = new Person(name,firstName,Gender.male,new Date());
            // Enregistrement dans la db
           	tx.begin();
           	manager.persist(personToSave);
           	tx.commit();
    	}
    	return jsonObj;
    }
    
    //Test object JSON param 
    @POST
	@Path("/insererpersonne")
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertPerson(Person person){
    	
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();
		manager.persist(person);		
		tx.commit();
		
		//return Response.status(200).entity(true).build();
	}
    
}
