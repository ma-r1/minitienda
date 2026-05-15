package minitienda.controlador;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.CD;

/**
 * 
 * Esta función permite añadir un CD al carrito
 * 
 * Implementa la lógica correspondiente con la funcionalidad 1
 * Recibe el "nombre" del producto y su "cantidad" que son recibidos como
 * parámetro a través de
 * una solicitud HTTP y a partir de ellos implementa
 */
public class HelperAnadirCD implements Helper {

  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // Extraemos la sesión HTTP para poder procesarla
    HttpSession session = request.getSession();

    // Extraemos el atributo carrito que puede o no ya estar almacenado en la sesión
    // conteniendo los CDs
    ArrayList<CD> carrito = (ArrayList<CD>) session.getAttribute("carrito");

    // Si el atributo no existe porque aún no se añadieron CDs se crea
    if (carrito == null) {
      carrito = new ArrayList<>();
      session.setAttribute("carrito", carrito);
    }

    // Extraemos los parámetros recibidos a través de la solicitud Http, el nombre y
    // la cantidad de productos(CDs)
    String nombrecd = request.getParameter("nombre");
    String cantidadString = request.getParameter("cantidad");

    // Validamos que los paráemtros sigan el formato esperado
    if (nombrecd != null && !nombrecd.trim().isEmpty() && cantidadString != null) {

      try {
        // Parseamos y obtenemos los datos para poder utilizarlos
        int cantidad = Integer.parseInt(cantidadString);

        if (cantidad > 0) {
          // Instanciamos un CD con los datos parseados
          CD nuevocd = new CD(nombrecd, cantidad);

          // En la función calcularPrecio se verifica que el precio sea coherente
          if (nuevocd.calcularPrecio()) {
            // Si efectivamente el CD es válido se añade a la estructura del carrito
            carrito.add(nuevocd);
          } else {
            // Si el CD no es válido se "deshace la solicitud"
            System.out.println("Error de precio");
            return "redirect:index.html";
          }

        }
      } catch (NumberFormatException e) {
        // Si la cantidad de CDs no es válida se "deshace la solicitud"
        System.out.println("La cantidad debe ser un número.");
        return "redirect:index.html";
      }
    } else {
      // Si el formato de los datos no es válido se "deshace la solicitud"
      System.out.println("Error con los datos del producto");
      return "redirect:index.html";
    }

    // Una vez se ha añadido el CD correctamente a la estructura del carrito
    // iteramos sobre esta para recalcular el precio final
    float total = 0.0f;
    for (CD cd : carrito) {
      total += cd.getCantidad() * cd.getPrecio();
    }
    // Establecemos el preico final como un atributo de la sesión, total
    session.setAttribute("total", total);
    // POST-Redirect-GET: redirigimos a mostrarcarrito para evitar re-envío al hacer F5
    return "redirect:ServletFrontController?accion=mostrarcarrito";
  }

}
