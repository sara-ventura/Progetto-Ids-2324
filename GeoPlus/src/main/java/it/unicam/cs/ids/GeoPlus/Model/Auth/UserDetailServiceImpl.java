package it.unicam.cs.ids.GeoPlus.Model.Auth;


import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailServiceImpl {

    @Autowired
    private ServiziAccount accountServices;

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> accountServices.getAccount(email)
                .orElseThrow(() -> new UsernameNotFoundException("Nessun account trovato per l'utente: " + email));
    }
}


