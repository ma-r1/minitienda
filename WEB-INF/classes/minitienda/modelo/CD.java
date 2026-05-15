package minitienda.modelo;
import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * Esta clase se utiliza como representación del objeto CD, es serializable para poder
 * enviar los datos a través de solicitudes sin problema
 */
public class CD implements Serializable{
  private String nombre; // String completo separado por delimitadores "|" que contiene toda la información del disco 
  private String titulo; // Título del CD
  private String autor; // Autor del CD
  private String pais; // País de origen del CD

  private float precio; // Precio del CD
  private int cantidad; // Cantidad de CDs(utilizado para los pedidos)

  public CD(String nombre, int cantidad){
    this.nombre=nombre;
    this.cantidad=cantidad;
  }

  // Getters
  public String getNombre() {return this.nombre;}
  public float getPrecio() {return this.precio;}
  public int getCantidad() {return this.cantidad;}
  public String getTitulo() {return this.titulo;}
  public String getAutor() {return this.autor;}
  public String getPais() {return this.pais;}

  // Setters
  public void setNombre(String nombre) {this.nombre = nombre;}
  public void setPrecio(float precio) {this.precio = precio;}
  public void setCantidad(int cantidad) {this.cantidad = cantidad;}
  public void setTitulo(String titulo) {this.titulo = titulo;}
  public void setAutor(String autor) {this.autor = autor;}
  public void setPais(String pais) {this.pais = pais;}

  /**
   * Fuunción para "calcular" el precio del CD, es decir,
   * parsear el string para obtener el número en punto flotante
   * @return
   */
  public boolean calcularPrecio(){
    String preciostr;
    StringTokenizer t = new StringTokenizer(this.nombre,"|");
    this.titulo = t.nextToken().trim();
    this.autor = t.nextToken().trim();
    this.pais = t.nextToken().trim();

    preciostr = t.nextToken();
    preciostr = preciostr.replace('$',' ').trim();
    this.precio = Float.parseFloat(preciostr);

    if(this.precio > 0){
      // aquí se debería también comprobar si correspondiese el precio con una
      // entrada de la base de datos
      return true;
    } else {
      // caso precio menor o igual a 0 
      return false;
    }
  }
}
