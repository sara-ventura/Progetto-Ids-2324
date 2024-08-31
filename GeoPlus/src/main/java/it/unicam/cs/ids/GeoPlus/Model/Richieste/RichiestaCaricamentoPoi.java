package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

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
