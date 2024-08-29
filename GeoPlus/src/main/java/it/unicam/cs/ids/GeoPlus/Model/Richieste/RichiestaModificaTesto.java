package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

/**
 * La classe astratta RichiestaModificaTesto rappresenta una richiesta generica 
 * di modifica del testo associato a una particolare entità nel sistema GeoPlus.
 * Estende la classe Richiesta utilizzando un parametro generico per gestire 
 * diversi tipi di entità richiesta.
 * Questa classe viene utilizzata per gestire le richieste che includono modifiche di testo,
 * come aggiornamenti di descrizioni o altre informazioni testuali relative a un'entità 
 * specifica nel contesto di un comune e da parte di un utente registrato.
 * </p>
 *
 * @param <T> il tipo di entità richiesta che estende EntitaRichiesta
 */


public abstract class RichiestaModificaTesto<T extends EntitaRichiesta> extends Richiesta<T> {

    private String modificaTesto;

/**
     * Costruttore che inizializza una nuova richiesta di modifica del testo con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     * @param entitàRichiesta l'entità richiesta alla quale si riferisce la modifica del testo
     * @param modificaTesto   il testo della modifica proposto
     */

    public RichiestaModificaTesto(UtenteRegistrato autoreRichiesta, Comune comune, T entitàRichiesta, String modificaTesto) {
        super(autoreRichiesta, comune, entitàRichiesta);
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
