package it.unicam.cs.ids.GeoPlus.Model;

import it.unicam.cs.ids.GeoPlus.Model.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

/**
 * La classe Segnalazione rappresenta una segnalazione effettuata da un utente registrato
 * riguardo a un contenuto specifico. Include informazioni sull'autore della segnalazione
 * e sul contenuto segnalato.
 */
public class Segnalazione {

    private final UtenteRegistrato autoreSegnalazione;
    private final Contenuto contenutoSegnalato;

    /**
     * Costruttore per creare un oggetto Segnalazione.
     *
     * @param comune             Il comune associato alla segnalazione (se necessario).
     * @param autoreSegnalazione L'utente registrato che effettua la segnalazione.
     * @param contenutoSegnalato Il contenuto che è stato segnalato.
     */
    public Segnalazione(Comune comune, UtenteRegistrato autoreSegnalazione, Contenuto contenutoSegnalato) {
        this.autoreSegnalazione = autoreSegnalazione;
        this.contenutoSegnalato = contenutoSegnalato;
    }


    // public long getIdSegnalazione() {
    //     return idSegnalazione;
    // }

    /**
     * Restituisce l'autore della segnalazione.
     *
     * @return L'utente registrato che ha effettuato la segnalazione.
     */
    public UtenteRegistrato getAutoreSegnalazione() {
        return autoreSegnalazione;
    }

    /**
     * Restituisce il contenuto che è stato segnalato.
     *
     * @return Il contenuto segnalato.
     */
    public Contenuto getContenutoSegnalato() {
        return contenutoSegnalato;
    }

    /**
     * Restituisce il comune associato alla segnalazione attraverso il contenuto segnalato.
     *
     * @return Il comune associato alla segnalazione.
     */
    public Comune getComuneSegnalazione() {
        return contenutoSegnalato.getComune();
    }


    // @Override
    // public boolean equals(Object o) {
    //     // Implementazione dell'uguaglianza
    // }

    // @Override
    // public int hashCode() {
    //     // Implementazione del calcolo dell'hash
    // }
}