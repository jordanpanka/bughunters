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
     */
    public Gombafonal gombafonalAdd(Gombafaj gf, Tekton honnan){
        System.out.println("Meghívódott a Tekton gombafonalAdd metódusa.");

        Skeleton skeleton = new Skeleton();
        if(skeleton.getTestCase().equals("Gombafonal novesztese")){
            boolean valasz = skeleton.Kerdes("szomszedos t2 es t3-as tekton? jelen van a g1es gombafaj a gombafonala a t2es tektonon?");
            if(valasz){
                Gombafonal gf2 = new Gombafonal();
                addFonal(gf2);
                return gf2;
            } else {
                throw new IllegalStateException("A gombafonal nem növeszthető ebben az állapotban.");
            }
        } 
        else if(skeleton.getTestCase().equals("Gombafonal novesztése Tektonra Gombatestbol")){
            boolean valasz = skeleton.Kerdes("van gombatest a g1 gombafajbol a t1 tektonon?");
            if(valasz){
                Gombafonal gf1 = new Gombafonal();
                addFonal(gf1);
                return gf1;
            } else {
                throw new IllegalStateException("A gombafonal nem növeszthető ebben az állapotban.");
            }
        }
        throw new IllegalStateException("Nem megfelelő feladat.");
    }

    /***
     * @brief A paraméterben kapott gombafaj gombatestet növeszt a tektonon
     * @param gf Gombafaj: 
     * @return Ha tud: növesztett gombatestet adja vissza, Ha nem tud: exception-t dob
     */
    public Gombatest gombatestNov(Gombafaj gf){
        
    }

    /***
     * @brief
     * @param sp
     * @param r
     */
    public void eszik(Spora sp, Rovar r){}

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