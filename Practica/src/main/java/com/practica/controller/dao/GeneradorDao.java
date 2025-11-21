package com.practica.controller.dao;

import com.google.gson.Gson;
import com.practica.controller.dao.implement.AdapterDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.models.Generador;
import com.practica.models.Uso;

public class GeneradorDao extends AdapterDao<Generador>{
    private Generador generador;
    private LinkedList<Generador> listAll;
    private Gson g = new Gson();

    public GeneradorDao(){
        super(Generador.class);
    }

    public Generador getGenerador() {
        if (generador == null) {
            generador = new Generador();
        }
        return generador;
    }

    public void setGenerador(Generador generador) {
        this.generador = generador;
    }

    public LinkedList<Generador> getListAll()throws Exception{
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save()throws Exception{
        Integer id = listAll().getSize()+1;
        getGenerador().setIdGenerador(id);
        this.persist(this.generador);
        this.listAll = listAll();
        return true;
    }

    public String toJson(){
        return g.toJson(getGenerador());
    }

    public String getAllGeneradorJson()throws Exception{
        LinkedList<Generador> generador = getListAll();
        return g.toJson(generador);
    }

    public Generador getGeneradorById(Integer id)throws Exception{
        return get(id);
    }

    public String getGeneradorJsonById(Integer id)throws Exception{
        return g.toJson(getGeneradorById(id));
    }

    public void update(Integer index, Generador generador)throws Exception{
        LinkedList<Generador> list = listAll();
        list.set(index, generador); 
        updateListFile(list); 
    }

    public Boolean deleteGeneradorById(Integer id) throws Exception {
        this.delete(id - 1);
        LinkedList<Generador> list = listAll();
        for (int i = 0; i < list.getSize(); i++) {
            list.get(i).setIdGenerador(i + 1);
        }
        updateListFile(list);
        this.listAll = list;
        
        return true;
    }

    public Uso getUso(String uso){
        return Uso.valueOf(uso);
    }

    public Uso[] getUso(){
        return Uso.values();
    }
}
