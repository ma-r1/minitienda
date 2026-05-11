package minitienda.modelo;

import java.io.Serializable;

public class Pedido implements Serializable {
    private int id;
    private String correoUsuario;
    private float importe;

    public Pedido() {
    }

    public Pedido(int id, String correoUsuario, float importe) {
        this.id = id;
        this.correoUsuario = correoUsuario;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public float getImporte() {
        return importe;
    }

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