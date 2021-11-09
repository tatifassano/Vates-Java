package org.example;


import javax.persistence.*;

@Entity
@Table(name="ventas")
public class Venta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private int id;

    @Column(name="id_recital")
    private int recital;

    @Column(name="comprador")
    private String comprador;

    @Column(name="cant_entradas")
    private int cant_entradas;

    @Column(name="cuotas")
    private int cuotas;

    @Column(name="total")
    private int total;

    public Venta(){

    }

    public Venta(int recital, String comprador, int cant_entradas, int cuotas, int total) {
        this.recital = recital;
        this.comprador = comprador;
        this.cant_entradas = cant_entradas;
        this.cuotas = cuotas;
        this.total = total;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCantEntradas() {
        return cant_entradas;
    }

    public void setCantEntradas(int cantEntradas) {
        this.cant_entradas = cantEntradas;
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

    @Override
    public String toString() {
        return  " Id: " + id +
                "- Recital: " + recital +
                "- Comprador: '" + comprador + '\'' +
                "- Cantidad Entradas: " + cant_entradas +
                "- Cuotas: " + cuotas +
                "- Importe Total: " + " $" + total;
    }
}
