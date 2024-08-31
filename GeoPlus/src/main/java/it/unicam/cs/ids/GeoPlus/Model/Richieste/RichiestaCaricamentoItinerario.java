package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

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
