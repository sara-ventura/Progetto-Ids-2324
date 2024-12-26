package it.unicam.cs.ids.GeoPlus.Model.Repository;


import it.unicam.cs.ids.GeoPlus.Model.Entita.Token;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllAccessTokensByAccount(Account account);

    Optional<Token> findByAccessToken(String token);

    Optional<Token > findByRefreshToken(String token);
}
