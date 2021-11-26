package com.example.model;

import javax.xml.bind.annotation.XmlElement;

public class Película {
    @XmlElement
    private int id;
    @XmlElement
    private String Título;
    @XmlElement
    private String Descripción;
    @XmlElement
    private String Sinopsis;
    @XmlElement
    private int Rating;
    @XmlElement
    private String Fecha_de_Registro;
    @XmlElement
    private String  Fecha_de_Actualización;
    @XmlElement
    private int Estado;
    @XmlElement
    private int Categoría;

    public Película (){

    }

    public Película(int id, String título, String descripción, String sinopsis, int rating, String fecha_de_Registro, String fecha_de_Actualización, int estado, int categoría) {

        this.id = id;
        Título = título;
        Descripción = descripción;
        Sinopsis = sinopsis;
        Rating = rating;
        Fecha_de_Registro = fecha_de_Registro;
        Fecha_de_Actualización = fecha_de_Actualización;
        Estado = estado;
        Categoría = categoría;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTítulo() {
        return Título;
    }

    public void setTítulo(String título) {
        Título = título;
    }

    public String getDescripción() {
        return Descripción;
    }

    public void setDescripción(String descripción) {
        Descripción = descripción;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        Sinopsis = sinopsis;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getFecha_de_Registro() {
        return Fecha_de_Registro;
    }

    public void setFecha_de_Registro(String fecha_de_Registro) {
        Fecha_de_Registro = fecha_de_Registro;
    }

    public String getFecha_de_Actualización() {
        return Fecha_de_Actualización;
    }

    public void setFecha_de_Actualización(String fecha_de_Actualización) {
        Fecha_de_Actualización = fecha_de_Actualización;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public int getCategoría() {
        return Categoría;
    }

    public void setCategoría(int categoría) {
        Categoría = categoría;
    }

    @Override
    public String toString() {
        return "Película{" +
                "id=" + id +
                ", Título='" + Título + '\'' +
                ", Descripción='" + Descripción + '\'' +
                ", Sinopsis='" + Sinopsis + '\'' +
                ", Rating=" + Rating +
                ", Fecha_de_Registro='" + Fecha_de_Registro + '\'' +
                ", Fecha_de_Actualización='" + Fecha_de_Actualización + '\'' +
                ", Estado=" + Estado +
                ", Categoría=" + Categoría +
                '}';
    }
}
