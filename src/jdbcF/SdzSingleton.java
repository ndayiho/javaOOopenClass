package jdbcF;

public class SdzSingleton {

    //Le singleton
    private static SdzSingleton single;
    //Variable d'instance
    private String name = "";

    //Constructeur privé
    private SdzSingleton(){
        this.name = "Mon singleton";
        System.out.println("\t\tCRÉATION DE L'INSTANCE ! ! !");
    }

    //Méthode d'accès au singleton
    public static SdzSingleton getInstance(){
        if(single == null)
            single = new SdzSingleton();

        return single;
    }

    //Accesseur
    public String getName(){
        return this.name;
    }
}
