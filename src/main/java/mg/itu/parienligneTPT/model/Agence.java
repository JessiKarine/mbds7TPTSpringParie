package mg.itu.parienligneTPT.model;

import javax.persistence.*;

@Entity
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    private String nom;

    public Agence() {
    }


    public Agence(String nom) {
        this.nom = nom;
    }

    public Agence(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
