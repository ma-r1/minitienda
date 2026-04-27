package minitienda;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletCaja extends HttpServlet {

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    String accion = request.getParameter("accion");
    HttpSession session = request.getSession();
    if("finalizar".equals(accion)){
      session.removeAttribute("cds");
      session.removeAttribute("total");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
      dispatcher.forward(request, response);
    }
    else {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/caja.jsp");
      dispatcher.forward(request, response);
    }

  }
}
