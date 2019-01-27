package devoir1;

public enum Marque {
    //Objets directement construits
    RENO("RENO"),
    PIGEOT ("PIGEOT"),
    TROEN("TROEN");
    private String nomMarque = "";
    //Constructeur

    Marque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }
}
