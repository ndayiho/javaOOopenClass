package datajava8;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class DateTimeManipulation {

    public static void main(String[] args) {
        //Le 25 Décembre 2018 a 13H37
        LocalDateTime ldt = LocalDateTime.of(2018, Month.DECEMBER, 25, 13, 37, 0);
        System.out.println("Date de référence : " + ldt);
//        //Utilisation de l'objet ChronoUnit pour changer l'objet
//        System.out.println("+1 semaine : " + ldt.plus(1, ChronoUnit.WEEKS));
//        System.out.println("+2 mois : " + ldt.plus(2, ChronoUnit.MONTHS));
        System.out.println("+3 ans : " + ldt.plus(3, ChronoUnit.YEARS));
        System.out.println("-1 an : " + ldt.minusYears(1));
//        System.out.println("+4 heures : " + ldt.plus(4, ChronoUnit.HOURS));
//        System.out.println("-5 secondes : " + ldt.minus(5, ChronoUnit.SECONDS));
//        System.out.println("-38 minutes : " + ldt.minusMinutes(38));

    }
}