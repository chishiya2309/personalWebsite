package com.mycompany.personalwebsite.controller;

import com.google.gson.Gson;
import com.mycompany.personalwebsite.dao.ProjectDAO;
import com.mycompany.personalwebsite.model.Project;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProjectServlet", urlPatterns = {"/api/projects"})
public class ProjectServlet extends HttpServlet {

    private ProjectDAO projectDAO;

    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Project> projects = projectDAO.getAllProjects();
        
        // Convert to JSON
        Gson gson = new Gson();
        String json = gson.toJson(projects);

        // Set response content type
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
        }
    }
}

