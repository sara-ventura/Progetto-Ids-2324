package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.factory;

import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaModificaTesto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.TipoModificaTesto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;


public interface RichiestaModificaDiTestoFactory {

    RichiestaModificaTesto creaRichiesta(UtenteRegistrato autoreRichiesta, Comune comune, String modifica, TipoModificaTesto tipoModifica, EntitaRichiesta entitaRichiesta);
}
