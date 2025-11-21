package com.practica.controller.dao.services;

import com.practica.controller.dao.FamiliaDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.models.Familia;

public class FamiliaServices {
    public FamiliaDao obj;
    public FamiliaServices(){
        this.obj = new FamiliaDao();
    }
    
    public Boolean save()throws Exception{
        return obj.save();
    }

    public LinkedList<Familia> listAll()throws Exception{
        return obj.getListAll();
    }

    public Familia getFamilia(){
        return obj.getFamilia();
    }

    public void setFamilia(Familia familia){
        obj.setFamilia(familia);
    }

    public String toJson(){
        return obj.toJson();
    }

    public String getAllFamiliasToJson()throws Exception{
        return obj.getAllFamiliasJson();
    }

    public Familia getFamiliaById(Integer id)throws Exception{
        return obj.getFamiliaById(id);
    }

    public String getFamiliaJsonById(Integer id)throws Exception{
        return obj.getFamiliaJsonById(id);
    }

    public void update(Integer index, Familia familia)throws Exception{
        obj.update(index, familia);
    }

    public Boolean deleteFamiliaById(Integer id)throws Exception{
        return obj.deleteFamiliaById(id);
    }
}
