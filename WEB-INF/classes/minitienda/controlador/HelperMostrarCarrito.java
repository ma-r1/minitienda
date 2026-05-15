package minitienda.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Este helper cumple con la funcionalidad de mostrar la lista de CDs del carrito y
 *  el importe total de la selección a través de devolver carrito.jsp
 */
public class HelperMostrarCarrito implements Helper {
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception{
    return "/carrito.jsp";
  }
}
