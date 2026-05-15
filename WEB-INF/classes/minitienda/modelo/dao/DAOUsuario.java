package minitienda.modelo.dao;

import minitienda.modelo.Usuario;
import java.sql.*;

/**
 *  Esta clase representa la comunicación con la base de datos para la tabla de usuarios
 */
public class DAOUsuario {

  /**
   * Esta función permite el registro del usuario a través de una transacción mediante jdbc
   * 
   * @param objeto usuario que se almacena los datos que se pretenden registrar
   * @return Booleano que indica la salida del registro, true si el proceso se ejecuta correctamente, false en caso contrario
   */
  public boolean registrarUsuario(Usuario u) {
    String sql = "INSERT INTO usuarios (correo, password, tipo_tarjeta, num_tarjeta) VALUES (?, ?, ?, ?)";
    Connection con = null;
    PreparedStatement ps = null;
    boolean resultado = false;

    try {
      con = BDConexion.getConexion();
      ps = con.prepareStatement(sql);
      ps.setString(1, u.getCorreo());
      ps.setString(2, u.getPassword());
      ps.setString(3, u.getTipo_tarjeta());
      ps.setString(4, u.getNum_tarjeta());
      int filas = ps.executeUpdate();
      resultado = filas > 0;
    } catch (SQLException e) {
      System.out.println("Error al registrar usuario.");
      e.printStackTrace();
    } finally {
      try {
        if (ps != null) ps.close();
      } catch (SQLException e) {
        System.out.println("Imposible cerrar cursores");
      }
      try {
        if (con != null) con.close();
      } catch (SQLException e) {
        System.out.println("Imposible cerrar cursores");
      }
    }
    return resultado;
  }

  /**
   * 
   * Esta función se utiliza para validar el inicio de sesión de un usuario comparándolo con la entrada existente que debería corresponderle
   * en la base de datos 
   * 
   * @param correo Correo del usuario a validar
   * @param password Contraseña del usuario a validar
   * @return Devuelve un booleano que indica el resultade del proceso de ejecución, verdadero si todo es correcto falso en caso contrario
   */
  public boolean validarUsuario(String correo, String password) {
    String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean resultado = false;

    try {
      con = BDConexion.getConexion();
      ps = con.prepareStatement(sql);
      ps.setString(1, correo);
      ps.setString(2, password);
      rs = ps.executeQuery();
      resultado = rs.next();
    } catch (SQLException e) {
      System.out.println("Error al validar usuario.");
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) rs.close();
      } catch (SQLException e) {
        System.out.println("Imposible cerrar cursores");
      }
      try {
        if (ps != null) ps.close();
      } catch (SQLException e) {
        System.out.println("Imposible cerrar cursores");
      }
      try {
        if (con != null) con.close();
      } catch (SQLException e) {
        System.out.println("Imposible cerrar cursores");
      }
    }
    return resultado;
  }
}
