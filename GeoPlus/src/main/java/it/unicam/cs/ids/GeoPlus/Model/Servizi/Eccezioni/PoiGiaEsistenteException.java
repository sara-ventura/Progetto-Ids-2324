package it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni;

public class PoiGiaEsistenteException extends RuntimeException  {

    public PoiGiaEsistenteException() {
        super("Esiste già un poi in queste coordinate.");
    }
}
