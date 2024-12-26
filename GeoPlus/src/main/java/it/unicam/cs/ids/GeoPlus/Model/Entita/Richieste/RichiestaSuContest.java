package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class RichiestaSuContest extends Richiesta {
    @OneToOne
    private Contenuto contenuto;
    @ManyToOne
    private Contest contest;

    public RichiestaSuContest(Account autoreRichiesta, Comune comune, Contenuto contenuto, Contest contest) {
        super(autoreRichiesta, comune);
        this.contenuto = contenuto;
        this.contest = contest;
    }

    public RichiestaSuContest() {

    }

    @Override
    public Contenuto getEntitaRichiesta() {
        return contenuto;
    }

    public Contest getContest() {
        return contest;
    }
}
