package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;

import jakarta.persistence.Embeddable;

//marker interface
@Embeddable
public enum Ruoli {
    CONTRIBUTOR_AUTORIZZATO,
    CURATORE,
    ANIMATORE,
    CONTRIBUTOR,
    TURISTA_AUTENTICATO,
    AMMINISTRATORE_COMUNALE
}
