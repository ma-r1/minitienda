package minitienda.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.Usuario;
import minitienda.modelo.dao.DAOUsuario;

public class HelperRegistrar implements Helper{
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String tipo_tarjeta = request.getParameter("tipo_tarjeta");
    String num_tarjeta = request.getParameter("num_tarjeta");

    Usuario u = new Usuario (email, password, tipo_tarjeta, num_tarjeta);
    DAOUsuario dao = new DAOUsuario();

    if (dao.registrarUsuario(u)){
      HttpSession session = request.getSession();
      session.invalidate();
      return "redirect:index.html";
    } else return "/login.jsp";
  }
}
