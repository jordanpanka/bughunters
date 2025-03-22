package bughunters;

import java.util.ArrayList;
import java.util.List;

/***
 * Ez az a típus, ahol egy idő után felszívódik a lerakott gombafonal.
 */
public class Disszolator extends Tekton {

    /***
     * @brief a tektonon tartózkodó gombafonalat felszívja, ha már lejárt a gombafonal ideje
     */
    @Override
    public void gombafonalFelszivas() {
        System.out.println("Meghívódott a Disszolator gombafonalFelszivas metódusa.");

        boolean valasz = Skeleton.getInstance().Kerdes("gf1 gombafonal már öt kör óta rajta van a tektonon?");
        if(valasz){
            List<Gombafonal> fonalak = getFonalak(); // Elkerüljük a többszöri metódushívást

        if (fonalak != null) { 
            for (Gombafonal gombafonal : new ArrayList<>(fonalak)) { 
                        gombafonal.vegpontTorles();  
                    } 
                }
                    
            /*for (Gombafonal gombafonal : getFonalak()) {
                gombafonal.vegpontTorles(); 
                
                if(getFonalak()==null)
                
            }*/
            
        }
        
    }
}