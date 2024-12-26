package it.unicam.cs.ids.GeoPlus.Model.Gestori;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestoreSalvataggi {
    @Autowired
    private ServiziAccount serviziAccount;

    public Boolean salvaContenuto(Contenuto contenuto, Account utente) {
        boolean aggiunta = utente.aggiungiContenutoSalvato(contenuto);
        if (aggiunta) {
            serviziAccount.salvaUtente(utente);
            return true;
        }
        return false;
    }

    public Boolean eliminaContenutoDaiSalvati(Contenuto contenuto, Account utente) {
        boolean rimozione = utente.rimuoviContenutoSalvato(contenuto);
        if (rimozione) {
            serviziAccount.salvaUtente(utente);
            return true;
        }
        return false;
    }
}
