package cursoVates.tp3.entidades;

import javax.persistence.Column;

public class RecaudacionPorRecital {

    private int id;

    private String artista;

    private int precioEntrada;

    private int capacidadLocal;

    private int ocupacion;

    private boolean cancelada;

    private long total;

    public RecaudacionPorRecital(int id, String artista, int precioEntrada, int capacidadLocal, int ocupacion, boolean cancelada ,long total) {
        this.id = id;
        this.artista = artista;
        this.precioEntrada = precioEntrada;
        this.capacidadLocal = capacidadLocal;
        this.ocupacion = ocupacion;
        this.cancelada = cancelada;
        this.total = total;
    }

    public int getRecital() {
        return id;
    }

    public void setRecital(long recital) {
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

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "RecaudacionPorRecital{" +
                "id=" + id +
                ", artista='" + artista + '\'' +
                ", precioEntrada=" + precioEntrada +
                ", capacidadLocal=" + capacidadLocal +
                ", ocupacion=" + ocupacion +
                ", cancelada=" + cancelada +
                ", total=" + total +
                '}';
    }
}
