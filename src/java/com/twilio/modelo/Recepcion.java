/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twilio.modelo;

/**
 *
 * @author Daniel
 */
public class Recepcion {
//clase que se utiliza para guardar  las preguntas que llegan desde twilio
    String pregunta, respuesta;

    public Recepcion() {
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    

}
