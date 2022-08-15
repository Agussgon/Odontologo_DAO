package test.com.dh.clinica.service;

import com.dh.clinica.dao.impl.OdontologoDaoH2;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

class OdontologoServiceTest {

    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    final static Logger log = Logger.getLogger(OdontologoDaoH2.class);


    @Test
    public void guardar() {

      Odontologo odontologo= odontologoService.guardar (new Odontologo(123007,"Ali","Perez"));

      log.debug("Guardando odontologo: "+ odontologo);
      Assert.assertTrue(odontologoService.buscar(1) != null);

    }


    @Test
    public void listarProfesionales() {

        Odontologo odontologo1= odontologoService.guardar( new Odontologo(123004,"Juan","Perez"));
        Odontologo odontologo2= odontologoService.guardar(new Odontologo(123204,"Luis","Rodriguez"));
        Odontologo odontologo3= odontologoService.guardar(new Odontologo(123504,"Florencia","Martinez"));

        List<Odontologo> odontologos = odontologoService.listarProfesionales();

        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() > 0);
        log.debug("Listando odontologos "+odontologos);

    }

}