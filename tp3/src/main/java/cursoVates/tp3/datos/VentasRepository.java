package cursoVates.tp3.datos;

import cursoVates.tp3.entidades.Venta;
import cursoVates.tp3.entidades.VentasView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentasRepository extends JpaRepository<Venta, Integer> {

    //Obtener todas las ventas ordenadas por comprador.
    List<VentasView> getAllByOrderByComprador();

    //Obtener todas las ventas con cuotas igual al parametro.
    List<VentasView> getAllByCuotasEquals(int cuotas);

    //Lista las ventas ordenadas por cuotas.
    List<VentasView> getAllByOrderByCuotas();

    //cantidad de cuotas = 3.
    @Query("select count(v) from Venta v where v.cuotas=3" )
    int cantidad();

    //Ventas ordenadas por total
    List<VentasView> getAllByOrderByTotalDesc();

    //Ventas ordenadas por cant entradas
    List<VentasView> getAllByOrderByCantEntradasDesc();


}
