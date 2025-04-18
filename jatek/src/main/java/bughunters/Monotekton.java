package bughunters;

import java.awt.List;

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
    //----------------------------------------------------------------------------------------
    @Override
    public Gombatest gombatestNov(Gombafaj gf, boolean sporaval) throws Exception{
        //System.out.println("Meghívódik a Monotekton gombatestNov metódusa.");

        if(!getFonalak().isEmpty()){
            //gf van e jelen
            for (Gombafonal gombafonal : getFonalak()) {
                if(gombafonal.getGombafaj() == gf && 
                (gombafonal.getVegpont1() == this || gombafonal.getVegpont2() == this)){

                    if(sporaval){
                        //gf-hez van e spora a tektonon
                        for (Spora spora : getSporak()) {
                            if(spora.getGombafaj() == gf){
                                spora.fogyaszt(3);
                                Gombatest gombatest = new Gombatest();
                                return gombatest;
                            }
                        }
                        throw new Exception("Nincs a Gombafajhoz tartozó spóra.");
                    } 
                    else {
                        Gombatest gombatest = new Gombatest();
                        return gombatest;
                    }
                } 
                else {
                    throw new Exception("Nincs jelen a megadott Gombafaj a tektonon.");
                }
            }
        } else {
            throw new Exception("Nem tud gombatestet növeszteni erre a Tektonra, mert nincs jelen Gombafonal.");
        }
    }

    public Monotekton(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        super(szomszed,gombafonal,spora);
    }

    public Monotekton(){}
}