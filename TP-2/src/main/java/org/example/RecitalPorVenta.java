package org.example;

public class RecitalPorVenta {

    //Datos venta
    private int id;

    private int recital;

    private String comprador;

    private int cant_entradas;

    private int cuotas;

    private int total;

    //Datos recital
    private int idRecital;

    private String artista;

    private int precioEntrada;

    private int capacidadLocal;

    private int ocupacion;

    private boolean cancelada;

    public RecitalPorVenta() {
    }

    public RecitalPorVenta(int id, int recital, String comprador, int cant_entradas, int cuotas, int total, int idRecital, String artista, int precioEntrada, int capacidadLocal, int ocupacion, boolean cancelada) {
        this.id = id;
        this.recital = recital;
        this.comprador = comprador;
        this.cant_entradas = cant_entradas;
        this.cuotas = cuotas;
        this.total = total;
        this.idRecital = idRecital;
        this.artista = artista;
        this.precioEntrada = precioEntrada;
        this.capacidadLocal = capacidadLocal;
        this.ocupacion = ocupacion;
        this.cancelada = cancelada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRecital() {
        return idRecital;
    }

    public void setIdRecital(int idRecital) {
        this.idRecital = idRecital;
    }

    public int getRecital() {
        return recital;
    }

    public void setRecital(int recital) {
        this.recital = recital;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public int getCant_entradas() {
        return cant_entradas;
    }

    public void setCant_entradas(int cant_entradas) {
        this.cant_entradas = cant_entradas;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    @Override
    public String toString() {
        return "RecitalPorVenta{" +
                "id=" + id +
                ", recital=" + recital +
                ", comprador='" + comprador + '\'' +
                ", cant_entradas=" + cant_entradas +
                ", cuotas=" + cuotas +
                ", total=" + total +
                ", idRecital=" + idRecital +
                ", artista='" + artista + '\'' +
                ", precioEntrada=" + precioEntrada +
                ", capacidadLocal=" + capacidadLocal +
                ", ocupacion=" + ocupacion +
                ", cancelada=" + cancelada +
                '}';
    }
}
