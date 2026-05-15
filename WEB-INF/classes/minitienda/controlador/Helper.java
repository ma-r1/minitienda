package minitienda.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Clase genérica utilizada como base para implementar los planes necesarios de acción necesarios
 * ajustados a las acciones ejecutadas por el usuario.
 * 
 * El helper implementa la lógica de como procesar la acción del usuario
 * y devuelve la ruta de la vista en .jsp con la que se responde
 * 
 */
public interface Helper {
  //Devuelve la vista a la que se debe redirigir
  String ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
