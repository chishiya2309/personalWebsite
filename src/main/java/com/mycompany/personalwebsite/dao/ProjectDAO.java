package com.mycompany.personalwebsite.dao;

import com.mycompany.personalwebsite.model.Project;
import com.mycompany.personalwebsite.util.ConnectionPool;
import com.mycompany.personalwebsite.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM projects ORDER BY week ASC";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Project p = new Project();
                p.setId(rs.getInt("id"));
                p.setWeek(rs.getInt("week"));
                p.setTitle(rs.getString("title"));
                p.setDescription(rs.getString("description"));
                p.setTechStack(rs.getString("tech_stack"));
                p.setDemoLink(rs.getString("demo_link"));
                p.setRepoLink(rs.getString("repo_link"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setCreatedAt(rs.getDate("created_at"));
                projects.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return projects;
    }
}
