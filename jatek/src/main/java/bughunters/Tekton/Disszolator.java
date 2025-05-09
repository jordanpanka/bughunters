package bughunters.Tekton;

import bughunters.Gombafaj.*;

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
        List<Gombafonal> gombafonalaim = getFonalak();
        List<Gombafonal> torlendoFonalak = new ArrayList<>();

        for (Gombafonal gombafonal : gombafonalaim) {
            if(gombafonal.getMiota() >= 5){
                //gombafonal.vegpontTorles();
                torlendoFonalak.add(gombafonal);
            }
        }
        

        for (Gombafonal torlendoFonal : torlendoFonalak) {
            torlendoFonal.vegpontTorles();
        }
    }   

    public Disszolator(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        super(szomszed,gombafonal,spora);
    }

    public Disszolator(){}
}