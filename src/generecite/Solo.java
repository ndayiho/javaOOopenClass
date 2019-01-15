package generecite;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Solo<T> {

    //Variable d'instance
    private T valeur;
    //Constructeur par défaut
    public Solo(){
        this.valeur = null;
    }
    //Constructeur avec paramètre inconnu pour l'instant
    public Solo(T val){
        this.valeur = val;
    }
    //Définit la valeur avec le paramètre
    public void setValeur(T val){
        this.valeur = val;
    }
    //Retourne la valeur déjà « castée » par la signature de la méthode !
    public T getValeur(){
        return this.valeur;
    }


    public static void main(String[] args) {
        Solo<Integer> val = new Solo<Integer>(12);
        int nbre = val.getValeur();
        Solo<String> val2 = new Solo<String>("test");
        Solo<Float> val3 = new Solo<Float>(11.F);
        String nbre2 = val2.getValeur();
            //Ici, on essaie de mettre un nombre à virgule flottante à la place d'un entier
                val3.setValeur(12.2f);
        Float nbre3=val3.getValeur();
        System.out.println("Notre valeur est de : "+nbre3);
    }
}
