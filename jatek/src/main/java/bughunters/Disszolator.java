package bughunters;

/***
 * Ez az a típus, ahol egy idő után felszívódik a lerakott gombafonal.
 */
public class Disszolator extends Tekton {

    /***
     * @brief a tektonon tartózkodó gombafonalat felszívja, ha már lejárt a gombafonal ideje
     */
    public void gombafonalFelszivas() {
        System.out.println("Meghívódott a Disszolator gombafonalFelszivas metódusa.");
        Skeleton skeleton = new Skeleton();

        if(skeleton.getTestCase().equals("Gombafonal felszívódása Disszolátoron")){
            boolean valasz = skeleton.Kerdes("gf1 gombafonal már öt kör óta rajta van a tektonon?");

            if(valasz){
                for (Gombafonal gombafonal : getFonalak()) {
                    gombafonal.vegpontTorles();
                }
            } else {
                return;
            }
        }
    }
}