package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;

import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;


/**
 * La classe RichiestaModificaTestoItinerario rappresenta una richiesta specifica
 * di modifica del testo associato a un oggetto di tipo Itinerario nel sistema GeoPlus.
 * Estende la classe astratta RichiestaModificaTesto
 * utilizzando il tipo specifico Itinerario per gestire le richieste di modifica del testo.
 * Questa classe è utilizzata per gestire le richieste che includono modifiche di testo 
 * su itinerari specifici, come descrizioni, indicazioni o altre informazioni testuali
 * associate a un itinerario.
 */

public class RichiestaModificaTestoItinerario extends RichiestaModificaTesto {

    private Itinerario itinerario;

    /**
     * Costruttore che inizializza una nuova richiesta di modifica del testo per un itinerario con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     * @param itinerario      l'itinerario al quale si riferisce la modifica del testo
     * @param modificaTesto   il testo della modifica proposto
     */

    public RichiestaModificaTestoItinerario(UtenteRegistrato autoreRichiesta, Comune comune, Itinerario itinerario, String modificaTesto) {
        super(autoreRichiesta, comune, itinerario, modificaTesto);
    }

    public RichiestaModificaTestoItinerario() {

    }

    @Override
    public Itinerario getEntitaRichiesta() {
        return itinerario;
    }

//
//    @Override
//    public boolean equals(Object o) {
//
//    }


/**
     * Genera il codice hash per l'oggetto RichiestaModificaTestoItinerario.
     * L'implementazione utilizza il codice hash della superclasse per garantire che 
     * le caratteristiche uniche dell'oggetto siano prese in considerazione.
     *
     * @return il codice hash calcolato per l'oggetto
     */

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
