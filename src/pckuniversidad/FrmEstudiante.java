/**
 * Captura txtNacimiento y pantalla (String): dd/MM/yyyy
 * en BD nacimiento_est y variable NACIMIENTO (Date): yyyy-MM-dd
 */
package pckuniversidad;

// import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JCB
 */
public class FrmEstudiante extends javax.swing.JFrame {

   private final String TABLA = "tblestudiantes";
   private final ConectorBD CBD = new ConectorBD();

   private String sql;
   private String ID, TIPOID, NOMBRE, APELLIDOS, GENERO, TIPOSANGRE;
   private String TELEFONOS, DIRECCION, CORREO;
   private Date NACIMIENTO;

   private void limpiarGUI() {
      this.txtId.setText("");
      this.txtNombre.setText("");
      this.txtApellidos.setText("");
      this.txtNacimiento.setText("");
      this.txtTelefonos.setText("");
      this.txtDireccion.setText("");
      this.txtCorreo.setText("");

      this.cmbTipoId.setSelectedIndex(0);
      this.cmbGenero.setSelectedIndex(0);
      this.cmbTipoSangre.setSelectedIndex(0);

      this.txtId.setEnabled(true);
      this.btnGuardar.setEnabled(true);
      this.btnActualizar.setEnabled(false);
      this.btnEliminar.setEnabled(false);

      this.txtId.grabFocus();
   }

   private void obtenerDatosGUI() {
      ID = txtId.getText();
      TIPOID = String.valueOf(cmbTipoId.getSelectedIndex());
      NOMBRE = txtNombre.getText().toUpperCase();
      APELLIDOS = txtApellidos.getText().toUpperCase();
      GENERO = (cmbGenero.getSelectedItem().toString().charAt(0) + "");
      TIPOSANGRE = cmbTipoSangre.getSelectedItem().toString();
/* Date date = new Date();
   DateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
   System.out.println("Fecha: "+dateFormat.format(date));
*/
      try {
         SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
         //Date fchNacimiento = null;
         Date fchNacimiento = formatoDelTexto.parse(txtNacimiento.getText());
         SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
         NACIMIENTO = formatoDelTexto.parse(txtNacimiento.getText());
         NACIMIENTO.G = fchNacimiento;
      } catch (ParseException ex) {
         ex.printStackTrace();
      }
      //System.out.println(fchNacimiento.toString());
      
      TELEFONOS = txtTelefonos.getText();
      DIRECCION = txtDireccion.getText();
      CORREO = txtCorreo.getText();
   }

   private boolean guardar() {
      boolean estado = false;

      SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyyy-MM-dd");
      Date NACIMIENTO_DB = null;
      try {
         NACIMIENTO_DB = formatoDelTexto.parse(NACIMIENTO.toString());
      } catch (ParseException ex) {
         ex.printStackTrace();
      }

      if (CBD.conectar()) {
         sql = "INSERT INTO " + TABLA + "VALUES ( "
               + ID + ","
               + TIPOID + ","
               + "'" + NOMBRE + "'" + ","
               + "'" + APELLIDOS + "'" + ","
               + "'" + GENERO + "'" + ","
               + "'" + TIPOSANGRE + "'" + ","
               + "'" + NACIMIENTO_DB + "'" + ","
//               + "'" + NACIMIENTO + "'" + ","
               + "'" + DIRECCION + "'" + ","
               + "'" + TELEFONOS + "'" + ","
               + "'" + CORREO + "'" + ","
               + " );";
         if (CBD.equals(sql)) {
            estado = true;
         }
         CBD.desconectar();
      } else {
         Util.advertir(this, "ERROR: Verifique la conexión con la BD", getTitle());
      }
      return estado;
   }

   private boolean actualizar() {
      boolean estado = false;

      if (CBD.conectar()) {
         sql = "UPDATE " + TABLA + "SET "
               + "tipoid_est = " + TIPOID + ","
               + "nombre_est = " + "'" + NOMBRE + "'" + ","
               + "apellidos_est = " + "'" + APELLIDOS + "'" + ","
               + "genero_est = " + "'" + GENERO + "'" + ","
               + "tiposangre_est = " + "'" + TIPOSANGRE + "'" + ","
               + "nacimiento_est = " + "'" + NACIMIENTO + "'" + ","
               + "direccion_est = " + "'" + DIRECCION + "'" + ","
               + "telefonos_est = " + "'" + TELEFONOS + "'" + ","
               + "correo_est = " + "'" + CORREO + "'"
               + " WHERE id_est = " + ID + ";";
         if (CBD.ejecutar(sql)) {
            estado = true;
         }
         CBD.desconectar();
      } else {
         Util.advertir(this, "ERROR: Verifique la conexión con la BD", getTitle());
      }
      return estado;
   }

   private boolean buscar(String id) {
      boolean estado = false;

      if (CBD.conectar()) {
         sql = "SELECT * FROM" + TABLA + " WHERE id_est=" + id + ";";
         ResultSet regs = CBD.seleccionar(sql);
         try {
            if (regs.next()) {
               txtId.setText(String.valueOf(regs.getInt("id_est")));
               cmbTipoId.setSelectedIndex(regs.getInt("tipoid_est"));
               txtNombre.setText(regs.getString("nombre_est"));
               txtApellidos.setText(regs.getString("apellidos_est"));
               String c = regs.getString("genero_est");
               int i = ("*".equals(c)) ? 0 : ("F".equals(c)) ? 1 : 2;
               cmbGenero.setSelectedIndex(i);
               cmbTipoSangre.setSelectedItem(regs.getString("tiposangre_est"));
               txtNacimiento.setText(regs.getString("nacimiento_est"));
               txtTelefonos.setText(regs.getString("telefonos_est"));
               txtDireccion.setText(regs.getString("direccion_est"));
               txtCorreo.setText(regs.getString("correo_est"));
               estado = true;
            }
         } catch (SQLException sqle) {
            System.out.println("ERROR: buscar()");
            System.out.println(sqle.getMessage());
         }
         CBD.desconectar();
      } else {
         Util.advertir(this, "ERROR: Verifique la conexión con la BD", getTitle());
      }
      return estado;
   }

   public FrmEstudiante() {
      initComponents();
   }

   /**
    * This method is called from within the constructor to initialize the form. WARNING:
    * Do NOT modify this code. The content of this method is always regenerated by the
    * Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel1 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      jLabel4 = new javax.swing.JLabel();
      txtId = new javax.swing.JTextField();
      txtNombre = new javax.swing.JTextField();
      txtApellidos = new javax.swing.JTextField();
      cmbTipoId = new javax.swing.JComboBox<>();
      jLabel5 = new javax.swing.JLabel();
      jLabel6 = new javax.swing.JLabel();
      cmbGenero = new javax.swing.JComboBox<>();
      cmbTipoSangre = new javax.swing.JComboBox<>();
      jLabel7 = new javax.swing.JLabel();
      jLabel8 = new javax.swing.JLabel();
      txtTelefonos = new javax.swing.JTextField();
      jLabel9 = new javax.swing.JLabel();
      txtDireccion = new javax.swing.JTextField();
      jLabel10 = new javax.swing.JLabel();
      txtCorreo = new javax.swing.JTextField();
      jPanel1 = new javax.swing.JPanel();
      btnLimpiar = new javax.swing.JButton();
      btnGuardar = new javax.swing.JButton();
      btnBuscar = new javax.swing.JButton();
      btnActualizar = new javax.swing.JButton();
      btnEliminar = new javax.swing.JButton();
      btnCerrar = new javax.swing.JButton();
      txtNacimiento = new javax.swing.JTextField();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      jLabel1.setText("IDENTIFICACIÓN");

      jLabel2.setText("TIPO IDENTIFICACIÓN");

      jLabel3.setText("NOMBRE");

      jLabel4.setText("APELLIDOS");

      txtId.setText("txtId");

      txtNombre.setText("txtNombre");

      txtApellidos.setText("txtApellidos");

      cmbTipoId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "DNI/NIF", "PERMISO EXTRANJEROS" }));

      jLabel5.setText("GÉNERO");

      jLabel6.setText("TIPO DE SANGRE");

      cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "FEMENINO", "MASCULINO" }));

      cmbTipoSangre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "A+", "A-", "B+", "B-", "AB+", "AB-", "0+", "0-" }));

      jLabel7.setText("FECHA DE NACIMIENTO (dd/mm/aaaa)");

      jLabel8.setText("TELÉFONOS");

      txtTelefonos.setText("txtTelefonos");

      jLabel9.setText("DIRECCIÓN");

      txtDireccion.setText("txtDireccion");

      jLabel10.setText("COOREO ELECTRÓNICO");

      txtCorreo.setText("txtCorreo");

      jPanel1.setBackground(new java.awt.Color(0, 204, 204));

      btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      btnLimpiar.setForeground(new java.awt.Color(0, 0, 255));
      btnLimpiar.setText("LIMPIAR");

      btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      btnGuardar.setForeground(new java.awt.Color(0, 0, 255));
      btnGuardar.setText("GUARDAR");
      btnGuardar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnGuardarActionPerformed(evt);
         }
      });

      btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      btnBuscar.setForeground(new java.awt.Color(0, 0, 255));
      btnBuscar.setText("BUSCAR");
      btnBuscar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBuscarActionPerformed(evt);
         }
      });

      btnActualizar.setForeground(new java.awt.Color(0, 0, 255));
      btnActualizar.setText("ACTUALIZAR");
      btnActualizar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnActualizarActionPerformed(evt);
         }
      });

      btnEliminar.setForeground(new java.awt.Color(0, 0, 255));
      btnEliminar.setText("ELIMINAR");

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(btnLimpiar)
            .addGap(18, 18, 18)
            .addComponent(btnGuardar)
            .addGap(18, 18, 18)
            .addComponent(btnBuscar)
            .addGap(31, 31, 31)
            .addComponent(btnActualizar)
            .addGap(30, 30, 30)
            .addComponent(btnEliminar)
            .addContainerGap(36, Short.MAX_VALUE))
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnLimpiar)
               .addComponent(btnGuardar)
               .addComponent(btnBuscar)
               .addComponent(btnActualizar)
               .addComponent(btnEliminar))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

      btnCerrar.setForeground(new java.awt.Color(255, 0, 0));
      btnCerrar.setText("CERRAR");

      txtNacimiento.setText("txtNacimiento");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jLabel9)
                     .addComponent(jLabel7)
                     .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(cmbTipoId, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(jLabel2)))
                     .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                           .addComponent(jLabel10)
                           .addComponent(txtTelefonos, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                           .addComponent(txtCorreo)))
                     .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(jLabel5)
                           .addComponent(jLabel3)
                           .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(jLabel4)
                           .addComponent(jLabel6)
                           .addComponent(jLabel8)
                           .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(cmbTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                  .addGap(27, 27, 27))))
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCerrar)
            .addGap(43, 43, 43))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGap(37, 37, 37)
                  .addComponent(jLabel1))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jLabel2)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGap(3, 3, 3)
                  .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(layout.createSequentialGroup()
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(cmbTipoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel3)
               .addComponent(jLabel4))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(27, 27, 27)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel5)
               .addComponent(jLabel6))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(cmbTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(31, 31, 31)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel7)
               .addComponent(jLabel8))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(txtTelefonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel9)
               .addComponent(jLabel10))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(9, 9, 9)
            .addComponent(btnCerrar)
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      obtenerDatosGUI();
      if (guardar()) {
         Util.informar(this, "Datos guardados correctamente.", getTitle());
         limpiarGUI();
      } else {
         Util.advertir(this, "ERROR: No se guardan los datos.", getTitle());
      }
   }//GEN-LAST:event_btnGuardarActionPerformed

   private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
      obtenerDatosGUI();
      if (actualizar()) {
         Util.informar(this, "Datos actualizados correctamente.", getTitle());
         limpiarGUI();
      } else {
         Util.advertir(this, "ERROR: No se actualizaron los datos.", getTitle());
      }
   }//GEN-LAST:event_btnActualizarActionPerformed

   private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
      String id = Util.capturar(this, "Teclee la identificación", getTitle());
      limpiarGUI();
      if( buscar(id)) {
         btnGuardar.setEnabled(false);
         btnActualizar.setEnabled(true);
         btnEliminar.setEnabled(true);
         txtId.setEditable(false);
         Util.informar(this, "Datos buscados correctamente", getTitle());
       } else {
         Util.advertir(this, "No se encontraron datos.", getTitle());
      }
   }//GEN-LAST:event_btnBuscarActionPerformed

   /**
    * @param args the command line arguments
    */
   public static void main(String args[]) {
      /* Set the Nimbus look and feel */
      //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
      /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
       */
      try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new FrmEstudiante().setVisible(true);
         }
      });
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnActualizar;
   private javax.swing.JButton btnBuscar;
   private javax.swing.JButton btnCerrar;
   private javax.swing.JButton btnEliminar;
   private javax.swing.JButton btnGuardar;
   private javax.swing.JButton btnLimpiar;
   private javax.swing.JComboBox<String> cmbGenero;
   private javax.swing.JComboBox<String> cmbTipoId;
   private javax.swing.JComboBox<String> cmbTipoSangre;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel10;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JLabel jLabel8;
   private javax.swing.JLabel jLabel9;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JTextField txtApellidos;
   private javax.swing.JTextField txtCorreo;
   private javax.swing.JTextField txtDireccion;
   private javax.swing.JTextField txtId;
   private javax.swing.JTextField txtNacimiento;
   private javax.swing.JTextField txtNombre;
   private javax.swing.JTextField txtTelefonos;
   // End of variables declaration//GEN-END:variables
}
