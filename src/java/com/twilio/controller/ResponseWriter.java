/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twilio.controller;

/**
 *
 * @author Daniel
 */
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseWriter {
    public void writeIn(HttpServletResponse response, String xml) throws IOException {
        response.setContentType("application/xml");
        response.getWriter().write(xml);
    }
}
