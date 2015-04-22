package servlet;

import fr.istic.tpjpa.domain.*;
import fr.istic.tpjpa.jpa.JpaTest;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 14007427 on 27/01/2015.
 */
@WebServlet(name="dbinfo",
urlPatterns={"/dbinfo"})
public class DbInfo extends HttpServlet {

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
        
        Query query = manager.createQuery("select a from Home a");

        List<Home> resultList = query.getResultList();

        for(Home maison : resultList){
            pw.println(maison.toString());
        }
        tx.commit();
    }

    @Override
    public void destroy() {
        manager.close();
    }

}
