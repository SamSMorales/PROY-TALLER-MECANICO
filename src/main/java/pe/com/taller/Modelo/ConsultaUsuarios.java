package pe.com.taller.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import pe.com.taller.Vista.AdmPersonalRegistrado;
import pe.com.taller.Vista.Registro;

public class ConsultaUsuarios {

    Connection conexion;
    PreparedStatement sentencia_preparada;
    ResultSet resultado;
    String sql;
    AdmPersonalRegistrado frmadmPersonalRegistrado = new AdmPersonalRegistrado();
    Registro registro = new Registro();

    public void buscarUsuarioRegistrado(String usuario, String contraseña) {

        String tipo_permiso_usuario;

        try {

            conexion = Conexion.conectar();
            // sql = "SELECT usuario, contraseña FROM usuarios WHERE usuario= ' " + usuario + " ' && contraseña= ' " + contraseña + " ' ";
            sql = "SELECT * FROM usuarios WHERE usuario= ' " + usuario + " ' && contraseña= ' " + contraseña + " ' ";
            sentencia_preparada = conexion.prepareStatement(sql);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {

                usuario = resultado.getString("usuario");
                contraseña = resultado.getString("contraseña");

                if (usuario != null && contraseña != null) {

                    tipo_permiso_usuario = resultado.getString("rol");
                    switch (tipo_permiso_usuario) {

                        case "administrador":
                            frmadmPersonalRegistrado.setVisible(true);
                            break;
                        case "empleado":
                            registro.setVisible(true);
                            break;
                    }

                }

            } else {

                JOptionPane.showMessageDialog(null, "Error en el usuario o contraseña ingresada");

            }
            
            conexion.close();
            Conexion.desconectar();
            

        } catch (SQLException e) {

            System.out.println("Error : " + e);

        }

    }

  
}
