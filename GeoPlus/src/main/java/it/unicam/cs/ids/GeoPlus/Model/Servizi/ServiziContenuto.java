package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.ContenutoMultimediale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.ContenutoTestuale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Repository.ContenutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiziContenuto {
    @Autowired
    private ContenutoRepository contenutoRepository;
    @Autowired
    private ServiziPoi serviziPoi;
    @Autowired
    private ServiziComune serviziComune;


    public ContenutoMultimediale creaContenutoMultimediale(Poi poi, String file, String didascalia) {
        if (validaPoi(poi)) {
            throw new IllegalArgumentException("Poi non trovato");
        }
        return new ContenutoMultimediale(poi, file, didascalia);
    }

    public ContenutoTestuale creaContenutoTestuale(Poi poi, String testo) {
        if (validaPoi(poi)) {
            throw new IllegalArgumentException("Poi non trovato");
        }return new ContenutoTestuale(poi, testo);
    }

    public void salvaContenuto(Contenuto contenuto) {
        contenutoRepository.save(contenuto);
        contenuto.getPoi().aggiungiContenuto(contenuto);
        serviziPoi.aggiornaPoi(contenuto.getPoi());
    }

    public void eliminaContenuto(Contenuto contenuto) {
        contenutoRepository.delete(contenuto);
        contenuto.getPoi().rimuoviContenuto(contenuto);
        serviziPoi.aggiornaPoi(contenuto.getPoi());
    }

    public void aggiornaContenuto(Contenuto contenuto) {
        contenutoRepository.save(contenuto);
    }

    public Contenuto getContenuto(Long id) {
        return contenutoRepository.findById(id).orElse(null);
    }

    private boolean validaPoi(Poi poi) {
        return poi == null || !poi.isApprovato();
    }
}
