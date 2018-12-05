/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Affan
 */
public class register extends HttpServlet {

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
           String username = request.getParameter("username");
           String email = request.getParameter("email");
           String contact = request.getParameter("contact");
           String password = request.getParameter("password");
           
           try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/info","root","root10");
                Statement stmnt = conn.createStatement();
                
                ResultSet rs = stmnt.executeQuery("select * from user where uname = '" + username + "'");
                //ResultSet rs = stmnt.executeQuery("select * from user");
                
                
                String check_user = "";
                String check_email = "";
                String check_contact = "";
                
                while(rs.next())
                {
                    check_user = rs.getString("uname");
                    check_email = rs.getString("email");
                    check_contact = rs.getString("contact");
                }
                
                if(username.equals(check_user))
                {
                    out.println("Username already exists.");
                }
                else
                {
                    stmnt.executeUpdate("insert into user(uname, contact, email, password) values('"+ username +"','"+ contact +"','"+ email +"','"+ password +"')");
                    out.println("Data Successfully Inserted About "+ username + "!");
                }
                
                
                /*if(username.equals(check_user))
                {
                    out.println("Username already exists!");
                }
                else
                {
                    if(email.equals(check_email))
                    {
                        out.println("A user already exists with this email.");
                    }
                    else
                    {
                        if(check_contact.equals(contact))
                        {
                            out.println("A user already exists with this contact number.");
                        }
                        else
                        {
                            stmnt.executeUpdate("insert into user(uname, contact, email, password) values('"+ username +"','"+ contact +"','"+ email +"','"+ password +"')");
                            out.println("Data Successfully Inserted About "+ username + "!");
                            
                            
                        }
                    }
                }*/
                
                //stmnt.executeUpdate("insert into user(uname, contact, email, password) values('"+ username +"','"+ contact +"','"+ email +"','"+ password +"')");
                //out.println("Data Successfully Inserted About "+ username + "!");
                TimeUnit.SECONDS.sleep(3);
                //response.sendRedirect("login.html");
      
                conn.close();
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }
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
