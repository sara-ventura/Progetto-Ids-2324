package it.unicam.cs.ids.GeoPlus.Model.Gestori;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Segnalazione;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziContenuto;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziSegnalazioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestoreSegnalazioni {
    @Autowired
    private ServiziContenuto serviziContenuto;
    @Autowired
    private ServiziSegnalazioni serviziSegnalazioni;

    public void accettaSegnalazione(Segnalazione segnalazione) {
        Contenuto contenuto = segnalazione.getContenutoSegnalato();
        serviziSegnalazioni.eliminaSegnalazione(segnalazione);
        serviziContenuto.eliminaContenuto(contenuto);
    }

    public void rifiutaSegnalzione(Segnalazione segnalazione) {
        serviziSegnalazioni.eliminaSegnalazione(segnalazione);
    }
}