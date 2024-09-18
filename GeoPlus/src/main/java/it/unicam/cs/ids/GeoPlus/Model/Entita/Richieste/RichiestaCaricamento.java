package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import jakarta.persistence.Entity;

@Entity
public abstract class RichiestaCaricamento extends Richiesta{
    public RichiestaCaricamento(UtenteStandard autoreRichiesta, Comune comune) {
        super(autoreRichiesta, comune);
    }


    public RichiestaCaricamento() {

    }
}
