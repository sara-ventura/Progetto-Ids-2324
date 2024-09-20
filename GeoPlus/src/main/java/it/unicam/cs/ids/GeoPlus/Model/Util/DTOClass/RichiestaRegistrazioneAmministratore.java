package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;

import javax.validation.constraints.NotBlank;

public class RichiestaRegistrazioneAmministratore {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nomeComune;

    @NotBlank
    private String descrizione;

    private Coordinate coordinateCentrali;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNomeComune() {
        return nomeComune;
    }
    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public Coordinate getCoordinateCentrali() {
        return coordinateCentrali;
    }

    public void setCoordinateCentrali(Coordinate coordinateCentrali) {
        this.coordinateCentrali = coordinateCentrali;
    }
}
