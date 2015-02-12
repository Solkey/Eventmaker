/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import model.Events;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Solkey
 */
    

public class View extends HttpServlet {

    @Resource
    UserTransaction ut;
    
    @PersistenceContext
    EntityManager em;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Events e = new Events();
            
            ut.begin();
        
            List<Events> List = em.createQuery("SELECT x FROM Events x").getResultList();
            
            ut.commit();
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Eventmaker</title>");
            out.println("<link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\">");
             out.println("<link href=\"view.css\" rel=\"stylesheet\">");
            out.println("<link href=\"css/bootstrap.css\" rel=\"stylesheet\">");
            //out.println("<h:outputStylesheet library="css" name="style.css"  />");
            out.println("</head>");
            out.println("<body>");
            out.println(" <div class=\"container\">\n" +
    "        <nav class=\"navbar\"  style=\"width:490px; height:30px;\">\n" +
    "        <div class=\"container-fluid\">\n" +
    "        <!-- Brand and toggle get grouped for better mobile display -->\n" +
    "        <div class=\"navbar-header\">\n" +
    "          <a class=\"navbar-brand\" href=\"Hello.html\">Eventmaker</a>\n" +
    "        </div>\n" +
    "   \n" +
    "    <!-- Collect the nav links, forms, and other content for toggling -->\n" +
    "        <div  class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n" +
    "        <ul class=\"nav navbar-nav\">\n" +
    "        <li><a href=\"Hello.html\">Главная</a></li>\n" +
    "        <li><a href=\"View\">Найти</a></li>\n" +
    "        <li><a href=\"createEvent.html\">Создать</a></li>\n" +
    "        <li><a href=\"index.html\">Подписка</a></li>\n" +
    "        </ul>\n" +
    "        <ul class=\"nav navbar-nav navbar-right\">\n" +
    "        </ul>\n" +
    "        </div><!-- /.navbar-collapse -->\n" +
    "       </div><!-- /.container-fluid -->\n" +
    "       </nav>");
            out.println("<form action=http://localhost:8080/Eventmaker/Hello.html>");
            out.println("<div class=\"well\">");
            out.println("<h1  align=\"center\" style=\"color: black;\">Набережные Челны</h1>");   
            out.println("</div>");
            //out.println("<img src=\"bg.jpg\" class=\"img\" >");
            for(Events i : List){
                out.println("<div class=\"well\">");
                out.println("<h1 align=\"center\">"+i.getName()+"</h1>");
                out.println("<h1 align=\"center\">"+i.getDescription()+"</h1>");
                out.println("<h1 align=\"center\">"+i.getDate()+"</h1>");
                out.println("<h1 align=\"center\">"+i.getTime()+"</h1>");
                out.println("<h1 align=\"center\">"+i.getPlace()+"</h1>");
                out.println("<h1 align=\"center\">"+i.getCost()+"</h1>");
                out.println("<h1 align=\"center\">"+i.getContacts()+"</h1>");
                out.println("</div>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (RollbackException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
