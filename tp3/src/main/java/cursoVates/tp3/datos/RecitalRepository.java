package cursoVates.tp3.datos;

import cursoVates.tp3.entidades.Recital;
import cursoVates.tp3.entidades.RecitalView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecitalRepository extends JpaRepository<Recital, Integer> {

    List<RecitalView> getAllByOrderByArtista();

    //cantidad de cancelados
    @Query("select count(r) from Recital r where r.cancelada = true" )
    int cantidadCancelados();

    //Recitales cancelados
    List<RecitalView> getAllByCanceladaTrue();

    //Recitales disponibles. No completos y no cancelados
    @Query("select r from Recital r where r.ocupacion < r.capacidadLocal and r.cancelada = false")
    List<RecitalView> recitalesDisponibles();

    //Recitales completos y no cancelados
    @Query("select r from Recital r where r.ocupacion = r.capacidadLocal and r.cancelada = false")
    List<RecitalView> recitalesCompletos();


}
