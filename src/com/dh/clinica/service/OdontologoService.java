package com.dh.clinica.service;

import com.dh.clinica.dao.IDao;
import com.dh.clinica.model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo guardar(Odontologo odontologo){
        return odontologoDao.guardar(odontologo);

    }

    public List<Odontologo> listarProfesionales(){
        return odontologoDao.listarProfesionales();
    }

    public Odontologo buscar(Integer id){
        return odontologoDao.buscar(id);
    }

}
