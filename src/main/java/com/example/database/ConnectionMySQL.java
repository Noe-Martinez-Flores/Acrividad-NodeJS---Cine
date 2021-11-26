package com.example.database;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;


public class ConnectionMySQL {
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Cine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");

    }

    public static void main(String[] args) {
        try{
            Connection con = ConnectionMySQL.getConnection();
            System.out.println("Conexion exitosa");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
