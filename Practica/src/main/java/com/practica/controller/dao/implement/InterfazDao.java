package com.practica.controller.dao.implement;
import com.practica.controller.tda.list.LinkedList;

public interface InterfazDao <T>{
    public void persist(T object) throws Exception;
    public void merge(Integer Index, T object) throws Exception;
    public LinkedList<T> listAll() throws Exception;
    public T get(Integer id) throws Exception;
    public void delete(Integer index) throws Exception;
    public void update(Integer index, T object) throws Exception; 
}
