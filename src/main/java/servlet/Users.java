package servlet;

import fr.istic.tpjpa.domain.Person;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="users",
urlPatterns={"/Users"})
public class Users extends HttpServlet {
	
    private EntityManagerFactory factory;
    private EntityManager manager;
    private EntityTransaction tx;
    private PrintWriter pw;

    @Override
    public void init() throws ServletException {
       factory = Persistence.createEntityManagerFactory("example");
       manager = factory.createEntityManager();
       tx = manager.getTransaction();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        pw = resp.getWriter();
        tx.begin();
        //create();
        Query query = manager.createQuery("select a from Person a");

        List<Person> resultList = query.getResultList();

        for(Person pers : resultList){
            pw.println(pers.toString());
        }
        tx.commit();
    }
    
    @Override
    public void destroy() {
        manager.close();
    }
    
}
