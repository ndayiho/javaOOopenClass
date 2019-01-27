package devoir1;

import java.util.ArrayList;
import java.util.List;

public class Garage {
    List< Vehicule> listVehicule= new ArrayList<>();

    public void addVoiture(Vehicule lag1) {
        listVehicule.add(lag1);
    }

    @Override
    public String toString() {

        if(listVehicule.isEmpty()){
            return "-----------------------\n"+"garage Openclass room \n"+"-----------------------";

        }else {

            return"test";
        }

    }
}
