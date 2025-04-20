package bughunters;

import java.util.ArrayList;
import java.util.List;

/***
 * @brief Ez az a típus, ahol egy idő után felszívódik a lerakott gombafonal.
 */
public class Disszolator extends Tekton {

    /***
     * @brief a tektonon tartózkodó gombafonalat felszívja, ha már lejárt a gombafonal ideje
     */
    @Override
    public void gombafonalFelszivas() {
        //System.out.println("Meghívódik a Disszolator gombafonalFelszivas metódusa.");

        for (Gombafonal gombafonal : getFonalak()) {
            if(gombafonal.getMiota() >= 5){
                gombafonal.vegpontTorles();
            }
        }
    }   

    public Disszolator(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        super(szomszed,gombafonal,spora);
    }

    public Disszolator(){}
}