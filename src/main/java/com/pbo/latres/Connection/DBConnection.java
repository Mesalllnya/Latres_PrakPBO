/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.latres.Connection;

/**
 *
 * @author Asus
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/latres_pbo";
    private static final String USER = "root"; 
    private static final String PASSWORD = "";
    
    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(Exception e){
            System.out.println("Koneksi ke database gagal: " + e.getMessage());
        }
        return conn;
    }
}
