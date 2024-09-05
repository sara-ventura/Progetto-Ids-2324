package it.unicam.cs.ids.GeoPlus.Model.Entita;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EntitaRichiesta{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long Id;

    private Long idAutore;

    public Long getId() {
        return Id;
    }


    public Long getIdAutore() {
        return idAutore;
    }

    public void setIdAutore(Long idAutore) {
        this.idAutore = idAutore;
    }
}
