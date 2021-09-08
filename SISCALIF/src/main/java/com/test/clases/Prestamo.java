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
public class Prestamo implements Serializable {
    //atributos
    private int codPrestamo;
    private int codLibro;
    private int cedulaUsr;
    //constructor
     public Prestamo() {
    }

    //getter y setter

    public int getCodPrestamo() {
        return codPrestamo;
    }

    public void setCodPrestamo(int codPrestamo) {
        this.codPrestamo = codPrestamo;
    }

    public int getCodLibro() {
        return codLibro;
    }

    public void setCodLibro(int codLibro) {
        this.codLibro = codLibro;
    }

    public int getCedulaUsr() {
        return cedulaUsr;
    }

    public void setCedulaUsr(int cedulaUsr) {
        this.cedulaUsr = cedulaUsr;
    }

    
     
}
