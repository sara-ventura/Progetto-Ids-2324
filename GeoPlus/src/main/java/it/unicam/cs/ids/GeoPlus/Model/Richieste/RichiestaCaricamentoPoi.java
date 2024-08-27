package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

public class RichiestaCaricamentoPoi extends RichiestaCaricamento<Poi> {
    public RichiestaCaricamentoPoi(UtenteRegistrato autoreRichiesta, Poi poi, Comune comune) {
        super(autoreRichiesta, poi, comune);
    }

    public RichiestaCaricamentoPoi() {

    }
}
