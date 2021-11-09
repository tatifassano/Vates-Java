package cursoVates.tp3.api;

import cursoVates.tp3.datos.VentasDAO;
import cursoVates.tp3.datos.VentasRepository;
import cursoVates.tp3.entidades.Venta;
import cursoVates.tp3.entidades.VentasView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentasAPI {

    @Autowired
    VentasDAO v;
    @Autowired
    VentasRepository vr;

    //obtengo todas las ventas
    @GetMapping("/todas")
    public List<Venta> obtenerTodas(){

        return v.obtenerVentas();
    }

    //obtengo todas las ventas usando ventasRepository.
    @GetMapping("/todas1")
    public List obtenerTodas1()
    {
        return vr.getAllByOrderByComprador();
    }

    //obtengo todas las ventas por cuotas
    @GetMapping("/{cuotas}")
    public List obtenerCuotas(@PathVariable int cuotas){

        return vr.getAllByCuotasEquals(cuotas);
    }

    //Ventas ordenadas por cuotas
    @GetMapping("/cuotas")
    public List obtenerVentasPorEntradasYCuotas(){
        return vr.getAllByOrderByCuotas();
    }

    //Devuelve la cantidad de ventas con cuotas = 3.
    @GetMapping("/cantidad")
    public int cantidad() {
        return vr.cantidad();
    }

    //Devuelve el total de una venta.
    @GetMapping("/total/{precio}/{cantEntradas}/{cuotas}")
    public int calcularTotal(@PathVariable int precio, @PathVariable int cantEntradas, @PathVariable int cuotas){
        int total = v.calcularTotal(precio, cantEntradas, cuotas);
        return total;
    }

    //Una forma de agregar.
    @PutMapping("/{recital}/{comprador}/{cantEntradas}/{cuotas}/{total}")
    public int agregar1(Venta nueva){

        nueva = new Venta(nueva.getRecital(), nueva.getComprador(), nueva.getCantEntradas(), nueva.getCuotas(), nueva.getTotal());
        v.agregarUnaVenta1(nueva);

        return nueva.getId();
    }

    //Otra forma de agregar.
    @PutMapping("/")
    public String agregarVenta(@RequestBody Venta nueva)
    {
        return v.agregarUnaVenta(nueva);
    }

    //Recaudacion por mes
    @GetMapping("/recaudacionMes")
    public String recuadacionPorMes(){

        return v.recaudacionPorMes();
    }

    //Ventas ordenadas por total
    @GetMapping("/total")
    public List ventasPorTotal(){
        return vr.getAllByOrderByTotalDesc();
    }


    //Ventas ordenadas por cant entradas
    @GetMapping("/cantEntradas")
    public List ventasPorEntradas(){
        return vr.getAllByOrderByCantEntradasDesc();
    }

}
