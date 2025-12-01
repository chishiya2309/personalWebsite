package com.mycompany.personalwebsite.dao;

import com.mycompany.personalwebsite.model.Message;
import com.mycompany.personalwebsite.util.ConnectionPool;
import com.mycompany.personalwebsite.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDAO {
    
    public boolean saveMessage(Message msg) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String sql = "INSERT INTO messages (name, email, message) VALUES (?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, msg.getName());
            ps.setString(2, msg.getEmail());
            ps.setString(3, msg.getMessage());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
