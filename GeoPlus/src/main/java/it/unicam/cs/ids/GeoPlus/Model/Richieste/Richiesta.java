package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.SoggettoRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;



/**
 * La classe Richiesta rappresenta una richiesta generica che può essere effettuata
 * da un utente registrato. Questa classe è astratta e serve come base per altre
 * specifiche tipologie di richieste.
 * Ogni richiesta ha un identificatore univoco, un autore (utente registrato), un comune
 * associato e un soggetto specifico della richiesta.
 */

public abstract class Richiesta {

    private Long RichiestaId;
    private UtenteRegistrato autoreRichiesta;
    private Comune comune;
    private SoggettoRichiesta soggettoRichiesta;


    public Richiesta() {
    }

/**
 * Costruttore della classe. Inizializza una nuova richiesta con l'autore specificato,
 * il comune associato e il soggetto della richiesta.
 *
 * @param autoreRichiesta l'utente registrato che ha creato la richiesta
 * @param comune il comune associato alla richiesta
 * @param soggettoRichiesta il soggetto specifico della richiesta
 */

    public Richiesta(UtenteRegistrato autoreRichiesta, Comune comune, SoggettoRichiesta soggettoRichiesta) {
        this.autoreRichiesta = autoreRichiesta;
        this.comune = comune;
        this.soggettoRichiesta = soggettoRichiesta;
    }

    public UtenteRegistrato getAutoreRichiesta() {
        return autoreRichiesta;
    }

    public Comune getComune() {
        return this.comune;
    }

    public SoggettoRichiesta getSoggettoRichiesta() {
        return soggettoRichiesta;
    }

//    @Override
//    public boolean equals(Object o) {
//
//    }

    @Override
    public int hashCode() {
        return Objects.hash(autoreRichiesta, comune);
    }


}
