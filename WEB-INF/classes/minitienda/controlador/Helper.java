package minitienda.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Helper {
  //Devuelve la vista a la que se debe redirigir
  String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
