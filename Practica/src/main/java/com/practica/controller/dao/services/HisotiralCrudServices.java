package com.practica.controller.dao.services;

import com.practica.controller.dao.HistorialCrudDao;
import com.practica.models.HistorialCrud;
import com.practica.models.TipoDeCrud;

public class HisotiralCrudServices {
    private HistorialCrudDao obj;

    public HisotiralCrudServices(){
        obj = new HistorialCrudDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean registrarHistorial(TipoDeCrud tipoDeCrud, String mensaje) throws Exception {
        return obj.registraHistorial(tipoDeCrud, mensaje);
    }

    public HistorialCrud[] listAll() throws Exception {
        return obj.getAllHistorialCrud();
    }

    public void setHistorialCrud(HistorialCrud historialCrud) {
        obj.setHistorialCrud(historialCrud);
    }

    public String toJson() throws Exception {
        return obj.toJson();
    }

    public HistorialCrud getHistorialCrud() {
        return obj.geHistorialCrud();
    }

    public HistorialCrud getHistorialCrudById(Integer id) throws Exception {
        return obj.getHistorialCrudById(id);
    }

    public String getHistorialCrudJsonById(Integer id) throws Exception {
        return obj.getHistorialCrudJsonById(id);
    }

    public HistorialCrud[] getListAll() {
        return obj.getListAll();
    }

    public void setListAll(HistorialCrud[] listAll) {
        obj.setListAll(listAll);
    }
}
