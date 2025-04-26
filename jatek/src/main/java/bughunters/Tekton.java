package bughunters;

import java.awt.font.GlyphVector;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

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
        //System.out.println("Létrejött egy új Tekton");
    }

    /***
     * @brief A Tekton osztály paraméteres konstruktora
     * @param szomszed List<Tekton>
     * @param gombafonal List<Gombafonal>
     * @param spora List<Spora>
     */
    public Tekton(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        szomszedok = szomszed;
        gombafonalak = gombafonal;
        sporak = spora;
       // System.out.println("Létrejött egy új Tekton");
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
        //System.out.println("Meghívódik a Tekton addSzomszed metódusa.");
        szomszedok.add(ujszomszed);
    }

    /***
     * @brief Gombafonalat ad hozzá a Tektonhoz
     * @param gf Gombafaj: Aktuális gombafaj
     * @param honnan Tekton: Vizsgáljuk hogy erről a tektonról vezet e fonal az aktuálisra
     * @return növesztett gombafonalat adja vissza
     * @throws Exception ha nem tud gombafonalat növeszteni vagy rossz feladatnál lett hívva
     */

    //--------Azt is nézni kell, hogy csak gombatest van a szomszédon, nem gombafonal-----------
    public Gombafonal gombafonalAdd(Gombafaj g, Tekton honnan) throws Exception {
        //System.out.println("Meghívódik a Tekton gombafonalAdd metódusa.");
        //ellenőrizni hogy létezik e már ilyen gombafonal
        if(getSzomszedok().contains(honnan)){
                List<Tekton> gombatestekHelye=new ArrayList<Tekton>();
            if (g.getGombaTestek() != null) {
                for (Gombatest gt : g.getGombaTestek()) {
                    gombatestekHelye.add(gt.getTekton());
                }
            }
            
            for(Gombafonal gfonal : honnan.getFonalak())
            {
                gombatestekHelye.add(gfonal.getVegpont1());
                gombatestekHelye.add(gfonal.getVegpont2());
            }
            //debuggolás
            if(gombatestekHelye.contains(honnan)){ 
                //System.out.println("Van odavezető fonal.");  
                Gombafonal gf2 = new Gombafonal(g,this,honnan);
                addFonal(gf2);
                return gf2;
            }
            throw new Exception("Nem lehet fonalat növeszteni.");
        }
        else{
            throw new Exception("Nem lehet fonalat növeszteni.");
        }
       
    }

    /***
     * @brief Gombatestet növeszt a tektonon
     * @param gf Gombafaj: Az a gombafaj, ami gombatestet akar növeszteni
     * @return Ha tud növeszteni gombatestet, akkor azt adja vissza
     * @throws Exception ha nem tud gombatestet növeszteni vagy nem megfelelő feladatnál lett hívva
     */
    public Gombatest gombatestNov(Gombafaj gf,boolean sporaval) throws Exception{
        //System.out.println("Meghívódik a Tekton gombatestNov metódusa.");
        try{
            for (Gombatest gombatest : gf.getGombaTestek()) {
                if(gombatest.getTekton() == this){
                    throw new Exception("Már van gombatest ezen a tektonon.");
                }
            }
            if(sporaval){
                boolean elfogyott=false;

                if(sporak!=null){
                    for(Spora sp:sporak){
                        if(sp.getGombafaj().equals(gf)) {
                            sp.fogyaszt(3);
                            if(sp.getMennyiseg()==0){
                                elfogyott=true;
                            }                                                    
                            if(elfogyott){
                                torlesSpora(sporak.get(0));
                            }
                        }
                    }
                }
                //sporak.get(0).fogyaszt(3);
                //boolean valasz=Skeleton.getInstance().Kerdes("Elfogyott a spóra a növesztés által?");

                Gombatest gt1 = new Gombatest(gf,this);
                return gt1;
            }
            else {
                /*
                if(gf.getGombaTestek()!=null){
                    for (Gombatest gtest : gf.getGombaTestek()) {
                        if (gtest.getTekton().equals(this)) {
                            throw new Exception("Már van gombatest ezen a Tektonon.");
                        }
                    }
                }
                */

                Gombatest gt1 = new Gombatest(gf,this);
                return gt1;
            }
                   
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
        //System.out.println("Meghívódik a Tekton torlesSpora metódusa.");
        sporak.remove(spora);
    }

    /***
     * @brief A rovar evését segítő függvény
     * @param sp Spora: amit a rovar meg akar enni
     * @param r Rovar: akutális rovar ami eszik
     */
    public void eszik(Spora sp, Rovar r) throws Exception {
        //System.out.println("Meghívódik a Tekton eszik metódusa.");
            
        if(r.getAllapot() != rovarAllapot.Benitott){
            if(sp.getMennyiseg() >= 1){
                boolean utolsoSpora = sp.getMennyiseg() == 1 ? true : false;
                for (Spora spora : sporak) {
                    if(spora == sp) {
                        sp.fogyaszt(1, r);
                    }
                }
                if(utolsoSpora){
                    for (Spora spora : sporak) {
                        if(spora == sp) {
                            sporak.remove(sp);
                            break;
                        }
                    }
                }
            } 
            else {
                throw new Exception("Nincs elég spóra, amit meg tudna enni.");
            }
        }
        else {
            throw new Exception("A rovar benitott allapotban van.");
        }
    }

    /***
     * @brief A spóraszórást valósítja meg azáltal, hogy a szomszedok listájában szereplő tektonokra spórát rak
     * @param gf Gombafaj: Ehhez a fajhoz tartozik a spóra
     */
    public void sporaSzor(Gombafaj gf){
        //System.out.println("Meghívódik a Tekton sporaSzor metódusa.");
        //boolean valasz=Skeleton.getInstance().Kerdes("Fejlett a gombatest?");

        List<Gombatest> szurtLista = gf.getGombaTestek().stream().filter(gt -> gt.getTekton().equals(this)).collect(Collectors.toList());

        //fejlett
        if(gf.getGombatestFejlettsegIdo() <= szurtLista.get(0).getKor())
        {
            for (Tekton tekton : szomszedok) {

                if(gf.getNev().equalsIgnoreCase("Légyölő galóca")){
                    Benito b2 = new Benito();
                    b2.setGombafaj(gf);
                    b2.szorasTortent();
                    tekton.addSpora(b2);
                }
                else if(gf.getNev().equalsIgnoreCase("Vargánya gomba")){
                    //spora = (Lassito)spora;
                    Lassito l = new Lassito();
                    l.setGombafaj(gf);
                    l.szorasTortent();
                    tekton.addSpora(l);
                }
                else if(gf.getNev().equalsIgnoreCase("Foltos püffeteg")){
                    Osztodo o = new Osztodo();
                    //spora = (Osztodo)spora;
                    o.setGombafaj(gf);
                    o.szorasTortent();
                    tekton.addSpora(o);
                }
                else if(gf.getNev().equalsIgnoreCase("Szegfűgomba")){
                    VagasKeptelenito v = new VagasKeptelenito();
                    //spora = (VagasKeptelenito)spora;
                    v.setGombafaj(gf);
                    v.szorasTortent();
                    tekton.addSpora(v);
                }
                else if(gf.getNev().equalsIgnoreCase("Csiperke gomba")){
                    Gyorsito gy = new Gyorsito();
                    //spora = (Gyorsito)spora;
                    gy.setGombafaj(gf);
                    gy.szorasTortent();
                    tekton.addSpora(gy);
                }
                
                for (Tekton tektonszomszed : tekton.getSzomszedok()) {
                    if(tektonszomszed != this) {
                        if(gf.getNev().equalsIgnoreCase("Légyölő galóca")){
                            Benito b2 = new Benito();
                            b2.setGombafaj(gf);
                            b2.szorasTortent();
                            tektonszomszed.addSpora(b2);
                        }
                        else if(gf.getNev().equalsIgnoreCase("Vargánya gomba")){
                            //spora = (Lassito)spora;
                            Lassito l = new Lassito();
                            l.setGombafaj(gf);
                            l.szorasTortent();
                            tektonszomszed.addSpora(l);
                        }
                        else if(gf.getNev().equalsIgnoreCase("Foltos püffeteg")){
                            Osztodo o = new Osztodo();
                            //spora = (Osztodo)spora;
                            o.setGombafaj(gf);
                            o.szorasTortent();
                            tektonszomszed.addSpora(o);
                        }
                        else if(gf.getNev().equalsIgnoreCase("Szegfűgomba")){
                            VagasKeptelenito v = new VagasKeptelenito();
                            //spora = (VagasKeptelenito)spora;
                            v.setGombafaj(gf);
                            v.szorasTortent();
                            tektonszomszed.addSpora(v);
                        }
                        else if(gf.getNev().equalsIgnoreCase("Csiperke gomba")){
                            Gyorsito gy = new Gyorsito();
                            //spora = (Gyorsito)spora;
                            gy.setGombafaj(gf);
                            gy.szorasTortent();
                            tektonszomszed.addSpora(gy);
                        }
                    }
                }
            }
        }

        //nem fejlett
        else {
            for (Tekton tekton : szomszedok) {
                if(gf.getNev().equalsIgnoreCase("Légyölő galóca")){
                    Benito b2 = new Benito();
                    b2.setGombafaj(gf);
                    b2.szorasTortent();
                    tekton.addSpora(b2);
                }
                else if(gf.getNev().equalsIgnoreCase("Vargánya gomba")){
                    //spora = (Lassito)spora;
                    Lassito l = new Lassito();
                    l.setGombafaj(gf);
                    l.szorasTortent();
                    tekton.addSpora(l);
                }
                else if(gf.getNev().equalsIgnoreCase("Foltos püffeteg")){
                    Osztodo o = new Osztodo();
                    //spora = (Osztodo)spora;
                    o.setGombafaj(gf);
                    o.szorasTortent();
                    tekton.addSpora(o);
                }
                else if(gf.getNev().equalsIgnoreCase("Szegfűgomba")){
                    VagasKeptelenito v = new VagasKeptelenito();
                    //spora = (VagasKeptelenito)spora;
                    v.setGombafaj(gf);
                    v.szorasTortent();
                    tekton.addSpora(v);
                }
                else if(gf.getNev().equalsIgnoreCase("Csiperke gomba")){
                    Gyorsito gy = new Gyorsito();
                    //spora = (Gyorsito)spora;
                    gy.setGombafaj(gf);
                    gy.szorasTortent();
                    tekton.addSpora(gy);
                }
            }
        }
    }

    /***
     * @brief A Tekton sporak listájához hozzáadja a paraméterben kapott spórát
     * @param sp Spora: amit hozzáadunk a listához
     */
    public void addSpora(Spora sp){
        //System.out.println("Meghívódik a Tekton addSpora metódusa.");
        sporak.add(sp);
    }

    /***
     * @brief A törés során érintett tektonok új szomszédait állítja be
     * @param t Tekton: Törés után keletkezett új tekton
     */
    public void szomszedAllitas(Tekton t){
        //System.out.println("Meghívódik a Tekton szomszedAllitas metódusa.");
        
        // t1 --> t1 -- t2 szomszédok 
        szomszedok.add(t);
        t.addSzomszed(this);

        int db = szomszedok.size();
        int fele = db / 2;

        //aktTektonból a szomszédok kitörlése, a másik tektonhoz pedig hozzáadás
        for(int i = fele; i < db; i++){
            t.addSzomszed(szomszedok.get(i));
            szomszedok.remove(szomszedok.get(i));
        }
    }

    /***
     * @brief Feladata beállítani a törés során érintett tektonok gombafonalait
     * @return Azt/Azokat a gombafonalat/akat adja vissza, amik majd az új tektonhoz lesznek kötve
     */
    public List<Gombafonal> gombafonalIgazitas(){
        //System.out.println("Meghívódik a Tekton gombafonalIgazitas metódusa.");

        List<Gombafonal> ujFonal = new ArrayList<Gombafonal>();

        for (Gombafonal gombafonal : gombafonalak) {
            if(gombafonal.getVegpont1().equals(this)){
                if(!szomszedok.contains(gombafonal.getVegpont2())) {
                    ujFonal.add(gombafonal);
                }
            }

            if(gombafonal.getVegpont2().equals(this)) {
                if(!szomszedok.contains(gombafonal.getVegpont1())) {
                    ujFonal.add(gombafonal);
                }
            }
        }

        for (Gombafonal gombafonal : ujFonal) {
             gombafonalak.remove(gombafonal);
        }
       
        return ujFonal;
    }

    /***
     * @brief Megadja hogy két tekton között van e út (össze vannak kötve)
     * @param a Tekton: A cél tekton, ahova vizsgáljuk hogy vezet e út
     * @return true: van út, false: nincs út
     */
    public boolean vanUt(Tekton a){
        //System.out.println("Meghívódik a Tekton vanUt metódusa.");

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
        //System.out.println("Meghívódik a "+this.getClass().getSimpleName()+" addFonal metódusa.");
        gombafonalak.add(gf);
    }

    /***
     * @brief Elszakítja a paraméterben kapott gombafonalat, azáltal hogy kiveszi a gombafonal listából
     * @param gf Gombafonal: elszakítani kívánt gombafonal
     */
    @Override
    public void fonalSzakad(Gombafonal gf){
        //System.out.println("Meghívódik a "+this.getClass().getSimpleName()+" fonalSzakad metódusa.");

        gombafonalak.remove(gf);
        
        for (Tekton tekton : szomszedok) {
            if(tekton == gf.getVegpont2() || tekton == gf.getVegpont1()){

                List<Gombafonal> ideiglenes=new ArrayList<Gombafonal>();
                for (Gombafonal gombafonal : tekton.getFonalak()) {
                    
                    if(gombafonal.equals(gf)){
                        
                        //tekton.getFonalak().remove(gf);
                        ideiglenes.add(gf);
                        
                    }
                    
                }
                
                tekton.getFonalak().removeAll(ideiglenes);
            }
           
        } 
        
    }

    public void gombafonalFelszivas(){}
    public void eletbenTartas(){}
}