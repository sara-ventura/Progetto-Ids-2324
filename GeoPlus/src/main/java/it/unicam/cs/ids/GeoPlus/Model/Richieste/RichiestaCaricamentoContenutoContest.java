package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

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
