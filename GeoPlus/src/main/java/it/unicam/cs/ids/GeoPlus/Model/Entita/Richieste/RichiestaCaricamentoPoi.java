package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

public class RichiestaCaricamentoPoi extends Richiesta {
    private Poi poi;

    public RichiestaCaricamentoPoi(UtenteRegistrato autoreRichiesta, Comune comune, Poi poi) {
        super(autoreRichiesta, comune);
        this.poi = poi;

    }

    public RichiestaCaricamentoPoi() {

    }

    @Override
    public Poi getEntitaRichiesta() {
        return poi;
    }
}
