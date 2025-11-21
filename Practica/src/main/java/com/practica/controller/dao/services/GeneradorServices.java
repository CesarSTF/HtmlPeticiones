package com.practica.controller.dao.services;

import com.practica.controller.dao.GeneradorDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.models.Familia;
import com.practica.models.Generador;
import com.practica.models.Uso;

public class GeneradorServices {
    private GeneradorDao obj;
    public GeneradorServices(){
        this.obj = new GeneradorDao();
    }

    public Boolean save()throws Exception{
        return obj.save();
    }

    public LinkedList<Generador> listAll()throws Exception{
        return obj.getListAll();
    }

    public Generador getGenerador(){
        return obj.getGenerador();
    }

    public void setGenerador(Generador generador){
        obj.setGenerador(generador);
    }

    public String toJson(){
        return obj.toJson();
    }  

    public String getAllGeneradorToJson()throws Exception{
        return obj.getAllGeneradorJson();
    }

    public Generador getGeneradorById(Integer id)throws Exception{
        return obj.getGeneradorById(id);
    }

    public String getGeneradorJsonById(Integer id)throws Exception{
        return obj.getGeneradorJsonById(id);
    }

    public void update(Integer index, Generador generador)throws Exception{
        obj.update(index, generador);
    }

    public Boolean deleteGeneradorById(Integer id) throws Exception {
        return obj.deleteGeneradorById(id);
    }

    public Uso getUso(String uso){
        return Uso.valueOf(uso);
    }
    public Uso[] getUso(){
        return Uso.values();
    }
}
