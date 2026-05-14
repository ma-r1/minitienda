package minitienda.modelo;
import java.io.Serializable;
import java.util.StringTokenizer;

public class CD implements Serializable{
  private String nombre;
  private float precio;
  private int cantidad;

  public CD(String nombre, int cantidad){
    this.nombre=nombre;
    this.cantidad=cantidad;
  }

  public String getNombre() {return this.nombre;}
  public float getPrecio() {return this.precio;}
  public int getCantidad() {return this.cantidad;}

  public void setNombre(String nombre) {this.nombre = nombre;}
  public void setPrecio(float precio) {this.precio = precio;}
  public void setCantidad(int cantidad) {this.cantidad = cantidad;}
  
  public boolean calcularPrecio(){
    String preciostr;
    StringTokenizer t = new StringTokenizer(this.nombre,"|");
    t.nextToken();
    t.nextToken();
    t.nextToken();
    preciostr = t.nextToken();
    preciostr = preciostr.replace('$',' ').trim();
    if(this.precio > 0){
      // aquí se debería también comprobar si correspondiese el precio con una
      // entrada de la base de datos
      this.precio = Float.parseFloat(preciostr);
      return true;
    } else {
      // caso precio menor o igual a 0 
      return false;
    }
  }
}
