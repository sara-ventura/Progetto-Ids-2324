package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;

import jakarta.persistence.Embeddable;

/**
 * Enum che rappresenta i ruoli non autorizzati nel sistema GeoPlus.
 * Questi ruoli definiscono utenti con privilegi limitati o senza autorizzazioni
 * speciali all'interno dell'applicazione.
 * Ogni valore enum implementa l'interfaccia {@link Ruoli}, identificando
 * questi ruoli come parte del modello degli utenti nel sistema.
 */
@Embeddable
public enum RuoliNonAutorizzati implements Ruoli {
    /**
     * Ruolo di un contributore non autorizzato.
     * Il contributo non autorizzato può caricare contenuti nella piattaforma,
     * ma dovranno essere prima validati dal curatore.
     */
    CONTRIBUTOR,

    /**
     * Ruolo di un turista autenticato.
     * Il turista autenticato è un utente registrato che non appartiene al Comune.
     * Può caricare contenuti nella piattaforma,ma dovranno essere prima validati dal curatore.
     */
    TURISTA_AUTENTICATO
}