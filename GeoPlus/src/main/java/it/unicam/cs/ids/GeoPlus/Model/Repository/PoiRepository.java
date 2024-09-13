package it.unicam.cs.ids.GeoPlus.Model.Repository;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {
    Poi findByPosizionePoi(Coordinate posizione);

    List<Poi> findAllByNomePoi(String nomePoi);


    @Query("SELECT p FROM PoiTemporaneo p")
    List<PoiTemporaneo> findAllTemporanei();

    Poi findByNomePoi(String nomePoi);
}
