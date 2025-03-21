package bughunters;

import java.util.ArrayList;
import java.util.List;

public class Tekton implements FonalKezeles {
    private List<Tekton> szomszedok; //melyik tektonok a szomszédjai
    private List<Gombafonal> gombafonalak; //megtalálható gombafonalak listája
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
                if(!valasz1){
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
        System.out.println("Meghívódott a Tekton sporaSzor metódusa.");
        Skeleton skeleton = new Skeleton();

        if(skeleton.getTestCase().equals("Spóra szórása már létező Spórával")){
            Benito b2 = new Benito(30, 2, gf);

            for (Tekton tekton : szomszedok) {
                tekton.addSpora(b2);
                //hogyan hívjam b1-re???
            } 
        }
        if(skeleton.getTestCase().equals("Spóra szórása még nem létező Spórával")){
            Benito b1 = new Benito(30, 1, gf);

            for (Tekton tekton : szomszedok) {
                tekton.addSpora(b1);
            }
        }
        if(skeleton.getTestCase().equals("Spóra szórása fejlett gombatest által")){
            Benito b1 = new Benito(30, 1, gf);
            Benito b2 = new Benito(30,2,gf);

            for (Tekton tekton : szomszedok) {
                tekton.addSpora(b1);
                for (Tekton tektonszomszed : tekton.getSzomszedok()) {
                    tekton.addSpora(b2);
                }
            }
        }
    }

    /***
     * @brief
     * @param sp
     */
    public void addSpora(Spora sp){}

    /***
     * @brief A törés során érintett tektonok új szomszédait állítja be
     */
    public void szomszedAllitas(){
        System.out.println("Meghívódott a Tekton szomszedAllitas metódusa.");
        
        Tekton ujTekton = new Tekton();
        szomszedok.add(ujTekton);

        //mi alapján mondom meg melyik a szomszédja amit törölni kell?
    }

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
     * @brief Gombafonalat hozzáadja a gombafonalak listához
     * @param gf Gombafonal: a listához hozzáadandó fonal
     */
    @Override
    public void addFonal(Gombafonal gf){
        gombafonalak.add(gf);
    }

    /***
     * @brief Elszakítja a paraméterben kapott gombafonalat, azáltal hogy kiveszi a gombafonal listából
     * @param gf Gombafonal: elszakítani kívánt gombafonal
     */
    @Override
    public void fonalSzakad(Gombafonal gf){

        ///csak annyi hogy kiszedem a gombafonalak listából vagy kell állítgassak végpontokat is?

        System.out.println("Meghívódott a Tekton fonalSzakad metódusa.");
        Skeleton skeleton = new Skeleton();

        if(skeleton.getTestCase().equals("Fonal vágás")){
            gombafonalak.remove(gf);
            for (Tekton tekton : szomszedok) {
                if(tekton == gf.getVegpont2()){

                    //melyik vegpontot nézzük?

                    for (Gombafonal gombafonal : tekton.getFonalak()) {
                        if(gombafonal == gf){
                            tekton.getFonalak().remove(gf);
                        }
                    }
                }
            }
        }
    }
}