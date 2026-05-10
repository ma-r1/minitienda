package minitienda.controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.CD;

public class HelperAnadirCD implements Helper{
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception{
    HttpSession session = request.getSession();
    ArrayList<CD> carrito = (ArrayList<CD>) session.getAttribute("carrito");
    if (carrito == null){
      carrito = new ArrayList<>();
      session.setAttribute("carrito", carrito);
    }

    String nombrecd = request.getParameter("nombre");
    String cantidadString = request.getParameter("cantidad");
    if (nombrecd != null && !nombrecd.trim().isEmpty() && cantidadString != null){
      try {
        int cantidad = Integer.parseInt(cantidadString);
        if (cantidad > 0)  {
          CD nuevocd = new CD(nombrecd, cantidad);
          nuevocd.calcularPrecio();
          carrito.add(nuevocd);
        } 
      } catch (NumberFormatException e) {System.out.println("La cantidad debe ser un número.");}
    }

    float total = 0.0f;
    for (CD cd : carrito) {
      total += cd.getCantidad() * cd.getPrecio();
    }
    session.setAttribute("total", total);
    return "/carrito.jsp";
  }
}
