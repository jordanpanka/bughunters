package bughunters;

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
        System.out.println("Meghívódott a Monotekton gombafonalAdd metódusa.");
        
        if(getFonalak().isEmpty() || getFonalak().get(0).getGombafaj() == gf){
            Gombafonal gf2 = new Gombafonal(gf,this,honnan);
            addFonal(gf2);
            return gf2;
        } else {
            throw new Exception("Nem növeszthet ide gombafonalat.");
        }
    }

    /***
     * @brief Gombatestet növeszt saját magán a paraméterből kapott Gombafaj típusának megfelelően
     * @param gf Gombafaj: Az a gombafaj, ami gombatestet akar növeszteni
     * @return Ha tud növeszteni gombatestet, akkor azt adja vissza
     * @exception Exception akkor dobódik ha nem lehet ide gombatestet növeszteni
     */
    @Override
    public Gombatest gombatestNov(Gombafaj gf) throws Exception{
        System.out.println("Meghívódott a Monotekton gombatestNov metódusa.");

        if(gf == getFonalak().get(0).getGombafaj()){
            getSporak().get(0).fogyaszt(3);
            Gombatest gt1 = new Gombatest();
            return gt1;
        } else {
            throw new Exception("Nem tud gombatestet növeszteni erre a Tektonra.");
        }
    }
}