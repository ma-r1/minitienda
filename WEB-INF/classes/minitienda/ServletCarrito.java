package minitienda;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletCarrito extends HttpServlet {

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

      HttpSession session = request.getSession(true);
      ArrayList<CD> cds = (ArrayList<CD>) session.getAttribute("cds");
      if (cds == null){
        cds = new ArrayList<>();
        session.setAttribute("cds", cds);
      }

      String accion = request.getParameter("accion");
      if("eliminar".equals(accion)){
        String indicestring = request.getParameter("indice");
        if (indicestring != null){
          try {
            int indice = Integer.parseInt(indicestring);
            if (indice >= 0 && indice < cds.size()) cds.remove(indice);
          } catch (NumberFormatException e) {System.out.println("Índice inválido.");}
        }
      }
      
      String nombrecd = request.getParameter("nombre");
      String cantidadstring = request.getParameter("cantidad");
      if (nombrecd != null && !nombrecd.trim().isEmpty() && cantidadstring != null){
        try {
          int cantidad = Integer.parseInt(cantidadstring);
          if (cantidad > 0){
            CD nuevocd = new CD(nombrecd, cantidad);
            nuevocd.calcularPrecio();
            cds.add(nuevocd);
          }
        } catch (NumberFormatException e) {System.out.println("La cantidad debe ser un número.");}
      } 

      
    RequestDispatcher dispatcher = request.getRequestDispatcher("/carrito.jsp");
    dispatcher.forward(request, response);
 
  }
}
