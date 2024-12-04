package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Segnalazione;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Repository.SegnalazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiziSegnalazioni {
    @Autowired
    private SegnalazioniRepository segnalazioneRepository;


    public Segnalazione creaSegnalazione(Comune comune, UtenteStandard autoreSegnalazione, Contenuto contenutoSegnalato, String motivoSegnalazione) {
        Segnalazione segnalazione = new Segnalazione(comune, autoreSegnalazione, contenutoSegnalato, motivoSegnalazione);
        return segnalazioneRepository.save(segnalazione);
    }


    public void eliminaSegnalazione(Segnalazione segnalazione) {
        segnalazioneRepository.delete(segnalazione);
    }


    public Segnalazione getSegnalazione(long idSegnalazione) {
        return segnalazioneRepository.findById(idSegnalazione).orElse(null);
    }


    public List<Segnalazione> getSegnalazioniPerComune(Comune comune) {
        return segnalazioneRepository.findByComuneSegnalazione(comune);
    }
}

