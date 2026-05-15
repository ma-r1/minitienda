package minitienda.modelo;

import java.io.Serializable;

/**
 *  Esta clase se utiliza para modelar los datos de un pedido, es serializable para poder enviar los datos
 *  a través de las peticiones
 * 
*/
public class Pedido implements Serializable {
    private int id; //ID del pedido
    private String correoUsuario; //correo del usuario que realizó el pedido
    private float importe; //importe total del pedido

    public Pedido(){}

    public Pedido(int id, String correoUsuario, float importe) {
        this.id = id;
        this.correoUsuario = correoUsuario;
        this.importe = importe;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public float getImporte() {
        return importe;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
}