package it.unicam.cs.ids.GeoPlus.Model.Auth;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;

public record RichiestaRegistrazioneComune(String password, String email, String username, String nomeComune, Ruoli ruolo) {
}
