package devoir1;


import java.util.ArrayList;
import java.util.List;

public class Vehicule {

    double prix ;
    String nom;
    List<Option> options= new ArrayList<Option>();
    Marque nomMarque;



    Moteur moteur;

    @Override
    public String toString() {
        return "Vehicule{" +
                "prix=" + prix +
                ", nom='" + nom + '\'' +
                ", options=" + options +
                ", nomMarque=" + nomMarque +
                '}';
    }
public void addOption(Option option){
    options.add(option);
}

    public double getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public List<Option> getOptions() {
        return options;
    }

    public Marque getMarque() {
        return nomMarque;
    }

    public Moteur getMoteur() {
        return moteur;
    }

    public void setMoteur(Moteur moteur) {
        this.moteur = moteur;
    }


}


