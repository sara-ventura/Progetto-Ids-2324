package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;

import jakarta.persistence.Embeddable;

/**
 * Enum che rappresenta i ruoli autorizzati nel sistema GeoPlus.
 * Questi ruoli definiscono i livelli di autorizzazione e le responsabilità
 * degli utenti all'interno dell'applicazione.
 * Ogni valore enum implementa l'interfaccia {@link Ruoli}, indicandone
 * la natura come ruolo specifico nell'applicazione.
 */
@Embeddable
public enum RuoliAutorizzati implements Ruoli {
    /**
     * Ruolo di un contributore autorizzato.
     * Gli utenti con questo ruolo possono aggiungere contenuti
     * all'interno della piattaforma senza validazione da parte del curatore.
     */
    CONTRIBUTOR_AUTORIZZATO,

    /**
     * Ruolo di un curatore.
     *  Il curatore si occupa della gestione delle segnalazioni e della validazione
     *  di tutti i contenuti inseriti dagli utenti non autorizzati.
     */
    CURATORE,

    /**
     * Ruolo di un animatore
     * L'animatore si occupa della creazione dei contest e della loro gestione interna
     */
    ANIMATORE
}