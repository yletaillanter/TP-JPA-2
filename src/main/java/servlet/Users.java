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

        Query query = manager.createQuery("select a from Person a");

        List<Person> resultList = query.getResultList();

        pw.println(header);
        for(Person pers : resultList){
        	pw.println("<tr>");
        	pw.println("<td>"+pers.getFirstName()+"</td>");
        	pw.println("<td>"+pers.getName()+"</td>");
        	pw.println("<td>"+pers.getGender()+"</td>");
        	pw.println("<td>"+pers.getEmail()+"</td>");
        	pw.println("<td>"+pers.getFacebookProfil()+"</td>");
        	pw.println("</tr>");        	
        }
        pw.println(footer);
        tx.commit();
        
    }
    
    @Override
    public void destroy() {
        manager.close();
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
		      +"<li class='has-dropdown active'>"
		        +"<a href='#'>Personnes</a>"
		        +"<ul class='dropdown'>"
		          +"<li class='active'><a href='http://localhost:8080/Users'>Voir les personnes</a></li>"
		          +"<li><a href='http://localhost:8080/ajoutperson.html'>Ajouter une personne</a></li>"
		        +"</ul>"
		      +"</li>"
		      +"<li class='has-dropdown'>"
		        +"<a href='#'>Maisons</a>"
		        +"<ul class='dropdown'>"
		          +"<li><a href='http://localhost:8080/Homes'>Voir les maisons</a></li>"
		          +"<li><a href='http://localhost:8080/ajoutmaison.html'>Ajouter une maison</a></li>"
		        +"</ul>"
		      +"</li>"
		    +"</ul>"
		    +"</section>"
			+"</nav>"	
		    +"<table> <thead> <tr> <th>Nom</th> <th>Prenom</th> <th>Genre</th> <th >Email</th> <th >Facebook profil</th> </tr> </thead> <tbody>";
    
    String footer = "</tbody> </thead> </table>"
    			    
		    +"<script src='foundation-5/js/vendor/jquery.js'></script>"
		    +"<script src='foundation-5/js/foundation.min.js'></script>"
		    +"<script>"
		      +"$(document).foundation();"
		    +"</script>"
		  +"</body>"
		+"</html>";
}
