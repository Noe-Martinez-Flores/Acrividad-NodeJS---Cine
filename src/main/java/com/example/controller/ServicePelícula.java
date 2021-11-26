package com.example.controller;


import com.example.model.CategoríaDAO;
import com.example.model.Película;
import com.example.model.PelículaDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/pelicula")
public class ServicePelícula {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Película> getPelículas() {
        return new PelículaDAO().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Película getPelícula(@PathParam("id") int id) {
        return new PelículaDAO().findById(id);
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Película create(MultivaluedMap<String, String> formParams){
        int id = Integer.parseInt(formParams.get("id").get(0));
        if(new PelículaDAO().create(getParams(id, formParams), true))
            return new PelículaDAO().findById(id);
        return null;
    }

    @POST
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Película update(@PathParam("id") int id, MultivaluedMap<String, String> formParams){
        if(new PelículaDAO().create(getParams(id,formParams),false))
            return new PelículaDAO().findById(id);
        return null;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean delete(@PathParam("id") int id){

        return new PelículaDAO().delete(id);
    }


    private Película getParams(int id, MultivaluedMap<String, String> formParams){
        String Título = formParams.get("Título").get(0);
        String Descripción = formParams.get("Descripción").get(0);
        String Sinopsis = formParams.get("Sinopsis").get(0);
        int Rating = Integer.parseInt (formParams.get("Rating").get(0));
        String Fecha_de_Registro = formParams.get("Fecha_de_Registro").get(0);
        String Fecha_de_Actualización = formParams.get("Fecha_de_Actualización").get(0);
        int Estado = Integer.parseInt(formParams.get("Estado").get(0));
        int Categoría = Integer.parseInt(formParams.get("Categoría").get(0));
        Película película = new Película(id,Título,Descripción,Sinopsis,Rating,Fecha_de_Registro,Fecha_de_Actualización,Estado,Categoría);
        System.out.println(película);
        return película;
    }
}
