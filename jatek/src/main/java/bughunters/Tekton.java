package bughunters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
     * @brief Új szomszédot ad hozzá az aktuális tektonhoz
     * @param ujszomszed
     */
    public void addSzomszed(Tekton ujszomszed){
        szomszedok.add(ujszomszed);
    }

    /***
     * @brief Gombafonalat ad hozzá a Tektonhoz
     * @param gf Gombafaj: Aktuális gombafaj
     * @param honnan Tekton: Vizsgáljuk hogy erről a tektonról vezet e fonal az aktuálisra
     * @return növesztett gombafonalat adja vissza
     * @throws Exception ha nem tud gombafonalat növeszteni vagy rossz feladatnál lett hívva
     */
    public Gombafonal gombafonalAdd(Gombafaj gf, Tekton honnan) throws Exception {
        System.out.println("Meghívódott a Tekton gombafonalAdd metódusa.");
        //van-e már ilyen gombafonal és milyen állapotban van, mert ha van és haldokló vagy utolsó esély akkor csak azt kell átállítani
        if(Skeleton.getInstance().getTestCase().equals("Gombafonal novesztese")){
            boolean valasz = Skeleton.getInstance().Kerdes("szomszedos t2 es t3-as tekton? jelen van a g1es gombafaj a gombafonala a t2es tektonon?");
            if(valasz){
                Gombafonal gf2 = new Gombafonal(gf,this,honnan);
                addFonal(gf2);
                return gf2;
            } else {
                throw new Exception("A gombafonal nem növeszthető ebben az állapotban.");
            }
        } 
        else if(Skeleton.getInstance().getTestCase().equals("Gombafonal novesztése Tektonra Gombatestbol")){
            boolean valasz = Skeleton.getInstance().Kerdes("van gombatest a g1 gombafajbol a t1 tektonon?");
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

        if(Skeleton.getInstance().getTestCase().equals("Gombatest növesztése Tektonra")){
            boolean valasz = Skeleton.getInstance().Kerdes("alkalmas a tekton testnövesztésre?");
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

        if(Skeleton.getInstance().getTestCase().equals("Bénító spóra evése")){
            boolean valasz = Skeleton.getInstance().Kerdes("van-e meg spora, amit meg tud enni?");
            if(valasz){
                boolean valasz1 = Skeleton.getInstance().Kerdes("maradni fog-e meg spora?");
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
        
        if(Skeleton.getInstance().getTestCase().equals("Spóra szórása már létező Spórával")){
            Benito b2 = new Benito(30, 2, gf);

            for (Tekton tekton : szomszedok) {
                tekton.addSpora(b2);
                Benito b1 = null;
                for (Spora spora : sporak) {
                    if(b2.getGombafaj() == spora.getGombafaj()){
                        b1 = (Benito)spora;
                    }
                }
                b1.szorasTortent();
            } 
        }
        if(Skeleton.getInstance().getTestCase().equals("Spóra szórása még nem létező Spórával")){
            Benito b1 = new Benito(30, 1, gf);

            for (Tekton tekton : szomszedok) {
                tekton.addSpora(b1);
            }
        }
        if(Skeleton.getInstance().getTestCase().equals("Spóra szórása fejlett gombatest által")){
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
    public void szomszedAllitas(Tekton t){
        System.out.println("Meghívódott a Tekton szomszedAllitas metódusa.");
        
        szomszedok.add(t);

        t.addSzomszed(this);
        t.addSzomszed(szomszedok.get(1));

        szomszedok.remove(1);

        //mi alapján mondom meg melyik a szomszédja amit törölni kell?
    }

    /***
     * @brief
     * @return
     */
    public List<Gombafonal> gombafonalIgazitas(){}

    /***
     * @brief Megadja hogy két tekton között van e út (össze vannak kötve)
     * @param a Tekton: 
     * @return true: van út, false: nincs út
     */
    public boolean vanUt(Tekton a){
        Set<Tekton> latogatott = new HashSet<>();
        Queue<Tekton> sor = new LinkedList<>();
        
        sor.add(this);
        latogatott.add(this);

        while (!sor.isEmpty()) {
            Tekton jelenlegi = sor.poll();
            if (jelenlegi == a) return true;

            for (Gombafonal gf : jelenlegi.getFonalak()) {
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
     * @brief Elszakítja a paraméterben kapott gombafonalat, azáltal hogy kiveszi a gombafonal listából
     * @param gf Gombafonal: elszakítani kívánt gombafonal
     */
    @Override
    public void fonalSzakad(Gombafonal gf){
        System.out.println("Meghívódott a Tekton fonalSzakad metódusa.");

        if(Skeleton.getInstance().getTestCase().equals("Fonal vágás")){
            gombafonalak.remove(gf);
            for (Tekton tekton : szomszedok) {
                if(tekton == gf.getVegpont2() || tekton == gf.getVegpont1()){
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