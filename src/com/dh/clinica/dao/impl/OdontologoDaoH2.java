package com.dh.clinica.dao.impl;

import com.dh.clinica.dao.IDao;
import com.dh.clinica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {


    final static Logger log = Logger.getLogger(OdontologoDaoH2.class);


    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(matricula,nombre,apellido) VALUES(?,?,?)");
            preparedStatement.setInt(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            preparedStatement.close();
 //ver si no tira error por los id autogenerados
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        log.info("dando de alta en el sistema un nuevo odontologo: "+odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarProfesionales() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT *  FROM odontologos");
            ResultSet listaOdontologos = preparedStatement.executeQuery();

            while (listaOdontologos.next()) {
                int idOdontologo = listaOdontologos.getInt("id");
                int matricula = listaOdontologos.getInt("matricula");
                String nombre = listaOdontologos.getString("nombre");
                String apellido = listaOdontologos.getString("apellido");

                Odontologo odontologo = new Odontologo(idOdontologo,matricula,nombre,apellido);
                odontologos.add(odontologo);
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        log.info("Lista de profesionales regsitrados en el sistema: "+ odontologos);
        return odontologos;

    }


    @Override
    public Odontologo buscar(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT id,matricula,nombre,apellido FROM odontologos where id = ?");
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int idOdontologo = result.getInt("id");
                int matricula = result.getInt("matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");

                odontologo= new Odontologo(idOdontologo,matricula,nombre,apellido );
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        log.info("Buscando odontologo por id " + odontologo);
        return odontologo;
    }
}
