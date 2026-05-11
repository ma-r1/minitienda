package minitienda.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.Pedido;
import minitienda.modelo.Usuario;
import minitienda.modelo.dao.DAOUsuario;
import minitienda.modelo.dao.DAOPedido;

public class HelperRegistrar implements Helper {
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String tipo_tarjeta = request.getParameter("tipo_tarjeta");
    String num_tarjeta = request.getParameter("num_tarjeta");

    Usuario usuario = new Usuario(email, password, tipo_tarjeta, num_tarjeta);

    DAOUsuario daoUsuario = new DAOUsuario();

    if (daoUsuario.registrarUsuario(usuario)) {
      HttpSession session = request.getSession();

      Object totalObj = session.getAttribute("total");
      float total = 0.0f;

      if (totalObj instanceof Number) {
        total = ((Number) totalObj).floatValue();
      }

      if (total <= 0) {
        return "/carrito.jsp";
      }

      DAOPedido daoPedido = new DAOPedido();
      int idPedido = daoPedido.guardarPedido(email, total);

      if (idPedido == -1) {
        request.setAttribute("error", "Usuario registrado, pero no se pudo guardar el pedido.");
        return "/login.jsp";
      }

      Pedido pedido = new Pedido(idPedido, email, total);

      request.setAttribute("pedido", pedido);

      session.removeAttribute("carrito");
      session.setAttribute("total", 0.0f);

      return "/confirmacion.jsp";
    } else {
      request.setAttribute("error", "No se pudo registrar el usuario.");
      return "/login.jsp";
    }
  }
}