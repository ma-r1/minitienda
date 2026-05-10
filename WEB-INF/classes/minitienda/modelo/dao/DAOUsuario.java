package minitienda.modelo.dao;

import minitienda.modelo.Usuario;

import java.sql.*;

public class DAOUsuario {
  public boolean registrarUsuario(Usuario u){
    String sql = "INSERT INTO usuarios (correo, password, tipo_tarjeta, num_tarjeta) VALUES (?, ?, ?, ?)";

    try {
      Connection con = BDConexion.getConexion(); 
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, u.getCorreo());
      ps.setString(2, u.getPassword());
      ps.setString(3, u.getTipo_tarjeta());
      ps.setString(4, u.getNum_tarjeta());

      int filas = ps.executeUpdate();
      return filas > 0;
    } catch (SQLException e) {System.out.println("Error al registrar usuario."); return false;}
  }

  public boolean validarUsuario(String correo, String password){
    String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
    try {
      Connection con = BDConexion.getConexion();
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, correo);
      ps.setString(2, password);
      ResultSet rs = ps.executeQuery();
      return rs.next();
    } catch (SQLException e){System.out.println("Error al validar usuario."); return false;}
  }
}
