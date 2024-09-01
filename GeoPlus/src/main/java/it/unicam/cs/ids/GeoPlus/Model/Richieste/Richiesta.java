package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;

/**
 * La classe astratta Richiesta rappresenta una richiesta generica
 * nel sistema GeoPlus. Funziona come classe base per varie tipologie di richieste
 * che possono essere gestite nel sistema.
 */

public abstract class Richiesta {


    private Long RichiestaId;
    private UtenteRegistrato autoreRichiesta;
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

//    @Override
//    public boolean equals(Object o) {
//
//    }


    @Override
    public int hashCode() {
        return Objects.hash(autoreRichiesta, comune);
    }


}
