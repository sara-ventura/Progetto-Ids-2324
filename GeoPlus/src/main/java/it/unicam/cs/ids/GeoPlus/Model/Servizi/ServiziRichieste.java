package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder.RichiesteDirector;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.*;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Repository.RichiesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ServiziRichieste {
    @Autowired
    private RichiesteRepository richiesteRepository;

    @Autowired
    private RichiesteDirector richiestaDirector;

    public void creaRichiestaCaricamento(Account autore, EntitaRichiesta entitaRichiesta, Comune comune) {
        RichiestaCaricamento richiesta = richiestaDirector.creaRichiestaCaricamento(autore, entitaRichiesta, comune);
        richiesteRepository.save(richiesta);
    }

    public void creaRichiestaModificaTesto(Account autore, EntitaRichiesta entitaRichiesta, Comune comune, String modifica, TipoModificaTesto tipoModifica) {
        RichiestaModificaTesto richiesta = richiestaDirector.creaRichiestaModificaTesto(autore, entitaRichiesta, comune, modifica, tipoModifica);
        richiesteRepository.save(richiesta);
    }

    public void creaRichiestaModificaPeriodoTempo(Account autore, PoiTemporaneo poiTemporaneo, Comune comune, LocalDateTime dataApertura, LocalDateTime dataChiusura) {
        RichiestaModificaPT richiesta = richiestaDirector.creaRichiestaModificaPeriodoTempo(autore, poiTemporaneo, comune, dataApertura, dataChiusura);
        richiesteRepository.save(richiesta);
    }

    public void creaRichiestaModificaOrario(Account autore, Poi poi, Comune comune, int giorno, LocalTime orarioApertura, LocalTime orarioChiusura) {
        RichiestaModificaOrario richiesta = richiestaDirector.creaRichiestaModificaOrario(autore, poi, comune, giorno, orarioApertura, orarioChiusura);
        richiesteRepository.save(richiesta);
    }

    public void creaRichiestaCaricamentoContenutoContest(Account autore, Contenuto contenuto, Contest contest, Comune comune) {
        RichiestaSuContest richiesta = richiestaDirector.creaRichiestaCaricamentoContenutoContest(autore, contenuto, contest, comune);
        richiesteRepository.save(richiesta);
    }

    public void eliminaRichiesta(Richiesta richiesta) {
        richiesteRepository.delete(richiesta);
    }

    public Richiesta getRichiesta(Long id) {
        return richiesteRepository.findById(id).orElse(null);
    }

    public List<Richiesta> getRichieste(Comune comune) {
        return richiesteRepository.findByComune(comune);
    }


}
