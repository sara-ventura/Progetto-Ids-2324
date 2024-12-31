package it.unicam.cs.ids.GeoPlus.Controller;


import it.unicam.cs.ids.GeoPlus.Model.Auth.*;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziAutenticazione;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerAutenticazioni {
    @Autowired
    private ServiziAutenticazione authService;


    @Autowired
    private LogoutHandlerImpl logoutHandler;


    @PostMapping("/aut/registra/turista")
    public ResponseEntity<?> registraTurista(@RequestBody RichiestaRegistrazione request) {
        try {
            RispostaAut response = authService.registraTurista(request);
            return ResponseEntity.ok(response);
        } catch (ExistingUserException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops sembra ci sia stato un errore durante la registrazione" + e.getMessage());
        }
    }

    @PostMapping("/aut/registra/utente")
    public ResponseEntity<?> registraUtenteComune(@RequestBody RichiestaRegistrazioneComune request) {
        try {
            RispostaAut response = authService.registraUtenteComune(request);
            return ResponseEntity.ok(response);
        } catch (ExistingUserException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops sembra ci sia stato un errore durante la registrazione" + e.getMessage());
        }
    }

    @PostMapping("/aut/registra/amministartore")
    public ResponseEntity<?> registraAmministratore(@RequestBody RichiestaRegistrazioneAmministratore request) {
        try {
            RispostaAut response = authService.registraAmministratore(request);
            return ResponseEntity.ok(response);
        } catch (ExistingUserException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops sembra ci sia stato un errore durante la registrazione" + e.getMessage());
        }
    }



    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody RichiestaLogin request) {
        try {
            RispostaAut response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ops! Sembra che le tue credenziali non siano corrette!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'autenticazione: " + e.getMessage());
        }
    }


    @PostMapping("/auth/refresh_token")
    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return authService.refreshToken(request, response);
    }


    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
            return ResponseEntity.ok("Logout eseguito con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante il logout: " + e.getMessage());
        }
    }

}


