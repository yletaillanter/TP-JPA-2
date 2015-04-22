package servlet;

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

import fr.istic.tpjpa.domain.Home;

@WebServlet(name="homes",
urlPatterns={"/Homes"})
public class Homes extends HttpServlet {
	
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
        Query query = manager.createQuery("select a from Home a");

        List<Home> resultList = query.getResultList();

        for(Home home : resultList){
            pw.println(home.toString());
            
        }
        tx.commit();
    }
    
    @Override
    public void destroy() {
        manager.close();
    }
    
}
