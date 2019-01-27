package devoir1;

public class Moteur {
    TypeMoteur type;
    String cylindre;
    double prix;

    public Moteur(String cylindre, double prix) {
        this.cylindre = cylindre;
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        return "Moteur{" +
                "type=" + type +
                ", cylindre='" + cylindre + '\'' +
                ", prix=" + prix +
                '}';
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }



}
