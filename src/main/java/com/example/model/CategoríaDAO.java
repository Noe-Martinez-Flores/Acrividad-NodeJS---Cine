package com.example.model;

import com.example.database.ConnectionMySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoríaDAO {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;

    public List<Categoría> findAll (){
        List<Categoría> listCategoría = new ArrayList<>();

        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM Categoría");
            rs = cstm.executeQuery();

            while (rs.next()){
                Categoría categoría = new Categoría();
                categoría.setId(rs.getInt("id"));
                categoría.setNombre(rs.getString("Nombre"));

                listCategoría.add(categoría);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return listCategoría;
    }

    public Categoría findById (int id){
        Categoría categoría = null;
            try {
                con = ConnectionMySQL.getConnection();
                cstm = con.prepareCall("SELECT  * FROM Categoría WHERE id = ?");
                cstm.setInt(1,id);

                rs = cstm.executeQuery();

                if (rs.next()){
                    categoría = new Categoría();
                    categoría.setId(rs.getInt("id"));
                    categoría.setNombre(rs.getString("Nombre"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                closeConnection();
            }
        return  categoría;
    }

    public boolean create (Categoría categoría, boolean isCraete){
        boolean flag = false;

        try {
            con = ConnectionMySQL.getConnection();
            if (isCraete){
                cstm = con.prepareCall("INSERT INTO Categoría (Nombre) VALUES (?)");
                cstm.setString(1,categoría.getNombre());
            }else{
                cstm = con.prepareCall("UPDATE Categoría SET Nombre = ? WHERE id = ?");
                cstm.setString(1,categoría.getNombre());
                cstm.setInt(2,categoría.getId());
            }

            flag = cstm.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return flag;
    }

    public boolean delete (int id){
        boolean flag= false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("DELETE FROM Categoría WHERE id = ?");
            cstm.setInt(1,id);

            flag = cstm.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return flag;
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            if (cstm != null) {
                cstm.close();
            }
            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
