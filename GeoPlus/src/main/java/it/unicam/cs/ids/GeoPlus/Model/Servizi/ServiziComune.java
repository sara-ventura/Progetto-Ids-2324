package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Repository.ComuniRepository;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.ComuneGiaEsistenteException;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.ComuneNonTrovatoException;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.NomeComuneIncompatibileException;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.SistemaOSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ServiziComune {

    @Autowired
    private ComuniRepository repository;

    @Autowired
    private SistemaOSM sistemaOSM;

    public Comune creaComune(String nomeComune, String descrizione, Coordinate coordinate)
            throws ComuneGiaEsistenteException, ComuneNonTrovatoException, NomeComuneIncompatibileException {
        if (!sistemaOSM.verificaComuneEsiste(nomeComune)) {
            throw new ComuneNonTrovatoException("Il nome del comune inserito non è valido. Si prega di controllare e riprovare.\"");
        }
        if (getComune(coordinate) != null) {
            throw new ComuneGiaEsistenteException("");
        }
        String nomeComuneDaCoordinate = sistemaOSM.getNomeComuneDaCoordinate(coordinate);
        String nomeComuneFormattato = sistemaOSM.formattaNomeComune(nomeComune);
        if (!Objects.equals(nomeComuneDaCoordinate, nomeComuneFormattato)) {
            throw new NomeComuneIncompatibileException();
        }
        Comune nuovoComune = new Comune(nomeComune, descrizione, coordinate);
        return repository.save(nuovoComune);
    }

    public void salvaComune(Comune comune) {
        repository.save(comune);
    }


    public Comune getComune(Coordinate coordinate) {
        String nomeComune = sistemaOSM.getNomeComuneDaCoordinate(coordinate);
        return repository.findByNomeComune(nomeComune);
    }

    public Comune getComune(String nome) {
        return repository.findByNomeComune(nome);
    }

    public Comune getComune(long idComune) {
        return repository.findById(idComune).orElse(null);
    }
}
