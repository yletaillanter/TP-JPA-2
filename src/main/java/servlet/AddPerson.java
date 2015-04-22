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

        manager.persist(person);
        
        
        pw.println(header);
        pw.println("<script>");  
        pw.println("alert('Person added')");  // "+req.getParameter("firstName")+" "+req.getParameter("lastName"));  
        pw.println("window.location = 'http://localhost:8080/Users'");
        pw.println("</script>");
        pw.println(footer);
        
        tx.commit();
    }
    
    // PAGE HTML
    String header = "<!doctype html>"
		+"<html class='no-js' lang='en'>"
		+"<head>"
		+"<meta charset='utf-8' />"
		+"<meta name='viewport' content=width=device-width, initial-scale=1.0' />"
		+"<title>Poweoo | Welcome</title>"
		+"<link rel='stylesheet' href='foundation-5/css/foundation.css' />"
		+"<script src='foundation-5/js/vendor/modernizr.js'></script>"
		+"</head>"
		+"<body>"
		+"<nav class='top-bar' data-topbar role='navigation'>"
		+"<ul class='title-area'>"
		+"<li class='name'>"
		+"<h1><a href='http://localhost:8O80/'>Poweoo</a></h1>"
		+"</li>"
		+"</ul>"
		+"<section class='top-bar-section'>"
		+"<!-- Left Nav Section -->"
		    +"<ul class='left'>"
		      +"<li><a href='http://localhost:8080/'>Accueil</a></li>"
		      +"<li class='has-dropdown'>"
		        +"<a href='#'>Personnes</a>"
		        +"<ul class='dropdown'>"
		          +"<li><a href='http://localhost:8080/Users'>Voir les personnes</a></li>"
		          +"<li><a href='http://localhost:8080/ajoutperson.html'>Ajouter une personne</a></li>"
		        +"</ul>"
		      +"</li>"
		      +"<li class='has-dropdown active'>"
		        +"<a href='#'>Maisons</a>"
		        +"<ul class='dropdown'>"
		          +"<li class='active'><a href='http://localhost:8080/Homes'>Voir les maisons</a></li>"
		          +"<li><a href='#'>Ajouter une maison</a></li>"
		        +"</ul>"
		      +"</li>"
		    +"</ul>"
		    +"</section>"
			+"</nav>";
    
    String footer ="<script src='foundation-5/js/vendor/jquery.js'></script>"
		    +"<script src='foundation-5/js/foundation.min.js'></script>"
		    +"<script>"
		      +"$(document).foundation();"
		    +"</script>"
		  +"</body>"
		+"</html>";
}
