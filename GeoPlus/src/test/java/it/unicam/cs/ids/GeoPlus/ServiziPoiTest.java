package it.unicam.cs.ids.GeoPlus;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Repository.PoiRepository;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziComune;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziPoi;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ServiziPoiTest {

    @Autowired
    private PoiRepository poiRepository;

    @Autowired
    private ServiziComune serviziComune;

    @Autowired
    private ServiziPoi serviziPoi;

    @Test
    public void testCreaPoi() {
        Comune comune = serviziComune.creaComune("Napoli", "pizza", new Coordinate(40.8518, 14.2681));
        Poi poi = serviziPoi.creaPoi("Statua", "bella statua", new Coordinate(40.8517, 14.2681), comune);
        assertNotNull(poi);

    }

    @Test
    @Transactional
    public void testCreaPoiTemporaneo() {
        Comune comune = serviziComune.creaComune("Napoli", "pizza", new Coordinate(40.8518, 14.2681));
        PoiTemporaneo poi = serviziPoi.creaPoi("Fiera", "fiera della pizza", new Coordinate(40.8515, 14.2680), comune, new PeriodoTempo());
        assertNotNull(poi);
    }

    @Test
    @Transactional
    public void testSalvaPoi() {
        Comune comune = serviziComune.creaComune("Napoli", "pizza", new Coordinate(40.8518, 14.2681));
        Poi poi = serviziPoi.creaPoi("Statua", "bella statua", new Coordinate(40.8518, 14.2681), comune);
        serviziPoi.salvaPoi(poi, comune);
        assertEquals(comune.getPoiAssociati().getFirst(), poi);
        assertNotNull(serviziPoi.getPoi(poi.getPosizionePoi()));
    }

    @Test
    @Transactional
    public void testEliminaPoi() {
        Comune comune = serviziComune.creaComune("Napoli", "pizza", new Coordinate(40.8518, 14.2681));
        Poi poi = serviziPoi.creaPoi("Statua", "bella statua", new Coordinate(40.8517, 14.2681), comune);
        assertNotNull(poi);
        serviziPoi.eliminaPoi(poi, comune);
        assertTrue(comune.getPoiAssociati().isEmpty());
        assertNull(serviziPoi.getPoi(poi.getPosizionePoi()));
    }


    @Test
    @Transactional
    public void testRimuoviPoiTemporaneiScaduti() {
        LocalDateTime dataScadenza = LocalDateTime.now().minusDays(1);
        PeriodoTempo periodoTempoScaduto = new PeriodoTempo(LocalDateTime.now().minusDays(10), dataScadenza);
        PoiTemporaneo poiScaduto = new PoiTemporaneo("POI Scaduto", "Descrizione", new Coordinate(), periodoTempoScaduto);
        poiRepository.save(poiScaduto);
        LocalDateTime dataChiusuraNonScaduta = LocalDateTime.now().plusDays(1);
        PeriodoTempo periodoTempoNonScaduto = new PeriodoTempo(LocalDateTime.now(), dataChiusuraNonScaduta);
        PoiTemporaneo poiNonScaduto = new PoiTemporaneo("POI Non Scaduto", "Descrizione", new Coordinate(), periodoTempoNonScaduto);
        poiRepository.save(poiNonScaduto);
        serviziPoi.rimuoviPoiTemporaneiScaduti();
        assertNull(poiRepository.findByNomePoi(poiScaduto.getNomePoi()));
        assertNotNull(poiRepository.findByNomePoi(poiNonScaduto.getNomePoi()));
    }

}
