package cursoVates.tp3.api;

import cursoVates.tp3.datos.RecitalRepository;
import cursoVates.tp3.datos.RecitalesDAO;
import cursoVates.tp3.entidades.RecaudacionPorRecital;
import cursoVates.tp3.entidades.Recital;
import cursoVates.tp3.entidades.RecitalPorVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/recitales")
public class RecitalesAPI {

    @Autowired
    RecitalesDAO r;
    @Autowired
    RecitalRepository rr;

    //Buscar todos los recitales
    @GetMapping("/todos")
    public List<Recital> obtenerTodos(){

        return r.obtenerRecitales();
    }

    //Buscar recital por id
    @GetMapping("/{id}")
    public Recital obtenerUno(@PathVariable int id){

        Recital buscado = r.obtenerPorId(id);
        if(buscado == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recital no encontrado");
        return buscado;
    }


    //Recitales ordenados por artista. Usando RecitalRepository.
    @GetMapping("/artista")
    public List recitalesPorArtista(){
        return rr.getAllByOrderByArtista();
    }

    //Cantidad de recitales cancelados. Usando RecitalRepository.
    @GetMapping("/cancelados")
    public int cantRecitalesCancelados(){
        return rr.cantidadCancelados();
    }

    //Lista de recitales cancelados. Usando RecitalRepository.
    @GetMapping("/cancelados1")
    public List recitalesCancelados(){
        return rr.getAllByCanceladaTrue();
    }

    //Lista de recitales disponibles. Usando RecitalRepository.
    @GetMapping("/disponibles")
    public List recitalesDisponibles(){
        return rr.recitalesDisponibles();
    }

    //Lista de recitales completos. Usando RecitalRepository.
    @GetMapping("/completos")
    public List recitalesCompletos(){
        return rr.recitalesCompletos();
    }

   /*@GetMapping("/nombre/{nombre}")
    public Recital obtenerUnoPorNombre(@PathVariable String nombre){

        Recital buscado = r.obtenerPorNombre(nombre);
        if(buscado == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recital no encontrado");
        return buscado;
    }*/

    //Mayor convocatoria
    @GetMapping("/mayorC")
    public List<RecaudacionPorRecital> obtenerMayorConvocatoria(){

        List<RecaudacionPorRecital> lista = r.mayorConvocatoria();

        return lista;
    }

    //Alta recital
    @PutMapping("/agregar")
    public void agregarRecital(@RequestBody Recital nuevo)
    {
        r.agregarUnRecital(nuevo);
    }

    //Mejor venta
    @GetMapping("/mejorVenta")
    public List<RecitalPorVenta> mejorVenta(){

        List<RecitalPorVenta> lista = r.mejorVenta();

        return lista;
    }

    //Cancelar Recital
    @PutMapping("/cancelar/{id}")
    public String cancelar(@PathVariable int id, @RequestBody Recital modificado) {

        return r.cancelarRecital(id, modificado);
    }

    //Modificar recital
    @PutMapping("/modificar/{id}")
    public void modificar(@PathVariable int id, @RequestBody Recital modificado) {

        r.modificarRecital(id, modificado);
    }

    //Mostrar cartelera
    @GetMapping("/cartelera")
    public String cartelera(){
        return r.recitalesCompletos();
    }




}
