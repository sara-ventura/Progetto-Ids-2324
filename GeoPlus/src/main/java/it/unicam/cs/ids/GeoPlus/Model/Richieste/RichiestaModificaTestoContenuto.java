package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

/**
 * La classe RichiestaModificaTestoContenuto rappresenta una richiesta specifica
 * di modifica del testo associato a un oggetto di tipo Contenuto nel sistema GeoPlus.
 * Estende la classe astratta RichiestaModificaTesto<Contenuto>, 
 * utilizzando il tipo specifico Contenuto per gestire le richieste di modifica del testo.
 * Questa classe è utilizzata per gestire le richieste che includono modifiche di testo 
 * su contenuti specifici, come descrizioni, articoli, o altre forme di testo che 
 * possono essere associate a un oggetto di tipo Contenuto.
 */

public class RichiestaModificaTestoContenuto extends RichiestaModificaTesto {

  private Contenuto contenuto;


  /**
     * Costruttore che inizializza una nuova richiesta di modifica del testo per un contenuto con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     * @param contenuto       il contenuto al quale si riferisce la modifica del testo
     * @param modificaTesto   il testo della modifica proposto
     */

    public RichiestaModificaTestoContenuto(UtenteRegistrato autoreRichiesta, Comune comune, Contenuto contenuto, String modificaTesto) {
        super(autoreRichiesta, comune, contenuto, modificaTesto);
      this.contenuto = contenuto;

    }

    public RichiestaModificaTestoContenuto() {

    }

  @Override
  public Contenuto getEntitaRichiesta() {
    return null;
  }
}
