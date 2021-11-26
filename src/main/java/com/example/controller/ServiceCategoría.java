package com.example.controller;

import com.example.model.Categoría;
import com.example.model.CategoríaDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/categoria")
public class ServiceCategoría {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoría> getCategoría() {
        return new CategoríaDAO().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Categoría getCategoría(@PathParam("id") int id) {
        return new CategoríaDAO().findById(id);
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Categoría create(MultivaluedMap<String, String> formParams){
        int id = Integer.parseInt(formParams.get("id").get(0));
        if(new CategoríaDAO().create(getParams(id, formParams), true))
            return new CategoríaDAO().findById(id);
        return null;
    }

    @POST
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Categoría update(@PathParam("id") int id, MultivaluedMap<String, String> formParams){
        if(new CategoríaDAO().create(getParams(id,formParams),false))
            return new CategoríaDAO().findById(id);
        return null;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCategoría(@PathParam("id") int id){
        return new CategoríaDAO().delete(id);
    }


    private Categoría getParams(int id, MultivaluedMap<String, String> formParams){
        String Nombre = formParams.get("Nombre").get(0);

        Categoría   categoría  = new Categoría(id,Nombre);
        System.out.println(categoría);
        return categoría;
    }
}
