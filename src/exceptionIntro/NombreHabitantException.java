package exceptionIntro;

class NombreHabitantException extends Exception{
    public NombreHabitantException(){
        System.out.println("Vous essayez d'instancier une classe Ville avec un nombre d'habitants négatif !");
    }
    public NombreHabitantException(int nombre){
        System.out.println("Vous essayez d'instancier une classe Ville avec un nombre d'habitants négatif !"+"le nmbre est "+nombre);
    }
}