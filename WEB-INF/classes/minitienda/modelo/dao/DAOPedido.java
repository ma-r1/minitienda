package minitienda.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOPedido {

    public int guardarPedido(String correoUsuario, float importe) {
        String sql = "INSERT INTO pedidos (correo_usuario, importe) VALUES (?, ?)";
        try {
            Connection coin = BDConexion.getConexion();
            PreparedStatement ps = coin.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, correoUsuario);
            ps.setFloat(2, importe);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al guardar el pedido en la BD.");
            e.printStackTrace();
        }
        return -1;
    }
}
