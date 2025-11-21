package com.practica.controller.dao.implement;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

import com.practica.controller.tda.list.LinkedList;
import com.google.gson.Gson;

public class AdapterDao<T> implements InterfazDao<T>{
    private Class<T> clazz;
    protected Gson g;
    public String URL = "media/";

    public AdapterDao(Class<T> clazz) {
        this.clazz = clazz;
        this.g = new Gson();
    }

    public void persist(T object) throws Exception {
        LinkedList<T> list = listAll();
        list.add(object); 
        
        String info = "";
        try {
            info = g.toJson(list.toArray());
        } catch (Exception e) {
            throw new Exception("Error al convertir a JSON");
        }
        saveFile(info);
    }

    public void merge(Integer index, T object) throws Exception {
        LinkedList<T> list = listAll();
        list.set(index, object);

        String info = "";
        try {
            info = g.toJson(list.toArray());
        } catch (Exception e) {
            throw new Exception("Error al convertir a JSON");
        }
        saveFile(info);
    }

    public LinkedList<T> listAll() throws Exception {
        LinkedList<T> list = new LinkedList<>();
        try {
            String data = readFile();

            Type arrayType = Array.newInstance(clazz, 0).getClass();
            T[] arrayObjects = g.fromJson(data, arrayType);

            for (T obj : arrayObjects) {
                if (obj != null) {
                    list.add(obj);
                }
            }

        } catch (Exception e) {
            throw new Exception("Error al convertir a JSON");
        }
        return list;
    }

    public T get(Integer id) throws Exception {
        return listAll().get(id - 1);
    }

    private String readFile() throws Exception {
        File file = new File(URL + clazz.getSimpleName() + ".json");
        if (!file.exists()) {
            return "[]"; 
        }

        Scanner in = new Scanner(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }

    private void saveFile(String info) throws Exception {
        File dir = new File(URL);
        if (!dir.exists()) {
            dir.mkdirs(); 
        }

        File file = new File(URL + clazz.getSimpleName() + ".json");
        FileWriter f = new FileWriter(file);
        f.write(info);
        f.flush();
        f.close();
    }

    protected void updateListFile(LinkedList<T> list) throws Exception {
        String info = g.toJson(list.toArray());
        saveFile(info);  
    }

    public void update(Integer index, T object) throws Exception {
        LinkedList<T> list = listAll();
        
        try {
            list.set(index, object); 
            updateListFile(list);     
        } catch (Exception e) {
            throw new Exception("Error al actualizar el elemento: " + e.getMessage());
        }
    }
    
    public void delete(Integer index) throws Exception {
        LinkedList<T> list = listAll();
        
        try {
            list.delete(index);  
        } catch (Exception e) {
            throw new Exception("Error al eliminar el elemento: " + e.getMessage());
        }

        String info = "";
        try {
            info = g.toJson(list.toArray());
        } catch (Exception e) {
            throw new Exception("Error al convertir a JSON");
        }
        saveFile(info);
    }
}
