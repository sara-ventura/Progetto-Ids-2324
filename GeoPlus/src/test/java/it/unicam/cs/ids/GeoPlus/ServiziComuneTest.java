package it.unicam.cs.ids.GeoPlus;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Repository.ComuniRepository;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.ComuneGiaEsistenteException;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.ComuneNonTrovatoException;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.NomeComuneIncompatibileException;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziComune;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiziComuneTest {
    @Autowired
    private ServiziComune serviziComune;

    @Autowired
    private ComuniRepository repository;


    @Test
    @Transactional
    public void testCreaComune_Success() throws ComuneGiaEsistenteException {
        String nomeComune = "Napoli";
        String descrizione = "Città della pizza";
        Coordinate coordinate = new Coordinate(40.8518, 14.2681);
        Comune comuneCreato = serviziComune.creaComune(nomeComune, descrizione, coordinate);
        assertNotNull(comuneCreato);
        assertEquals(nomeComune, comuneCreato.getNomeComune());
        assertEquals(descrizione, comuneCreato.getDescrizione());
        Optional<Comune> comuneSalvato = repository.findById(comuneCreato.getIdComune());
        assertTrue(comuneSalvato.isPresent(), "Il comune dovrebbe essere presente nel repository");
        assertEquals(nomeComune, comuneSalvato.get().getNomeComune());
    }

    @Test
    @Transactional
    public void testCreaComuneComuneEsistenteNelSistema() throws ComuneGiaEsistenteException {
        String nomeComune = "Roma";
        String descrizione = "Capitale d'Italia";
        Coordinate coordinate = new Coordinate(41.9028, 12.4964);
        Comune comuneCreato = serviziComune.creaComune(nomeComune, descrizione, coordinate);
        assertThrows(ComuneGiaEsistenteException.class, () -> {
            serviziComune.creaComune(nomeComune, descrizione, coordinate);
        });
    }

    @Test
    @Transactional
    public void testCreaComuneComuneNonEsistenteNelOSM() throws ComuneGiaEsistenteException {
        String nomeComune = "dentifricio";
        String descrizione = "Capitale d'Italia";
        Coordinate coordinate = new Coordinate(41.9028, 12.4964);
        assertThrows(ComuneNonTrovatoException.class, () -> {
            serviziComune.creaComune(nomeComune, descrizione, coordinate);
        });
    }

    @Test
    @Transactional
    public void testCreaComuneNomeComuneDivergente() throws ComuneGiaEsistenteException {
        String nomeComune = "Torino";
        String descrizione = "Città della Mole Antonelliana";
        Coordinate coordinate = new Coordinate(41.9028, 12.4964);//coordinate di daje roma
        assertThrows(NomeComuneIncompatibileException.class, () -> {
            serviziComune.creaComune(nomeComune, descrizione, coordinate);
        });
    }


    @Test
    @Transactional
    public void testGetComuneByCoordinate() throws ComuneGiaEsistenteException {
        String nomeComune = "Firenze";
        String descrizione = "Città del Rinascimento";
        Coordinate coordinate = new Coordinate(43.7696, 11.2558);
        serviziComune.creaComune(nomeComune, descrizione, coordinate);
        Comune comuneTrovato = serviziComune.getComune(coordinate);
        assertNotNull(comuneTrovato);
        assertEquals(nomeComune, comuneTrovato.getNomeComune());
        assertEquals(coordinate.getLatitudine(), comuneTrovato.getCoordinateCentrali().getLatitudine());
        assertEquals(coordinate.getLongitudine(), comuneTrovato.getCoordinateCentrali().getLongitudine());
    }

}
