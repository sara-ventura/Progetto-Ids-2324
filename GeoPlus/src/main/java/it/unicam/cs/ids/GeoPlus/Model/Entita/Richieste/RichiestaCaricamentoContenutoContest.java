package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

public class RichiestaCaricamentoContenutoContest extends Richiesta {
    private Contenuto contenuto;
    private Contest contest;

    public RichiestaCaricamentoContenutoContest(UtenteRegistrato autoreRichiesta, Comune comune, Contenuto contenuto, Contest contest) {
        super(autoreRichiesta, comune);
        this.contenuto = contenuto;
        this.contest = contest;
    }

    public RichiestaCaricamentoContenutoContest(){

    }

    @Override
    public Contenuto getEntitaRichiesta() {
        return contenuto;
    }
}
