/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckuniversidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectorBD {

   private Connection conexion;
   private Statement sentencia;

   //Datos para la conexion con la BD
   private final String servidor = "localhost";
   private final String puerto = "3306";
   private final String BD = "bd_universidad";
   private final String corrector = "?serverTimezone=UTC";
   private final String usuario = "root";
   private final String clave = "censyra";
   private final String URL = "jdbc;mysql://"
         + servidor + ":" + puerto + "/" + BD + corrector;

   /**
    * Método constructor, que inicializa los atributos internos del conector de BD
    */
   public ConectorBD() {
      this.conexion = null;
      this.sentencia = null;
   }

   /**
    * Método para crear la comunicación con la BD
    *
    * @return true cuando la conexión se crea correctamente y false cuando no es posible
    */
   public boolean conectar() {
      boolean estado = false;
      try {
         //Levantar el Driver de MySQL
         Class.forName("com.mysql.cj.jdbc.Driver");
         try {
            //Establecer la conexión con la BD
            conexion = DriverManager.getConnection(URL, usuario, clave);
            estado = true;
         } catch (SQLException ex) {
            System.out.println("ERROR: ConectorBD.conectar()");
            System.out.println("Al intentar la conexión con la BD");
            System.out.println(ex.getMessage());
         }
      } catch (ClassNotFoundException cex) {
         System.out.println("ERROR: ConectorBD.conectar()");
         System.out.println("No se encontró el Driver de conexión con MySQL");
         System.out.println(cex.getMessage());
      }
      return estado;
   }

   /**
    * Para ejecutar sentencias SQL: SELECT
    *
    * @param sql texto con el script sql para seleccionar registros
    * @return ResultSet con la información seleccionada o null en caso de no
    */
   public ResultSet seleccionar(String sql) {
      ResultSet resultado = null;
      try {
         sentencia = conexion.createStatement();
         resultado = sentencia.executeQuery(sql);
      } catch (SQLException sqle) {
         System.err.println("ERROR: ConectorBD.seleccionar(sql)");
         System.out.println(sqle.getMessage());
      }
      return resultado;
   }

   /**
    * Para ejecutar sentencias SQL: INSERT, UPDATE, DELETE
    *
    * @param sql texto con el script sql para ejecutar sobre la BD
    * @return true si la sentencia tiene éxito, y false en caso de no
    */
   public boolean ejecutar(String sql) {
      boolean estado = false;
      try {
         sentencia = conexion.createStatement();
         sentencia.execute(sql);
         sentencia.close();
         estado = true;
      } catch (SQLException sqle) {
         System.err.println("ERROR: ConectorBD.ejecutar(sql)");
         System.err.println(sqle.getMessage());
      }
      return estado;
   }

   /**
    * Para cerrar la conexión de forma correcta con la BD verificando que exista la
    * conexión
    */
   public void desconectar() {
      try {
         if (conexion != null) {
            conexion.close();
            conexion = null;
         }
      } catch (SQLException sqle) {
         System.err.println("ERROR: ConectorBD.desconectar()");
         System.err.println(sqle.getMessage());
      }
   }
   
   /**
    * Retorna la conexión actual que esté establecida
    * @return conexion
    */
   public Connection getConnection(){
      return conexion;
   }
} //Fin de la clase ConectorBD
