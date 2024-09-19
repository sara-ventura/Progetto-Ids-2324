package it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni;

public class ComuneGiaEsistenteException extends RuntimeException {
    public ComuneGiaEsistenteException() {
        super("Il comune è già presente nella piattaforma. Non è possibile aggiungere duplicati.");
    }
}