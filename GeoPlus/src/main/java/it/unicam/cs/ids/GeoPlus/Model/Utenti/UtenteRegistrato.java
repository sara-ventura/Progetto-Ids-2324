package it.unicam.cs.ids.GeoPlus.Model.Utenti;

import it.unicam.cs.ids.GeoPlus.Model.Comune;

public class UtenteRegistrato {

    private Long idUtente;


//    private Ruoli ruoloUtente;

    private Comune comuneApparteneza;

    public UtenteRegistrato(Comune comuneApparteneza) {
//        this.ruoloUtente = ruoloUtente;
        this.comuneApparteneza = comuneApparteneza;
    }

    public UtenteRegistrato() {

    }

//    public Ruoli getRuoloUtente() {
//        return this.ruoloUtente;
//    }

    public Comune getComuneApparteneza() {
        return comuneApparteneza;
    }

//    public void setRuoloUtente(Ruoli ruoloUtente) {
//        this.ruoloUtente = ruoloUtente;
//    }

    public void setComuneApparteneza(Comune comuneApparteneza) {
        this.comuneApparteneza = comuneApparteneza;
    }

//    @Override
//    public boolean equals(Object o) {
//
//    }
//    @Override
//    public int hashCode() {
//
//    }

}
