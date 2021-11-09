package org.example;


import javax.persistence.*;

@Entity
@Table(name="recitales")
public class Recital {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nombre_artista")
    private String artista;

    @Column(name="precio_entrada")
    private int precioEntrada;

    @Column(name="capacidad_local")
    private int capacidadLocal;

    @Column(name="ocupacion")
    private int ocupacion;

    @Column(name="cancelada")
    private boolean cancelada;

    public Recital(){

    }


    public Recital(String artista, int precioEntrada, int capacidadLocal) {
        this.artista = artista;
        this.precioEntrada = precioEntrada;
        this.capacidadLocal = capacidadLocal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(int precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public int getCapacidadLocal() {
        return capacidadLocal;
    }

    public void setCapacidadLocal(int capacidadLocal) {
        this.capacidadLocal = capacidadLocal;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(int ocupacion) {
        this.ocupacion = ocupacion;
    }

    public boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    @Override
    public String toString() {
        return "Recital: " +
                "Id: " + id +
                " -Artista:'" + artista + '\'' +
                " -Precio Entrada: " + "$" + precioEntrada +
                " -Capacidad del Local: " + capacidadLocal +
                " -Ocupacion: " + ocupacion +
                " -Cancelada: " + cancelada;
    }
}
