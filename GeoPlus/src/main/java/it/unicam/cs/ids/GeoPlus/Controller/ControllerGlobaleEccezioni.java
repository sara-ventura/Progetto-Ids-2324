package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.ComuneNonTrovatoException;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.ComuneGiaEsistenteException;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.EmailGiaUtilizzata;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.NomeComuneIncompatibileException;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.PoiGiaEsistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@ControllerAdvice
@RestController
public class ControllerGlobaleEccezioni {
    @ExceptionHandler(ComuneGiaEsistenteException.class)
    public ResponseEntity<String> handleComuneGiaEsistente(ComuneGiaEsistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ComuneNonTrovatoException.class)
    public ResponseEntity<String> handleComuneNonTrovato(ComuneNonTrovatoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comune non trovato: " + ex.getMessage());
    }

    @ExceptionHandler(NomeComuneIncompatibileException.class)
    public ResponseEntity<String> handleNomeComuneIncompatibile(NomeComuneIncompatibileException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmailGiaUtilizzata.class)
    public ResponseEntity<String> handleEmailGiaUtilizzata(EmailGiaUtilizzata ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento non trovato: " + ex.getMessage());
    }

    @ExceptionHandler(PoiGiaEsistenteException.class)
    public ResponseEntity<String> handlePoiGiaEsiatenteException(PoiGiaEsistenteException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno del server: " + ex.getMessage());
    }
}
