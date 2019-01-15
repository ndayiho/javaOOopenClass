package enumeration;

public class MainEnum {
    public static void main(String args[]){
Langage langage=Langage.JAVA;
          for(Langage lang : Langage.values()){
            if(Langage.JAVA.equals(lang))
                System.out.println("J'aime le : " + langage.toString());
            else
                System.out.println(lang);
        }
        Langage l1 = Langage.JAVA;
        Langage l2 = Langage.PHP;

        l1.getEditor();
        l2.getEditor();
        System.out.println(l2.isAime());
        }

}
