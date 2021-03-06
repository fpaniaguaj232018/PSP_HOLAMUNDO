/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernandopaniagua.controller;

import com.fernandopaniagua.exceptions.ConnectionNotEnabledException;
import com.fernandopaniagua.exceptions.DuplicateEmailException;
import com.fernandopaniagua.exceptions.UnknownException;
import com.fernandopaniagua.model.User;
import com.fernandopaniagua.model.UsersManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author fernando.paniagua
 */
public class CreadorUsuariosWS extends HttpServlet {

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
        //Recogida de info
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String ip = request.getRemoteAddr();
        //Llamada al business
        User nuevoUser = new User(email, password, name, surname, ip);
        UsersManager um = new UsersManager();
        //Creamos el JSON
        JSONObject jso = new JSONObject();
        try {
            um.addUser(nuevoUser);
            jso.put("code", "0");
            jso.put("mensaje", "El usuario se ha creado satisfactoriamente");
            //0. Ha ido bien
        } catch (ConnectionNotEnabledException ex) {
            //-2. Base de datos no disponible
            jso.put("code", "-2");
            jso.put("mensaje", "La conexión con la base de datos no está disponible");
        } catch (DuplicateEmailException ex) {
            //-1. Clave duplicada
            jso.put("code", "-1");
            jso.put("mensaje", "La dirección de correo ya existe");
        } catch (UnknownException ex) {
            //-3. Error desconocido
            jso.put("code", "-3");
            jso.put("mensaje", "Ha ocurrido un error imprevisto");
        }
        //ESCRIBIMOS EL JSON EN EL STREAM DE SALIDA
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(jso.toJSONString());
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
