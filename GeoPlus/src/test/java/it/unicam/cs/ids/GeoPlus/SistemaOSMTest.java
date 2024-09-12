package it.unicam.cs.ids.GeoPlus;

import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.SistemaOSM;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SistemaOSMTest {
    @Autowired
    private SistemaOSM sistemaOSM;


    @Test
    void verificaComuneEsiste_ComuneEsistente() {
        String nomeComune = "Corropoli";
        boolean exists = sistemaOSM.verificaComuneEsiste(nomeComune);
        assertTrue(exists, "Il comune dovrebbe esistere.");
    }


    @Test
    void verificaComuneEsiste_ComuneEsistente2() {
        String nomeComune = "Bosco";
        boolean exists = sistemaOSM.verificaComuneEsiste(nomeComune);
        assertTrue(exists, "Il comune dovrebbe esistere.");
    }


    @Test
    void verificaComuneEsiste_ComuneEsistente3() {
        String nomeComune = "San Giovanni Rotondo";
        boolean exists = sistemaOSM.verificaComuneEsiste(nomeComune);
        assertTrue(exists, "Il comune dovrebbe esistere.");
    }


    @Test
    void verificaComuneEsiste_ComuneEsistente4() {
        String nomeComune = "Alba Adriatica";
        boolean exists = sistemaOSM.verificaComuneEsiste(nomeComune);
        assertTrue(exists, "Il comune dovrebbe esistere.");
    }


    @Test
    void verificaComuneEsiste_ComuneEsistente5() {
        String nomeComune = "Rocca Calascio";
        boolean exists = sistemaOSM.verificaComuneEsiste(nomeComune);
        assertTrue(exists, "Il comune dovrebbe esistere.");
    }

    @Test
    void verificaComuneEsiste_ComuneInesistente() {
        String nomeComune = "ComuneInesistente";
        boolean exists = sistemaOSM.verificaComuneEsiste(nomeComune);
        assertFalse(exists, "Il comune non dovrebbe esistere.");
    }

    @Test
    void verificaComuneEsiste_ComuneInesistente2() {
        String nomeComune = "";
        boolean exists = sistemaOSM.verificaComuneEsiste(nomeComune);
        assertFalse(exists, "Il comune non dovrebbe esistere.");
    }

    @Test
    public void ContieneCoordinate_CoordinateInComune() {
        Coordinate coord = new Coordinate(41.9028, 12.4964);
        String nomeComune = "Roma";
        boolean contiene = sistemaOSM.contieneCoordinate(coord, nomeComune);
        assertTrue(contiene);
    }

    @Test
    public void ContieneCoordinate_CoordinateInComune2() {
        Coordinate coord = new Coordinate(40.8522, 14.2681);
        String nomeComune = "Napoli";
        boolean contiene = sistemaOSM.contieneCoordinate(coord, nomeComune);
        assertTrue(contiene);
    }

    @Test
    public void ContieneCoordinate_CoordinateFuoriComune() {
        Coordinate coord = new Coordinate(40.7128, -74.0060);
        String nomeComune = "Roma";
        boolean contiene = sistemaOSM.contieneCoordinate(coord, nomeComune);
        assertFalse(contiene);
    }

    @Test
    public void getNomeComuneDaCoordinate_CoordinateDiRoma() {
        Coordinate coord = new Coordinate(41.9028, 12.4964);
        String nomeComune = sistemaOSM.getNomeComuneDaCoordinate(coord);
        assertEquals("Roma", nomeComune, "Il comune dovrebbe essere Roma.");
    }

    @Test
    public void getNomeComuneDaCoordinate_CoordinateDiNapoli() {
        Coordinate coord = new Coordinate(40.8518, 14.2681);
        String nomeComune = sistemaOSM.getNomeComuneDaCoordinate(coord);
        assertEquals("Napoli", nomeComune, "Il comune dovrebbe essere Napoli.");
    }

    @Test
    public void getNomeComuneDaCoordinate_CoordinateNonValida() {
        Coordinate coord = new Coordinate(0, 0);
        String nomeComune = sistemaOSM.getNomeComuneDaCoordinate(coord);
        assertNull(nomeComune, "Il comune non dovrebbe esistere per coordinate non valide.");
    }

    @Test
    public void getNomeComuneDaCoordinate_CoordinateFuoriComune() {
        Coordinate coord = new Coordinate(51.5074, -0.1278);
        String nomeComune = sistemaOSM.getNomeComuneDaCoordinate(coord);
        assertNotNull(nomeComune, "Il nome del comune non dovrebbe essere nullo.");
        assertFalse(nomeComune.contains("Italia"), "Il comune non dovrebbe essere in Italia.");
        assertTrue(nomeComune.contains("Westminster") || nomeComune.contains("Londra"),
                "Il comune dovrebbe indicare che è a Londra o Westminster.");
    }
}
