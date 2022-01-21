/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twilio.controller;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//class where the project base is scanned in order to be initialized 
@Configuration
@EnableWebMvc//esta listo para empezar el proyecto web
@ComponentScan(basePackages = "com.twilio.")//
public class InitConfiguration {
    
}
