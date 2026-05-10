package minitienda.controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.CD;

public class HelperEliminarCD implements Helper {
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception{
    HttpSession session = request.getSession();
    ArrayList<CD> carrito = (ArrayList<CD>) session.getAttribute("carrito");
    if (carrito != null){
      String indicestring = request.getParameter("indice");
      if(indicestring != null){
        try {
          int indice = Integer.parseInt(indicestring);
          if (indice >= 0 && indice < carrito.size()){
            carrito.remove(indice);
          }
        } catch (NumberFormatException e) {System.out.println("Índice de eliminación inválido.");}
      }
    }

    float total = 0.0f;
    for (CD cd : carrito) {
      total += cd.getCantidad() * cd.getPrecio();
    }
    session.setAttribute("total", total);
    return "/carrito.jsp";
  }
}
