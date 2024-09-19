package it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni;

public class NomeComuneIncompatibileException extends RuntimeException {

    public NomeComuneIncompatibileException() {
        super("Il nome del comune fornito non corrisponde a quello trovato dalle coordinate.");
    }
}

