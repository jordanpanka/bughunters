package bughunters.Tekton;

import bughunters.Gombafaj.*;

import java.util.List;

public class Monotekton extends Tekton {
    /***
     * @brief Gombafonalat ad hozzá a Tektonhoz
     * @param gf Gombafaj: Aktuális gombafaj
     * @param honnan Tekton: Vizsgáljuk hogy erről a tektonról vezet e fonal az aktuálisra
     * @return Növesztett gombafonalat adja vissza
     * @exception Exception akkor dobódik ha nem tud oda növeszteni fonalat
     */
    @Override
    public Gombafonal gombafonalAdd(Gombafaj gf, Tekton honnan) throws Exception{
        //System.out.println("Meghívódik a Monotekton gombafonalAdd metódusa.");
        
        if(getFonalak().isEmpty()){
            Gombafonal gf2 = new Gombafonal(gf,this,honnan);
            addFonal(gf2);
            return gf2;
        }
        else {
            for (Gombafonal gombafonal : getFonalak()) {
                if(gombafonal.getGombafaj() == gf && 
                (gombafonal.getVegpont1() == this || gombafonal.getVegpont2() == this)){
                    Gombafonal gf2 = new Gombafonal(gf,this,honnan);
                    addFonal(gf2);
                    return gf2;
                }
            }
            throw new Exception("Nem növeszthet ide gombafonalat.");
        }
    }

    /***
     * @brief Gombatestet növeszt saját magán a paraméterből kapott Gombafaj típusának megfelelően
     * @param gf Gombafaj: Az a gombafaj, ami gombatestet akar növeszteni
     * @return Ha tud növeszteni gombatestet, akkor azt adja vissza
     * @exception Exception akkor dobódik ha nem lehet ide gombatestet növeszteni
     */
    /*
    @Override
    public Gombatest gombatestNov(Gombafaj gf, boolean sporaval) throws Exception {
        if (getFonalak().isEmpty()) {
            throw new Exception("Nem tud gombatestet növeszteni erre a Tektonra, mert nincs jelen Gombafonal.");
        }

        // Ellenőrizzük, hogy van-e a megadott Gombafajhoz tartozó Gombafonal
        boolean gfJelen = false;
        for (Gombafonal gombafonal : getFonalak()) {
            if (gombafonal.getGombafaj() == gf && 
                (gombafonal.getVegpont1() == this || gombafonal.getVegpont2() == this)) {
                gfJelen = true;
                break;
            }
        }

        if (!gfJelen) {
            throw new Exception("Nincs jelen a megadott Gombafaj a tektonon.");
        }

        // Spóra ellenőrzés, ha szükséges
        if (sporaval) {
            boolean vanSpora = false;
            for (Spora spora : getSporak()) {
                if (spora.getGombafaj() == gf) {
                    spora.fogyaszt(3);
                    vanSpora = true;
                    break;
                }
            }
            if (!vanSpora) {
                throw new Exception("Nincs a Gombafajhoz tartozó spóra.");
            }
        }

        return new Gombatest();
    }
    */

    public Monotekton(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        super(szomszed, gombafonal, spora);
    }


    public Monotekton(){}
}