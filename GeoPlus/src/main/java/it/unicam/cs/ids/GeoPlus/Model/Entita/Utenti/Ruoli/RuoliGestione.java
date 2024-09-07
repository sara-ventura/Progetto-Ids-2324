package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;

/**
 * Enum che rappresenta i ruoli di gestione nel sistema GeoPlus.
 * Questi ruoli definiscono i livelli di autorizzazione e le responsabilità
 * degli utenti all'interno dell'applicazione.
 * Ogni valore enum implementa l'interfaccia {@link Ruoli}, indicandone
 * la natura come ruolo specifico nell'applicazione.
 */
public enum RuoliGestione implements Ruoli{
    /**
     * Ruolo di un amministratore comunale.
     * L' amministratore comunale può creare utenti autorizzati e assegnare ruoli,
     *
     */
    AMMINISTRATORE_COMUNALE
}
