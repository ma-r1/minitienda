package minitienda.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.Pedido;
import minitienda.modelo.dao.DAOUsuario;
import minitienda.modelo.dao.DAOPedido;

/**
 *  Este Helper implementa la lógica necesaria para tramitar un pedido con una solicitud de 
 *  inicio de sesión asociada 
 */
public class HelperLogin implements Helper {
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // Extraemos los parámetros de la solicitud, email y password
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // Instanciamos un nuevo dao de usuario para poder communicarnos con la base de datos
    DAOUsuario daoUsuario = new DAOUsuario();

    // Comprobamos que el usuario exista
    if (daoUsuario.validarUsuario(email, password)) {
      // Extraemos la sesión http
      HttpSession session = request.getSession();

      // Extraemos el atributo total de la sesión
      Object totalObj = session.getAttribute("total");
      float total = 0.0f;
      if (totalObj instanceof Number) {
        total = ((Number) totalObj).floatValue();
      }

      // Si el importe total es menor o igual a 0 devolvemos a la página principal
      if (total <= 0) {
        System.out.println("Error, importe total de 0");
        return "redirect:index.html";
      }

      // Instanciamos un dao del pedido que se está tramitando para comunicarnos con la base de datos
      DAOPedido daoPedido = new DAOPedido();
      int idPedido = daoPedido.guardarPedido(email, total);

      // Si el proceso de guardar el pedido falla se devuelve a la pantalla de login
      if (idPedido == -1) {
        request.setAttribute("error", "No se pudo guardar el pedido.");
        return "/vista/login.jsp";
      }

      // Creamos una nueva instancia de pedido 
      Pedido pedido = new Pedido(idPedido, email, total);

      // Se almacenan los datos del pedido para la confirmación
      request.setAttribute("pedido", pedido);

      // Se borra el carrito y el importe total en si porque el pedido ya está registrado
      session.removeAttribute("carrito");
      session.setAttribute("total", 0.0f);

      return "/vista/confirmacion.jsp";
    } else {
      // Si al inicar sesión los datos son incorrectos se devuelve al usuario al formulario de login de nuevo
      request.setAttribute("error", "Correo o contraseña incorrectos.");
      return "/vista/login.jsp";
    }
  }
}