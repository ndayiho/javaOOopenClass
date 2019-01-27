package datajava8;



import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class ZoneDateIds {

    public static void main(String[] args) {
//    Map<String, String> maps = ZoneId.SHORT_IDS;
//        maps.values().stream().forEach((x) -> {System.out.println(x + " -- " + ZoneId.of(x).getRules());});
//
//        //Et connaître notre fuseau
//        System.out.println("");
//        System.out.println("Fuseau horaire courant : "+ZoneId.systemDefault());
//        System.out.println("Règle appliquer aux heures : " + ZoneId.systemDefault().getRules());

        LocalDateTime ldt = LocalDateTime.now();
        List<ZoneId> lzi = Arrays.asList(
                ZoneId.systemDefault(),
                ZoneId.of("Asia/Tokyo"),
                ZoneId.of("America/Anchorage")
        );

        lzi	.stream()
                .forEach((x) -> {
                    System.out.println(x + " : \t" + ldt.atZone(x).toInstant());
                });
    }
}
