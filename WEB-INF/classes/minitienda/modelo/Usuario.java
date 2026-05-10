package minitienda.modelo;
import java.io.Serializable;
import java.util.StringTokenizer;

public class Usuario implements Serializable{
  private String correo;
  private String password;
  private String tipo_tarjeta;
  private String num_tarjeta;

  public Usuario(String correo, String password, String tipo_tarjeta, String num_tarjeta){
    this.correo = correo;
    this.password = password;
    this.tipo_tarjeta = tipo_tarjeta;
    this.num_tarjeta = num_tarjeta;
  }

  public String getCorreo() {return this.correo;}
  public String getPassword() { return this.password;}
  public String getTipo_tarjeta() { return this.tipo_tarjeta;}
  public String getNum_tarjeta() { return this.num_tarjeta;}

  public void setCorreo(String correo) {this.correo = correo;}
  public void setPassword(String password) {this.password = password;}
  public void setTipo_tarjeta(String tipo_tarjeta) {this.tipo_tarjeta = tipo_tarjeta;}
  public void setNum_tarjeta(String num_tarjeta) {this.num_tarjeta = num_tarjeta;}
  
}
