package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

public class RichiestaCaricamentoItinerario extends RichiestaCaricamento<Itinerario> {
    public RichiestaCaricamentoItinerario(UtenteRegistrato autoreRichiesta, Itinerario Itinerario, Comune comune) {
        super(autoreRichiesta, Itinerario, comune);
    }

    public RichiestaCaricamentoItinerario() {

    }
}
