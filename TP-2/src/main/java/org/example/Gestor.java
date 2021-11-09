package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Gestor {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("personasPU");
    EntityManager em = emf.createEntityManager();


    //Alta Recital
    public void altaRecital(String nombre, int precio, int capacidad){

        Recital nuevo = new Recital(nombre, precio, capacidad);
        em.merge(nuevo);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.flush();
        t.commit();
    }

    //Alta venta
    public void altaVenta(int numeroRecital, String comprador, int cantidadEntradas, int cuotas, int total){

        Venta nueva = new Venta(numeroRecital, comprador, cantidadEntradas, cuotas, total);
        em.merge(nueva);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.flush();
        t.commit();
    }

    //Busqueda de recital por id
    public Recital buscarRecital(int numero){

        Recital buscado = em.find(Recital.class, numero);
        return buscado;
    }

    //Busqueda de venta por id
    public Venta buscarVenta(int numero){

        Venta buscado = em.find(Venta.class, numero);
        return  buscado;
    }

    //Calculo total devolver en caso de cancelacion
    public int calcularTotalDevolver(int precio, int ocupacion){

        int devolver = 0;

        devolver = (int) (precio * ocupacion * 1.30);

        return devolver;
    }

    //Calculo de total de venta segun cantidad de cuotas
    public int calcularTotal(int precio, int cantidadEntradas, int cuotas){

        int total = 0;

        if(cuotas == 1){
            total = (precio * cantidadEntradas);
        }
        else if(cuotas == 3){
            total = (int) (precio * cantidadEntradas * 1.10);
        }
        else if(cuotas == 6){
            total = (int) (precio * cantidadEntradas * 1.15);
        }

        return total;
    }

    //Listado completo de recitales
    public List<Recital> listarRecitales(){

        TypedQuery<Recital> consulta = em.createQuery("select r from Recital r ", Recital.class);
        consulta.getResultStream().forEach(System.out::println);
        return consulta.getResultList();
    }

    public List<Recital> listarRecitalesSinCancelar(){

        TypedQuery<Recital> consulta = em.createQuery("select r from Recital r where r.cancelada = false ", Recital.class);
        consulta.getResultStream().forEach(System.out::println);
        return consulta.getResultList();
    }

    //Listado completo de recitales. Otra forma
    public List<Recital> listarRecitales1(){

        List<Recital> consulta = em.createQuery("select r from Recital r ", Recital.class).getResultList();

        return consulta;
    }

    //Listado completo de ventas.
    public List<Venta> listarVentas1(){

        List<Venta> consulta = em.createQuery("select v from Venta v ", Venta.class).getResultList();

        return consulta;
    }

    //Listado completo de ventas.
    public List<Venta> listarVentas(){

        TypedQuery<Venta> consulta = em.createQuery("select v from Venta  v", Venta.class);
        consulta.getResultStream().forEach(System.out::println);
        return consulta.getResultList();
    }

    //Suma ocupacion al recital.
    public void sumarEntradas(int entradas, Recital recital){

        if(recital != null){
            recital.setOcupacion(recital.getOcupacion() + entradas);
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.flush();
            em.merge(recital);
            t.commit();
        }
    }

    //Cancela recital, poniendo cancelada en true.
    public void cancelarRecital(Recital recital){

        if(recital != null){
            recital.setCancelada(true);
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.flush();
            em.merge(recital);
            t.commit();
        }
    }

    //Listado de recitales con convocatoria mayor al 80%. Sin contar los canclados.
    public List<RecaudacionPorRecital> mayorConvocatoria(){

        //StringBuilder sb = new StringBuilder();

        TypedQuery<RecaudacionPorRecital> consulta = em.createQuery("select new org.example.RecaudacionPorRecital(r.id, r.artista, sum(v.total))  " +
                "from Recital r join Venta v on (r.id=v.recital) " +
                "where r.ocupacion> (r.capacidadLocal*0.8) and r.cancelada = false " +
                "group by r.id", RecaudacionPorRecital.class);
        List<RecaudacionPorRecital> lista = consulta.getResultList();

        lista.stream().forEach(System.out::println);
        return lista;
    }

    //Mejor venta. Con cuota = 1 y sin tener en cuenta los recitales cancelados.
    //Ya que se devuelve el importe de esa venta al cancelar el recital.
    /*public String mejorVenta(){

        StringBuilder sb = new StringBuilder();

        List<Venta> consulta = em.createQuery("SELECT v \n" +
                "                            FROM Venta v join Recital r on v.recital = r.id \n" +
                "                            where r.cancelada = false and v.total = (select MAX(v1.total)" +
                "                               from Venta v1 join Recital r2 on v1.recital = r2.id " +
                "                               where v1.cuotas = 1 and r2.cancelada = false)", Venta.class).getResultList();

        for(Venta v : consulta){
            sb.append((v) + " \n ");
        }
        return sb.toString();
    }*/

    //Recital asociado a la mejor venta. Con cuota = 1 y sin tener en cuenta los recitales cancelados.
    //Ya que se devuelve el importe de esa venta al cancelar el recital.
   /* public String recitalMayorVenta1(){

        StringBuilder sb = new StringBuilder();

        List<Recital> consulta = em.createQuery("SELECT r" +
                " \n" +
                "                            FROM Venta v join Recital r on v.recital = r.id \n" +
                "                            where r.cancelada = false and v.total = (select MAX(v1.total)" +
                "                               from Venta v1 join Recital r2 on v1.recital = r2.id " +
                "                               where v1.cuotas = 1 and r2.cancelada = false)", Recital.class).getResultList();

        for(Recital r : consulta){
            sb.append("Recital asociado a la mejor venta: "  + "Id: " + r.getId() + " - " + " Artista: " + r.getArtista());
        }
        return sb.toString();
    }*/

    //Mejor venta + datos de recital asociado.
    public List<RecitalPorVenta> recitalMayorVenta(){

        TypedQuery<RecitalPorVenta> consulta = em.createQuery("SELECT new org.example.RecitalPorVenta(v.id, v.recital, v.comprador, v.cant_entradas, v.cuotas, v.total, r.id, r.artista, r.precioEntrada, r.capacidadLocal, r.ocupacion, r.cancelada)" +
                " \n" +
                "                            FROM Venta v join Recital r on v.recital = r.id \n" +
                "                            where r.cancelada = false and v.total = (select MAX(v1.total)" +
                "                               from Venta v1 join Recital r2 on v1.recital = r2.id " +
                "                               where v1.cuotas = 1 and r2.cancelada = false)", RecitalPorVenta.class);

        List<RecitalPorVenta> lista = consulta.getResultList();
        lista.stream().forEach(System.out::println);
        return lista;
    }

    //Devuelve recitales completos. Para mostrar en cartelera. Sin contar los cancelados.
    public String recitalesCompletos(){

        StringBuilder sb = new StringBuilder();

        List<Recital> lista =  listarRecitales1();

        for(Recital r : lista){

            if(!r.getCancelada()){
                if(r.getOcupacion() == r.getCapacidadLocal()){
                    sb.append((r).getArtista() + " - " + " COMPLETO " + "\n ");
                }
                else if(r.getOcupacion() < r.getCapacidadLocal()){
                    sb.append((r).getArtista() + " - " + " Quedan " + (r.getCapacidadLocal() - r.getOcupacion()) + " entradas disponibles " + "\n ");
                }
            }
        }
        return sb.toString();
    }

    //Calculo para saber cuanto cobrar en los proximos 6 meses
    public String recaudacionPorMes(){

        StringBuilder sb = new StringBuilder();

        List<Venta> lista =  listarVentas1();
        int totalCuota1 = 0;
        int totalCuota3 = 0;
        int totalCuota6 = 0;
        int cuota3Dividida = 0;
        int cuota6Dividida = 0;

        for(Venta v : lista){
            if(v.getCuotas() == 1){
                totalCuota1 += v.getTotal();
            }
            else if(v.getCuotas() == 3){
                totalCuota3 += v.getTotal();
            }
            else if(v.getCuotas() == 6){
                totalCuota6 += v.getTotal();
            }
        }

        cuota3Dividida = totalCuota3 / 3;
        cuota6Dividida = totalCuota6 / 6;

        sb.append("El total a cobrar el mes 1 es: " + (totalCuota1 + cuota3Dividida + cuota6Dividida) + " \n ");
        sb.append("El total a cobrar el mes 2 es: " + (cuota3Dividida + cuota6Dividida) + " \n ");
        sb.append("El total a cobrar el mes 3 es: " + (cuota3Dividida + cuota6Dividida) + " \n ");
        sb.append("El total a cobrar el mes 4 es: " + cuota6Dividida + " \n ");
        sb.append("El total a cobrar el mes 5 es: " + cuota6Dividida + " \n ");
        sb.append("El total a cobrar el mes 6 es: " + cuota6Dividida);
        return sb.toString();
    }

}
