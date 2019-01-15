package collectionEtStream;
//Et une classe de test :

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


enum Couleur {
    MARRON("marron"),
    BLEU("bleu"),
    VERT("vert"),
    VERRON("verron"),
    INCONNU("non déterminé"),
    ROUGE("rouge mais j'avais piscine...");

    private String name = "";

    Couleur(String n){
        name = n;}
    public String toString() {return name;}
}

class Personne {

    public Double taille = 0.0d, poids = 0.0d;
    public String nom = "", prenom = "";
    public Couleur yeux = Couleur.INCONNU;
    public Personne() {	}
    public Personne(double taille, double poids, String nom, String prenom, Couleur yeux) {
        super();
        this.taille = taille;
        this.poids = poids;
        this.nom = nom;
        this.prenom = prenom;
        this.yeux = yeux;
    }

    public String toString() {
        String s = "Je m'appelle " + nom + " " + prenom;
        s += ", je pèse " + poids + " Kg";
        s += ", et je mesure " + taille + " cm.";
        return s;
    }
    public Double getTaille() {return taille;}
    public void setTaille(Double taille) {this.taille = taille;}
    public Double getPoids() {return poids;}
    public void setPoids(Double poids) {this.poids = poids;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public Couleur getYeux() {return yeux;}
    public void setYeux(Couleur yeux) {this.yeux = yeux;}
}




public class TestStream1 {
    public static void main(String[] args) {
        List<Personne> listP = Arrays.asList(
                new Personne(1.80, 70, "A", "Nicolas", Couleur.BLEU),
                new Personne(1.56, 50, "B", "Nicole", Couleur.VERRON),
                new Personne(1.75, 65, "C", "Germain", Couleur.VERT),
                new Personne(1.68, 50, "D", "Michel", Couleur.ROUGE),
                new Personne(1.96, 65, "E", "Cyrille", Couleur.BLEU),
                new Personne(2.10, 120, "F", "Denis", Couleur.ROUGE),
                new Personne(1.90, 90, "G", "Olivier", Couleur.VERRON)
        );
//
        Stream<Personne> sp = listP.stream();
        sp.forEach(System.out::println);
//        File file=new File("E:\\keepeek\\script\\test.txt");
////        PrintWriter test=new PrintWriter(test.txt, UTF16)
//        try{
//        FileOutputStream outPutFile = new FileOutputStream( file);
//            for (int i = 0; i < listP.size(); i++){
//                outPutFile.
//            }
//            outPutFile.flush();
//
//        }catch (Exception Ex){
//            System.out.println("votre fichier n'existe pas");
//        }

        System.out.println("\n--------------Après le filtre----------------------");
//        comme on a deja utilisé le stream , on va creer un nouveau stream
        List<Personne> listePlusDe50=new ArrayList<>();
        sp = listP.stream();
        sp.forEach(System.out::println);

        System.out.println("\nAprès le filtre et le map");
        sp = listP.stream();
        sp.	filter(x -> x.getPoids() > 50)
                .map(x -> x.getPoids())
                .forEach(System.out::println);

        System.out.println("\nAprès le filtre et le map et reduce");
        sp = listP.stream();

        Double sum = sp	.filter(x -> x.getPoids() > 50)
                .map(x -> x.getPoids())
                .reduce(0.0d, (x,y) -> x+y);
        System.out.println(sum);

        System.out.println("\n en utilisant Optional");
//        on traite les cas d'exception
        sp = listP.stream();

        Optional<Double> sumOpt = sp	.filter(x -> x.getPoids() > 250||x.getPoids() ==250)
                .map(x -> x.getPoids())
                .reduce((x,y) -> x+y);
//        if(sumOpt.isPresent())
//            System.out.println(sumOpt.get());
//        else
//            System.out.println("Aucun aggrégat de poids...");
//si il y a pas de resultat dans notre filtrage alors 1.0
        System.out.println(sumOpt.orElse(1.0));


        System.out.println("\n fonction count(): compter le nonbre d'elements filtré");
        sp = listP.stream();
        long count = sp	.filter(x -> x.getPoids() > 50)
                .map(x -> x.getPoids())
                .count();

        System.out.println("Nombre d'éléments : " + count);

        System.out.println("\n ------------------------------- Collectors--------------------------------");
        // C'est avec cet objet que nous pourrons dire que nous souhaitons avoir notre résultat sous forme de  Set  , de  Map  , de  List  et plus encore"
        sp = listP.stream();
        List<Double> ld = sp.filter(x -> x.getPoids() > 50)
                .map(x -> x.getPoids())
                .collect(Collectors.toList());
        List<Double> resultat=new ArrayList<>();
        resultat.addAll(ld);
        System.out.println(resultat);


        System.out.println("limit and skip");
        sp = listP.stream();
        List<Double> test = sp.filter(x -> x.getPoids() > 50)
                .map(x -> x.getPoids())
                .limit(1)
                .collect(Collectors.toList());
        List<Double> resultatTes=new ArrayList<>();
        resultatTes.addAll(test);
        System.out.println(resultatTes);


        System.out.println("findFirst and findAny");
        sp = listP.stream();
        Optional<Double> test2 = sp.filter(x -> x.getPoids() > 80)
                .map(x -> x.getPoids())
                .limit(1)
                .findAny();
        System.out.println(test2);
    }



}

