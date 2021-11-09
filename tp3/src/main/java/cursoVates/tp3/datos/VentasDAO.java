package cursoVates.tp3.datos;

import cursoVates.tp3.entidades.Recital;
import cursoVates.tp3.entidades.Venta;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@ApplicationScope
public class VentasDAO {

    @PersistenceContext EntityManager em;

    //Obtener todas las ventas
    public List<Venta> obtenerVentas(){
        List<Venta> salida = em.createQuery("select v from Venta v", Venta.class).getResultList();
        return salida;
    }


    //Agrego venta sumando ocupacion al recital y calculado total de la venta.
    //No puedo mostrar los mensajes en las excepciones. Pongo string para mostrar que anda bien.
       @Transactional
    public String agregarUnaVenta(Venta nueva) {

        String res = "";
        if (nueva != null) {
            Recital recital = em.find(Recital.class, nueva.getRecital());
            if (!recital.getCancelada()) {

                if (nueva.getCantEntradas() <= (recital.getCapacidadLocal() - recital.getOcupacion())) {

                    recital.setOcupacion(recital.getOcupacion() + nueva.getCantEntradas());
                    nueva.setTotal(calcularTotal(recital.getPrecioEntrada(), nueva.getCantEntradas(), nueva.getCuotas()));
                    em.persist(nueva);
                    res = "VENTA ACEPTADA";

                } else {
                    if (recital.getOcupacion() == recital.getCapacidadLocal())
                       res = "RECITAL COMPLETO, NO HAY MAS ENTRADAS";
                        //throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "RECITAL COMPLETO");
                    if (recital.getOcupacion() < recital.getCapacidadLocal()) {
                        //throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ENTRADAS INSUFICIENTES");
                        res = "ENTRADAS INSUFICIENTES, QUEDAN: " + (recital.getCapacidadLocal() - recital.getOcupacion()) + " ENTRADAS DISPONIBLES";
                    }
                }
            } else {
                res = "RECITAL CANCELADO, NO HAY VENTA DE ENTRADAS";
            }
        }
        return res;
    }

    //Prueba para agregar una venta con parametros.
    @Transactional
    public void agregarUnaVenta1(Venta nueva)
    {
        em.persist(nueva);
    }

    //Metodo para calcular total de una venta, teniendo en cuenta precio, cantidad de entradas y cuotas.
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

    //Metodo para obtener la recaudacion por mes en los proximos 6 meses.
    public String recaudacionPorMes(){

        StringBuilder sb = new StringBuilder();

        List<Venta> lista =  obtenerVentas();
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

        sb.append("El total a cobrar el mes 1 es: " + "$" + (totalCuota1 + cuota3Dividida + cuota6Dividida) + " \n ");
        sb.append("El total a cobrar el mes 2 es: " + "$" + (cuota3Dividida + cuota6Dividida) + " \n ");
        sb.append("El total a cobrar el mes 3 es: " + "$" + (cuota3Dividida + cuota6Dividida) + " \n ");
        sb.append("El total a cobrar el mes 4 es: " + "$" + cuota6Dividida + " \n ");
        sb.append("El total a cobrar el mes 5 es: " + "$" + cuota6Dividida + " \n ");
        sb.append("El total a cobrar el mes 6 es: " + "$" + cuota6Dividida);
        return sb.toString();
    }

}
