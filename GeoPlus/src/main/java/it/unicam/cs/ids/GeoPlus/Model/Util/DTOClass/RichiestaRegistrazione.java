package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class RichiestaRegistrazione {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Ruoli ruolo;

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

    public Ruoli getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruoli ruolo) {
        this.ruolo = ruolo;
    }
}
