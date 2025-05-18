package bughunters.Tekton;

import java.util.ArrayList;
import java.util.List;

import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Spora;

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

    @Override
    public Disszolator cloneTekton(){
        Disszolator ujTekton = new Disszolator();
        return ujTekton;
    }

    public Disszolator(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        super(szomszed,gombafonal,spora);
    }

    public Disszolator(){}
}