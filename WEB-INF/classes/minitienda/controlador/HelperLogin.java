package minitienda.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda.modelo.Usuario;
import minitienda.modelo.dao.DAOUsuario;

public class HelperLogin implements Helper{
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    DAOUsuario dao = new DAOUsuario();

    if (dao.validarUsuario(email, password)){
      HttpSession session = request.getSession();
      session.invalidate();
      return "redirect:index.html";
    } else return "/login.jsp";
  }
}
