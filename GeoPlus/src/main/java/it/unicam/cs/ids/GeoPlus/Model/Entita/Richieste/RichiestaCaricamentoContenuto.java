package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

public class RichiestaCaricamentoContenuto extends Richiesta {

    private Contenuto contenuto;

    public RichiestaCaricamentoContenuto(UtenteRegistrato autoreRichiesta, Comune comune, Contenuto Contenuto) {
        super(autoreRichiesta, comune);
        this.contenuto = Contenuto;
    }

    public RichiestaCaricamentoContenuto() {
    }

    @Override
    public Contenuto getEntitaRichiesta() {
        return contenuto;
    }
}
