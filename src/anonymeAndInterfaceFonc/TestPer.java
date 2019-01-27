package anonymeAndInterfaceFonc;

public class TestPer {
    public static void main(String[] args) {
        Personnage pers = new Guerrier();
        Personnage pers2 = new Guerrier();
        pers.soigner();
        pers.setSoin(new Operation());
//        pers.soigner();

        //Utilisation d'une classe anonyme: c est comme une classe crée d' une facon ano...
        pers.setSoin(new Soin() {
            public void soigner() {
                System.out.println("Je soigne avec une classe anonyme ! ");
            }
        });

        pers.soigner();


        EspritCombatif pisto= new CombatPistolet();

        pisto.combat();
    }

}
