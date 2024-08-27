package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

public class RichiestaCaricamentoContenuto extends RichiestaCaricamento<Contenuto> {

    public RichiestaCaricamentoContenuto(UtenteRegistrato autoreRichiesta, Contenuto Contenuto, Comune comune) {
        super(autoreRichiesta, Contenuto, comune);
    }

    public RichiestaCaricamentoContenuto() {

    }
}
