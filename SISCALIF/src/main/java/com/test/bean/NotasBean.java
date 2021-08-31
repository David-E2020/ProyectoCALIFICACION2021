/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

//import com.test.clases.ProductoCompra;
import com.test.conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Miriam Lopez - 2021
 */
public class NotasBean {

    //atributos
    private VariablesConexion variable;
    private Connection connection;
    // objeto que permite realizar el insert de notas
    private PreparedStatement insertNota;

    //constructores
    public NotasBean() throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando la conexion");
    }

    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }

    //metodos
    public String listarNotas() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select n.idnota,c.nombre_curso,n.nota,e.nombre,e.apellido ");
        query.append(" from notas n inner join cursos c on n.idcurso=c.idcurso ");
        query.append(" inner join estudiante e on e.idestudiante=n.id_estudiante; ");
        //query.append(" where n.idnota=?; ");
        
        try {
            PreparedStatement pst = connection.prepareStatement(query.toString());
            ResultSet resultado = pst.executeQuery();
            while (resultado.next()) {
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(1));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getDouble(3));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(4));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(5));
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }

    //buscar todos los productos de una determinada categoria
    public String listarProductoCategoria(String codCat) {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select p.nom_pro,p.des_pro,p.costo_uc,p.costo_uv,p.stock,pr.razon_social ");
        query.append(" from producto p ");
        query.append(" inner join proveedor pr on pr.cod_prov=p.cod_prov ");
        query.append(" where p.cod_cat=? ");
        try {
            PreparedStatement pst = connection.prepareStatement(query.toString());
            //pasando a la consulta el parametro del codigo de categoria
            pst.setInt(1, Integer.parseInt(codCat));
            ResultSet resultado = pst.executeQuery();
            while (resultado.next()) {
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(1));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getDouble(3));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getDouble(4));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(5));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(6));
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    //metodo que me permite registrar un producto

    public String registrarNota(HttpServletRequest request) {
        String salida = "";
        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into notas ");
                query.append(" values(nextval('sec_not'),?,?,?) ");
                if (insertNota == null) {
                    insertNota = connection.prepareStatement(query.toString());
                }
                //rescatando los datos del formulario
                
                double nota = Double.parseDouble(request.getParameter("nota"));
                //rescatando los FK de proveedor y categoria
                int idcu = Integer.parseInt(request.getParameter("idcur"));
                int idest = Integer.parseInt(request.getParameter("idestud"));
                //pasando los datos a la consulta
                insertNota.setInt(1, idcu);
                insertNota.setDouble(2, nota);
                insertNota.setInt(3, idest);
                //ejecutando la consulta
                int registro = insertNota.executeUpdate();
                if (registro == 1) {
                    salida = "Insercion correcta de producto";
                } else {
                    salida = "Error al insertar el registro de producto";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                String mensajeError = e.getMessage();
                if (mensajeError.contains("llave duplicada")) {
                    salida = "Error al ejecutar el insert, llave duplicada";
                } else {
                    salida = "Error en la ejecucion: " + mensajeError;
                }
            }
        }
        return salida;
    }

    /**
     * Metodo que busca la lista de Productos
     *
     * @param nombre
     * @return
     */
    public List<String> getProductos(String nombre) {
        List<String> listaProductos = new ArrayList<>();

        try {
            //obteniendo la lista de los productos
            StringBuilder query = new StringBuilder();

            query.append(" select p.cod_pro || ' ' ||  p.nom_pro ");
            query.append(" from producto p ");
            query.append(" where lower(p.nom_pro) like ");
            query.append("lower('");
            query.append(nombre);
            query.append("%')");

            PreparedStatement pst = connection.prepareStatement(query.toString());
            ResultSet resultado = pst.executeQuery();
            while (resultado.next()) {
                listaProductos.add(resultado.getString(1));
                System.out.println("pos: " + resultado.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
        return listaProductos;
    }
    //buscar un producto por su llave primaria

    

    //getter y setter
}
