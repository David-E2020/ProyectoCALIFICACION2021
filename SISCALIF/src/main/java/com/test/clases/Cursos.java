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
public class Cursos implements Serializable {
    //atributos
    private int codCursos;
    private String nomCursos;
    //constructor
     public Cursos() {
    }

    //getter y setter

    public int getCodCursos() {
        return codCursos;
    }

    public void setCodCursos(int codCursos) {
        this.codCursos = codCursos;
    }

    public String getNomCursos() {
        return nomCursos;
    }

    public void setNomCursos(String nomCursos) {
        this.nomCursos = nomCursos;
    }

    
     
}
