package org.example;

public class RecaudacionPorRecital {

    private int id;

    private String artista;

    private long total;

    public RecaudacionPorRecital(int id, String artista ,long total) {
        this.id = id;
        this.artista = artista;
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

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return  "Id: " + id +
                ", Artista: '" + artista + '\'' +
                ", Total: " + " $" + total;
    }
}
