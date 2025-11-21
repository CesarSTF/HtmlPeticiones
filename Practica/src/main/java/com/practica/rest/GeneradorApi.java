package com.practica.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.practica.controller.dao.services.GeneradorServices;
import com.practica.controller.dao.services.GeneradorServices;
import com.practica.controller.excepcion.ListEmptyException;
import com.practica.models.Familia;
import com.practica.models.Generador;
import com.practica.models.TipoDeCrud;
import com.practica.controller.dao.services.HisotiralCrudServices;

@Path("/generador")
public class GeneradorApi {
    private static final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response gettAllGenerador() throws ListEmptyException{
        HashMap<String, Object> res = new HashMap<>();
        GeneradorServices ps = new GeneradorServices();
        
        try {
            res.put("status", "success");
            res.put("message", "Consulta realizada con éxito.");
            res.put("data", ps.listAll().toArray());
            return Response.ok(res).build();            
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getGeneradorById(@PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        GeneradorServices ps = new GeneradorServices();
        HisotiralCrudServices hisotiralCrudServices = new HisotiralCrudServices();
        
        try {
            Generador generador = ps.getGeneradorById(id);
            if (generador != null) {
                res.put("status", "success");
                res.put("message", "Generador encontrado.");
                hisotiralCrudServices.registrarHistorial(TipoDeCrud.READ, "Generador liedo:" + generador.toString());
                res.put("data", generador);
                return Response.ok(res).build();
            } else {
                res.put("status", "error");
                res.put("message", "Generador no encontrado.");
                return Response.status(Status.NOT_FOUND).entity(res).build();
            }
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createGenerador")
    public Response createGenerador(String json) {
        HashMap<String, Object> res = new HashMap<>();
        GeneradorServices ps = new GeneradorServices();
        HisotiralCrudServices hisotiralCrudServices = new HisotiralCrudServices();

        try {
            Generador generador = gson.fromJson(json, Generador.class);
            ps.setGenerador(generador);
            ps.save();
            hisotiralCrudServices.registrarHistorial(TipoDeCrud.CREATE, "Generador creado" + generador.toString());
            res.put("status", "success");
            res.put("message", "Generador creado con éxito.");
            res.put("data", ps.toJson());
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error al crear el generador: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteGeneradorById(@PathParam("id") Integer id) {
        GeneradorServices ps = new GeneradorServices();
        HashMap<String, Object> res = new HashMap<>();
        HisotiralCrudServices hisotiralCrudServices = new HisotiralCrudServices();

        try {
            if (ps.deleteGeneradorById(id)) {
                res.put("status", "success");
                res.put("message", "Generador eliminado correctamente.");
                hisotiralCrudServices.registrarHistorial(TipoDeCrud.DELETE, "Generador eliminado:" + id);
                return Response.ok(res).build();
            } else {
                res.put("status", "error");
                res.put("message", "No se pudo eliminar el generador.");
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
            }
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGenerador(@PathParam("id") Integer id, Generador updateGenerador) {
        GeneradorServices ps = new GeneradorServices();
        HashMap<String, Object> res = new HashMap<>();
        HisotiralCrudServices hisotiralCrudServices = new HisotiralCrudServices();

        try {
            Generador existingGenerador = ps.getGeneradorById(id);
            if (existingGenerador != null) {
                updateGenerador.setIdGenerador(id); 
                ps.update(id - 1, updateGenerador);
                hisotiralCrudServices.registrarHistorial(TipoDeCrud.UPDATE, "Generado actualizado:" + updateGenerador.toString());
                res.put("status", "success");
                res.put("message", "Generador actualizado correctamente.");
                return Response.ok(res).build();
            } else {
                res.put("status", "error");
                res.put("message", "Generador no encontrado.");
                return Response.status(Status.NOT_FOUND).entity(res).build();
            }
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
