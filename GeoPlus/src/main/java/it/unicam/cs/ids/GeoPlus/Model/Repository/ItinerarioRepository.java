package it.unicam.cs.ids.GeoPlus.Model.Repository;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

    List<Itinerario> findAllByApprovatoAndNomeItinerario(Boolean approvato, String nomeItinerario);

    List<Itinerario> findAllByApprovatoAndComune(boolean approvato, Comune comune);

    @Query("SELECT i FROM Itinerario i JOIN i.listaPoi p WHERE p IN :listaPoi GROUP BY i HAVING COUNT(p) = :size")
    List<Itinerario> findByListaPoi(@Param("listaPoi") List<Poi> listaPoi, @Param("size") long size);
}
