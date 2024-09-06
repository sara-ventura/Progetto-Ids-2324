package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * La classe astratta Richiesta rappresenta una richiesta generica
 * nel sistema GeoPlus. Funziona come classe base per varie tipologie di richieste
 * che possono essere gestite nel sistema.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Richiesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RichiestaId;
    @ManyToOne
    private UtenteRegistrato autoreRichiesta;
    @ManyToOne
    private Comune comune;


    public Richiesta() {
    }

    /**
     * Costruttore che inizializza una nuova richiesta con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     */

    public Richiesta(UtenteRegistrato autoreRichiesta, Comune comune) {
        this.autoreRichiesta = autoreRichiesta;
        this.comune = comune;
    }

    /**
     * Restituisce l'utente registrato che ha effettuato la richiesta.
     *
     * @return l'autore della richiesta
     */

    public UtenteRegistrato getAutoreRichiesta() {
        return autoreRichiesta;
    }

    /**
     * Restituisce il comune associato alla richiesta.
     *
     * @return il comune associato alla richiesta
     */

    public Comune getComune() {
        return this.comune;
    }

    /**
     * Restituisce l'entità specifica associata alla richiesta.
     *
     * @return l'entità richiesta
     */

    public abstract EntitaRichiesta getEntitaRichiesta();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Richiesta richiesta = (Richiesta) o;
        return Objects.equals(autoreRichiesta, richiesta.autoreRichiesta) &&
                Objects.equals(comune, richiesta.comune);
    }


    @Override
    public int hashCode() {
        return Objects.hash(autoreRichiesta, comune);
    }


}
