package it.unicam.cs.ids.GeoPlus.Model.Gestori;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziUtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestoreSalvataggi {
    @Autowired
    private ServiziUtenteRegistrato serviziUtenteRegistrato;

    public Boolean salvaContenuto(Contenuto contenuto, UtenteStandard utente) {
        boolean aggiunta = utente.aggiungiContenutoSalvato(contenuto);
        if (aggiunta) {
            serviziUtenteRegistrato.salvaUtente(utente);
            return true;
        }
        return false;
    }

    public Boolean eliminaContenutoDaiSalvati(Contenuto contenuto, UtenteStandard utente) {
        boolean rimozione = utente.rimuoviContenutoSalvato(contenuto);
        if (rimozione) {
            serviziUtenteRegistrato.salvaUtente(utente);
            return true;
        }
        return false;
    }
}
