package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

public class RichiestaCaricamentoItinerario extends Richiesta {
    private Itinerario itinerario;

    public RichiestaCaricamentoItinerario(UtenteRegistrato autoreRichiesta, Comune comune, Itinerario Itinerario) {
        super(autoreRichiesta, comune);
        this.itinerario = Itinerario;

    }

    public RichiestaCaricamentoItinerario() {

    }

    @Override
    public Itinerario getEntitaRichiesta() {
        return itinerario;
    }
}
