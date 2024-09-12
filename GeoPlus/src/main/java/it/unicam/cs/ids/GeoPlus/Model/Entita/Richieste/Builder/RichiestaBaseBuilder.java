package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;

public abstract class RichiestaBaseBuilder implements RichiestaBuilder {
    private UtenteStandard autore;
    private Comune comune;

    @Override
    public void setAutore(UtenteStandard autore) { this.autore = autore; }


    @Override
    public void setComune(Comune comune) { this.comune = comune; }

    public UtenteStandard getAutore() { return autore; }

    public Comune getComune() { return comune; }

}
