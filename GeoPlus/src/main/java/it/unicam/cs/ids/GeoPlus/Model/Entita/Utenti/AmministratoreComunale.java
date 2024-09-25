package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import jakarta.persistence.Entity;

@Entity
public class AmministratoreComunale extends UtenteRegistrato{

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
