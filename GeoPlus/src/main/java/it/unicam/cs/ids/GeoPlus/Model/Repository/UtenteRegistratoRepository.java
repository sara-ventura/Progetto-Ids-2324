package it.unicam.cs.ids.GeoPlus.Model.Repository;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository per la gestione delle operazioni di persistenza degli oggetti {@link UtenteRegistrato}.
 * Estende {@link JpaRepository} per ereditare i metodi CRUD di base e definisce metodi personalizzati
 * per l'accesso ai dati.
 *
 */
@Repository
public interface UtenteRegistratoRepository extends JpaRepository<UtenteRegistrato, Long> {

    List<UtenteRegistrato> findByComuneAppartenenza(Comune comune);

    UtenteRegistrato findByIdCredenziali(Long idCredenziali);

    @Query("SELECT u FROM UtenteStandard u")

    List<UtenteStandard> findAllStandard();

}
