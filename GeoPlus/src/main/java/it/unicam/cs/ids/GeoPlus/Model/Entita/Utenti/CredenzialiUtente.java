package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.regex.Pattern;


@Entity
public class CredenzialiUtente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredenziali;


    private String email;


    private String password;

    public CredenzialiUtente(String email, String password) {
        if (validaEmail(email)) {
            throw new IllegalArgumentException("Email non valida.");
        }
        if (validaPassword(password)) {
            throw new IllegalArgumentException("Password non valida.");
        }
        this.email = email;
        this.password = password;
    }

    public CredenzialiUtente() {
    }

    public Long getIdCredenziali() {
        return idCredenziali;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        if (validaEmail(email)) {
            throw new IllegalArgumentException("Email non valida.");
        }
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        if (validaPassword(password)) {
            throw new IllegalArgumentException("Password non valida.");
        }
        this.password = password;
    }

    public static boolean validaEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email == null || !pattern.matcher(email).matches();
    }

    public static boolean validaPassword(String password) {
        return password == null || password.length() < 8 || !password.matches(".*[0-9].*");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredenzialiUtente that = (CredenzialiUtente) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
