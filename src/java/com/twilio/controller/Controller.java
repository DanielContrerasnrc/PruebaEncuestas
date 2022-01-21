/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twilio.controller;


import com.twilio.modelo.Recepcion;
import com.twilio.modelo.datos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/config")
public class Controller {
/*Variables with the information for the database connection*/
    private final String url = "jdbc:postgresql://conseappipiconsedev-postgresqldbserver.postgres.database.azure.com:5432/ipi_db_prod?sslmode=require";
    private final String user = "conseappipiconsedevadm@conseappipiconsedev-postgresqldbserver";
    private final String password = "th3_b3s7_c0unc1l_n0ru3gu3";

    //method to make the connection to the database
    public Connection connect() {
        Connection conn = null;        
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
//        try {
//// We register the PostgreSQL driver
//// Registramos el driver de PostgresSQL
//            try {
//                Class.forName("org.postgresql.Driver");
//            } catch (ClassNotFoundException ex) {
//                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
//            }
//            Connection connection = null;
//// Database connect
//// Conectamos con la base de datos
//            connection = DriverManager.getConnection("jdbc:postgresql://conseappipiconsedev-postgresqldbserver.postgres.database.azure.com:5432/ipi_db_prod?user=conseappipiconsedevadm@conseappipiconsedev-postgresqldbserver&amp;password=th3_b3s7_c0unc1l_n0ru3gu3&amp;sslmode=disable&amp;ssl=true");
//
//            boolean valid = connection.isValid(50000);
//            System.out.println(valid ? "TEST OK" : "TEST FAIL");
//        } catch (java.sql.SQLException sqle) {
//            System.out.println("Error: " + sqle);
//        }
    
}

//  the methods are configured to be called by the url
@Autowired
        public com.twilio.controller.SessionPrueba prueba;


//this method gives us the system time and is used to know if the application is working.
    @RequestMapping(value = "/horas/", method = RequestMethod.GET)
        public ResponseEntity<String> obtenerHoraActual() {
        String hora = prueba.horaDelSistema();
        return new ResponseEntity<String>(hora, HttpStatus.OK);
    }

//this method receives the information from twilio which comes in a json and sends it to session test to input the answers.        
    @RequestMapping(value = "/Data/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<String> registroData(@RequestBody datos pru) {
         Connection conexion= connect();


        boolean datorespuesta = false;
        String respuesta = "";
        for (int i = 0; i < pru.getRecepcion().size(); i++) {
            System.out.println("contador en i "+i);
            String a = pru.getRecepcion().get(i).getPregunta();
            String b = pru.getRecepcion().get(i).getRespuesta();
            datorespuesta = prueba.IngresoData(conexion,a, b);
        }
        if (datorespuesta) {
             try {
                 conexion.close();
             } catch (SQLException ex) {
                 Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
             }
            respuesta = "Correcto";
        } else {
            try {
                 conexion.close();
             } catch (SQLException ex) {
                 Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
             }
            respuesta = "fallo";
        }

        return new ResponseEntity<String>(respuesta, HttpStatus.OK);
    }
}
