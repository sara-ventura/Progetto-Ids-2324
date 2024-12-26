package it.unicam.cs.ids.GeoPlus.Model.Repository;


import it.unicam.cs.ids.GeoPlus.Model.Entita.RevokedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RevokedTokenRepository extends JpaRepository<RevokedToken, Long> {
    Optional<RevokedToken> findByToken(String token);
}
