package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.*;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class RichiesteDirector {
    @Autowired
    private RichiestaCaricamentoContenutoBuilder contenutoBuilder;

    @Autowired
    private RichiestaCaricamentoPoiBuilder poiBuilder;

    @Autowired
    private RichiestaCaricamentoItinerarioBuilder itinerarioBuilder;

    @Autowired
    private RichiestaModificaTestoPoiBuilder richiestaModificaTestoPoiBuilder;

    @Autowired
    private RichiestaModificaTestoContenutoBuilder richiestaModificaTestoContenutoBuilder;

    @Autowired
    private RichiestaModificaTestoItinerarioBuilder richiestaModificaTestoItinerarioBuilder;

    @Autowired
    private RichiestaCaricamentoContenutoContestBuilder contenutoContestBuilder;

    @Autowired
    private RichiestaModificaPeriodoTempoBuilder richiestaModificaPeriodoTempoBuilder;

    @Autowired
    private RichiestaModificaOrarioBuilder richiestaModificaOrarioBuilder;

    public RichiestaCaricamento creaRichiestaCaricamento(Account autore, EntitaRichiesta entitaRichiesta, Comune comune) {
        return switch (entitaRichiesta) {
            case Contenuto contenuto -> {
                contenutoBuilder.setAutore(autore);
                contenutoBuilder.setComune(comune);
                contenutoBuilder.setContenuto(contenuto);
                yield contenutoBuilder.build();
            }
            case Poi poi -> {
                poiBuilder.setAutore(autore);
                poiBuilder.setComune(comune);
                poiBuilder.setPoi(poi);
                yield poiBuilder.build();
            }
            case Itinerario itinerario -> {
                itinerarioBuilder.setAutore(autore);
                itinerarioBuilder.setComune(comune);
                itinerarioBuilder.setItinerario(itinerario);
                yield itinerarioBuilder.build();
            }
            default ->
                    throw new IllegalArgumentException("Tipo di entità non supportato: " + entitaRichiesta.getClass().getSimpleName());
        };
    }

    public RichiestaModificaTesto creaRichiestaModificaTesto(Account autore, EntitaRichiesta entitaRichiesta, Comune comune, String modifica, TipoModificaTesto tipoModifica) {
        return switch (entitaRichiesta) {
            case Contenuto contenuto -> {
                richiestaModificaTestoContenutoBuilder.setAutore(autore);
                richiestaModificaTestoContenutoBuilder.setComune(comune);
                richiestaModificaTestoContenutoBuilder.setContenuto(contenuto);
                richiestaModificaTestoContenutoBuilder.setModificaTesto(modifica);
                richiestaModificaTestoContenutoBuilder.setTipoModificaTesto(tipoModifica);
                yield richiestaModificaTestoContenutoBuilder.build();
            }
            case Poi poi -> {
                richiestaModificaTestoPoiBuilder.setAutore(autore);
                richiestaModificaTestoPoiBuilder.setComune(comune);
                richiestaModificaTestoPoiBuilder.setPoi(poi);
                richiestaModificaTestoPoiBuilder.setModificaTesto(modifica);
                richiestaModificaTestoPoiBuilder.setTipoModificaTesto(tipoModifica);
                yield richiestaModificaTestoPoiBuilder.build();
            }
            case Itinerario itinerario -> {
                richiestaModificaTestoItinerarioBuilder.setAutore(autore);
                richiestaModificaTestoItinerarioBuilder.setComune(comune);
                richiestaModificaTestoItinerarioBuilder.setItinerario(itinerario);
                richiestaModificaTestoItinerarioBuilder.setModificaTesto(modifica);
                richiestaModificaTestoItinerarioBuilder.setTipoModificaTesto(tipoModifica);
                yield richiestaModificaTestoItinerarioBuilder.build();
            }
            default ->
                    throw new IllegalArgumentException("Tipo di entità non supportato: " + entitaRichiesta.getClass().getSimpleName());
        };
    }

    public RichiestaModificaPT creaRichiestaModificaPeriodoTempo(Account autore, PoiTemporaneo poiTemporaneo, Comune comune, LocalDateTime dataApertura, LocalDateTime dataChiusura) {
        richiestaModificaPeriodoTempoBuilder.setAutore(autore);
        richiestaModificaPeriodoTempoBuilder.setComune(comune);
        richiestaModificaPeriodoTempoBuilder.setPoiTemporaneo(poiTemporaneo);
        richiestaModificaPeriodoTempoBuilder.setDataApertura(dataApertura);
        richiestaModificaPeriodoTempoBuilder.setDataChiusura(dataChiusura);
        return richiestaModificaPeriodoTempoBuilder.build();
    }

    public RichiestaModificaOrario creaRichiestaModificaOrario(Account autore, Poi poi, Comune comune, int giorno, LocalTime orarioApertura, LocalTime orarioChiusura) {
        richiestaModificaOrarioBuilder.setAutore(autore);
        richiestaModificaOrarioBuilder.setComune(comune);
        richiestaModificaOrarioBuilder.setPoi(poi);
        richiestaModificaOrarioBuilder.setGiorno(giorno);
        richiestaModificaOrarioBuilder.setOrarioApertura(orarioApertura);
        richiestaModificaOrarioBuilder.setOrarioChiusura(orarioChiusura);
        return richiestaModificaOrarioBuilder.build();
    }

    public RichiestaSuContest creaRichiestaCaricamentoContenutoContest(Account autore, Contenuto contenuto, Contest contest, Comune comune) {
        contenutoContestBuilder.setAutore(autore);
        contenutoContestBuilder.setComune(comune);
        contenutoContestBuilder.setContenuto(contenuto);
        contenutoContestBuilder.setContest(contest);
        return contenutoContestBuilder.build();
    }
}

