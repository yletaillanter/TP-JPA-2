package fr.istic.sir.rest;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.istic.tpjpa.domain.Heater;
import fr.istic.tpjpa.domain.Home;
import fr.istic.tpjpa.domain.Person;
import fr.istic.tpjpa.domain.SmartDevice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    
}
