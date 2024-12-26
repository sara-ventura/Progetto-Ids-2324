package it.unicam.cs.ids.GeoPlus.Model.Servizi;


import it.unicam.cs.ids.GeoPlus.Model.Auth.*;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Token;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Repository.TokenRepository;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.ComuneNonTrovatoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiziAutenticazione {
    @Autowired
    private ServiziComune serviziComune;
    @Autowired
    private ServiziAccount accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServiziJwt jwtService;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private AuthenticationManager authenticationManager;


    public RispostaAut registraTurista(RichiestaRegistrazione request) throws ExistingUserException {
        Account account = accountService.creaTurista(request.email(), passwordEncoder.encode(request.password()), request.username());
        String accessToken = jwtService.generateAccessToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);
        salvaTokenUtente(accessToken, refreshToken, account);
        return new RispostaAut(accessToken, refreshToken, "Utente registrato con successo");
    }


    public RispostaAut registraAmministratore(RichiestaRegistrazioneAmministratore request) throws ExistingUserException, ComuneNonTrovatoException {
        Comune comune = serviziComune.creaComune(request.nomeComune(), request.descrizione(), request.coordinateCentrali());
        if(comune == null) {
            throw new ComuneNonTrovatoException("Comune non trovato");
        }
        Account account = accountService.creaAmministratore(request.email(), passwordEncoder.encode(request.password()), request.username(), comune);
        String accessToken = jwtService.generateAccessToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);
        salvaTokenUtente(accessToken, refreshToken, account);
        return new RispostaAut(accessToken, refreshToken, "Utente registrato con successo");
    }

    public RispostaAut registraUtenteComune(RichiestaRegistrazioneComune request) throws ExistingUserException {
        Comune comune = serviziComune.getComune(request.nomeComune());
        Account account = accountService.creaUtenteComune(request.email(), passwordEncoder.encode(request.password()), request.username(), request.ruolo(), comune);
        String accessToken = jwtService.generateAccessToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);
        salvaTokenUtente(accessToken, refreshToken, account);
        return new RispostaAut(accessToken, refreshToken, "Utente registrato con successo");
    }

    public RispostaAut login(RichiestaLogin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        Account account = accountService.getAccount(request.email())
                .orElseThrow(() -> new BadCredentialsException(request.email()));
        ;
        String accessToken = jwtService.generateAccessToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);

        revocaTokenUtente(account);
        salvaTokenUtente(accessToken, refreshToken, account);
        return new RispostaAut(accessToken, refreshToken, "Bentornato!");

    }

    private void revocaTokenUtente(Account user) {
        List<Token> validTokens = tokenRepository.findAllAccessTokensByAccount(user);
        if (validTokens.isEmpty()) {
            return;
        }
        validTokens.forEach(t -> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    private void salvaTokenUtente(String accessToken, String refreshToken, Account account) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setAccount(account);
        tokenRepository.save(token);
    }

    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        Account account = accountService.getAccount(username)
                .orElseThrow(() -> new RuntimeException("No user found"));
        if (jwtService.isTokenRevoked(token)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if (jwtService.isValidRefreshToken(token, account)) {
            String accessToken = jwtService.generateAccessToken(account);
            String refreshToken = jwtService.generateRefreshToken(account);

            revocaTokenUtente(account);
            salvaTokenUtente(accessToken, refreshToken, account);

            return new ResponseEntity(new RispostaAut(accessToken, refreshToken, "New token generated"), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }
}