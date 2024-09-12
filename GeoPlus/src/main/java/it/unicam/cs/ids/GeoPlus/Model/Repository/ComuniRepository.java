package it.unicam.cs.ids.GeoPlus.Model.Repository;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuniRepository extends JpaRepository<Comune, Long> {
    Comune findByNomeComune(String nomeComune);
}
