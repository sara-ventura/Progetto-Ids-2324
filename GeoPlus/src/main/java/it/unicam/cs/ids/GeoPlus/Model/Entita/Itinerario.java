package it.unicam.cs.ids.GeoPlus.Model.Entita;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.*;


@Entity
public class Itinerario extends EntitaRichiesta {

    private String nomeItinerario;
    private String descrizioneItinerario;
    @OneToMany
    private List<Poi> listaPoi;
    @ManyToOne
    private Comune comune;


    public Itinerario(String nomeItinerario, String descrizioneItinerario, Comune comune, List<Poi> listaPoi) {
        this.nomeItinerario = nomeItinerario;
        this.descrizioneItinerario = descrizioneItinerario;
        this.comune = comune;
        this.listaPoi = riordinaPoi(listaPoi);
    }

    public Itinerario() {
    }

    public String getNomeItinerario() {
        return nomeItinerario;
    }


    public void setNomeItinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
    }


    public String getDescrizioneItinerario() {
        return descrizioneItinerario;
    }


    private void setDescrizioneItinerario(String descrizioneItinerario) {
        this.descrizioneItinerario = descrizioneItinerario;
    }


    public List<Poi> getListaPoi() {
        return listaPoi;
    }


    public boolean contienePoi(Poi poi) {
        return listaPoi.contains(poi);
    }


    public Comune getComune() {
        return comune;
    }


    private List<Poi> riordinaPoi(List<Poi> listaPoiNonOrdinata) {
        if (listaPoiNonOrdinata.isEmpty()) {
            return new ArrayList<>();
        }
        Poi puntoDiPartenza = listaPoiNonOrdinata.get(0);
        List<Poi> ordinati = new ArrayList<>(listaPoiNonOrdinata);
        ordinati.sort(Comparator.comparingDouble(poi -> calcolaDistanza(puntoDiPartenza.getPosizionePoi(), poi.getPosizionePoi())));
        return ordinati;
    }

    public double calcolaDistanza(Coordinate coordinata1, Coordinate coordinata2) {
        final int R = 6371; // Raggio della Terra in km
        double lat1 = Math.toRadians(coordinata1.getLatitudine());
        double lon1 = Math.toRadians(coordinata1.getLongitudine());
        double lat2 = Math.toRadians(coordinata2.getLatitudine());
        double lon2 = Math.toRadians(coordinata2.getLongitudine());
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;
        Itinerario that = (Itinerario) o;
        return
                Objects.equals(nomeItinerario, that.nomeItinerario) &&
                        Objects.equals(descrizioneItinerario, that.descrizioneItinerario) &&
                        Objects.equals(comune, that.comune) &&
                        Objects.equals(listaPoi, that.listaPoi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeItinerario, descrizioneItinerario, comune, listaPoi);
    }


}