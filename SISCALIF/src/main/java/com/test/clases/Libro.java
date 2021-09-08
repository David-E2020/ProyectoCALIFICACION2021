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
public class Libro implements Serializable {
    //atributos
    private int codLibro;
    private int codAutor;
    private String titulo;
    private int edicion;
    private int numEjemplar;
    
    //constructor
     public Libro() {
    }

    //getter y setter

    public int getCodLibro() {
        return codLibro;
    }

    public void setCodLibro(int codLibro) {
        this.codLibro = codLibro;
    }

    public int getCodAutor() {
        return codAutor;
    }

    public void setCodAutor(int codAutor) {
        this.codAutor = codAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public int getNumEjemplar() {
        return numEjemplar;
    }

    public void setNumEjemplar(int numEjemplar) {
        this.numEjemplar = numEjemplar;
    }

     
}
