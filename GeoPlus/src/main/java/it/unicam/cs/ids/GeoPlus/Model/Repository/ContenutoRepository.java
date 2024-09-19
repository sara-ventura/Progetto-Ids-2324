package it.unicam.cs.ids.GeoPlus.Model.Repository;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenutoRepository extends JpaRepository<Contenuto, Long> {

}
