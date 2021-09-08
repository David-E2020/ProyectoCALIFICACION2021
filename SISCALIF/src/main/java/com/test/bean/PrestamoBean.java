/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

import com.test.clases.Cursos;
import com.test.conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author FERMIN
 */
public class PrestamoBean {
    //atributos
    private Connection connection;
    private PreparedStatement insertPrestamo;
    private VariablesConexion variable;
    //objeto que rescatara los datos para ser modificados
    private Cursos prestamoModificar;
    //objeto para poder modificar el registro
    private PreparedStatement updateCursos;
    //Objeto para eliminar un registro
    private PreparedStatement deletePrestamo;
    
    //constructores
    public PrestamoBean()throws SQLException {
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
    
    public String registrarPrestamo(HttpServletRequest request){
        String mensaje="";
        if(request==null){
            return "";
        }
        if(connection!=null){
            try {
                //definiendo la consulta
                StringBuilder query=new StringBuilder();
                query.append(" insert into prestamo ");
                query.append(" values (nextval('sec_prestamo'),?,?,?,?) ");
                //enviando la consulta
                if(insertPrestamo==null){
                    insertPrestamo=connection.prepareStatement(query.toString()); 
                }
                //rescatando los parametros del formulario
                int codLibro=Integer.parseInt(request.getParameter("codLibro"));
                int codUsr=Integer.parseInt(request.getParameter("codUsuario"));
                //String fechaPrestamo=request.getParameter("fechaPrestamo");
                
                
                String fechaDevolucion=request.getParameter("fechaDevolucion");
                        
                //pasando los datos a los parametros de la consulta
                insertPrestamo.setInt(1, codLibro);
                insertPrestamo.setInt(2, codUsr);
                insertPrestamo.setDate(3, new java.sql.Date(new Date().getTime()));
                insertPrestamo.setDate(4, new java.sql.Date(new Date().getTime()));
                //ejecutando la consulta
                int registro=insertPrestamo.executeUpdate();
                if(registro==1){
                    mensaje="Registro realizado con exito";
                }
                else{
                    mensaje="Error al insertar el registro";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
        return mensaje;
    }
    
    //realizando el estado de todas las categorias que se tiene registradas
    public String listarLibro(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select c.idcurso,c.nombre_curso ");
        query.append(" from cursos c ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</td>");
                //adicionando enlace para modificar ese registro
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarCursos.jsp?cod=").append(resultado.getInt(1)).append(">Modificar</a>");
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append("<a href='listaCursos.jsp?cod=").append(resultado.getInt(1)).append("' onclick='return confirmarEliminacion();'>Eliminar</a>");
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    
    //metodo que lista la cursos en un select
    
    
     //metodo que permite buscar una cursos por su codigo o llave primaria
    
    
    
    
    //metodo que permite un registro de la tabla categoria
    
    
    //getter y setter

    public PreparedStatement getInsertPrestamo() {
        return insertPrestamo;
    }

    public void setInsertPrestamo(PreparedStatement insertPrestamo) {
        this.insertPrestamo = insertPrestamo;
    }

    public Cursos getPrestamoModificar() {
        return prestamoModificar;
    }

    public void setPrestamoModificar(Cursos prestamoModificar) {
        this.prestamoModificar = prestamoModificar;
    }

    public PreparedStatement getUpdateCursos() {
        return updateCursos;
    }

    public void setUpdateCursos(PreparedStatement updateCursos) {
        this.updateCursos = updateCursos;
    }

    
    
    
}
