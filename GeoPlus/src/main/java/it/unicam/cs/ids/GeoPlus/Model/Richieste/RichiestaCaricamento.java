package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;

import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;

import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;

public abstract class RichiestaCaricamento<T extends EntitaRichiesta> extends Richiesta<T> {

    public RichiestaCaricamento(UtenteRegistrato autoreRichiesta, T entitàRichiesta, Comune comune) {
        super(autoreRichiesta, comune, entitàRichiesta);
    }

    public RichiestaCaricamento() {

    }

//    @Override
//    public boolean equals(Object o) {
//
//    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
