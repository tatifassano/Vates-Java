package cursoVates.tp3.entidades;

public class RecitalPorVenta {

    //comprador
    private String comprador;

    //cantidad entradas
    private int cantEntradas;

    //cuotas
    private int cuotas;

    //total
    private int total;

    //aritsta
    private String artista;

    //precio entrada
    private int precio;

    //capacidad
    private int capacidadLocal;

    //ocupacion
    private int ocupacion;


    public RecitalPorVenta(String comprador, int cantEntradas, int cuotas, int total, String artista, int precio, int capacidadLocal, int ocupacion) {
        this.comprador = comprador;
        this.cantEntradas = cantEntradas;
        this.cuotas = cuotas;
        this.total = total;
        this.artista = artista;
        this.precio = precio;
        this.capacidadLocal = capacidadLocal;
        this.ocupacion = ocupacion;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public int getCantEntradas() {
        return cantEntradas;
    }

    public void setCantEntradas(int cantEntradas) {
        this.cantEntradas = cantEntradas;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
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

    @Override
    public String toString() {
        return "RecitalPorVenta{" +
                "comprador='" + comprador + '\'' +
                ", cantEntradas=" + cantEntradas +
                ", cuotas=" + cuotas +
                ", total=" + total +
                ", artista='" + artista + '\'' +
                ", precio=" + precio +
                ", capacidadLocal=" + capacidadLocal +
                ", ocupacion=" + ocupacion +
                '}';
    }
}
