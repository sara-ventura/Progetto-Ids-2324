package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.InvitoContest;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

@Entity
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;


    @Enumerated(EnumType.STRING)
    private Ruoli ruoloUtente;


    @ManyToOne
    private Comune comuneAppartenenza;

    @OneToMany
    private List<InvitoContest> listaInvitiContest;


    @OneToMany
    private List<Contenuto> listaContenutiSalvati;

    public Account(String email, String password, String username, Ruoli ruoloUtente, Comune comuneAppartenenza) {
        setEmail(email);
        setPassword(password);
        this.name = username;
        this.ruoloUtente = ruoloUtente;
        this.comuneAppartenenza = comuneAppartenenza;
        this.listaInvitiContest = new ArrayList<>();
        this.listaContenutiSalvati = new ArrayList<>();
    }

    public Account() {

    }

    public void setEmail(String email) {
        if (!checkEmail(email)) {
            throw new IllegalArgumentException("Email non valida.");
        }
        this.email = email;
    }


    public void setPassword(String password) {
        if (checkPassword(password)) {
            throw new IllegalArgumentException("Password non valida.");
        }
        this.password = password;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    private static boolean checkEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private static boolean checkPassword(String password) {
        return password.length() < 8 || !password.matches(".*[0-9].*");
    }

    public Ruoli getRuoloUtente() {
        return ruoloUtente;
    }

    public Comune getComuneAppartenenza() {
        return comuneAppartenenza;
    }

    public void setRuoloUtente(Ruoli ruoloUtente) {
        this.ruoloUtente = ruoloUtente;
    }

    public void setComuneAppartenenza(Comune comuneAppartenenza) {
        this.comuneAppartenenza = comuneAppartenenza;
    }

    public List<InvitoContest> getListaInvitiContest() {
        return listaInvitiContest;
    }

    public List<Contenuto> getListaContenutiSalvati() {
        return listaContenutiSalvati;
    }

    public void aggiungiInvitoContest(InvitoContest invitoContest) {
        if (!listaInvitiContest.contains(invitoContest)) {
            listaInvitiContest.add(invitoContest);
        }
    }

    public boolean rimuoviInvitoContest(InvitoContest invitoContest) {
        return listaInvitiContest.remove(invitoContest);
    }

    public boolean aggiungiContenutoSalvato(Contenuto contenuto) {
        if (!listaContenutiSalvati.contains(contenuto)) {
            return listaContenutiSalvati.add(contenuto);
        } else return false;
    }

    public boolean rimuoviContenutoSalvato(Contenuto contenuto) {
        return listaContenutiSalvati.remove(contenuto);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (ruoloUtente != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + ruoloUtente.name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Long getId() {
        return id;
    }

}
