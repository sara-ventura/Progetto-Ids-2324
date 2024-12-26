package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Richiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;

public interface RichiestaBuilder {

    void setAutore(Account autore);

    void setComune(Comune comune);

    Richiesta build();
}
