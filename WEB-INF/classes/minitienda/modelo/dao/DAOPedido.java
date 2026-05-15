package minitienda.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *  Clase que actúa como comunicación para la tabla pedidos de la base de datos
 */
public class DAOPedido {

    /**
     * Función para insertar en la tabla pedidos una nueva entrada de pedido
     * 
     * @param correoUsuario Parámetro correspondiente al correo del usuario que está tramitando el pedido
     * @param importe Importe total del pedido en trámite
     * @return Se devuelve el ID generado del pedido, devolviendo -1 en caso de que algo haya fallado
     */
    public int guardarPedido(String correoUsuario, float importe) {
        String sql = "INSERT INTO pedidos (correo_usuario, importe) VALUES (?, ?)";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int resultado = -1;

        try {
            con = BDConexion.getConexion();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, correoUsuario);
            ps.setFloat(2, importe);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al guardar el pedido en la BD.");
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
