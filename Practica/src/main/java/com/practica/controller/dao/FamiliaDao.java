package com.practica.controller.dao;

import com.practica.controller.dao.implement.AdapterDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.models.Familia;
import com.google.gson.Gson;

public class FamiliaDao extends AdapterDao<Familia>{
    private Familia familia;
    private LinkedList<Familia> listAll;
    private Gson g = new Gson();

    public FamiliaDao(){
        super(Familia.class);
    }

    public Familia getFamilia(){
        if (familia == null) {
            familia = new Familia();
        }
        return familia;
    }

    public void setFamilia(Familia familia){
        this.familia = familia;
    }

    public LinkedList<Familia> getListAll()throws Exception{
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll; 
    }

    public Boolean save()throws Exception{
        Integer id = listAll().getSize()+1;
        getFamilia().setIdFamilias(id);
        this.persist(this.familia);
        this.listAll = listAll();
        return true;
    }

    public String toJson(){
        return g.toJson(getFamilia());
    }

    public String getAllFamiliasJson()throws Exception{
        LinkedList<Familia> familias = getListAll();
        return g.toJson(familias); 
    }    

    public Familia getFamiliaById(Integer id)throws Exception{
        return get(id);
    }

    public String getFamiliaJsonById(Integer id)throws Exception{
        return g.toJson(getFamiliaById(id));
    }

    public void update(Integer index, Familia familia)throws Exception{
        LinkedList<Familia> list = listAll();
        list.set(index, familia);
        updateListFile(list);
    }

    public Boolean deleteFamiliaById(Integer id)throws Exception{
        this.delete(id - 1);
        LinkedList<Familia> list = listAll();
        for (int i = 0; i < list.getSize(); i++) {
            list.get(i).setIdFamilias(i + 1);
        }
        updateListFile(list);
        this.listAll = list;
        return true;
    }
}
