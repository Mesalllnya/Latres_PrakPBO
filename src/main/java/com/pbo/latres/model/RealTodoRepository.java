package com.pbo.latres.model;

import com.pbo.latres.dto.InsertTodoDTO;
import com.pbo.latres.Connection.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class RealTodoRepository implements TodoRepository {
        
    @Override
    public List<TodoTask> getAll() {
        List<TodoTask> tasks = new ArrayList<>();
        String query = "SELECT * FROM todos";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
             
            while (rs.next()) {
                tasks.add(new TodoTask(rs.getInt("id"), 
                        rs.getString("title"), 
                        rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public TodoTask getById(int id) {
        String query = "SELECT * FROM todos WHERE id = ?";
        try(Connection conn = DBConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                return new TodoTask(rs.getInt("id"), 
                        rs.getString("title"), 
                        rs.getString("status"));
            } 
        } catch (SQLException e) {
            e.printStackTrace();        
        }
        return null;
    }

    @Override
    public Boolean insert(InsertTodoDTO insertTodoDTO) {
        String query = "INSERT INTO todos (title, status) VALUES (?,?)";
        try(Connection conn = DBConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, insertTodoDTO.getTitle());
            pstmt.setString(2, insertTodoDTO.getStatus());
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(TodoTask todoTask) {
        String query = "UPDATE todos SET title = ?, status = ? WHERE id = ?";
        try(Connection conn = DBConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, todoTask.getTitle());
            pstmt.setString(2, todoTask.getStatus());
            pstmt.setInt(3, todoTask.getId());
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteById(int id) {
        String query = "DELETE FROM todos WHERE id = ?";
        try(Connection conn = DBConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id);
            return pstmt.executeUpdate()>0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}