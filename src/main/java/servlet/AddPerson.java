package servlet;

import fr.istic.tpjpa.domain.Gender;
import fr.istic.tpjpa.domain.Home;
import fr.istic.tpjpa.domain.Person;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by 14007427 on 28/01/2015.
 */
@WebServlet(name="addperson",
urlPatterns={"/AddPerson"})
public class AddPerson extends HttpServlet {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        pw = resp.getWriter();
        tx.begin();
        
        Person person;
        if(req.getParameter("gender").equals("M")) {
            person = new Person(req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    Gender.male,
                    new Date()
            );
        }
        else{
            person = new Person(req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    Gender.female,
                    new Date()
            );
        }

        //manager.persist(person);
        pw.println("Person added : "+req.getParameter("firstName")+" "+req.getParameter("lastName"));

        tx.commit();
        //(java.util.Date)req.getParameter("birthday");

    }
}
