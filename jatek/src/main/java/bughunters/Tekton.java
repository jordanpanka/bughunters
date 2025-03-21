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

    public List<Tekton> getSzomszedok(){
        return szomszedok;
    }
    public List<Gombafonal> getFonalak(){
        return gombafonalak;
    }
    public List<Spora> getSporak(){
        return sporak;
    }

    public void setSzomszedok(List<Tekton> ujszomszedok){
        szomszedok = ujszomszedok;
    }
    public void setFonalak(List<Gombafonal> ujfonalak){
        gombafonalak = ujfonalak;
    }
    public void setSporak(List<Spora> ujsporak){
        sporak = ujsporak;
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
     * @brief A rovar evését segítő függvény
     * @param sp Spora: amit a rovar meg akar enni
     * @param r Rovar: akutális rovar ami eszik
     */
    public void eszik(Spora sp, Rovar r) throws Exception {
        System.out.println("Meghívódott a Tekton eszik metódusa.");
        Skeleton skeleton = new Skeleton();

        if(skeleton.getTestCase().equals("Bénító spóra evése")){
            boolean valasz = skeleton.Kerdes("van-e meg spora, amit meg tud enni?");
            if(valasz){
                boolean valasz1 = skeleton.Kerdes("maradni fog-e meg spora?");
                sporak.get(0).fogyaszt(3);
                if(!valasz){
                    sporak.remove(0);
                }
            } else {
                throw new Exception("Nincs eleg spora.");
            }
        }
        throw new Exception("Nem megfelelő feladat");
    }

    /***
     * @brief A spóraszórást valósítja meg azáltal, hogy a szomszedok listájában szereplő tektonokra spórát rak
     * @param gf Gombafaj: Ehhez a fajhoz tartozik a spóra
     */
    public void sporaSzor(Gombafaj gf){
        
    }

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
    public boolean vanUt(Tekton a){
        Set<Tekton> latogatott = new HashSet<>();
        Queue<Tekton> sor = new LinkedList<>();
        
        sor.add(this);
        latogatott.add(this);

        while (!sor.isEmpty()) {
            Tekton jelenlegi = sor.poll();
            if (jelenlegi == a) return true;

            for (Gombafonal gf : jelenlegi.getGombafonalhalozat()) {
                Tekton szomszed = (gf.getVegpont1() == jelenlegi) ? gf.getVegpont2() : gf.getVegpont1();
                if (!latogatott.contains(szomszed)) {
                    latogatott.add(szomszed);
                    sor.add(szomszed);
                }
            }
        }
        return false;
        
    }

    /***
     * @brief Gombafonalat hozzáadja a gombafonalak listához
     * @param gf Gombafonal: a listához hozzáadandó fonal
     */
    @Override
    public void addFonal(Gombafonal gf){
        gombafonalak.add(gf);
    }

    /***
     * @brief
     * @param gf
     */
    @Override
    public void fonalSzakad(Gombafonal gf){}
}