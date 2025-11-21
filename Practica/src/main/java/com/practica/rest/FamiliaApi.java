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
import com.practica.controller.dao.services.FamiliaServices;
import com.practica.controller.dao.services.HisotiralCrudServices;
import com.practica.controller.excepcion.ListEmptyException;
import com.practica.models.Familia;
import com.practica.models.TipoDeCrud;

@Path("/familia")
public class FamiliaApi {
    private static final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllFamilias() throws ListEmptyException {
        HashMap<String, Object> res = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();

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
    public Response getFamiliaById(@PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        HisotiralCrudServices hisotiralCrudServices = new HisotiralCrudServices();

        try {
            Familia familia = ps.getFamiliaById(id);
            if (familia != null) {
                res.put("status", "success");
                res.put("message", "Familia encontrada.");
                hisotiralCrudServices.registrarHistorial(TipoDeCrud.READ, "Familia leida:" + id);
                res.put("data", familia);
                return Response.ok(res).build();
            } else {
                res.put("status", "error");
                res.put("message", "Familia no encontrada.");
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
    @Path("/createFamilia")
    public Response createFamilia(String json) {
        HashMap<String, Object> res = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        HisotiralCrudServices hisotiralCrudServices = new HisotiralCrudServices();

        try {
            Familia familia = gson.fromJson(json, Familia.class);
            ps.setFamilia(familia);
            ps.save();
            hisotiralCrudServices.registrarHistorial(TipoDeCrud.CREATE, "Familia creada:" + familia.toString());
            res.put("status", "success");
            res.put("message", "Familia creada con éxito.");
            res.put("data", ps.toJson());
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error al crear la familia: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteFamiliaById(@PathParam("id") Integer id) {
        FamiliaServices ps = new FamiliaServices();
        HashMap<String, Object> res = new HashMap<>();
        HisotiralCrudServices hisotiralCrudServices = new HisotiralCrudServices();

        try {
            if (ps.deleteFamiliaById(id)) {
                res.put("status", "success");
                res.put("message", "Familia eliminada con éxito.");
                hisotiralCrudServices.registrarHistorial(TipoDeCrud.DELETE, "Familia eliminada" + id);
                return Response.ok(res).build();
            } else {
                res.put("status", "error");
                res.put("message", "No se pudo eliminar la familia.");
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFamilia(@PathParam("id") Integer id, Familia updatedFamilia) {
        FamiliaServices ps = new FamiliaServices();
        HashMap<String, Object> res = new HashMap<>();
        HisotiralCrudServices hisotiralCrudServices = new HisotiralCrudServices();

        try {
            Familia existingFamilia = ps.getFamiliaById(id);
            if (existingFamilia != null) {
                updatedFamilia.setIdFamilias(id);
                ps.update(id - 1, updatedFamilia);
                hisotiralCrudServices.registrarHistorial(TipoDeCrud.UPDATE, "Familia actualizada:" + updatedFamilia.toString());
                res.put("status", "success");
                res.put("message", "Familia actualizada con éxito.");
                return Response.ok(res).build();
            } else {
                res.put("status", "error");
                res.put("message", "Familia no encontrada.");
                return Response.status(Status.NOT_FOUND).entity(res).build();
            }
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
