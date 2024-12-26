package it.unicam.cs.ids.GeoPlus.Model.Gestori;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.*;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class GestoreRichieste {
    @Autowired
    private ServiziRichieste serviziRichieste;
    @Autowired
    private ServiziPoi serviziPoi;
    @Autowired
    private ServiziItinerario serviziItinerario;
    @Autowired
    private ServiziContenuto serviziContenuto;

    @Autowired
    private ServiziContest serviziContest;

    public void approvaRichiesta(Richiesta richiesta) {
        switch (richiesta) {
            case RichiestaCaricamento richiestaCaricamento -> approvaRichiestaCaricamento(richiestaCaricamento);
            case RichiestaSuContest richiestaSuContest -> accettaRichiestaCaricamentoSuContest(richiestaSuContest);
            case RichiestaModificaTesto richiestaModificaTesto -> approvaRichiestaModificaTesto(richiestaModificaTesto);
            case RichiestaModificaPT richiestaModificaPT -> accettaRichiestaModificaPT(richiestaModificaPT);
            case RichiestaModificaOrario richiestaModificaOrario ->
                    accettaRichiestaModificaOrario(richiestaModificaOrario);
            default ->
                    throw new IllegalArgumentException("Tipo di richiesta non gestito: " + richiesta.getClass().getSimpleName());
        }
    }

    public void rifiutaRichiesta(Richiesta richiesta) {
        switch (richiesta) {
            case RichiestaCaricamento richiestaCaricamento -> rifiutaRichiestaCaricamento(richiestaCaricamento);
            case RichiestaSuContest richiestaSuContest -> rifiutaRichiestaCaricamentoSuContest(richiestaSuContest);
            case RichiestaModificaTesto richiestaModificaTesto -> rifiutaRichiestaModifica(richiestaModificaTesto);
            case RichiestaModificaPT richiestaModificaPT -> rifiutaRichiestaModifica(richiestaModificaPT);
            case RichiestaModificaOrario richiestaModificaOrario -> rifiutaRichiestaModifica(richiestaModificaOrario);
            default ->
                    throw new IllegalArgumentException("Tipo di richiesta non gestito: " + richiesta.getClass().getSimpleName());
        }
    }

    public void approvaRichiestaCaricamento(RichiestaCaricamento richiesta) {
        EntitaRichiesta entita = richiesta.getEntitaRichiesta();
        switch (entita) {
            case Poi poi -> {
                poi.setApprovato(true);
                serviziPoi.aggiornaPoi(poi);
            }
            case Itinerario itinerario -> {
                itinerario.setApprovato(true);
                serviziItinerario.aggiornaItinerario(itinerario);
            }
            case Contenuto contenuto -> {
                contenuto.setApprovato(true);
                serviziContenuto.aggiornaContenuto(contenuto);
            }
            default ->
                    throw new IllegalArgumentException("Tipo di entità non gestito: " + entita.getClass().getSimpleName());
        }
        serviziRichieste.eliminaRichiesta(richiesta);
    }


    public void rifiutaRichiestaCaricamento(RichiestaCaricamento richiesta) {
        EntitaRichiesta entita = richiesta.getEntitaRichiesta();
        switch (entita) {
            case Poi poi -> {
                serviziPoi.eliminaPoi(poi, richiesta.getComune());
            }
            case Itinerario itinerario -> {
                serviziItinerario.eliminaItinerario(itinerario);
            }
            case Contenuto contenuto -> {
                serviziContenuto.eliminaContenuto(contenuto);
            }
            default ->
                    throw new IllegalArgumentException("Tipo di entità non gestito: " + entita.getClass().getSimpleName());
        }
        serviziRichieste.eliminaRichiesta(richiesta);
    }

    public void accettaRichiestaCaricamentoSuContest(RichiestaSuContest richiesta) {
        Contenuto contenuto = richiesta.getEntitaRichiesta();
        contenuto.setApprovato(true);
        serviziContest.aggiungiContenutoContest(richiesta.getContest(), contenuto, richiesta.getAutoreRichiesta());
        serviziRichieste.eliminaRichiesta(richiesta);
    }

    public void rifiutaRichiestaCaricamentoSuContest(RichiestaSuContest richiesta) {
        Contenuto contenuto = richiesta.getEntitaRichiesta();
        serviziContenuto.eliminaContenuto(contenuto);
        serviziRichieste.eliminaRichiesta(richiesta);
    }

    public void approvaRichiestaModificaTesto(RichiestaModificaTesto richiesta) {
        String modificaTesto = richiesta.getModificaTesto();
        TipoModificaTesto tipoModifica = richiesta.getTipoModifica();
        switch (richiesta) {
            case RichiestaModificaTestoPoi poiRichiesta -> {
                Poi poi = poiRichiesta.getEntitaRichiesta();
                switch (tipoModifica) {
                    case NOME -> poi.setNomePoi(modificaTesto);
                    case DESCRIZIONE -> poi.setDescrizionePoi(modificaTesto);
                }
                serviziPoi.aggiornaPoi(poi);
            }
            case RichiestaModificaTestoIt itinerarioRichiesta -> {
                Itinerario itinerario = itinerarioRichiesta.getEntitaRichiesta();
                switch (tipoModifica) {
                    case NOME -> itinerario.setNomeItinerario(modificaTesto);
                    case DESCRIZIONE -> itinerario.setDescrizioneItinerario(modificaTesto);
                }
                serviziItinerario.aggiornaItinerario(itinerario);
            }
            case RichiestaModificaTestoC contenutoRichiesta -> {
                Contenuto contenuto = contenutoRichiesta.getEntitaRichiesta();
                contenuto.setTesto(modificaTesto);
                serviziContenuto.aggiornaContenuto(contenuto);
            }
            default -> throw new IllegalStateException("Unexpected value: " + richiesta);
        }
        serviziRichieste.eliminaRichiesta(richiesta);
    }

    public void accettaRichiestaModificaPT(RichiestaModificaPT richiesta) {
        PoiTemporaneo poiTemporaneo = richiesta.getEntitaRichiesta();
        LocalDateTime nuovaDataApertura = richiesta.getDataApertura();
        LocalDateTime nuovaDataChiusura = richiesta.getDataChiusura();
        poiTemporaneo.getPeriodoApertura().setDataInizio(nuovaDataApertura);
        poiTemporaneo.getPeriodoApertura().setDataFine(nuovaDataChiusura);
        serviziPoi.aggiornaPoi(poiTemporaneo);
        serviziRichieste.eliminaRichiesta(richiesta);
    }

    public void accettaRichiestaModificaOrario(RichiestaModificaOrario richiesta) {
        Poi poi = richiesta.getEntitaRichiesta();
        int giorno = richiesta.getGiorno();
        LocalTime nuovoOrarioApertura = richiesta.getOrarioApertura();
        LocalTime nuovoOrarioChiusura = richiesta.getOrarioChiusura();
        poi.setOrarioPoi(giorno, nuovoOrarioApertura, nuovoOrarioChiusura);
        serviziPoi.aggiornaPoi(poi);
        serviziRichieste.eliminaRichiesta(richiesta);
    }

    public void rifiutaRichiestaModifica(Richiesta richiesta) {
        serviziRichieste.eliminaRichiesta(richiesta);
    }
}

