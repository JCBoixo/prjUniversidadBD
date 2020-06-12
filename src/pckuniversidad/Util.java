package pckuniversidad;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * Clase que proporciona métodos multipropósito
 */

public class Util {
   public static String PATRON_FECHA_MYSQL = "YYYY-MM-dd";
   public static String PATRON_EUROS = "###.###,##";
   public static String aFechaMySQL(Date date) {
      String fecha;
      SimpleDateFormat sdf = new SimpleDateFormat(PATRON_FECHA_MYSQL);
      fecha = sdf.format(date);
      return fecha;
   }
   public static String formatoEuros(double valor) {
      DecimalFormat formateador = new DecimalFormat();
      String res = formateador.format(valor)+ " €";
      return res;
   }
   public static void informar(Component c, String mensaje, String titulo) {
      JOptionPane.showMessageDialog(c, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
   }
   public static void advertir(Component c, String mensaje, String titulo) {
      JOptionPane.showMessageDialog(c, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
   }
   public static String capturar(Component c, String mensaje, String titulo) {
      String datos = JOptionPane.showInputDialog(c, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
      if( datos == null){
         datos = "";
      }
      return datos;
   }
   public static boolean confirmar(Component c, String mensaje, String titulo){
      int respuesta = JOptionPane.showConfirmDialog(c, mensaje, titulo, JOptionPane.YES_NO_OPTION);
      if( respuesta == JOptionPane.YES_OPTION){
         return true;
      }else{
         return false;
      }
   }
   private static int hex( String color_hex ){
      return Integer.parseInt(color_hex, 16 );
   }
   public static Color COLOR_ENFOQUE = new Color( hex("CCFFCC"));
   public static Color COLOR_SINENFOQUE = Color.WHITE;
} //Fin de la clase Util
