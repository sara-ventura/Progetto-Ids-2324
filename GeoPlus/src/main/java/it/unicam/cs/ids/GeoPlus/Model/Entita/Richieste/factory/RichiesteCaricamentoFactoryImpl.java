package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.factory;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.*;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

public class RichiesteCaricamentoFactoryImpl implements RichiesteCaricamentoFactory{


    @Override
    public Richiesta creaRichiesta(UtenteRegistrato autoreRichiesta, Comune comune, EntitaRichiesta entitaRichiesta) {
        return switch (entitaRichiesta) {
            case Contenuto contenuto ->
                    new RichiestaCaricamentoContenuto(autoreRichiesta, comune, contenuto);
            case Itinerario itinerario ->
                    new RichiestaCaricamentoItinerario(autoreRichiesta, comune, itinerario);
            case Poi poi -> new RichiestaCaricamentoPoi(autoreRichiesta, comune, poi);
            default ->
                    throw new IllegalArgumentException("Tipo di entitaRichiesta non supportato: " + entitaRichiesta.getClass().getSimpleName());
        };
    }
}
