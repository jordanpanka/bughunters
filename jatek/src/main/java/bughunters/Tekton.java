package bughunters;

import java.util.ArrayList;
import java.util.List;

public class Tekton implements FonalKezeles {
    private List<Tekton> szomszedok; //melyik tektonok a szomszédjai
    private List<Gombafonal> gombafonalak; //megtalálható gombafajok listája
    private List<Spora> sporak; //megtalálható spórák listája

    public Tekton() {
        szomszedok = new ArrayList<>();
        gombafonalak = new ArrayList<>();
        sporak = new ArrayList<>();
    }

    public Tekton(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        szomszedok = szomszed;
        gombafonalak = gombafonal;
        sporak = spora;
    }

    /***
     * @brief Gombafonalat ad hozzá a Tektonhoz
     * @param gf Gombafaj: Aktuális gombafaj
     * @param honnan Tekton: Vizsgáljuk hogy erről a tektonról vezet e fonal az aktuálisra
     * @return növesztett gombafonalat adja vissza
     * @throws Exception ha nem tud gombafonalat növeszteni vagy rossz feladatnál lett hívva
     */
    public Gombafonal gombafonalAdd(Gombafaj gf, Tekton honnan) throws Exception{
        System.out.println("Meghívódott a Tekton gombafonalAdd metódusa.");

        Skeleton skeleton = new Skeleton();
        if(skeleton.getTestCase().equals("Gombafonal novesztese")){
            boolean valasz = skeleton.Kerdes("szomszedos t2 es t3-as tekton? jelen van a g1es gombafaj a gombafonala a t2es tektonon?");
            if(valasz){
                Gombafonal gf2 = new Gombafonal(gf,this,honnan);
                addFonal(gf2);
                return gf2;
            } else {
                throw new Exception("A gombafonal nem növeszthető ebben az állapotban.");
            }
        } 
        else if(skeleton.getTestCase().equals("Gombafonal novesztése Tektonra Gombatestbol")){
            boolean valasz = skeleton.Kerdes("van gombatest a g1 gombafajbol a t1 tektonon?");
            if(valasz){
                Gombafonal gf1 = new Gombafonal(gf,this,honnan);
                addFonal(gf1);
                return gf1;
            } else {
                throw new Exception("A gombafonal nem növeszthető ebben az állapotban.");
            }
        }
        throw new Exception("Nem megfelelő feladat.");
    }

    /***
     * @brief Gombatestet növeszt a tektonon
     * @param gf Gombafaj: Az a gombafaj, ami gombatestet akar növeszteni
     * @return Ha tud növeszteni gombatestet, akkor azt adja vissza
     * @throws Exception ha nem tud gombatestet növeszteni vagy nem megfelelő feladatnál lett hívva
     */
    public Gombatest gombatestNov(Gombafaj gf) throws Exception{
        System.out.println("Meghívódott a Tekton gombatestNov metódusa.");
        Skeleton skeleton = new Skeleton();

        if(skeleton.getTestCase().equals("Gombatest növesztése Tektonra")){
            boolean valasz = skeleton.Kerdes("alkalmas a tekton testnövesztésre?");
            if(valasz){
                try{
                    sporak.get(0).fogyaszt(3);
                    Gombatest gt1 = new Gombatest();
                    return gt1;
                }
                catch(Exception e){
                    throw e;
                }
            } else {
                throw new Exception("Gombatest nem növeszthető ebben az állapotban.");
            }
        }
        throw new Exception("Nem megfelelő feladat.");
    }

    /***
     * @brief
     * @param sp
     * @param r
     */
    public void eszik(Spora sp, Rovar r){

    }

    /***
     * @brief
     * @param gf
     */
    public void sporaSzor(Gombafaj gf){}

    /***
     * @brief
     * @param sp
     */
    public void addSpora(Spora sp){}

    /***
     * @brief
     */
    public void szomszedAllitas(){}

    /***
     * @brief
     * @return
     */
    public List<Gombafonal> gombafonalIgazitas(){}

    /***
     * @brief
     * @param a
     * @return
     */
    public boolean vanUt(Tekton a){return true;}

    /***
     * @brief
     * @param gf
     */
    @Override
    public void addFonal(Gombafonal gf){}

    /***
     * @brief
     * @param gf
     */
    @Override
    public void fonalSzakad(Gombafonal gf){}
}