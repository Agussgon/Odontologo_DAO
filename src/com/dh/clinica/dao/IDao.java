package com.dh.clinica.dao;

import java.util.List;

public interface IDao <T>{

    public T guardar(T t);
    public List<T> listarProfesionales();
    public T buscar(Integer id);


}
