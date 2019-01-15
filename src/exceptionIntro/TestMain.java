package exceptionIntro;

public class TestMain {

    public static void main(String[] args){
        Ville v = new Ville();
        Ville v1=null;
        Ville v2=null;
        Capitale capitale= new Capitale();
        try{
       v1 = new Ville("Marseille", 1236, "France");
       v2 = new Ville("Rio", 321654, "Brésil");

        }catch (NombreHabitantException | NomVilleException e2){
                System.out.println(e2.getMessage());
            }


        System.out.println("\n\n"+v1.decrisToi());
        System.out.println(v.decrisToi());
        System.out.println(v2.decrisToi()+"\n\n");
        v1.comparer(v2).toString();
        System.out.println(capitale.decrisToi(" \"\\n \\t ==>>\" + this.monument+ \" en est un monument"));
    }
}