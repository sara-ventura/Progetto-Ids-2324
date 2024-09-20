package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RichiestaRegistrazioneConComune {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Ruoli ruolo;

    @NotBlank
    private String comune;

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

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }
}
