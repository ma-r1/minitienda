package minitienda.controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.CD;

/**
 * Este helper implementa la funcionalidad 4 del enunciado, permitiendo
 * eliminar CDs del carrito sin afectar a la continuidad de la compra
 */
public class HelperEliminarCD implements Helper {

  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception {
    //Extraemos la sesión HTTP y la variable almacenada en ella carrito
    HttpSession session = request.getSession();
    ArrayList<CD> carrito = (ArrayList<CD>) session.getAttribute("carrito");

    //Comprobación de existencia del carrito
    if (carrito != null) {
      //Extraemos el parámetro "indice" que deberíamos haber recibido a través de la
      //solicitud HTTP y que contiene el índice en el carrito del producto a eliminar
      String indicestring = request.getParameter("indice");

      //Comprobamos si el indice existe
      if (indicestring != null) {
        try {
          int indice = Integer.parseInt(indicestring);
          if (indice >= 0 && indice < carrito.size()) {
            carrito.remove(indice);
          } else {
            System.out.println("Índice de eliminación inválido.");
            return "redirect:index.html";
          }
        } catch (NumberFormatException e) {
          //Si el indice no existe solicitud no permitida, devolvemos index.html
          System.out.println("Índice de eliminación inválido(formato de número no válido)");
          return "redirect:index.html";
        }
      } else {
        //Si el indice no existe solicitud no permitida, devolvemos index.html
        System.out.println("Índice de eliminación inexistente");
        return "redirect:index.html";
      }
    } else {
      //Devolvemos al usuario index.html en caso de que haya una solicitud incoherente
      //(eliminar teniendo el carrito vacío)
      System.out.println("Solicitud de eliminación con carrito vacío");
      return "redirect:index.html";
    }


    //Si el proceso de eliminación se ejecuta correctamente recalculamos el precio del CD
    float total = 0.0f;
    for (CD cd : carrito) {
      total += cd.getCantidad() * cd.getPrecio();
    }
    //Reestablecemos el atributo total con el nuevo valor
    session.setAttribute("total", total);
    return "redirect:ServletFrontController?accion=mostrarcarrito";
  }

}
