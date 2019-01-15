package collectionEtStream;

import java.util.*;

public class Coursv1 {
//    ikindi nuko twabonye ko dushbora gukora parcours yacu na iterateur !!!!!!!!
    public static void main(String[] args) {
        List <List>l = new LinkedList();
        List l1=new LinkedList();
        List l2=new LinkedList();
        l1.add(12);
        l1.add(13);
        l2.add("toto ! !");
        l2.add(12.20f);
        l.add(l1);
        l.add(l2);
        System.out.println(l);
        for(int i = 0; i < l.size(); i++)
            System.out.println("Élément à l'index " + i + " = " + l.get(i));

        System.out.println("\n \tParcours avec un itérateur ");
        System.out.println("-----------------------------------");
        ListIterator li = l.listIterator();
        while(li.hasNext()){
            System.out.println(li.next());
        }
    }


    public static class Coursv2 {
        public static void main(String[] args) {
            HashSet hs = new HashSet();
            hs.add("toto");
            hs.add(12);
            hs.add('d');

            Iterator it = hs.iterator();
            while(it.hasNext())
                System.out.println(it.next());

            System.out.println("\nParcours avec un tableau d'objet");
            System.out.println("-----------------------------------");

            Object[] obj = hs.toArray();
            for(Object o : obj)
                System.out.println(o);
        }

    }
}