package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;

public abstract class RichiestaBaseBuilder implements RichiestaBuilder {
    private Account autore;
    private Comune comune;

    @Override
    public void setAutore(Account autore) {
        this.autore = autore;
    }


    @Override
    public void setComune(Comune comune) {
        this.comune = comune;
    }

    public Account getAutore() {
        return autore;
    }

    public Comune getComune() {
        return comune;
    }

}
