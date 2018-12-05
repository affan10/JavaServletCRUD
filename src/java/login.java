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
import java.sql.Statement;
import java.sql.ResultSet;
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
public class login extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            
            String status = "";
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/info","root","root10");
                Statement stmnt = conn.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from user where uname = '" + username + "'");
                
                String pass = "";
                String uname = "";
                
                while(rs.next())
                {
                    pass = rs.getString("password");
                    uname = rs.getString("uname");
                    out.println(pass);
                }
                
                out.println("Here");
                //java.sql.ResultSet rs = stmnt.executeQuery("select * from user where uname = '" + username + "'"); 

                if(username.equals(uname))
                {
                    if(password.equals(pass))
                    {
                        out.println("You've Successfully Logged-in " + uname + "!");
                    }
                    else
                    {
                        out.println("Incorrect password, Re-enter!");
                        //TimeUnit.SECONDS.sleep(3);
                        //response.sendRedirect("login.html");
                    }
                }
                else
                {
                    out.println("Incorrect Username, Re-enter!");
                    //TimeUnit.SECONDS.sleep(3);
                    //response.sendRedirect("login.html");
                }
                
                
                
                /*String checkUser = rs.getString("uname");
                String checkPass = rs.getString("password");
                
                out.println(checkUser);
                out.println(checkPass);
                
                if (checkUser.equals(username) && checkPass.equals(password)) {
                    status = "True";
                    out.println("You've successfully logged-in to your account!");
                    
                } else {
                    status = "False";
                    out.println("Incorrect username or password! Re-enter!");
                    response.sendRedirect("login.html");
                }
                */
                conn.close();
                  
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            } /*catch (InterruptedException ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
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
