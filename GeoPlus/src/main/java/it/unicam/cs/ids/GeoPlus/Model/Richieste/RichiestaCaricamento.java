package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;

import it.unicam.cs.ids.GeoPlus.Model.EntitàRichiesta;

import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;

public abstract class RichiestaCaricamento<T extends EntitàRichiesta> extends Richiesta<T> {

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
