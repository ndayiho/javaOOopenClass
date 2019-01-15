package enumeration;

public class AvantEnumeration {

    public static final int PARAM1 = 1;
    public static final int PARAM2 = 2;

    public void fait(int param){
        if(param == PARAM1)
            System.out.println("Fait à la façon N°1");
        if(param == PARAM2)
            System.out.println("Fait à la façon N°2");
    }

    public static void main(String args[]){
        AvantEnumeration ae = new AvantEnumeration();
        ae.fait(AvantEnumeration.PARAM1);
        ae.fait(AvantEnumeration.PARAM2);
        ae.fait(4);
    }
}