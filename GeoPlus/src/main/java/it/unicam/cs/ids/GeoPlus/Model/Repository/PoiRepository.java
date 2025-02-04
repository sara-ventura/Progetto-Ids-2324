package it.unicam.cs.ids.GeoPlus.Model.Repository;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {
    Poi findByPosizionePoi(Coordinate posizione);

    List<Poi> findAllByNomePoi(String nomePoi);


    @Query("SELECT p FROM PoiTemporaneo p")
    List<PoiTemporaneo> findAllTemporanei();

    @Query("SELECT p FROM PoiTemporaneo p WHERE p.Id = :id")
    Optional<PoiTemporaneo> findTemporaneoById(@Param("id") Long id);


    Poi findByNomePoi(String nomePoi);

    List<Poi> findAllByApprovatoAndNomePoi(boolean b, String nomePoi);
}
