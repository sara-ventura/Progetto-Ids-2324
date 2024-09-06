package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.factory;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.*;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

public class RichiestaModificaTestoFactoryImpl implements RichiestaModificaDiTestoFactory {
    @Override
    public RichiestaModificaTesto creaRichiesta(UtenteRegistrato autoreRichiesta, Comune comune, String modifica, TipoModificaTesto tipoModifica, EntitaRichiesta entitaRichiesta) {
        return switch (entitaRichiesta) {
            case Contenuto contenuto ->
                    new RichiestaModificaTestoContenuto(autoreRichiesta, comune, contenuto, modifica, tipoModifica);
            case Itinerario itinerario ->
                    new RichiestaModificaTestoItinerario(autoreRichiesta, comune, itinerario, modifica, tipoModifica);
            case Poi poi -> new RichiestaModificaTestoPoi(autoreRichiesta, comune, poi, modifica, tipoModifica);
            default ->
                    throw new IllegalArgumentException("Tipo di entitaRichiesta non supportato: " + entitaRichiesta.getClass().getSimpleName());
        };
    }
}

