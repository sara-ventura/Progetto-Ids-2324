package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;

/**
 * La classe astratta Richiesta rappresenta una richiesta generica
 * nel sistema GeoPlus. Funziona come classe base per varie tipologie di richieste
 * che possono essere gestite nel sistema.
 * Questa classe utilizza un parametro generico T che deve estendere
 * EntitaRichiesta, permettendo così di definire diversi tipi di richieste
 * con diverse entità associate.
 *
 * @param <T> il tipo di entità richiesta che deve estendere EntitaRichiesta
 */

public abstract class Richiesta<T extends EntitaRichiesta> {


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
     * @param entitàRichiesta l'entità specifica associata alla richiesta
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

 /**
     * Genera il codice hash per l'oggetto Richiesta.
     * L'implementazione utilizza il codice hash degli attributi autoreRichiesta
     * e comune per garantire che le caratteristiche uniche dell'oggetto
     * siano prese in considerazione.
     *
     * @return il codice hash calcolato per l'oggetto
     */

    @Override
    public int hashCode() {
        return Objects.hash(autoreRichiesta, comune);
    }


}
