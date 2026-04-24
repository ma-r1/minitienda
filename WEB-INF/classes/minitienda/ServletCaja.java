package minitienda;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletCaja extends HttpServlet {

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

    RequestDispatcher dispatcher = request.getRequestDispatcher("/caja.jsp");
    dispatcher.forward(request, response);
 
  }
}
