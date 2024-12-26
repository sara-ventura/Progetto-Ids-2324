package it.unicam.cs.ids.GeoPlus.Model.Auth;

import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;

public record RichiestaRegistrazioneAmministratore(String password, String email, String username, String nomeComune, String descrizione, Coordinate coordinateCentrali) {
}
