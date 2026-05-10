package minitienda.controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.CD;

public class HelperMostrarCaja implements Helper {
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception{
    HttpSession session = request.getSession();
    ArrayList<CD> carrito = (ArrayList<CD>) session.getAttribute("carrito");
    if (carrito == null){
      carrito = new ArrayList<>();
      session.setAttribute("carrito", carrito);
    }
    float total = 0.0f;
    for (CD cd : carrito) {
      total += cd.getCantidad() * cd.getPrecio();
    }
    session.setAttribute("total", total);
    return "/caja.jsp";
  }
}
