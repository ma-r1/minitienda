package minitienda.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.Pedido;
import minitienda.modelo.Usuario;
import minitienda.modelo.dao.DAOUsuario;
import minitienda.modelo.dao.DAOPedido;

/**
 *  Este Helper implementa la funcionalidad necesaria para registrar un usuario en la base de datos
 *  y tramitar un pedido autentificado a través de dicho registro
 */
public class HelperRegistrar implements Helper {
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // Extraemos los parámetros pasados por la URL necesarios para crear la cuenta
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String tipo_tarjeta = request.getParameter("tipo_tarjeta");
    String num_tarjeta = request.getParameter("num_tarjeta");

    // Instanciamos un nuevo usuario para poder registrarlo a través del dao
    Usuario usuario = new Usuario(email, password, tipo_tarjeta, num_tarjeta);

    // Instanciamos el dao necesario para registrar el usuario
    DAOUsuario daoUsuario = new DAOUsuario();

    // Comprobamos si el usuario se registra correctamente 
    if (daoUsuario.registrarUsuario(usuario)) {
      // Extraemos la sesión http
      HttpSession session = request.getSession();

      // Obtenemos el importe total de la sesión, puesto que el objetivo es tramitar un pedido
      Object totalObj = session.getAttribute("total");
      float total = 0.0f;
      if (totalObj instanceof Number) {
        total = ((Number) totalObj).floatValue();
      }

      // Si el importe total es menor o igual a 0 devolvemos a la página del carrito al usuario
      // ya que es de asumir que el error tiene relación con los precios no con el registro en si 
      if (total <= 0) {
        return "/carrito.jsp";
      }

      // Instanciamos un dao de la tabla pedidos para registrar el nuevo pedido
      DAOPedido daoPedido = new DAOPedido();
      
      // Extraemos el id devuelto por la transacción
      int idPedido = daoPedido.guardarPedido(email, total);
      // Si es -1 ha habido un error y se devuelve al usuario a la página de login, registrando el usuario pero notificando que el pedido no se pudo completar
      if (idPedido == -1) {
        request.setAttribute("error", "Usuario registrado, pero no se pudo guardar el pedido.");
        return "/login.jsp";
      }

      // Instanciamos y almacenamos un nuevo pedido para poder almacenarlo para la redirección a confirmacion.jsp
      Pedido pedido = new Pedido(idPedido, email, total);

      request.setAttribute("pedido", pedido);

      // Se borra el carrito y el importe total en si porque el pedido ya está registrado
      session.removeAttribute("carrito");
      session.setAttribute("total", 0.0f);

      return "/confirmacion.jsp";
    } else {
      // Si al inicar sesión los datos son incorrectos se devuelve al usuario al formulario de login de nuevo
      request.setAttribute("error", "No se pudo registrar el usuario.");
      return "/login.jsp";
    }
  }
}