package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.InvitoContest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;


@Entity
public class UtenteStandard  extends UtenteRegistrato{


    @OneToMany
    private List<InvitoContest> listaInvitiContest;


    @OneToMany
    private List<Contenuto> listaContenutiSalvati;

    public UtenteStandard(Ruoli ruoloUtente, Long credenziali) {
        super(ruoloUtente, credenziali);
        this.listaInvitiContest = new ArrayList<>();
        this.listaContenutiSalvati = new ArrayList<>();
    }

    public UtenteStandard() {

    }

    public List<InvitoContest> getListaInvitiContest() {
        return listaInvitiContest;
    }

    public List<Contenuto> getListaContenutiSalvati() {
        return listaContenutiSalvati;
    }

    public void aggiungiInvitoContest(InvitoContest invitoContest) {
        if (!listaInvitiContest.contains(invitoContest)) {
            listaInvitiContest.add(invitoContest);
        }
    }

    public boolean rimuoviInvitoContest(InvitoContest invitoContest) {
        return listaInvitiContest.remove(invitoContest);
    }

    public boolean aggiungiContenutoSalvato(Contenuto contenuto) {
        if (!listaContenutiSalvati.contains(contenuto)) {
            return listaContenutiSalvati.add(contenuto);
        } else return false;
    }

    public boolean rimuoviContenutoSalvato(Contenuto contenuto) {
        return listaContenutiSalvati.remove(contenuto);
    }


    @Override
    public int hashCode() {

        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
