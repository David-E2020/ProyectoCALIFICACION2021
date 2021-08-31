/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.clases;

import java.io.Serializable;

/**
 *POJO de la tabla categoria para modificar los campos de un determinado registro
 * @author FERMIN
 */
public class EstudianteGrado implements Serializable {
    //atributos
    //private int codGrado;
    private String nomGrado;
    private String nomCurso;
    //constructor
     public EstudianteGrado() {
    }

    //getter y setter

    public String getNomGrado() {
        return nomGrado;
    }

    public void setNomGrado(String nomGrado) {
        this.nomGrado = nomGrado;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }
     


    
    
     
}
