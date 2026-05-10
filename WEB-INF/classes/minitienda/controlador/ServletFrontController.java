package minitienda.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletFrontController extends HttpServlet{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String accion = request.getParameter("accion");
    Helper helper = null;

    if ("anadircd".equals(accion)){
      helper = new HelperAnadirCD();
    } else if ("mostrarcarrito".equals(accion)){
      helper = new HelperMostrarCarrito();
    } else if ("eliminarcd".equals(accion)){
      helper = new HelperEliminarCD();
    } else if ("mostrarcaja".equals(accion)) {
      helper = new HelperMostrarCaja();
    } else if ("pagar".equals(accion)) {
      helper = new HelperPagar();
    }  else {
      helper = new HelperAnadirCD();
    }

    String vista = null;
    try {
      vista = helper.ejecutar(request, response);
    } catch (Exception e) {e.printStackTrace();}

    if (vista != null) {
      if (vista.startsWith("redirect:")){
        response.sendRedirect(vista.substring(9));
      } else request.getRequestDispatcher(vista).forward(request, response);
    }
  }
}
  