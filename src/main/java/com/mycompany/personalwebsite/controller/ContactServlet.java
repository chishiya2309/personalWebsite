package com.mycompany.personalwebsite.controller;

import com.google.gson.Gson;
import com.mycompany.personalwebsite.dao.MessageDAO;
import com.mycompany.personalwebsite.model.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ContactServlet", urlPatterns = {"/api/contact"})
public class ContactServlet extends HttpServlet {

    private MessageDAO messageDAO;

    @Override
    public void init() throws ServletException {
        messageDAO = new MessageDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set response type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        
        try {
            // Read JSON body from request
            BufferedReader reader = request.getReader();
            Message msg = gson.fromJson(reader, Message.class);
            
            // Validation
            if (msg == null || msg.getName() == null || msg.getEmail() == null || msg.getMessage() == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"success\": false, \"message\": \"Missing required fields\"}");
                return;
            }
            
            // Save to DB
            boolean success = messageDAO.saveMessage(msg);
            
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.print("{\"success\": true, \"message\": \"Message sent successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("{\"success\": false, \"message\": \"Database error\"}");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"success\": false, \"message\": \"Server error\"}");
        } finally {
            out.flush();
        }
    }
}

