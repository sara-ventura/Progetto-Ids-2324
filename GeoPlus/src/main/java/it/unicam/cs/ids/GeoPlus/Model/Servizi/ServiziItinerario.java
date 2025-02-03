package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Repository.ItinerarioRepository;
import it.unicam.cs.ids.GeoPlus.Model.Util.GestroreSistemaOSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiziItinerario {
    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    private ServiziComune serviziComune;

    @Autowired
    private GestroreSistemaOSM sistemaOSM;

    public Itinerario creaItinerario(String nomeItinerario, String descrizioneItinerario, Comune comune, List<Poi> listaPoi)
            throws IllegalArgumentException {
        if (listaPoi.size() < 2) {
            throw new IllegalArgumentException("La lista dei POI deve contenere almeno due POI.");
        }
        List<Itinerario> itinerariEsistenti = itinerarioRepository.findByListaPoi(listaPoi, listaPoi.size());
        if (!itinerariEsistenti.isEmpty()) {
            throw new IllegalArgumentException("Esiste già un itinerario con lo stesso percorso.");
        }
        for (Poi poi : listaPoi) {
            Comune comunePoi = serviziComune.getComune(poi.getPosizionePoi());
            if (comunePoi == null || !comune.getNomeComune().trim().equalsIgnoreCase(comunePoi.getNomeComune().trim())) {
                String nomeComuneOSM = sistemaOSM.getNomeComuneDaCoordinate(poi.getPosizionePoi());
                throw new IllegalArgumentException("Il Poi " + poi.getNomePoi() + "non appartiene al comune " + comune.getNomeComune() + " ma appartiene a " + nomeComuneOSM);
            }
        }
        return new Itinerario(nomeItinerario, descrizioneItinerario, comune, listaPoi);
    }

    public void salvaItinerario(Itinerario itinerario, Comune comune) {
        itinerarioRepository.save(itinerario);
        comune.aggiungiItinerario(itinerario);
        serviziComune.salvaComune(comune);
    }


    public void eliminaItinerario(Itinerario itinerario) {
        Comune comune = itinerario.getComune();
        itinerario.getComune().rimuoviItinerario(itinerario);
        serviziComune.salvaComune(comune);
        itinerarioRepository.delete(itinerario);
    }


    public void aggiornaItinerario(Itinerario itinerario) {
        itinerarioRepository.save(itinerario);
    }

    public Itinerario getItinerario(Long idItinerario) {
        return itinerarioRepository.findById(idItinerario).orElse(null);
    }


    public List<Itinerario> getItinerariApprovati(Comune comune) {
        return itinerarioRepository.findAllByApprovatoAndComune(true, comune);
    }

    public List<Itinerario> getItinerariApprovati(String nomeItineraio) {
        return itinerarioRepository.findAllByApprovatoAndNomeItinerario(true, nomeItineraio);
    }
}
