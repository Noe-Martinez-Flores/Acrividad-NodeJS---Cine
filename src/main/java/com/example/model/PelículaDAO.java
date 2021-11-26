package com.example.model;

import com.example.database.ConnectionMySQL;
import org.apache.tomcat.PeriodicEventListener;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.*;

public class PelículaDAO {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;

    public List<Película> findAll (){
        List<Película> listPelicula = new ArrayList<>();

        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM Película");
            rs = cstm.executeQuery();

            while (rs.next()){
                Película película = new Película();
                película.setId(rs.getInt("id"));
                película.setTítulo(rs.getString("Título"));
                película.setDescripción(rs.getString("Descripción"));
                película.setSinopsis(rs.getString("Sinopsis"));
                película.setRating(rs.getInt("Rating"));
                película.setFecha_de_Registro(rs.getString("Fecha_de_Registro"));
                película.setFecha_de_Actualización(rs.getString("Fecha_de_Actualización"));
                película.setEstado(rs.getInt("Estado"));
                película.setCategoría(rs.getInt("Categoría"));

                listPelicula.add(película);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return listPelicula;
    }

    public Película findById (int id){
        Película película = null;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT  * FROM Película WHERE id = ?");
            cstm.setInt(1,id);

            rs = cstm.executeQuery();

            if (rs.next()){
                película = new Película();
                película.setId(rs.getInt("id"));
                película.setTítulo(rs.getString("Título"));
                película.setDescripción(rs.getString("Descripción"));
                película.setSinopsis(rs.getString("Sinopsis"));
                película.setRating(rs.getInt("Rating"));
                película.setFecha_de_Registro(rs.getString("Fecha_de_Registro"));
                película.setFecha_de_Actualización(rs.getString("Fecha_de_Actualización"));
                película.setEstado(rs.getInt("Estado"));
                película.setCategoría(rs.getInt("Categoría"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return  película;
    }

    public boolean create (Película película, boolean isCraete){
        boolean flag = false;
        LocalDate fechaActual = LocalDate.now();
        String dia = ""+fechaActual;
        try {
            con = ConnectionMySQL.getConnection();
            if (isCraete){
                cstm = con.prepareCall("INSERT INTO Película (Título,Descripción,Sinopsis,Rating,Fecha_de_Registro,Categoría,Fecha_de_Actualización,Estado) VALUES (?,?,?,?,?,?,?,1)");
                cstm.setString(1, película.getTítulo());
                cstm.setString(2, película.getDescripción());
                cstm.setString(3,película.getSinopsis());
                cstm.setInt(4,película.getRating());
                cstm.setString(5,película.getFecha_de_Registro());
                cstm.setInt(6,película.getCategoría());
                cstm.setString(7,dia);

            }else{
                cstm = con.prepareCall("UPDATE Película SET Título = ?,Descripción = ?,Sinopsis = ?,Rating = ?,Fecha_de_Registro = ?,Categoría = ?,Fecha_de_Actualización = ?,Estado = 1 WHERE id = ?");
                cstm.setString(1, película.getTítulo());
                cstm.setString(2, película.getDescripción());
                cstm.setString(3,película.getSinopsis());
                cstm.setInt(4,película.getRating());
                cstm.setString(5,película.getFecha_de_Registro());
                cstm.setInt(6,película.getCategoría());
                cstm.setString(7,dia);
                cstm.setInt(8,película.getId());

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
            cstm = con.prepareCall("DELETE FROM Película WHERE id = ?");
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
