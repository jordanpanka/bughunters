package bughunters.Gombafaj;

import bughunters.Tekton.*;
import bughunters.Rovar.*;

import java.util.ArrayList;
import java.util.List;
/**
 * @brief A Gombafaj osztály a gombafajokat reprezentálja, és kezeli azok növekedését,
 * fonalhálózatát, valamint a spóraszórás és a haldoklás folyamatait.
 */
public class Gombafaj implements FonalKezeles{
    private String nev;
    private int termelesIdeje;
    private int eddigNovesztettTestekSzama;
    private int gombatestFejlettsegIdo;
    private int gombatestEletSzama;
    private int gombafonalEletSzama;
    private ArrayList<Gombatest> gombaTestek;
    private ArrayList<Gombafonal> gombafonalhalozat;
    /**
     * @brief Alapértelmezett konstruktor a Gombafaj létrehozására.
     */
    public Gombafaj(){System.out.println("Létrejött egy új Gombafaj.");}

    /**
     * @brief Paraméterezett konstruktor, amely létrehozza a gombafajt és inicializálja a listákat.
     *
     * @param n     A gomba neve.
     * @param tI    A termelési idő.
     * @param ents  Az eddig növesztett testek száma.
     * @param gtfi  A gombatest fejlettségi ideje.
     * @param gtesz A gombatest életszáma.
     */
    public Gombafaj(String n, int tI, int ents, int gtfi,int gtesz, int gfesz){
        nev=n;
        termelesIdeje=tI;
        eddigNovesztettTestekSzama=ents;
        gombatestFejlettsegIdo=gtfi;
        gombatestEletSzama=gtesz;
        gombafonalEletSzama = gfesz;
        gombaTestek=new ArrayList<Gombatest>();
        gombafonalhalozat=new ArrayList<Gombafonal>();
        //System.out.println("Létrejött egy új Gombafaj:" );
    }

    public ArrayList<Gombatest> getGombaTestekList() {
        return gombaTestek;
    }

    public String getNev() {
        return nev;
    }
    
    public void setNev(String nev) {
        this.nev = nev;
    }
    
    public int getTermelesIdeje() {
        return termelesIdeje;
    }
    
    public void setTermelesIdeje(int termelesIdeje) {
        this.termelesIdeje = termelesIdeje;
    }
    
    public int getEddigNovesztettTestekSzama() {
        return eddigNovesztettTestekSzama;
    }
    
    public void setEddigNovesztettTestekSzama(int eddigNovesztettTestekSzama) {
        this.eddigNovesztettTestekSzama = eddigNovesztettTestekSzama;
    }
    
    public int getGombatestFejlettsegIdo() {
        return gombatestFejlettsegIdo;
    }
    
    public void setGombatestFejlettsegIdo(int gombatestFejlettsegIdo) {
        this.gombatestFejlettsegIdo = gombatestFejlettsegIdo;
    }
    
    public int getGombatestEletSzama() {
        return gombatestEletSzama;
    }
    
    public void setGombatestEletSzama(int gombatestEletSzama) {
        this.gombatestEletSzama = gombatestEletSzama;
    }
    public int getGombafonalEletSzama() {
        return gombafonalEletSzama;
    }
    
    public void setGombafonalEletSzama(int gombafonalEletSzama) {
        this.gombafonalEletSzama = gombafonalEletSzama;
    }
    
    /**
     * @brief Visszaadja a gomba testeit tartalmazó listát.
     *
     * @return A gombatestek listája.
     */
    public ArrayList<Gombatest> getGombaTestek() {
        return gombaTestek;
    }
    /**
     * @brief Beállítja a gomba testeit tartalmazó listát.
     *
     * @param gombaTestek Az új lista.
     */
    public void setGombaTestek(ArrayList<Gombatest> gombaTestek) {
        this.gombaTestek = gombaTestek;
    }
    /**
     * @brief Visszaadja a gombafonalhálózatot tartalmazó listát.
     *
     * @return A gombafonalhálózat listája.
     */
    public ArrayList<Gombafonal> getGombafonalhalozat() {
        return gombafonalhalozat;
    }
    /**
     * @brief Beállítja a gombafonalhálózatot tartalmazó listát.
     *
     * @param gombafonalhalozat Az új lista.
     */
    public void setGombafonalhalozat(ArrayList<Gombafonal> gombafonalhalozat) {
        this.gombafonalhalozat = gombafonalhalozat;
    }
    /**
     * @brief A haldoklási folyamatot kezeli. Ellenőrzi, hogy a fonalak elérnek-e egy gombatesthez,
     * és ha nem, akkor változtatja az állapotukat.
     */
    //meg kell nézni, hogy a gombafajhoz meddig marad életben a haldoklás után
    //hogy kapcsolódik-e hozzá gombatest
    public void haldoklas(){
        //System.out.println("Meghívódik a Gombafaj haldoklas metódusa.(privát metódus)");
        ArrayList<Tekton> gombatestesTektonok=new ArrayList<>();

        if (gombaTestek != null) { 
            for (Gombatest gt : gombaTestek) { 
                gombatestesTektonok.add(gt.getTekton()); 
            } 
        }

        for(Gombafonal gf:gombafonalhalozat){
            boolean van=false;
            for(Tekton t:gombatestesTektonok){
                //System.out.print("A privát függvényhívás részei: ");
                if(t.vanUt(gf.getVegpont1())){
                    van=true;
                    break;
                }
            }
            /*
            if(!van && gf.getAllapot().equals("Ep")){
                gf.setAllapot(fonalAllapot.Haldoklo);
            }
            */
            if(!van && gf.getAllapot().equals(fonalAllapot.Ep)){
                gf.setAllapot(fonalAllapot.Haldoklo);
            }
        }
    }
     /**
     * @brief Új gombafonalat növeszt a megadott helyek között.
     *
     * @param hova    A cél Tekton.
     * @param honnan  A kiindulási Tekton.
     * @throws Exception Ha nem lehetséges a fonal növesztése.
     */
    public void  fonalNov(Tekton honnan, List<Gombafaj> t1fajok, Tekton hova, List<Gombafaj> t2fajok)throws Exception{
        //System.out.println("Meghívódik a Gombafaj fonalNov metódusa.");
        try{
            //System.out.println("Fonal növesztés inditasa------------------------");
            Gombafonal uj= hova.gombafonalAdd(this,t2fajok, honnan);
            //Ez azért kell, hogy ha Monotektonra nő a fonal, akkor csak a Tekton gombafonalAdd metódusát hívja meg, 
            //így nem ellenőrzi le a monotektonos részt. Ezzel leelenőrzi. Ha mindkettő Exception nélküli akkor mind1 melyik fonal lesz hasznalva.
            Gombafonal uj2 = honnan.gombafonalAdd(this, t1fajok, hova);
            
            //System.out.println("uj fonal: "+uj + "honnan: "+uj.getVegpont1() + "hova: "+uj.getVegpont2());
            //System.out.println("Szomszedosak? "+honnan.getSzomszedok().contains(hova));
            addFonal(uj);
            honnan.addFonal(uj);
            hova.addFonal(uj);
            //System.out.println("Fonal növesztés sikeres ------------------------");
        }catch(Exception e)
        {
            throw e;
            //throw new Exception("Nem nőhet fonal.------------------------");
        } 
    }
     /**
     * @brief Hozzáad egy új gombafonalat a hálózathoz.
     *
     * @param gf A hozzáadandó gombafonal.
     */
    public void addFonal(Gombafonal gf){
        //System.out.println("Meghívódik a Gombafaj addFonal metódusa.");
        if (gombafonalhalozat == null) {
            gombafonalhalozat = new ArrayList<>();
        }
        gombafonalhalozat.add(gf);
    }
    /**
     * @brief Egy adott gombafonal megszakadását kezeli.
     *
     * @param gf A megszakadt gombafonal.
     */
    public void  fonalSzakad(Gombafonal gf){
        //System.out.println("Meghivódik a Gombafaj fonalSzakad metódusa.");
        gombafonalhalozat.remove(gf);
        haldoklas();
    }
    /**
     * @brief Új gombatestet növeszt egy adott Tektonon.
     *
     * @param t A Tekton, amelyen a gombatest növekedni fog.
     */
    
    public void testNovesztes(Tekton t, boolean sporaval)throws Exception{
        //System.out.println("Meghívódik a Gombafaj testNovesztes metodusa.");
        try{
            Gombatest uj=t.gombatestNov(this,sporaval);
            addTest(uj);
            eddigNovesztettTestekSzama++;
        }catch(Exception e)
        {
            throw e;
            //throw new Exception("Nem nőhet gombatest.");
        }
    }

     /**
     * @brief Hozzáad egy új gombatestet a listához.
     *
     * @param gt A hozzáadandó gombatest.
     */
    public void addTest(Gombatest gt) {
        //System.out.println("Meghívódik a Gombafaj addTest metodusa.");

        if(gombaTestek == null) {
            gombaTestek = new ArrayList<Gombatest>();
        }
        gombaTestek.add(gt);
    }
    
    /**
     * @brief Spóraszórás folyamatát kezeli egy adott Tekton és Gombatest között.
     *
     * @param t A cél Tekton.
     * @param g A gombatest, amely spórát szór.
     */
    public void sporaSzoras(Tekton t, Gombatest g)throws Exception{
        //System.out.println("Meghívódik a Gombafaj sporaSzoras metodusa.");
        try
        {
            //System.out.println("---------------------------------\nsporaSzoras urit() ELKELZDŐDIK");
            g.urit();
            //System.out.println("sporaSzoras urit() megtortent\n--------------------------------");
            t.sporaSzor(this);
            //System.out.println("sporaSzoras sporaszor() megtortent\n--------------------------------");

            boolean valasz=false;
            if(g.getKor()==gombatestEletSzama)
            {
                valasz=true;
            }
            if(valasz){
                torolGombatest(g);
            }
        }catch(Exception e){
            throw e;
            //throw new Exception("Nem tud a gombatest spórát szórni.");
        }
    
    }

    void torolGombatest(Gombatest gt){
        //System.out.println("Meghívódik a Gombafaj torolGombatest metódusa.");
        gombaTestek.remove(gt);
    }
    /**
     * @brief Kezeli az utolsó esély állapotot a gombafonalhálózatban.
     */
    //HashMap melyik fonalról van szó
    //fonal haldokls állapotának növelése
    public void lastChance(){
        //System.out.println("Meghívódik a Gombafaj lastChance metodusa.");
        ArrayList<Tekton> gombatestesTektonok=new ArrayList<>();
        for(Gombatest gt: gombaTestek){
           gombatestesTektonok.add(gt.getTekton());
        }
        for(int i=0; i<gombafonalhalozat.size(); i++){
            boolean van=false;
            for(Tekton t:gombatestesTektonok){
                if(t.vanUt(gombafonalhalozat.get(i).getVegpont1())){
                    van=true;
                    break;
                }
            }

            if (van) {
                
                gombafonalhalozat.get(i).setAllapot(fonalAllapot.Ep);
                   
            }else if(!van){
                /*
                if(gombafonalhalozat.get(i).getAllapot().equals("Haldoklo") && gombafonalhalozat.get(i).getMiota()==gombafonalEletSzama )
                {
                    gombafonalhalozat.get(i).setAllapot(fonalAllapot.UtolsoEsely);

                }else if(gombafonalhalozat.get(i).getAllapot().equals("UtolsoEsely")){
                    //elér e testhez az adott fonal
                    gombafonalhalozat.get(i).vegpontTorles();     
                }  

                */  
                if(gombafonalhalozat.get(i).getAllapot().equals(fonalAllapot.Haldoklo) && gombafonalhalozat.get(i).getMiota()==(gombafonalEletSzama-1) )
                {
                    gombafonalhalozat.get(i).setAllapot(fonalAllapot.UtolsoEsely);

                }else if(gombafonalhalozat.get(i).getAllapot().equals(fonalAllapot.UtolsoEsely)){
                    //elér e testhez az adott fonal
                    gombafonalhalozat.get(i).vegpontTorles();     
                }      
            }
        }
    }
    
    //VÁLTOZTATAS: r.torolRovar(), kitörli a rovart a rovarasz listából
    public void rovarEves(Rovar r, Boolean testnovesztessel)throws Exception{
        try{
            if(r.getAllapot() == rovarAllapot.Benitott){
                if (testnovesztessel) {
                    testNovesztes(r.getTartozkodas(), false);
                }
                r.torolRovar();
            }
            else
            {
                throw new Exception("Nem bénított állapotban van a rovar.");
            }
             
        }catch(Exception e){
            throw new Exception("Nem nőhet gombatest.");
        }
       
    }

    //A játékos köre végén a cselekedetek elvégézése
    public void korVegiCselekedetek(){
        haldoklas();
        lastChance();
        for(Gombafonal gf:gombafonalhalozat){
            gf.tartozkodasNov();
        }
        for(Gombatest gt:gombaTestek){
            gt.sporaGyujtes();
        }
    }

}