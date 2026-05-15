package minitienda.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import minitienda.modelo.dao.BDConexion;

/**
 * 
 * Servlet controlador que actúa como punto de entrada único para las peticiones
 * enviadas por el frontend
 * 
 * Se implementa el patrón modelo vista-controlador recibiendo todas las
 * peticiones HTTP y dependiendo
 * del parámetro acción instancia unos helpers u otros, delegando la ejecución
 * de la lógica en dichos helpers.
 */
public class ServletFrontController extends HttpServlet {

  /**
   * Función de inicialización del servlet que invoca la configuración de la base
   * de datos
   * con las especificaciones del contexto de la aplicación de web.xml
   */
  public void init() throws ServletException {
    String url = getServletContext().getInitParameter("db.url");
    String usuario = getServletContext().getInitParameter("db.usuario");
    String clave = getServletContext().getInitParameter("db.clave");
    BDConexion.configurar(url, usuario, clave);
  }

  /**
   * Punto de entrada para procesar peticiones HTTP tipo POST que delega en la
   * lógica
   * de procesamiento de la soliciutd en doGET
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.doGet(request, response);
  }

  /**
   * Lee el parámetro "accion" que debería recibirse a través de la solicitud HTTP
   * e instancia el Helper
   * correspondiente
   * 
   * Si la accion es desconocida se redirige a "mostrar carrito"
   * 
   * El Helper ejecutado devuelve la vista .jsp que debe ser renderizada en
   * respuesta a la acción del usuario
   * procesada, si la variable vista empieza por "redirect:" el controlador hace
   * que el navegador mande otra solicitud
   * a otra URL cambinado la barra de direcciones, en otro caso, se hace forward
   * al JSP indicado y devolviendo el html
   * manteniendo la misma petición
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String accion = request.getParameter("accion");
    Helper helper = null;

    if ("anadircd".equals(accion)) {
      //Funcionalidad 1 
      helper = new HelperAnadirCD();
      
    } else if ("mostrarcarrito".equals(accion)) {
      //Funcionalidad 2 
      helper = new HelperMostrarCarrito();

    } else if ("eliminarcd".equals(accion)) {
      //Funcionalidad 4 
      helper = new HelperEliminarCD();

    } else if ("mostrarcaja".equals(accion)) {
      //Funcionalidad 3 
      helper = new HelperMostrarCaja();

    } else if ("pagar".equals(accion)) {
      //Funcionalidad 6
      helper = new HelperMostrarLogin();

    } else if ("login".equals(accion)) {
      //Funcionalidades 5,6,7,8
      helper = new HelperLogin();

    } else if ("registrar".equals(accion)) {
      //Funcionalidades 5,6,7,8
      helper = new HelperRegistrar();

    } else {
      helper = new HelperMostrarCarrito();
    }


    //Realizamos la ejecución del helper correspondiente y almacenamos el resultado que devuelve(la vista)
    String vista = null;
    try {
      vista = helper.ejecutar(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }

    //Procesamos la "vista" devuelta, decidiendo si realizar un redirect o forward
    //Distinguiendo como responde el navegador según lo solicitado por el helper que haya actuado
    if (vista != null) {
      if (vista.startsWith("redirect:")) {
        response.sendRedirect(vista.substring(9));
      } else {
        request.getRequestDispatcher(vista).forward(request, response);
      }
      ;
    }
  }
}
