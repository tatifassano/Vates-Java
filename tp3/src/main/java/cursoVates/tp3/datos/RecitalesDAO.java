package cursoVates.tp3.datos;

import cursoVates.tp3.entidades.RecaudacionPorRecital;
import cursoVates.tp3.entidades.Recital;
import cursoVates.tp3.entidades.RecitalPorVenta;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@ApplicationScope
public class RecitalesDAO {

    @PersistenceContext EntityManager em;

    public List<Recital> obtenerRecitales(){
        List<Recital> salida = em.createQuery("select r from Recital r", Recital.class).getResultList();
        return salida;
    }

    public Recital obtenerPorId(int id){
        Recital buscado = em.find(Recital.class, id);
        return buscado;
    }

    /*public Recital obtenerPorNombre(String nombre){

        //Recital buscado = em.createQuery("select r from Recital r where r.artista = nombre", Recital.class).getSingleResult();
        Recital buscado = em.find(Recital.class, nombre);
        return buscado;
    }*/

    @Transactional
    public void agregarUnRecital(Recital nuevo)
    {
        if(nuevo!= null) {
            em.persist(nuevo);
        }
    }

    //Modifica cancelado a true.
    @Transactional
    public void modificarRecital(int id, Recital modificado)
    {
        Recital r = em.find(Recital.class, id);
        if(r!=null) {
            if (modificado.getArtista() != null) r.setArtista(modificado.getArtista());
            if (modificado.getPrecioEntrada() != 0) r.setPrecioEntrada(modificado.getPrecioEntrada());
            if (modificado.getCapacidadLocal() != 0) r.setCapacidadLocal(modificado.getCapacidadLocal());
            if (modificado.getOcupacion() != 0) r.setOcupacion(modificado.getOcupacion());
            if (modificado.getCancelada()) r.setCancelada(modificado.getCancelada());
        }
    }

    @Transactional
    public String cancelarRecital(int id, Recital modificado)
    {
        String res ="El total a devolver es: ";
        Recital r = em.find(Recital.class, id);
        if(r!=null) {
            if (modificado.getCancelada()) r.setCancelada(modificado.getCancelada());
        }
        int totalADevolver = calcularTotalDevolver(r.getPrecioEntrada(), r.getOcupacion());

        return res + totalADevolver;
    }

    //Calculo total devolver en caso de cancelacion
    public int calcularTotalDevolver(int precio, int ocupacion){

        int devolver = 0;

        devolver = (int) (precio * ocupacion * 1.30);

        return devolver;
    }

    //Mayor Recaudacion
    public List<RecaudacionPorRecital> mayorConvocatoria(){

        TypedQuery<RecaudacionPorRecital> consulta = em.createQuery("select new cursoVates.tp3.entidades.RecaudacionPorRecital(r.id, r.artista, r.precioEntrada, r.capacidadLocal, r.ocupacion, r.cancelada ,sum(v.total))  " +
                "from Recital r join Venta v on (r.id=v.recital) " +
                "where r.ocupacion> (r.capacidadLocal*0.8) and r.cancelada = false " +
                "group by r.id", RecaudacionPorRecital.class);
        List<RecaudacionPorRecital> lista = consulta.getResultList();

        lista.stream().forEach(System.out::println);
        return lista;
    }

    //Mejor Venta
    public List<RecitalPorVenta> mejorVenta(){

        List<RecitalPorVenta> consulta = em.createQuery("SELECT new cursoVates.tp3.entidades.RecitalPorVenta(v.comprador, v.cantEntradas, v.cuotas, v.total, r.artista, r.precioEntrada, r.capacidadLocal, r.ocupacion) \n" +
                "                            FROM Venta v join Recital r on v.recital = r.id \n" +
                "                            where r.cancelada = false and v.total = (select MAX(v1.total)" +
                "                               from Venta v1 join Recital r2 on v1.recital = r2.id " +
                "                               where v1.cuotas = 1 and r2.cancelada = false)", RecitalPorVenta.class).getResultList();


        return consulta;
    }

    //obtengo los recitales completos y las entradas disponibles de los no completos. Sin tener en cuenta los cancelados.
    public String recitalesCompletos(){

        StringBuilder sb = new StringBuilder();

        List<Recital> lista =  obtenerRecitales();

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


}
