package com.example.model;

import javax.xml.bind.annotation.XmlElement;

public class Categoría {
    @XmlElement
    private int id;
    @XmlElement
    private String Nombre;

    public Categoría (){

    }

    public Categoría(int id, String nombre) {
        this.id = id;
        Nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categoría{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }
}
