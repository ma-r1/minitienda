package minitienda.modelo.dao;

import java.sql.*;

public class BDConexion {
  private static String url;
  private static String usuario;
  private static String clave;

  public static void configurar(String ur, String us, String cl){
    url = ur;
    usuario = us;
    clave = cl;
  }

  public static Connection getConexion() throws SQLException {
    try {
      Class.forName("org.postgresql.Driver");
    } catch(ClassNotFoundException e) {e.printStackTrace();}
    return DriverManager.getConnection(url, usuario, clave);
  }
}
