package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaCaricamentoContenutoContest;
import org.springframework.stereotype.Component;

@Component
public class RichiestaCaricamentoContenutoContestBuilder extends RichiestaBaseBuilder {
    private Contenuto contenuto;
    private Contest contest;

    public void setContenuto(Contenuto contenuto) {
        this.contenuto = contenuto;
    }
    public void setContest(Contest contest) {
        this.contest = contest;
    }


    @Override
    public RichiestaCaricamentoContenutoContest build() {
        return new RichiestaCaricamentoContenutoContest(this.getAutore(), this.getComune(), this.contenuto, this.contest);
    }
}