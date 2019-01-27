package anonymeAndInterfaceFonc.lamda;

public class LamdaTes {
    public static void main(String[] args) {
//        Dialogue d = new Dialogue() {
//            public void parler(String question) {
//                System.out.println("Tu as dis : " + question);
//            }
//        };
//        d.parler("Bonjour");

        Dialogue d = (s,t) -> System.out.println("Tu as dis : " + s+" "+t);
        d.parler("Bonjour","Honoré");
    }
}
