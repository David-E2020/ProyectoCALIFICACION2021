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
public class Estudiante implements Serializable {
    //atributos
    private int codEstudiante;
    private String nomEstudiante;
    private String apEstudiante;
    private int ciEstudiante;
    private String codigoEstudiante;
    private int idgradoEstudiante;
    private int idCursoEstudiante;
    //constructor
     public Estudiante() {
    }

    //getter y setter

    public int getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(int codEstudiante) {
        this.codEstudiante = codEstudiante;
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

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public int getIdgradoEstudiante() {
        return idgradoEstudiante;
    }

    public void setIdgradoEstudiante(int idgradoEstudiante) {
        this.idgradoEstudiante = idgradoEstudiante;
    }

    public int getIdCursoEstudiante() {
        return idCursoEstudiante;
    }

    public void setIdCursoEstudiante(int idCursoEstudiante) {
        this.idCursoEstudiante = idCursoEstudiante;
    }

    

    

   

    

    
     
}
