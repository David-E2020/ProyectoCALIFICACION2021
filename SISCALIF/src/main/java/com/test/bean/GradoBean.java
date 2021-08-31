/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;
import com.test.clases.Grado;
import com.test.conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PreDestroy;


/**
 *
 * @author FERMIN
 */
public class GradoBean {
    //atributos
    private Connection connection;
    
    private VariablesConexion variable;
    //objeto para poder modificar el registro
    private Grado gradoModificar;
    
    
    
    //constructores
    public GradoBean()throws SQLException {
        //instanciando
        variable=new VariablesConexion();
        variable.inicioConexion();
        //obteniendo la conexion
        connection=variable.getConnection();
        System.out.println("Iniciando la conexion");
    }
    
    //metodos
    @PreDestroy
    public void cerrarConexion(){
        variable.cerrarConexion();
    }
    
    
    //metodo que lista la cursos en un select
    public String listarGradoSelect(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select g.idgrado,g.grado ");
        query.append(" from grado g ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<option value='");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("'>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</option>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    
    
    //metodo que permite buscar una cursos por su codigo o llave primaria
    public void buscarGrado(String codGrado){
        gradoModificar=new Grado();
        StringBuilder query=new StringBuilder();
        query.append("select idgrado,grado,paralelo,asesor " );
        query.append(" from grado ");
        query.append(" where idgrado=? ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            pst.setInt(1, Integer.parseInt(codGrado));
            ResultSet resultado=pst.executeQuery();
            //utilizamos una condicion porque la busqueda nos devuelve 1 registro
            if(resultado.next()){
                //cargando la informacion a nuestro objeto gado Modificar de tipo grado
                gradoModificar.setCodGrado(resultado.getInt(1));
                gradoModificar.setGrado(resultado.getString(2));
                gradoModificar.setParalelo(resultado.getString(3));
                gradoModificar.setAsesor(resultado.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
    }
    
    //getter y setter

    public Grado getGradoModificar() {
        return gradoModificar;
    }

    public void setGradoModificar(Grado gradoModificar) {
        this.gradoModificar = gradoModificar;
    }
    

    
    
}
