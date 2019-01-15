package exceptionIntro;

public class except1 {
    public static void main(String[] args){
        int j = 20, i = 0;
        try{
        System.out.println(j/i);
        }catch (ArithmeticException e){
            System.out.println("Division par zéro !" + e.getMessage());
        }
        System.out.println("coucou toi !");

    }
}
