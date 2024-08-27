package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;

import it.unicam.cs.ids.GeoPlus.Model.SoggettoRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;

/**
 * La classe RichiestaModificaTesto rappresenta una richiesta di modifica di testo
 * che può essere effettuata da un utente registrato all'interno di un determinato comune.
 * Estende la classe astratta Richiesta.
 * Ogni oggetto di questa classe contiene le informazioni relative all'autore della richiesta,
 * il soggetto della richiesta, il comune di riferimento, e il testo della modifica proposta.
 */
public class RichiestaModificaTesto extends Richiesta {

    private String modifica;

/**
     * Costruttore che inizializza una nuova richiesta di modifica di testo con i dettagli specificati.
     *
     * @param autoreRichiesta    l'utente registrato che ha effettuato la richiesta
     * @param soggettoRichiesta  il soggetto della richiesta (ad esempio, l'elemento che deve essere modificato)
     * @param comune             il comune associato alla richiesta
     * @param modifica           il testo della modifica proposta
     */

    public RichiestaModificaTesto(UtenteRegistrato autoreRichiesta, SoggettoRichiesta soggettoRichiesta, Comune comune, String modifica) {
        super(autoreRichiesta, comune, soggettoRichiesta);
        this.modifica = modifica;
    }

    public RichiestaModificaTesto() {

    }

 /**
     * Restituisce il testo della modifica proposta.
     *
     * @return il testo della modifica proposta
     */

    public String getModifica() {
        return modifica;
    }
//
//    @Override
//    public boolean equals(Object o) {
//
//    }

 /**
     * Genera il codice hash per l'istanza corrente di RichiestaModificaTesto.
     * Il codice hash è calcolato utilizzando il codice hash della superclasse e il testo della modifica.
     *
     * @return il codice hash dell'istanza corrente
     */

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), modifica);
    }
}
