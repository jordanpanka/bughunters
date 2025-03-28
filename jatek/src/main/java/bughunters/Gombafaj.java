package bughunters;

import java.util.ArrayList;
/**
 * @brief A Gombafaj osztály a gombafajokat reprezentálja, és kezeli azok növekedését,
 * fonalhálózatát, valamint a spóraszórás és a haldoklás folyamatait.
 */
public class Gombafaj implements FonalKezeles{
   /*private String nev;
    private int termelesIdeje;
    private int eddigNovesztettTestekSzama;
    private int gombatestFejlettsegIdo;
    private int gombatestEletSzama;*/
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
    public Gombafaj(String n, int tI, int ents, int gtfi,int gtesz){
        /*nev=n;
        termelesIdeje=tI;
        eddigNovesztettTestekSzama=ents;
        gombatestFejlettsegIdo=gtfi;
        gombatestEletSzama=gtesz;*/
        gombaTestek=new ArrayList<Gombatest>();
        gombafonalhalozat=new ArrayList<Gombafonal>();
        System.out.println("Létrejött egy új Gombafaj:" );
    }
    /*public String getNev() {
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
    }*/
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
    private void haldoklas(){
        System.out.println("Meghívódik a Gombafaj haldoklas metódusa.(privát metódus)");
        ArrayList<Tekton> gombatestesTektonok=new ArrayList<>();

        if (gombaTestek != null) { 
            for (Gombatest gt : gombaTestek) { 
                gombatestesTektonok.add(gt.getTekton()); 
            } 
        }

        for(Gombafonal gf:gombafonalhalozat){
            boolean van=false;
            for(Tekton t:gombatestesTektonok){
                System.out.print("A privát függvényhívás részei: ");
                if(t.vanUt(gf.getVegpont1())){
                    van=true;
                    break;
                }
            }
            if(!van && gf.getAllapot().equals("Ep")){
                gf.allapotvalt();
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
    public void  fonalNov(Tekton honnan, Tekton hova)throws Exception{
        System.out.println("Meghívódik a Gombafaj fonalNov metódusa.");
        try{
            Gombafonal uj= hova.gombafonalAdd(this,honnan);
            addFonal(uj);
            honnan.addFonal(uj);
        }catch(Exception e)
        {
            throw new Exception("Nem nőhet fonal.");
        } 
    }
     /**
     * @brief Hozzáad egy új gombafonalat a hálózathoz.
     *
     * @param gf A hozzáadandó gombafonal.
     */
    public void addFonal(Gombafonal gf){
        System.out.println("Meghívódik a Gombafaj addFonal metódusa.");
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
        System.out.println("Meghivódik a Gombafaj fonalSzakad metódusa.");
        gombafonalhalozat.remove(gf);
        haldoklas();
    }
    /**
     * @brief Új gombatestet növeszt egy adott Tektonon.
     *
     * @param t A Tekton, amelyen a gombatest növekedni fog.
     */
    public void testNovesztes(Tekton t)throws Exception{
        System.out.println("Meghívódik a Gombafaj testNovesztes metodusa.");
        try{
            Gombatest uj=t.gombatestNov(this);
            addTest(uj);

        }catch(Exception e)
        {
            throw new Exception("Nem nőhet gombatest.");
        }
    }
     /**
     * @brief Hozzáad egy új gombatestet a listához.
     *
     * @param gt A hozzáadandó gombatest.
     */
    void addTest(Gombatest gt){
        System.out.println("Meghívódik a Gombafaj addTest metodusa.");
        if (gombaTestek == null) {
            gombaTestek = new ArrayList<>();
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
        System.out.println("Meghívódik a Gombafaj sporaSzoras metodusa.");
        try
        {
            g.urit();
            t.sporaSzor(this);
            boolean valasz=Skeleton.getInstance().Kerdes("Elhal a gombatest?");
            if(valasz){
                torolGombatest(g);
            }
        }catch(Exception e){
            throw new Exception("Nem tud a gombatest spórát szórni.");
        }
    
    }
    void torolGombatest(Gombatest gt){
        System.out.println("Meghívódik a Gombafaj torolGombatest metódusa.");
        gombaTestek.remove(gt);
    }
    /**
     * @brief Kezeli az utolsó esély állapotot a gombafonalhálózatban.
     */
    //HashMap melyik fonalról van szó
    public void lastChance(){
        System.out.println("Meghívódik a Gombafaj lastChance metodusa.");
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

            if (van && i!=0) {
                    if(Skeleton.getInstance().Kerdes("Haldoklo állapotban van a fonal?") )
                {
                    gombafonalhalozat.get(i).allapotvalt();
                    gombafonalhalozat.get(i).allapotvalt();
                        
                }
                else if(Skeleton.getInstance().Kerdes("UtolsoEsely állapotban van a fonal?")){
                    //elér e testhez az adott fonal
                    gombafonalhalozat.get(i).allapotvalt();
                }      
            }else if(!van){
                if(Skeleton.getInstance().Kerdes("Haldoklo állapotban van a fonal?") )
                {
                    gombafonalhalozat.get(i).allapotvalt();

                }else if(Skeleton.getInstance().Kerdes("UtolsoEsely állapotban van a fonal?")){
                    //elér e testhez az adott fonal
                    gombafonalhalozat.get(i).vegpontTorles();     
                }      
            }

            
        }
    }
    /**
     * @brief A törés utáni haldoklási folyamatot kezeli.
     */
    public void toresHaldoklas(){
        System.out.println("Meghívódik a Gombafaj toresHaldoklas metodusa.");
        haldoklas();
    }

}