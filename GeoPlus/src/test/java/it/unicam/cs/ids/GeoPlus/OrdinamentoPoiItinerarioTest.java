package it.unicam.cs.ids.GeoPlus;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrdinamentoPoiItinerarioTest {


    private Itinerario itinerario;

    @BeforeEach
    public void setUp() {
        Comune comune = new Comune();

        List<Poi> listaPoi = new ArrayList<>();
        listaPoi.add(new Poi("Circo Massimo", ".", new Coordinate(41.879174, 12.492231))); // Circo Massimo
        listaPoi.add(new Poi("Colosseo", ".", new Coordinate(41.890251, 12.492373))); // Colosseo
        listaPoi.add(new Poi("Foro Romano", ".", new Coordinate(41.892460, 12.485183))); // Foro Romano
        listaPoi.add(new Poi("Fontana di Trevi", ".", new Coordinate(41.903045, 12.483529))); // Fontana di Trevi
        listaPoi.add(new Poi("Pantheon", ".", new Coordinate(41.898611, 12.476111))); // Pantheon
        itinerario = new Itinerario("Itinerario di Test", "Test di ordinamento", comune, listaPoi);
    }

    @Test
    public void testListaPoiOrdinata() {
        List<Poi> listaOrdinata = itinerario.getListaPoi();
        assertNotNull(listaOrdinata);
        assertEquals(5, listaOrdinata.size());
        List<Double> distanze = new ArrayList<>();
        for (Poi poi : listaOrdinata) {
            double distanza = itinerario.calcolaDistanza(listaOrdinata.get(0).getPosizionePoi(), poi.getPosizionePoi());
            distanze.add(distanza);
        }
        for (int i = 1; i < distanze.size(); i++) {
            assertTrue(distanze.get(i) >= distanze.get(i - 1), "Distanza non ordinata: " + distanze);
        }
    }
}
