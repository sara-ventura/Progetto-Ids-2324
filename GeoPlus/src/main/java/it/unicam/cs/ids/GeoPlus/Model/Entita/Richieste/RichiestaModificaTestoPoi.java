package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;


/**
 * La classe RichiestaModificaTestoPoi rappresenta una richiesta specifica
 * di modifica del testo associato a un oggetto di tipo Poi nel sistema GeoPlus.
 * Estende la classe astratta RichiestaModificaTesto
 * utilizzando il tipo specifico Poi per gestire le richieste di modifica del testo.
 * Questa classe è utilizzata per gestire le richieste che includono modifiche di testo
 * su punti di interesse (Point of Interest - POI) specifici, come descrizioni,
 * informazioni turistiche o altre forme di testo che possono essere associate a un POI.
 */

@Entity
public class RichiestaModificaTestoPoi extends RichiestaModificaTesto {

    @ManyToOne
    private Poi poi;

    /**
     * Costruttore che inizializza una nuova richiesta di modifica del testo per un POI con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     * @param poi             il punto di interesse (POI) al quale si riferisce la modifica del testo
     * @param modificaTesto   il testo della modifica proposto
     */

    public RichiestaModificaTestoPoi(UtenteRegistrato autoreRichiesta, Comune comune, Poi poi, String modificaTesto, TipoModificaTesto tipoModifica) {
        super(autoreRichiesta, comune, modificaTesto, tipoModifica);
        this.poi = poi;
    }

    public RichiestaModificaTestoPoi() {

    }

    @Override
    public Poi getEntitaRichiesta() {
        return poi;
    }
}