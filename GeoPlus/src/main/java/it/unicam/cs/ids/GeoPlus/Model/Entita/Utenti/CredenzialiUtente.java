package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * La classe CredenzialiUtente rappresenta le credenziali di accesso di un utente,
 * che includono un'email e una password. È inoltre possibile validare l'email
 * e la password per garantire che rispettino determinati criteri.
 */
@Entity
public class CredenzialiUtente {
    /**
     * Identificatore univoco delle credenziali, generato automaticamente dal database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredenziali;

    /**
     * Indirizzo email dell'utente, utilizzato per l'autenticazione.
     */
    private String email;

    /**
     * Password dell'utente, utilizzata per l'autenticazione.
     */
    private String password;

    /**
     * Costruttore con parametri che inizializza l'email e la password,
     * verificando la validità dei dati forniti.
     *
     * @param email L'indirizzo email dell'utente.
     * @param password La password dell'utente.
     * @throws IllegalArgumentException se l'email o la password non sono valide.
     */
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

    /**
     * Restituisce l'identificatore univoco delle credenziali.
     *
     * @return L'ID delle credenziali.
     */
    public Long getIdCredenziali() {
        return idCredenziali;
    }

    /**
     * Restituisce l'indirizzo email dell'utente.
     *
     * @return L'indirizzo email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta un nuovo indirizzo email, previa verifica della sua validità.
     *
     * @param email Il nuovo indirizzo email da impostare.
     * @throws IllegalArgumentException se l'email non è valida.
     */
    public void setEmail(String email) {
        if (validaEmail(email)) {
            throw new IllegalArgumentException("Email non valida.");
        }
        this.email = email;
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return La password dell'utente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta una nuova password, previa verifica della sua validità.
     *
     * @param password La nuova password da impostare.
     * @throws IllegalArgumentException se la password non è valida.
     */
    public void setPassword(String password) {
        if (validaPassword(password)) {
            throw new IllegalArgumentException("Password non valida.");
        }
        this.password = password;
    }

    /**
     * Verifica se l'indirizzo email è valido.
     *
     * @param email L'indirizzo email da verificare.
     * @return {@code true} se l'email non è valida, {@code false} se è corretta.
     */
    public static boolean validaEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email == null || !pattern.matcher(email).matches();
    }

    /**
     * Verifica se la password è valida. La password deve essere di almeno 8 caratteri
     * e contenere almeno un numero.
     *
     * @param password La password da verificare.
     * @return {@code true} se la password non è valida, {@code false} se è corretta.
     */
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
