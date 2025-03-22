package bughunters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/***
 * @brief A feladata a Tekton megvalósítása, valamint a további tektontípusok közös 
 * tulajdonságainak/függvényeinek meghatározása
 */
public class Tekton implements FonalKezeles {
    private List<Tekton> szomszedok; //melyik tektonok a szomszédjai
    private List<Gombafonal> gombafonalak; //megtalálható gombafonalak listája
    private List<Spora> sporak; //megtalálható spórák listája

    public Tekton() {
        szomszedok = new ArrayList<>();
        gombafonalak = new ArrayList<>();
        sporak = new ArrayList<>();
    }

    /***
     * 
     * @param szomszed List<Tekton>
     * @param gombafonal List<Gombafonal>
     * @param spora List<Spora>
     */
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
     * @param ujszomszed Tekton: új szomszéd
     */
    public void addSzomszed(Tekton ujszomszed){
        System.out.println("Meghívódott a Tekton addSzomszed metódusa.");
        szomszedok.add(ujszomszed);
    }

    /***
     * @brief Gombafonalat ad hozzá a Tektonhoz
     * @param gf Gombafaj: Aktuális gombafaj
     * @param honnan Tekton: Vizsgáljuk hogy erről a tektonról vezet e fonal az aktuálisra
     * @return növesztett gombafonalat adja vissza
     * @throws Exception ha nem tud gombafonalat növeszteni vagy rossz feladatnál lett hívva
     */
    public Gombafonal gombafonalAdd(Gombafaj g, Tekton honnan) throws Exception {
        System.out.println("Meghívódott a Tekton gombafonalAdd metódusa.");
        //ellenőrizni hogy létezik e már ilyen gombafonal
        List<Tekton> gombatestekHelye=new ArrayList<>();
        for(Gombatest  gt: g.getGombaTestek())
        {
            gombatestekHelye.add(gt.getTekton());
        }
        for(Gombafonal gfonal : honnan.getFonalak())
        {
            gombatestekHelye.add(gfonal.getVegpont1());
            gombatestekHelye.add(gfonal.getVegpont2());
        }
        if(gombatestekHelye.contains(honnan)){   
            Gombafonal gf2 = new Gombafonal(g,this,honnan);
            addFonal(gf2);
            return gf2;
        }
        throw new Exception("Nem lehet fonalat növeszteni.");
    }

    /***
     * @brief Gombatestet növeszt a tektonon
     * @param gf Gombafaj: Az a gombafaj, ami gombatestet akar növeszteni
     * @return Ha tud növeszteni gombatestet, akkor azt adja vissza
     * @throws Exception ha nem tud gombatestet növeszteni vagy nem megfelelő feladatnál lett hívva
     */
    public Gombatest gombatestNov(Gombafaj gf) throws Exception{
        System.out.println("Meghívódott a Tekton gombatestNov metódusa.");

           
                try{
                    sporak.get(0).fogyaszt(3);

                    boolean valasz=Skeleton.getInstance().Kerdes("Elfogyott a spóra a növesztés által?");
                    if(valasz){
                        torlesSpora(sporak.get(0));
                    }
                    Gombatest gt1 = new Gombatest();
                    return gt1;
                }
                catch(Exception e){
                    throw e;
                }
    }
    
    /***
     * @brief Törli a spórát a sporak listából
     * @param spora Spora: törlendő spóra
     */
    public void torlesSpora(Spora spora){
        System.out.println("Meghívódott a Tekton torlesSpora metódusa.");
        sporak.remove(spora);
    }

    /***
     * @brief A rovar evését segítő függvény
     * @param sp Spora: amit a rovar meg akar enni
     * @param r Rovar: akutális rovar ami eszik
     */
    public void eszik(Spora sp, Rovar r) throws Exception {
        System.out.println("Meghívódott a Tekton eszik metódusa.");

            boolean valasz = Skeleton.getInstance().Kerdes("van-e meg spora, amit meg tud enni?");
            boolean valasz1 = Skeleton.getInstance().Kerdes("es nincs Benitott allapotban a rovar?");
            if(valasz && valasz1){
                boolean valasz2 = Skeleton.getInstance().Kerdes("maradni fog-e meg spora?");
                for (Spora spora : sporak) {
                    if(spora == sp) {
                        sp.fogyaszt(3, r);
                    }
                }

                if(!valasz2){
                    for (Spora spora : sporak) {
                        if(spora == sp) {
                            sporak.remove(sp);
                            break;
                        }
                    }
                }
            } else {
                throw new Exception("Nincs spora.");
            }
    }

    /***
     * @brief A spóraszórást valósítja meg azáltal, hogy a szomszedok listájában szereplő tektonokra spórát rak
     * @param gf Gombafaj: Ehhez a fajhoz tartozik a spóra
     */
    public void sporaSzor(Gombafaj gf){
        System.out.println("Meghívódott a Tekton sporaSzor metódusa.");
        boolean valasz=Skeleton.getInstance().Kerdes("Fejlett a gombatest?");
        if(valasz)
        {
            for (Tekton tekton : szomszedok) {
                Benito b1=new Benito();
                b1.setGombafaj(gf);
                tekton.addSpora(b1);
                for (Tekton tektonszomszed : tekton.getSzomszedok()) {
                    Benito b2=new Benito();
                    b2.setGombafaj(gf);
                    tektonszomszed.addSpora(b2);
                }
            }
        }
        else{
            for (Tekton tekton : szomszedok) {
                Benito b1=new Benito();
                b1.setGombafaj(gf);
                tekton.addSpora(b1);
            }
        }
   
    }

    /***
     * @brief A Tekton sporak listájához hozzáadja a paraméterben kapott spórát
     * @param sp Spora: amit hozzáadunk a listához
     */
    public void addSpora(Spora sp){
        System.out.println("Meghívódott a Tekton addSpora metódusa.");
        sporak.add(sp);
    }

    /***
     * @brief A törés során érintett tektonok új szomszédait állítja be
     * @param t Tekton: Törés után keletkezett új tekton
     */
    public void szomszedAllitas(Tekton t){
        System.out.println("Meghívódott a Tekton szomszedAllitas metódusa.");
        
        szomszedok.add(t);

        t.addSzomszed(this);
        t.addSzomszed(szomszedok.get(2));

        szomszedok.remove(2);
    }

    /***
     * @brief Feladata beállítani a törés során érintett tektonok gombafonalait
     * @return Azt/Azokat a gombafonalat/akat adja vissza, amik majd az új tektonhoz lesznek kötve
     */
    public List<Gombafonal> gombafonalIgazitas(){
        System.out.println("Meghívódott a Tekton gombafonalIgazitas metódusa.");

        List<Gombafonal> ujFonal = new ArrayList<Gombafonal>();
        ujFonal.add(gombafonalak.get(1));
        return ujFonal;
    }

    /***
     * @brief Megadja hogy két tekton között van e út (össze vannak kötve)
     * @param a Tekton: A cél tekton, ahova vizsgáljuk hogy vezet e út
     * @return true: van út, false: nincs út
     */
    public boolean vanUt(Tekton a){
        System.out.println("Meghívódott a Tekton vanUt metódusa.");

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
        System.out.println("Meghívódott a Tekton addFonal metódusa.");
        gombafonalak.add(gf);
    }

    /***
     * @brief Elszakítja a paraméterben kapott gombafonalat, azáltal hogy kiveszi a gombafonal listából
     * @param gf Gombafonal: elszakítani kívánt gombafonal
     */
    @Override
    public void fonalSzakad(Gombafonal gf){
        System.out.println("Meghívódott a Tekton fonalSzakad metódusa.");

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