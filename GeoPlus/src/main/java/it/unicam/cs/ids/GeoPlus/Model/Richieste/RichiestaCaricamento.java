package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;

import it.unicam.cs.ids.GeoPlus.Model.SoggettoRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;


/**
 * La classe RichiestaCaricamento rappresenta una richiesta di caricamento 
 * effettuata da un utente registrato all'interno di un determinato comune.
 * Estende la classe astratta Richiesta.
 * Questa classe è utilizzata per gestire le richieste di caricamento che includono informazioni 
 * sull'autore della richiesta, il soggetto della richiesta e il comune associato.
 */

public class RichiestaCaricamento extends Richiesta {

/**
     * Costruttore che inizializza una nuova richiesta di caricamento con i dettagli specificati.
     *
     * @param autoreRichiesta   l'utente registrato che ha effettuato la richiesta
     * @param soggettoRichiesta il soggetto della richiesta (ad esempio, l'elemento da caricare)
     * @param comune            il comune associato alla richiesta
     */

    public RichiestaCaricamento(UtenteRegistrato autoreRichiesta, SoggettoRichiesta soggettoRichiesta, Comune comune) {
        super(autoreRichiesta, comune, soggettoRichiesta);
    }

//    @Override
//    public boolean equals(Object o) {
//
//    }

  /**
     * Genera il codice hash per l'istanza corrente di RichiestaCaricamento.
     * Il codice hash è calcolato utilizzando il metodo hash della classe Objects
     * Poiché non ci sono campi specifici nella classe RichiestaCaricamento, 
     * il codice hash viene calcolato senza parametri aggiuntivi rispetto alla superclasse.
     *
     * @return il codice hash dell'istanza corrente
     */

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
