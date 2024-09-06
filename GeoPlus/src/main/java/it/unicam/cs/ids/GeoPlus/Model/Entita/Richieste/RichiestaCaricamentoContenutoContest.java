package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class RichiestaCaricamentoContenutoContest extends Richiesta {
    @OneToOne
    private Contenuto contenuto;
    @ManyToOne
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
