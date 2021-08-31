/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.clases;

import java.io.Serializable;

/**
 *
 * @author FERMIN
 */

public class Estudiante implements Serializable {
    //atributos
    private int idEstudiante;
    private int idGrado;
    private int idCurso;
    private String nomEstudiante;
    private String apEstudiante;
    private int ciEstudiante;
    private String codEstudiante;
    private String nomCurso;
    private String nomGrado;
    //constructor
     public Estudiante() {
    }

    //getter y setter

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomEstudiante() {
        return nomEstudiante;
    }

    public void setNomEstudiante(String nomEstudiante) {
        this.nomEstudiante = nomEstudiante;
    }

    public String getApEstudiante() {
        return apEstudiante;
    }

    public void setApEstudiante(String apEstudiante) {
        this.apEstudiante = apEstudiante;
    }

    public int getCiEstudiante() {
        return ciEstudiante;
    }

    public void setCiEstudiante(int ciEstudiante) {
        this.ciEstudiante = ciEstudiante;
    }

    public String getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }

    public String getNomGrado() {
        return nomGrado;
    }

    public void setNomGrado(String nomGrado) {
        this.nomGrado = nomGrado;
    }

    
   
    
     
}