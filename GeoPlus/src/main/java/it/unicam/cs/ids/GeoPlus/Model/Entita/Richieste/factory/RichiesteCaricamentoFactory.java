package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.factory;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Richiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

public interface RichiesteCaricamentoFactory {
    Richiesta creaRichiesta(UtenteRegistrato autoreRichiesta, Comune comune, EntitaRichiesta entitaRichiesta);
}
