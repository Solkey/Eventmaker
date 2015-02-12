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

/**
 *
 * @author Solkey
 */
public class EventSave extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");   
        Events e = new Events();
        e.setName(request.getParameter("name"));
        e.setDescription(request.getParameter("description"));
        e.setTime(request.getParameter("time"));
        e.setDate(request.getParameter("date"));
        e.setPlace(request.getParameter("place"));
        e.setCost(request.getParameter("cost"));
        e.setContacts(request.getParameter("contacts"));
        
        ut.begin();

        em.persist(e);
        //List<Events> List = em.createQuery("SELECT x FROM Events x").getResultList();
        ut.commit();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='Reg.css' rel='stylesheet' type='text/css'/>");
            out.println("<link href=\"css/bootstrap.css\" rel=\"stylesheet\">\n");
            out.println("<title>Eventmaker</title>");      
            out.println("<link href='index.css' rel='stylesheet' type='text/css'/>");
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
            out.println("<h1 align=\"center\">  Мероприятие "  + request.getParameter("name") + " успешно создано!" + "</h1>");
            //out.println("<h1>  "  + List + "</h1>");
            out.println("<form action=http://localhost:8080/Eventmaker/View>");
            out.println("</body>");
            out.println("</html>");
        } catch (NotSupportedException ex) {
            Logger.getLogger(EventSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(EventSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(EventSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(EventSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(EventSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(EventSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(EventSave.class.getName()).log(Level.SEVERE, null, ex);
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
