package minitienda.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Este Helper implementa la funcionalidad 6 del enunciado a través de login.jsp
 * proporcionando un formulario para rellanar los datos de sesión del usuario o registrarse 
 */
public class HelperMostrarLogin implements Helper {
  @Override
  public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception{
    return "/login.jsp";
  }
}
