package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.factory;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaModificaOrario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

import java.time.LocalTime;

public interface RichiestaModificaOrarioFactory {
    RichiestaModificaOrario creaRichiesta(UtenteRegistrato autoreRichiesta, Comune comune, Poi poi, int giorno, LocalTime orarioApertura, LocalTime orarioChiusura);


}
