package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import jakarta.persistence.Entity;

/**
 * La classe AmministratoreComunale rappresenta un utente con ruolo di amministratore comunale.
 * Questo ruolo è assegnato tramite l'enum {@link RuoliGestione#AMMINISTRATORE_COMUNALE}.
 *
 */
@Entity
public class AmministratoreComunale extends UtenteRegistrato{

    /**
     * Costruttore che inizializza un AmministratoreComunale con l'ID delle credenziali.
     *
     * @param credenziali L'ID delle credenziali associate all'amministratore comunale.
     */
    public AmministratoreComunale(Long credenziali) {
        super(Ruoli.AMMINISTRATORE_COMUNALE, credenziali);
    }

    public AmministratoreComunale() {

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
