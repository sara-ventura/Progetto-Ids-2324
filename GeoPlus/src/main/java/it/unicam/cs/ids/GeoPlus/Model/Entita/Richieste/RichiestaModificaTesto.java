package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

/**
 * La classe astratta RichiestaModificaTesto rappresenta una richiesta generica 
 * di modifica del testo associato a una particolare entità nel sistema GeoPlus.
 * Estende la classe Richiesta utilizzando un parametro generico per gestire 
 * diversi tipi di entità richiesta.
 */


public abstract class RichiestaModificaTesto extends Richiesta {

    private String modificaTesto;

/**
     * Costruttore che inizializza una nuova richiesta di modifica del testo con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     * @param entitaRichiesta l'entità richiesta alla quale si riferisce la modifica del testo
     * @param modificaTesto   il testo della modifica proposto
     */

    public RichiestaModificaTesto(UtenteRegistrato autoreRichiesta, Comune comune, EntitaRichiesta entitaRichiesta, String modificaTesto) {
        super(autoreRichiesta, comune);
        this.modificaTesto = modificaTesto;
    }

    public RichiestaModificaTesto() {

    }

 /**
     * Restituisce il testo della modifica proposto per l'entità richiesta.
     *
     * @return il testo della modifica proposto
     */

    public String getModificaTesto() {
        return modificaTesto;
    }
}
