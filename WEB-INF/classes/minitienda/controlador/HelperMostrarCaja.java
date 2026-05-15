package minitienda.controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.CD;

/**
 *  Este Helper implementa la lógica de la funcionalidad 3 del enunciado para
 *  presentar el importe final y permitir el pago de dicho importe
 */
public class HelperMostrarCaja implements Helper {
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception{
    // Extraemos la sesión http y el atributo carrito para ver los CDs
    HttpSession session = request.getSession();
    ArrayList<CD> carrito = (ArrayList<CD>) session.getAttribute("carrito");
    
    // Si el carrito no existía aún creamos e inicializamos la variable
    if (carrito == null){
      carrito = new ArrayList<>();
      session.setAttribute("carrito", carrito);
    }
    // Calculamos e inicializamos el importe total a cobrar por el carrito actual
    float total = 0.0f;
    for (CD cd : carrito) {
      total += cd.getCantidad() * cd.getPrecio();
    }
    session.setAttribute("total", total);
    return "/vista/caja.jsp";
  }
}
