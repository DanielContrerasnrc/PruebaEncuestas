/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twilio.controller;

//import com.twilio.dao.RepositorioPrueba;
//import com.twilio.modelo.Postgress;
//import com.twilio.modelo.SatisfactionSurvey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;

/**
 *
 * @author Daniel
 */
@Service
public class SessionPrueba {

    public SessionPrueba() {
    }

    public String horaDelSistema() {
        Calendar now = Calendar.getInstance();
        String hora = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
        System.out.println("" + hora);
        return hora;
    }

    //method for the insertion of the data that are sent from the controller of the method registroData
    boolean IngresoData(Connection conexion, String Pregunta, String Respuesta) {
        boolean a = false;
        int pre = Integer.parseInt(Pregunta);
        int res = Integer.parseInt(Respuesta);
        try {
            String query = " insert into satisfaction_survey (pregunta,respuesta) values ( ?, ?)";
// create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setInt(1, pre);
            preparedStmt.setInt(2, res);
// execute the preparedstatement
            preparedStmt.execute();
            preparedStmt.close();
//            conexion.close();
            a = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            a = false;
        }
        return a;
    }

}
