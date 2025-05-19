package bughunters.Egyeb;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import bughunters.Gombafaj.Benito;
import bughunters.Gombafaj.Gombafaj;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Gyorsito;
import bughunters.Gombafaj.Lassito;
import bughunters.Gombafaj.Osztodo;
import bughunters.Gombafaj.Spora;
import bughunters.Gombafaj.VagasKeptelenito;
import bughunters.Grafika.GGombafonal;
import bughunters.Grafika.GGombatest;
import bughunters.Grafika.GRovar;
import bughunters.Grafika.GSpora;
import bughunters.Grafika.GTekton;
import bughunters.Grafika.Grafika;
import bughunters.Grafika.JatekInditasaAblak;
import bughunters.Rovar.Rovar;
import bughunters.Rovar.rovarAllapot;
import bughunters.Tekton.Disszolator;
import bughunters.Tekton.Infinator;
import bughunters.Tekton.Monotekton;
import bughunters.Tekton.Puritekton;
import bughunters.Tekton.Tekton;

/**
 * A parancsok állapotát reprezentáló enum
 * Game - Játék állapot, ahol nem érhetők el az Arrange parancsok
 * Test - Teszt állapot, ahol minden parancs elérhető
 */
enum parancsAllapot {
    Game,               // Játék állapotban van a parancskezelő, nem fér hozzá Arrange parancsokhoz.
    Test                // Teszt állapotban van a parancskezelő, minden parancshoz hozzáfér.
}

public class Parancskezelok {
    private List<Jatekos> jatekosok;
    private List<Gombasz> gombaszok;
    private List<Rovarasz> rovaraszok;
    private HashMap<String, Object> objektumok;
    public HashMap<String, Object> getObjektumok() {
        return objektumok;
    }



    public void setObjektumok(HashMap<String, Object> objektumok) {
        this.objektumok = objektumok;
    }

    private HashMap<Object,String> objektumokbolString;
    private Jatekter jatekter;
    private parancsAllapot allapot;
    private Jatekos aktivJatekos;
    private Grafika grafika;


    /**
     * @brief Parancskezelő konstruktor, inicializálja az adattagokat
     */
    public Parancskezelok() {
        this.objektumok = new HashMap<>();
        this.objektumokbolString = new HashMap<>();
        this.jatekosok = new ArrayList<>();
        this.gombaszok = new ArrayList<>();
        this.rovaraszok = new ArrayList<>();
        this.jatekter = new Jatekter();
        this.allapot = parancsAllapot.Test;

        this.grafika = new Grafika();
    }

    public void setGrafika(Grafika grafika) {
        this.grafika = grafika;
    }

    public Grafika getGrafika() {
        return grafika;
    }

    /**
     * @brief Beállítja a parancskezelő állapotát
     * @param allapot Az új állapot (Game/Test)
     */
    public void setParancsAllapot(parancsAllapot allapot) {
        this.allapot = allapot;
    }
    
    /**
     * @brief Visszaadja a parancskezelő aktuális állapotát
     * @return A parancskezelő állapota (Game/Test)
     */
    public parancsAllapot getParancsAllapot() {
        return allapot;
    }

    public Jatekter getJatekter() {
        return jatekter;
    }
    public void setjatekter(Jatekter jatekter) {
        this.jatekter = jatekter;
    }

    public List<Jatekos> getJatekosok() {
        return jatekosok;
    }
    public List<Rovarasz> getRovaraszok() {
        return rovaraszok;
    }
    public List<Gombasz> getGombaszok() {
        return gombaszok;
    }
    public void setJatekosok(List<Jatekos> jatekosok) {
        this.jatekosok = jatekosok;
    }

    public void setAktivJatekos(Jatekos aktivJatekos) {
        this.aktivJatekos = aktivJatekos;
    }
    public Jatekos getAktivJatekos() {
        return aktivJatekos;
    }

    //A játékosok a játék indítása után nem férhetnek hozzá Arrange parancsokhoz, de a tesztek mindenhez hozzáférnek

    public BufferedImage getGombatestKepByFaj(Gombafaj gf){
        BufferedImage img = null;
        try {
            switch(gf.getNev()) {
                case "Csiperke gomba":
                    img = ImageIO.read(new File("../../Resources/kepek/gyorsito_csiperke.PNG"));
                    break;
                case "Foltos püffeteg":
                    img = ImageIO.read(new File("../../Resources/kepek/osztodo_foltos.PNG"));
                    break;
                case "Vargánya gomba":
                    img = ImageIO.read(new File("../../Resources/kepek/lassito_varganya.PNG"));
                    break;
                case "Légyölő galóca":
                    img = ImageIO.read(new File("../../Resources/kepek/benito_legyolo.PNG"));
                    break;
                case "Szegfűgomba":
                    img = ImageIO.read(new File("../../Resources/kepek/vagasGatlo_szegfu.PNG"));
                    break;
                default:
            }
        } catch (Exception e) {
            System.out.println("Nem sikerult betolteni a Resources/kepeket");
            e.printStackTrace();
        }   
        return img;
    }

    public BufferedImage getRovarKepByColor(Color szin){
        Color BROWN = new Color(121, 87, 53);
        Color LIGHTBROWN = new Color(172, 86, 0);
        BufferedImage img = null;
        try {
            if (szin == Color.RED) {
                img= ImageIO.read(new File("../../Resources/kepek/rovar4.PNG"));
            } else if (szin == Color.ORANGE) {
                img = ImageIO.read(new File("../../Resources/kepek/rovar1.PNG")); 
            } else if (szin == Color.MAGENTA) {
                img = ImageIO.read(new File("../../Resources/kepek/rovar5.PNG"));
            } else if (szin.equals(BROWN)) {
                img = ImageIO.read(new File("../../Resources/kepek/rovar3.PNG"));
            }else if (szin.equals(LIGHTBROWN)) {
                img = ImageIO.read(new File("../../Resources/kepek/rovar2.PNG"));
            }
        } catch (Exception e) {
            System.out.println("Nem sikerult betolteni a Resources/kepeket");
            e.printStackTrace();
        }
        return img;
    }


    public Color getColorKepByFaj(Gombafaj gf){
        Color szin = null;
        switch(gf.getNev()) {
            case "Csiperke gomba":
                szin =  Color.YELLOW;
                break;
            case "Foltos püffeteg":
                szin =  Color.PINK;
                break;
            case "Vargánya gomba":
                szin = Color.BLUE;
                break;
            case "Légyölő galóca":
                szin = Color.RED;
                break;
            case "Szegfűgomba":
                szin = new Color(157, 64, 210);
                break;
            default:
        }
        return szin;
    }

    public Color getColorTektonByName(char tektonNev){
        Color szin = null;
        switch(tektonNev) {
            case 't':
                szin = new Color(221, 190, 126);
                break;
            case 'm':
                szin = new Color(118, 152, 180);
                break;
            case 'd':
                szin = new Color(141, 183, 117);
                break;
            case 'p':
                szin = new Color(179, 182, 111);
                break;
            case 'i':
                szin = new Color(109, 184, 154);
                break;
            default:
        }
        return szin;
    }

    /**
     * @brief visszaad egy tektont név alapján.
     * @param nev A keresendő tekton neve
     * @return A keresett tekton objektuma
     */
    public Tekton getTekton(String nev){
        try {
            
            //Tekton tekton = (Tekton)objektumok.get(nev);
            Tekton tekton = parancsTektonCast(nev.charAt(0), nev);
            if(tekton != null){
                return tekton;
            }
        } catch (Exception e) {
            System.out.println("Parancskezelok getTekton() null vagy convert hiba" + e.getMessage());
        }
        return null;
    }

    /**
     * @brief visszaad egy List<string>-et a tekonok neveire.
     * @return A tektonok nevei egy istában
     */
    public List<String> getTektonNevList(){
        List<String> tektonNevek = new ArrayList<>();
        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("tekton\\d+") || kulcs.matches("monotekton\\d+") || kulcs.matches("puritekton\\d+") || kulcs.matches("disszolator\\d+") || kulcs.matches("infinator\\d+")) {
                tektonNevek.add(kulcs);
            }
        }
        szamszeruSort(tektonNevek);
        return tektonNevek;
    }

    public void kezdetiRovarokGombak(HashMap<Gombasz, Tekton> gombaszokTestei, HashMap<Rovarasz, Tekton> rovaraszokRovarjai) throws Exception {
        //Felveszi a játékosok kezdő objektumait.
        for(Gombasz gombasz : gombaszokTestei.keySet()){
            try {
                 //egyéb gombatest a tektonon ellenőrzés
                        ArrayList<Gombatest> gombatestek = new ArrayList<>();
                        for(Gombasz egygombasz : gombaszok) {
                            Gombafaj gombaszFaja = egygombasz.getGombafaj();
                            gombatestek.addAll(gombaszFaja.getGombaTestekList());
                        }
                        for(Gombatest gombatest : gombatestek) {
                            if(gombatest.getTekton().equals(gombaszokTestei.get(gombasz))) {
                                throw new Exception("Mar van gombatest az adott tektonon");
                            }
                        }
                gombasz.JatekEleiTestNovesztes(gombaszokTestei.get(gombasz), false);
            } catch (Exception e) {
                //ha hiba van, töröljük az összes gombász gombatesteit amit felvehettünk már eddig.
                for(Gombasz gombasz1 : gombaszok){
                    gombasz1.getGombafaj().getGombaTestekList().clear();
                }
                throw new Exception("Nem sikerult gombatestet noveszteni "+e.getMessage());
            }
        }
        for(Rovarasz rovarasz : rovaraszokRovarjai.keySet()){
            try {
                Rovar rovar = new Rovar(rovaraszokRovarjai.get(rovarasz),rovarasz);
                rovarasz.addRovar(rovar);
                GRovar grafRovar = new GRovar(getRovarKepByColor(rovarasz.getSzin()));

                String ujRovarNev = ujRovarNev(); //rovar neve
                objektumok.put(ujRovarNev, rovar);
                objektumokbolString.put(rovar, ujRovarNev);

                //GRAFIKA HASHMAP BERAKÁSA
                grafika.getRovarok().put(rovar, grafRovar);
            } catch (Exception e) {
                throw new Exception("Nem sikerult létrehozni a Rovart " + e.getMessage());
            }
        }
        updateHashMaps();
    }

    /**
     * @brief Felveszi a megkapott Rovaraszt a listákba. Név duplikáció esetén megejelnít egy hibaablakot és visszatér.
     */
    public void rovaraszFelvetel(Rovarasz rs) throws Exception {
        if (objektumok.containsKey(rs.getNev())) {
            throw new Exception("Mar van ilyen nevu jatekos");
        }
        jatekosok.add(rs);
        rovaraszok.add(rs);
        objektumok.put(rs.getNev(), rs);
        objektumokbolString.put(rs, rs.getNev());
    }    

    /**
     * @brief Felveszi a megkapott Gombaszt a listákba. Név duplikáció esetén megejelnít egy hibaablakot és visszatér.
     */
    public void gombaszFelvetel(Gombasz gs) throws Exception {
        //Felveszi a megkapott Gombaszt a listákba.
        if (objektumok.containsKey(gs.getNev())) {
            throw new Exception("Mar van ilyen nevu jatekos");
        }
        jatekosok.add(gs);
        gombaszok.add(gs);
        objektumok.put(gs.getNev(), gs);
        objektumokbolString.put(gs, gs.getNev());
    }

    public void eszik(Rovar r, Spora s) throws Exception { 
        // A paraméterben megadott Rovar megeszi a Gombafaj egyik spóráját a megadott Tektonról

        if(r == null){
            throw new Exception("parancskezelok eszik() rovar null");
        }
        if(s == null){
            throw new Exception("parancskezelok eszik() spora null");
        }
        Tekton t=r.getTartozkodas();
        if(!t.getSporak().contains(t)){
            throw new Exception("A rovar nem a megadott tektonon tartozkodik");
        }

        List<Spora> sporak = t.getSporak();

        if(!sporak.contains(s)){
            throw new Exception("Az adott gombafajnak nincsen spórája a tektonon");
        }

        if(r.getAllapot() == rovarAllapot.Benitott){
            throw new Exception("A rovar le van benitva");
        }
        try {
            Rovarasz rovarasz = (Rovarasz)aktivJatekos;
            rovarasz.eszik(s, r); 
        } catch (Exception e) {
            throw e;
        }
        
    }

    public void vag(Rovar r, Gombafonal gf) throws Exception {
        // A paraméterben megadott rovar elvágja a megadott gombafonalat
                        if(r == null){
                            throw new Exception("Vag() rovar objektum null");
                        }

                        if(gf == null){
                            throw new Exception("Vag() Gombafonal objektum null");
                        }

                        if(r.getAllapot() == rovarAllapot.Benitott){
                            throw new Exception("A rovar le van benitva");
                        }
                        if(r.getAllapot() == rovarAllapot.VagasKeptelen){
                            throw new Exception("Rovar vagaskeptelenito allapotban van");
                        }
                        try {
                            Rovarasz rovarasz = (Rovarasz)aktivJatekos;
                            rovarasz.vag(gf, r);
                        } catch(Exception e) {
                            throw new Exception("Nem sikerult elvagni a gombafonalat");
                        }
    }

    public void maszik(Rovar r, Tekton t) throws Exception {
        // A paraméterben megadott rovar átmászik a megadott tektonra a parancs hatására
                        if(r == null){
                            throw new Exception("Nem letezik a rovar. Parancskezelok maszik()");
                        }
                        if(t == null){
                           throw new Exception("Nem letezik a Tekton. Parancskezelok maszik()");
                        }
                        if(r.getAllapot() == rovarAllapot.Benitott){
                            throw new Exception("A rovar le van benitva");
                        }

                        try{
                            Rovarasz rovarasz = (Rovarasz)aktivJatekos;
                            rovarasz.maszik(t, r);
                        }catch(Exception e){
                            throw new Exception("Nem lehetett átmenni a megadott tektonra");
                        }
    }

    public void rovart_eszik(Rovar r) throws Exception {
        // A gombasz jatekos megeszi a paraméterben kapott rovart
            if(r == null){
                throw new Exception("Nem letezik a rovar. Parancskezelok rovart_eszik()");
            }

            Gombasz gombasz = (Gombasz)aktivJatekos;

            if(r.getAllapot() != rovarAllapot.Benitott){
                throw new Exception("A rovar nincs lebenitva");
            }
            try {
                gombasz.rovarEves(r);
            } catch (Exception e) {
                throw new Exception("Nem sikerult megenni a rovart");
            }
    }

    public void gfnov(Tekton t1, Tekton t2) throws Exception {
        // A megadott gombafaj gombafonalat húz a megadott két tekton közé
            
            if(t1 == null){
                throw new Exception("parancskezelok gfnov() tekton1 null");
            }
            if(t2 == null){
                throw new Exception("parancskezelok gfnov() tekton2 null");
            }
           
            try {
                Gombasz gombasz = (Gombasz)aktivJatekos;
                gombasz.fonalNov(t1, t2);
            } catch (Exception e) {
                throw new Exception("Nem sikerult gombafonalat noveszteni");
            }
    }

    public void sporaszor(Gombatest gt) throws Exception {
        // A megadott gombatest sporat szor

        if(gt == null){
            throw new Exception("parancskezelok sporaszor() gombatest null");
        }

        Tekton tartozkodas = gt.getTekton();
        try {
            Gombasz gombasz = (Gombasz)aktivJatekos;
            gombasz.sporaSzoras(tartozkodas, gt);
        } catch (Exception e) {
            throw new Exception("Nem sikerult sporat szorni");
        }
    }

    public void gtNov(Tekton t1) throws Exception {
        // A megadott Tektonra gombatestet növeszt
                        if(t1 == null){
                            throw new Exception("parancskezelok gtNov() tekton null");
                        }
                        Gombasz gombasz = (Gombasz)aktivJatekos;

                        List<Spora> sporak = t1.getSporak();
                        boolean vanSpora = false;

                        for(Spora spora : sporak) {
                            if(spora.getGombafaj().equals(gombasz.getGombafaj())) {
                                if(spora.getMennyiseg() >= 3){
                                    vanSpora = true;
                                    break;
                                }
                            }
                        }
                        if(!vanSpora){
                            throw new Exception("Nincs eleg spora gombatest noveszteshez");
                        }

                        //egyéb gombatest a tektonon ellenőrzés
                        ArrayList<Gombatest> gombatestek = new ArrayList<>();
                        for(Gombasz egygombasz : gombaszok) {
                            Gombafaj gombaszFaja = egygombasz.getGombafaj();
                            gombatestek.addAll(gombaszFaja.getGombaTestekList());
                        }
                        for(Gombatest gombatest : gombatestek) {
                            if(gombatest.getTekton().equals(t1)) {
                                throw new Exception("Mar van gombatest az adott tektonon");
                            }
                        }

                        try{
                            gombasz.testNovesztes(t1, true);
                        }catch(Exception e){
                            throw e;
                        }
    }

    /**
     * @brief Az aktuális játékos köre véget ér
     */
    public void endTurn() {
         aktivJatekos.korVege();
    }

    public void gombaszFelvetel(String nev, Gombafaj gf) throws Exception {
        // Ellenőrzi, hogy ne legyen két azonos nevű játékos, illetve egy gombafaj csak egy játékoshoz tartozzon
        if(nev== null || gf == null) {
            throw new Exception("Hibasan adta meg az adatokat");
        }
        if(gombaszok != null) {
            for(Jatekos jatekos : jatekosok) {
                if(jatekos.getNev().equals(nev)) {
                    throw new Exception("Mar van ilyen nevu jatekos");
                }
            }
            for(Gombasz gombasz : gombaszok) {
                if(gombasz.getGombafaj().getNev().equals(gf.getNev())) {
                    throw new Exception("Mar van ilyen gombafaj");
                }
            }
        }
        Gombasz gombasz = new Gombasz(nev,gf);
        gombasz.akciopontAlapbaallit();

        objektumok.put(gf.getNev(), gf);                               //gombafaj neve alapjan mentjuk el a gombafajt a Map-en
        objektumokbolString.put(gf, gf.getNev());

        gombaszok.add(gombasz);
        jatekosok.add(gombasz);
        objektumok.put(nev, gombasz);                               //Gombasz neve alapjan mentjuk el a gombaszt a Map-en
        objektumokbolString.put(gombasz, nev); 
    }

    public void rovaraszFelvetel(String nev, Color szin) throws Exception {
        // Ellenőrzi, hogy ne legyen két azonos nevű játékos, illetve egy rovar szín csak egy játékoshoz tartozzon
        if( szin == null) {
              throw new Exception("Hibasan adta meg az adatokat, szin null");
        }
        if(nev== null ) {
              throw new Exception("Hibasan adta meg az adatokat, nev null");
        }

        if(rovaraszok != null) {
            for(Jatekos jatekos : jatekosok) {
                if(jatekos.getNev().equals(nev)) {
                      throw new Exception("Mar van ilyen nevu jatekos");
                }
            }
            for(Rovarasz rovarasz : rovaraszok) {
                if(rovarasz.getSzin().equals(szin)) {
                      throw new Exception("Mar van színű rovar");
                }
            }
        }
        Rovarasz rovarasz = new Rovarasz(nev,szin);
        rovarasz.akciopontAlapbaallit();

        rovaraszok.add(rovarasz);
        jatekosok.add(rovarasz);
        objektumok.put(nev, rovarasz);                               //rovarasz neve alapjan mentjuk el a gombaszt a Map-en
        objektumokbolString.put(rovarasz, nev); 
    }

    /**
     * @brief Feldolgozza a bemeneti akciót és végrehajtja a megfelelő műveletet. A metódus a bemeneti string alapján azonosítja a parancsot, ellenőrzi a jogosultságokat, majd végrehajtja a kért műveletet. A parancsok lehetnek act (játék műveletek), arrange (elrendezés) vagy assert (ellenőrzés) típusúak.
     * @param action A bemeneti parancs string
     * @param output A kimeneti író
     * @throws Exception Ha hiba történik a parancs végrehajtása közben
     */
    public void bemenetAkcio(String action, PrintWriter output) throws Exception {
            //megkapja a bemeneti stringet, azalapján eldönti melyik akció fut le.
            // (likelihood ellenőrzés) %-r eves%
            //elindul az akció.
            //akció lehet create() is, ebben az esetben HashMap-be belekerülnek az új objektumok.
            //create() féle akció nem csak constructor, de mondjuk osztodas(), gombatest novesztes esetén is lehetséges.
            //Kinyeri a fontos objektumokat név/string alapján a HashMapből
            //végrehajtja az akciót a kinyert objektumokkal.
            try {
                if (allapot == parancsAllapot.Game) {
                    //játékhoz szükséges akciók - act és assert parancsok
                    //act parancsok
                    if(action.matches("/act -eszik -r \\S+ -g \\S+ -t \\S+")){
                        //Kérdés: Itt kell-e lekezelni, hogy ha a tekton és a rovar tartozkodása nem egyezik meg, akkor nem tud enni?
                        
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        if(rovar == null){
                            output.println("Nem letezik: " + rovarStr);
                            return;
                        }

                        String tekton1 = action.split(" ")[7];
                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);

                        if(Tekton1 == null){
                            output.println("Nem letezik: " + tekton1);
                            return;
                        }
                        if(!rovar.getTartozkodas().equals(Tekton1)) {
                            output.println("A rovar nem a megadott tektonon tartozkodik");
                            return;
                        }

                        List<Spora> sporak = Tekton1.getSporak();
                        
                        String gombaTipus = action.split(" ")[5];
                        char gombaTip = gombaTipus.charAt(0);

                        if(rovar.getAllapot() == rovarAllapot.Benitott){
                            output.println("A rovar le van benitva");
                            return;
                        }

                        int db = 0;
                        for(Spora spora : sporak) {
                            String sporaTipus = objektumokbolString.get(spora);
                            char sporaTip = sporaTipus.charAt(0);
                            if(sporaTip == gombaTip) {
                                Rovarasz rovarasz = (Rovarasz)aktivJatekos;
                                rovarasz.eszik(spora, rovar);

                                //rovar.eszik(spora);
                                db++;
                                break;
                            }
                        }

                        if(db == 0){
                            output.println("Nincs " + gombaTipus + " spora " + tekton1 + " tektonon");
                            return;
                        }
                       
                        output.println(gombaTipus + " hatas ervenyesul " + rovarStr + " rovarra");
                        if(gombaTipus.equals("osztodo")){
                            output.println("Hozzaadva "+ujRovarNev());
                        }
                    }
                    if(action.matches("/act -vag -r \\S+ -gf \\S+")){
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        if(rovar == null){
                            output.println("Nem letezik: " + rovarStr);
                            return;
                        }

                        String gombafonalStr = action.split(" ")[5];
                        Gombafonal gombafonal = (Gombafonal)objektumok.get(gombafonalStr);

                        if(gombafonal == null){
                            output.println("Nem letezik: " + gombafonalStr);
                            return;
                        }
                        if(rovar.getAllapot() == rovarAllapot.Benitott){
                            output.println("A rovar le van benitva");
                            return;
                        }
                        if(rovar.getAllapot() == rovarAllapot.VagasKeptelen){
                            output.println("Rovar vagaskeptelenito allapotban van");
                            return;
                        }

                        //rovar.vag(gombafonal);

                        Rovarasz rovarasz = (Rovarasz)aktivJatekos;
                        rovarasz.vag(gombafonal, rovar);

                        output.println("Megszunt " + gombafonalStr);
                    }
                    if(action.matches("/act -maszik -r \\S+ -t \\S+")){         ////GYANÚ: a tektont nem kapja meg parancsbol
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        if(rovar == null){
                            output.println("Nem letezik: " + rovarStr);
                            return;
                        }

                        String tektonStr = action.split(" ")[5];
                        Tekton tekton = (Tekton)objektumok.get(tektonStr);

                        if(tekton == null){
                            output.println("Nem letezik: " + tektonStr);
                            return;
                        }
                        if(rovar.getAllapot() == rovarAllapot.Benitott){
                            output.println("A rovar le van benitva");
                            return;
                        }

                        try{
                            //rovar.maszik(tekton);

                            Rovarasz rovarasz = (Rovarasz)aktivJatekos;
                            rovarasz.maszik(tekton, rovar);
                        }catch(Exception e){
                            output.println("Nem lehet atmenni " + tektonStr + " tektonra");
                            return;
                        }

                        output.println(rovarStr + " " + tektonStr + " tektonon van");
                    }
                    if(action.matches("/act -reszik -r \\S+")){
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        if(rovar == null){
                            output.println("Nem letezik: " + rovarStr);
                            return;
                        }

                        Gombasz gombasz = (Gombasz)aktivJatekos;

                        if(rovar.getAllapot() != rovarAllapot.Benitott){
                            output.println("A rovar nincs lebenitva");
                            return;
                        }

                        gombasz.rovarEves(rovar);

                        output.println("Megszunt " + rovarStr);
                        output.println("Hozzaadva " + ujGombatestNev());
                    }
                    if(action.matches("/act -gfnov -t \\S+ -t \\S+ -g \\S+")){ 
                        String gombafaj = action.split(" ")[7];
                        //char gombaf = gombafaj.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(gombafaj);

                        if(gf == null){
                            output.println("Nem letezik: " + gombafaj);
                            return;
                        }

                        String tekton1 = action.split(" ")[3];
                        String tekton2 = action.split(" ")[5];

                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);
                        Tekton Tekton2 = (Tekton)objektumok.get(tekton2);

                        if(Tekton1 == null){
                            output.println("Nem letezik: " + tekton1);
                            return;
                        }
                        if(Tekton2 == null){
                            output.println("Nem letezik: " + tekton2);
                            return;
                        }

                        List<Tekton> szomszedok = Tekton1.getSzomszedok();
                        List<Gombafonal> gombafonalak1 = Tekton1.getFonalak();
                        /*
                        int db = 0;
                        for(Tekton szomszed : szomszedok) {
                            if (szomszed.equals(Tekton2)) {
                                db++;
                                break;
                            }
                        }
                        for(Gombafonal fonal : gombafonalak1) {
                            if (fonal.getGombafaj().equals(gf)) {
                                db++;
                                break;
                            }
                        }

                        if(db < 2){
                            output.println("Nem sikerult gombafonalat noveszteni");
                            return;
                        }

                        if(tekton1.charAt(0) == 'm'){
                            if(!gombafonalak1.isEmpty()){
                                for(Gombafonal fonal : gombafonalak1) {
                                    if (!fonal.getGombafaj().equals(gf)) {
                                        output.println("Nem sikerult gombafonalat noveszteni");
                                        return;
                                    }
                                }
                            }
                        }else if(tekton2.charAt(0) == 'm'){
                            List<Gombafonal> gombafonalak2 = Tekton2.getFonalak();
                            if(!gombafonalak2.isEmpty()){
                                for(Gombafonal fonal : gombafonalak2) {
                                    if (!fonal.getGombafaj().equals(gf)) {
                                        output.println("Nem sikerult gombafonalat noveszteni");
                                        return;
                                    }
                                }
                            }
                        }*/

                        try {
                            //gf.fonalNov(Tekton1, Tekton2);

                            Gombasz gombasz = (Gombasz)aktivJatekos;
                            gombasz.fonalNov(Tekton1, Tekton2);
                        } catch (Exception e) {
                            output.println("Nem sikerult gombafonalat noveszteni");
                            return;
                        }
                        
                        output.println("Hozzaadva " + ujGombafonalNev());
                    }
                    if(action.matches("/act -spszor -gt \\S+")){
                        String gombatestStr = action.split(" ")[3];
                        Gombatest gombatest = (Gombatest)objektumok.get(gombatestStr);

                        if(gombatest == null){
                            output.println("Nem letezik: " + gombatestStr);
                            return;
                        }

                        Tekton tartozkodas = gombatest.getTekton();
                        
                        Gombasz gombasz = (Gombasz)aktivJatekos;
                        gombasz.sporaSzoras(tartozkodas, gombatest);

                        output.println("Sikeres szoras");
                    }
                    
                    if(action.matches("/act -gt -t \\S+")){
                        String tektonStr = action.split(" ")[3];
                        Tekton tekton = (Tekton)objektumok.get(tektonStr);

                        if(tekton == null){
                            output.println("Nem letezik: " + tektonStr);
                            return;
                        }
                        if(tektonStr.charAt(0) == 'p'){
                            output.println("Ezen a tektonon nem lehet gombatestet noveszteni");
                            return;
                        }

                        Gombasz gombasz = (Gombasz)aktivJatekos;

                        List<Spora> sporak = tekton.getSporak();
                        boolean vanSpora = false;

                        for(Spora spora : sporak) {
                            if(spora.getGombafaj().equals(gombasz.getGombafaj())) {
                                if(spora.getMennyiseg() >= 3){
                                    vanSpora = true;
                                    break;
                                }
                            }
                        }
                        if(!vanSpora){
                            output.println("Nincs eleg spora gombatest noveszteshez");
                            return;
                        }

                        //egyéb gombatest a tektonon ellenőrzés
                        ArrayList<Gombatest> gombatestek = new ArrayList<>();
                        for(Gombasz egygombasz : gombaszok) {
                            Gombafaj gombaszFaja = egygombasz.getGombafaj();
                            gombatestek.addAll(gombaszFaja.getGombaTestekList());
                        }
                        for(Gombatest gombatest : gombatestek) {
                            if(gombatest.getTekton().equals(tekton)) {
                                output.println("Mar van gombatest az adott tektonon");
                                return;
                            }
                        }


                        try{
                            gombasz.testNovesztes(tekton, true);
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                            output.println("Mar van gombatest az adott tektonon");
                            return;
                        }

                        output.println("Hozzaadva " + ujGombatestNev());
                    }
                   
                    
                    if(action.matches("/act -endTurn")){
                        aktivJatekos.korVege();
                        //Kiíratás?
                    }
                    if (action.matches("/assert -tszomszedok -t \\S+")) {
                        String tektonStr = action.split(" ")[3];
                        listTektonSzomszedok(output, tektonStr);
                    }else if (action.matches("/assert -gfszomszedok -t \\S+")) {
                        String tektonStr = action.split(" ")[3];
                        listGFSzomszedok(output, tektonStr);                        
                    }else if (action.matches("/assert -gfallapot \\S+")) {
                        String gombafonalNev = action.split(" ")[2];
                        showFonalAllapot(output, gombafonalNev);
                    }else if (action.matches("/assert -rallapot \\S+")) {
                        String rovarNev = action.split(" ")[2];
                        showRovarAllapot(output, rovarNev);
                    }else if (action.matches("/assert -list -gf")) {
                        listGf(output);
                    }else if (action.matches("/assert -list -gt")) {
                        listGt(output);
                    }else if (action.matches("/assert -list -r -t")) {
                        listRovarok(output);
                    }else if (action.matches("/assert -list -t")) {
                        listTektonok(output);
                    }else if (action.matches("/assert -list -spora -db -t")) {
                        listSpora(output);
                    }else if (action.matches("/assert -list -jatekosok")) {
                        listJatekosok(output);
                    }
                    //HashMap-ek frissítése minden sikeres parancs után
                    updateHashMaps();
                }else if (allapot == parancsAllapot.Test) {
                    //teszteléshez szükséges akciók - minden parancs

                    //arrange parancsok
                    if (action.matches("/arrange -t \\S+")) {
                        String tektonTipus = action.split(" ")[2];
                        char tektonTip = tektonTipus.charAt(0);
                        Tekton ujTekton = tektonLetrehoz(tektonTip);
                        jatekter.tektonAdd(ujTekton);
                        String ujNev = ujTektonNev(tektonTip);
                        objektumok.put(ujNev, ujTekton);
                        objektumokbolString.put(ujTekton, ujNev);

                        GTekton gTekton = new GTekton(getColorTektonByName(tektonTip));
                        grafika.getTektonok().put(ujTekton,gTekton);

                        output.println("Hozzaadva " + ujNev);
                    }
                    if(action.matches("/arrange -j \\S+ -r -t \\S+")){
                        rovaraszFelvetel(action,output);
                    }
                    if(action.matches("/arrange -szomszed -t \\S+ -t \\S+")){
                        String tekton1 = action.split(" ")[3];
                        String tekton2 = action.split(" ")[5];

                        //Tekton Tekton1 = (Tekton)objektumok.get(tekton1);
                        //Tekton Tekton2 = (Tekton)objektumok.get(tekton2);
                        Tekton Tekton1 = parancsTektonCast(tekton1.charAt(0), tekton1);
                        Tekton Tekton2 = parancsTektonCast(tekton2.charAt(0), tekton2);
                        
                        if(Tekton1 == null){
                            output.println("Nem letezik: " + tekton1);
                            return;
                        }
                        if(Tekton2 == null){
                            output.println("Nem letezik: " + tekton2);
                            return;
                        }

                        Tekton1.addSzomszed(Tekton2);
                        Tekton2.addSzomszed(Tekton1);

                        output.println("Szomszedos lett " + tekton1 + " es " + tekton2);
                    }
                    if(action.matches("/arrange -j \\S+ -g \\S+")){
                        gombaszFelvetele(action,output);
                    }
                    if(action.matches("/arrange -gf -t \\S+ -t \\S+ -g \\S+")){
                        String gombafaj = action.split(" ")[7];
                        //char gombaf = gombafaj.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(gombafaj);

                        if(gf == null){
                            output.println("Nem letezik: " + gombafaj);
                            return;
                        }

                        String tekton1 = action.split(" ")[3];
                        String tekton2 = action.split(" ")[5];

                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);
                        Tekton Tekton2 = (Tekton)objektumok.get(tekton2);

                        if(Tekton1 == null){
                            output.println("Nem letezik: " + tekton1);
                            return;
                        }
                        if(Tekton2 == null){
                            output.println("Nem letezik: " + tekton2);
                            return;
                        }

                        Gombafonal ujGombafonal = new Gombafonal(gf, Tekton1, Tekton2);

                        if(ujGombafonal == null){
                            output.println("Nem sikerult hozzaadni a gombafonalat");
                            return;
                        }

                        gf.addFonal(ujGombafonal);
                        Tekton1.addFonal(ujGombafonal);
                        Tekton2.addFonal(ujGombafonal);
                        String ujNev = ujGombafonalNev();
                        objektumok.put(ujNev, ujGombafonal);
                        objektumokbolString.put(ujGombafonal, ujNev);

                        GGombafonal gGombafonal = new GGombafonal(getColorKepByFaj(gf));
                        grafika.getGombafonalak().put(ujGombafonal, gGombafonal);

                        output.println("Hozzaadva " + ujNev);
                    }
                    if(action.matches("/arrange -s \\S+ -t \\S+ \\d+")){
                        String sporaTipus = action.split(" ")[2];
                        char sporaTip = sporaTipus.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(sporaTipus);

                        if(gf == null){
                            output.println("Nem letezik: " + sporaTipus);
                            return;
                        }

                        String tekton1 = action.split(" ")[4];
                        //Tekton Tekton1 = (Tekton)objektumok.get(tekton1);
                        Tekton proba = parancsTektonCast(tekton1.charAt(0), tekton1);

                        if(proba == null){
                            output.println("Nem letezik: " + tekton1);
                            return;
                        }

                        String szamResz = action.split(" ")[5];
                        int szam = Integer.parseInt(szamResz);

                        if(szam < 0){
                            output.println("Nem lehet negativ szamu spora");
                            return;
                        }

                        Spora ujSpora = sporaLetrehoz(sporaTip, gf, szam);
                        proba.addSpora(ujSpora);
                        String ujNev = ujSporaNev(sporaTip);
                        objektumok.put(ujNev, ujSpora);
                        objektumokbolString.put(ujSpora, ujNev);

                        GSpora gSpora = new GSpora(getColorKepByFaj(gf));
                        grafika.getSporak().put(ujSpora, gSpora);

                        output.println("Hozzaadva " + ujNev + " spora");
                    }
                    if(action.matches("/arrange -gt \\S+ -t \\S+")){
                        String gombaTipus = action.split(" ")[2];
                        //char gombaTip = gombaTipus.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(gombaTipus);

                        if(gf == null){
                            output.println("Nem letezik: " + gombaTipus);
                            return;
                        }

                        String tekton1 = action.split(" ")[4];
                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);

                        if(Tekton1 == null){
                            output.println("Nem letezik: " + tekton1);
                            return;
                        }

                        Gombatest ujGombatest = new Gombatest(gf, Tekton1);
                        gf.addTest(ujGombatest);
                        String ujNev = ujGombatestNev();
                        objektumok.put(ujNev, ujGombatest);
                        objektumokbolString.put(ujGombatest, ujNev);

                        output.println("Hozzaadva " + ujNev);
                    }
                    if (action.matches("/arrange -gt \\S+ -sr \\S+")) {
                        String gombatestNev = action.split(" ")[2];
                        Gombatest gombatest = (Gombatest)objektumok.get(gombatestNev);
                        int sporaRaktar = Integer.parseInt(action.split(" ")[4]);
                        if (gombatest == null) {
                            output.println("Nem letezik: " + gombatestNev);
                            return;
                        }
                        if (sporaRaktar < 0) {
                            output.println("Nem lehet negativ szamu spora");
                            return;
                        }
                        gombatest.setSporaRaktar(sporaRaktar);
                        output.println(gombatestNev + " gombatest spora raktara " + sporaRaktar);
                    }

                    //act parancsok
                    if(action.matches("/act -eszik -r \\S+ -g \\S+ -t \\S+")){
                        //Kérdés: Itt kell-e lekezelni, hogy ha a tekton és a rovar tartozkodása nem egyezik meg, akkor nem tud enni?
                        
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        if(rovar == null){
                            output.println("Nem letezik: " + rovarStr);
                            return;
                        }

                        String tekton1 = action.split(" ")[7];
                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);

                        if(Tekton1 == null){
                            output.println("Nem letezik: " + tekton1);
                            return;
                        }
                        if(!rovar.getTartozkodas().equals(Tekton1)) {
                            output.println("A rovar nem a megadott tektonon tartozkodik");
                            return;
                        }

                        List<Spora> sporak = Tekton1.getSporak();
                        
                        String gombaTipus = action.split(" ")[5];
                        char gombaTip = gombaTipus.charAt(0);

                        if(rovar.getAllapot() == rovarAllapot.Benitott){
                            output.println("A rovar le van benitva");
                            return;
                        }

                        int db = 0;
                        for(Spora spora : sporak) {
                            String sporaTipus = objektumokbolString.get(spora);
                            char sporaTip = sporaTipus.charAt(0);
                            if(sporaTip == gombaTip) {
                                rovar.eszik(spora);
                                db++;
                                break;
                            }
                        }

                        if(db == 0){
                            output.println("Nincs " + gombaTipus + " spora " + tekton1 + " tektonon");
                            return;
                        }
                       
                        output.println(gombaTipus + " hatas ervenyesul " + rovarStr + " rovarra");
                        if(gombaTipus.equals("osztodo")){
                            output.println("Hozzaadva "+ujRovarNev());
                        }
                    }
                    if(action.matches("/act -vag -r \\S+ -gf \\S+")){
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        if(rovar == null){
                            output.println("Nem letezik: " + rovarStr);
                            return;
                        }

                        String gombafonalStr = action.split(" ")[5];
                        Gombafonal gombafonal = (Gombafonal)objektumok.get(gombafonalStr);

                        if(gombafonal == null){
                            output.println("Nem letezik: " + gombafonalStr);
                            return;
                        }
                        if(rovar.getAllapot() == rovarAllapot.Benitott){
                            output.println("A rovar le van benitva");
                            return;
                        }
                        if(rovar.getAllapot() == rovarAllapot.VagasKeptelen){
                            output.println("Rovar vagaskeptelenito allapotban van");
                            return;
                        }

                        rovar.vag(gombafonal);

                        output.println("Megszunt " + gombafonalStr);
                    }
                    if(action.matches("/act -maszik -r \\S+ -t \\S+")){         ////GYANÚ: a tektont nem kapja meg parancsbol
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        if(rovar == null){
                            output.println("Nem letezik: " + rovarStr);
                            return;
                        }

                        String tektonStr = action.split(" ")[5];
                        Tekton tekton = (Tekton)objektumok.get(tektonStr);

                        if(tekton == null){
                            output.println("Nem letezik: " + tektonStr);
                            return;
                        }
                        if(rovar.getAllapot() == rovarAllapot.Benitott){
                            output.println("A rovar le van benitva");
                            return;
                        }

                        try{
                            rovar.maszik(tekton);
                        }catch(Exception e){
                            output.println("Nem lehet atmenni " + tektonStr + " tektonra");
                            return;
                        }

                        output.println(rovarStr + " " + tektonStr + " tektonon van");
                    }
                    if(action.matches("/act -reszik -r \\S+")){
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        if(rovar == null){
                            output.println("Nem letezik: " + rovarStr);
                            return;
                        }

                        Gombasz gombasz = (Gombasz)aktivJatekos;

                        if(rovar.getAllapot() != rovarAllapot.Benitott){
                            output.println("A rovar nincs lebenitva");
                            return;
                        }

                        gombasz.rovarEves(rovar);

                        output.println("Megszunt " + rovarStr);
                        output.println("Hozzaadva " + ujGombatestNev());
                    }
                    if(action.matches("/act -gfnov -t \\S+ -t \\S+ -g \\S+")){ 
                        String gombafaj = action.split(" ")[7];
                        //char gombaf = gombafaj.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(gombafaj);

                        if(gf == null){
                            output.println("Nem letezik: " + gombafaj);
                            return;
                        }

                        String tekton1 = action.split(" ")[3];
                        String tekton2 = action.split(" ")[5];

                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);
                        Tekton Tekton2 = (Tekton)objektumok.get(tekton2);

                        if(Tekton1 == null){
                            output.println("Nem letezik: " + tekton1);
                            return;
                        }
                        if(Tekton2 == null){
                            output.println("Nem letezik: " + tekton2);
                            return;
                        }

                        List<Tekton> szomszedok = Tekton1.getSzomszedok();
                        List<Gombafonal> gombafonalak1 = Tekton1.getFonalak();
                        /*
                        int db = 0;
                        for(Tekton szomszed : szomszedok) {
                            if (szomszed.equals(Tekton2)) {
                                db++;
                                break;
                            }
                        }
                        for(Gombafonal fonal : gombafonalak1) {
                            if (fonal.getGombafaj().equals(gf)) {
                                db++;
                                break;
                            }
                        }

                        if(db < 2){
                            output.println("Nem sikerult gombafonalat noveszteni");
                            return;
                        }

                        if(tekton1.charAt(0) == 'm'){
                            if(!gombafonalak1.isEmpty()){
                                for(Gombafonal fonal : gombafonalak1) {
                                    if (!fonal.getGombafaj().equals(gf)) {
                                        output.println("Nem sikerult gombafonalat noveszteni");
                                        return;
                                    }
                                }
                            }
                        }else if(tekton2.charAt(0) == 'm'){
                            List<Gombafonal> gombafonalak2 = Tekton2.getFonalak();
                            if(!gombafonalak2.isEmpty()){
                                for(Gombafonal fonal : gombafonalak2) {
                                    if (!fonal.getGombafaj().equals(gf)) {
                                        output.println("Nem sikerult gombafonalat noveszteni");
                                        return;
                                    }
                                }
                            }
                        }*/

                        try {
                            gf.fonalNov(Tekton1, Tekton2);
                        } catch (Exception e) {
                            output.println("Nem sikerult gombafonalat noveszteni");
                            return;
                        }
                        
                        output.println("Hozzaadva " + ujGombafonalNev());
                    }
                    if(action.matches("/act -spszor -gt \\S+")){
                        String gombatestStr = action.split(" ")[3];
                        Gombatest gombatest = (Gombatest)objektumok.get(gombatestStr);

                        if(gombatest == null){
                            output.println("Nem letezik: " + gombatestStr);
                            return;
                        }

                        Tekton tartozkodas = gombatest.getTekton();
                        
                        Gombasz gombasz = (Gombasz)aktivJatekos;
                        gombasz.sporaSzoras(tartozkodas, gombatest);

                        output.println("Sikeres szoras");
                    }
                    if(action.matches("/act -tores -t \\S+")){
                        String tektonStr = action.split(" ")[3];
                        //Tekton tekton = (Tekton)objektumok.get(tektonStr);
                        Tekton tekton = parancsTektonCast(tektonStr.charAt(0),tektonStr);
                        
                        if(tekton == null){
                            output.println("Nem letezik: " + tektonStr);
                            return;
                        }

                        jatekter.tores(1, tekton, true);
                        
                        output.println("Sikeres tores");
                        output.println("Hozzaadva " + ujTektonNev(tektonStr.charAt(0)));
                    }
                    if(action.matches("/act -gt -t \\S+")){
                        String tektonStr = action.split(" ")[3];
                        Tekton tekton = (Tekton)objektumok.get(tektonStr);

                        if(tekton == null){
                            output.println("Nem letezik: " + tektonStr);
                            return;
                        }
                        if(tektonStr.charAt(0) == 'p'){
                            output.println("Ezen a tektonon nem lehet gombatestet noveszteni");
                            return;
                        }

                        Gombasz gombasz = (Gombasz)aktivJatekos;

                        List<Spora> sporak = tekton.getSporak();
                        boolean vanSpora = false;

                        for(Spora spora : sporak) {
                            if(spora.getGombafaj().equals(gombasz.getGombafaj())) {
                                if(spora.getMennyiseg() >= 3){
                                    vanSpora = true;
                                    break;
                                }
                            }
                        }
                        if(!vanSpora){
                            output.println("Nincs eleg spora gombatest noveszteshez");
                            return;
                        }

                        //egyéb gombatest a tektonon ellenőrzés
                        ArrayList<Gombatest> gombatestek = new ArrayList<>();
                        for(Gombasz egygombasz : gombaszok) {
                            Gombafaj gombaszFaja = egygombasz.getGombafaj();
                            gombatestek.addAll(gombaszFaja.getGombaTestekList());
                        }
                        for(Gombatest gombatest : gombatestek) {
                            if(gombatest.getTekton().equals(tekton)) {
                                output.println("Mar van gombatest az adott tektonon");
                                return;
                            }
                        }


                        try{
                            gombasz.testNovesztes(tekton, true);
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                            output.println("Mar van gombatest az adott tektonon");
                            return;
                        }

                        output.println("Hozzaadva " + ujGombatestNev());
                    }
                    if(action.matches("/act -gt \\S+ -fejlett")){
                        String gombatestStr = action.split(" ")[2];
                        Gombatest gombatest = (Gombatest)objektumok.get(gombatestStr);

                        if(gombatest == null){
                            output.println("Nem letezik: " + gombatestStr);
                            return;
                        }

                        int gtFejlettsegIdo = gombatest.getGombafaj().getGombatestFejlettsegIdo();

                        gombatest.setKor(gtFejlettsegIdo);

                        output.println(gombatestStr + " fejlett");
                    }
                    if(action.matches("/act -aktivJatekos \\S+")){
                        for(int i = 0; i < jatekosok.size(); i++){
                            if(jatekosok.get(i).getNev().equals(action.split(" ")[2])){
                                aktivJatekos = jatekosok.get(i);
                                output.println("Aktiv jatekos " + aktivJatekos.getNev());
                                break;
                            }
                        }
                    }
                    if(action.matches("/act -endTurn")){
                        //aktivJatekos.korVege();
                        //aktivJatekos.endTurnForTests();
                        //Kiíratás?

                        for (Jatekos jatekos : jatekosok) {
                            jatekos.endTurnForTests();
                        }
                    }
                    if (action.matches("/assert -tszomszedok -t \\S+")) {
                        String tektonStr = action.split(" ")[3];
                        listTektonSzomszedok(output, tektonStr);
                    }else if (action.matches("/assert -gfszomszedok -t \\S+")) {
                        String tektonStr = action.split(" ")[3];
                        listGFSzomszedok(output, tektonStr);                        
                    }else if (action.matches("/assert -gfallapot \\S+")) {
                        String gombafonalNev = action.split(" ")[2];
                        showFonalAllapot(output, gombafonalNev);
                    }else if (action.matches("/assert -rallapot \\S+")) {
                        String rovarNev = action.split(" ")[2];
                        showRovarAllapot(output, rovarNev);
                    }else if (action.matches("/assert -list -gf")) {
                        listGf(output);
                    }else if (action.matches("/assert -list -gt")) {
                        listGt(output);
                    }else if (action.matches("/assert -list -r -t")) {
                        listRovarok(output);
                    }else if (action.matches("/assert -list -t")) {
                        listTektonok(output);
                    }else if (action.matches("/assert -list -spora -db -t")) {
                        listSpora(output);
                    }else if (action.matches("/assert -list -jatekosok")) {
                        listJatekosok(output);
                    }
                    //HashMap-ek frissítése minden sikeres parancs után
                    updateHashMaps();
                }
            } catch (Exception e) {
                throw e;
            }

    }

    /**
     * @brief Generál egy új tekton nevet a megadott típus alapján
     * @param c A tekton típusa (t, m, d, p, i)
     * @return Az új tekton neve, vagy null hibás típus esetén
     */
    public String ujTektonNev(char c) {
        switch (c) {
            case 't':
                int maxSzamT = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("tekton\\d+")) {
                        String szamResz = kulcs.substring(6); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamT) {
                            maxSzamT = szam;
                        }
                    }
                }
                return "tekton" + (maxSzamT + 1); // új név a következő Rovarhoz
                
            case 'm':
                int maxSzamM = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("monotekton\\d+")) {
                        String szamResz = kulcs.substring(10); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamM) {
                            maxSzamM = szam;
                        }
                    }
                }
                return "monotekton" + (maxSzamM + 1); // új név a következő Rovarhoz
                
            case 'd':
                int maxSzamD = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("disszolator\\d+")) {
                        String szamResz = kulcs.substring(11); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamD) {
                            maxSzamD = szam;
                        }
                    }
                }
                return "disszolator" + (maxSzamD + 1); // új név a következő Rovarhoz
                
            case 'p':
                int maxSzamP = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("puritekton\\d+")) {
                        String szamResz = kulcs.substring(10); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamP) {
                            maxSzamP = szam;
                        }
                    }
                }
                return "puritekton" + (maxSzamP + 1); // új név a következő Rovarhoz
                
            case 'i':
                int maxSzamI = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("infinator\\d+")) {
                        String szamResz = kulcs.substring(9); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamI) {
                            maxSzamI = szam;
                        }
                    }
                }
                return "infinator" + (maxSzamI + 1); // új név a következő Rovarhoz
                
            default:
                System.out.println("Nincs ilyen tipus: " + c);
                break;
        }
        
        return null;
    }

    /**
     * @brief Generál egy új spóra nevet a megadott típus alapján
     * @param c A spóra típusa (b, g, l, v, o)
     * @return Az új spóra neve, vagy null hibás típus esetén
     */
    public String ujSporaNev(char c) {
        switch (c) {
            case 'b':
                int maxSzamB = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("benito\\d+")) {
                        String szamResz = kulcs.substring(6); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamB) {
                            maxSzamB = szam;
                        }
                    }
                }
                return "benito" + (maxSzamB + 1); // új név a következő Rovarhoz
                
            case 'g':
                int maxSzamG = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("gyorsito\\d+")) {
                        String szamResz = kulcs.substring(8); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamG) {
                            maxSzamG = szam;
                        }
                    }
                }
                return "gyorsito" + (maxSzamG + 1); // új név a következő Rovarhoz
                
            case 'l':
                int maxSzamL = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("lassito\\d+")) {
                        String szamResz = kulcs.substring(7); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamL) {
                            maxSzamL = szam;
                        }
                    }
                }
                return "lassito" + (maxSzamL + 1); // új név a következő Rovarhoz
                
            case 'v':
                int maxSzamV = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("vagaskeptelenito\\d+")) {
                        String szamResz = kulcs.substring(15); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamV) {
                            maxSzamV = szam;
                        }
                    }
                }
                return "vagaskeptelenito" + (maxSzamV + 1); // új név a következő Rovarhoz
                
            case 'o':
                int maxSzamO = 0;
                for (String kulcs : objektumok.keySet()) {
                    if (kulcs.matches("osztodo\\d+")) {
                        String szamResz = kulcs.substring(7); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamO) {
                            maxSzamO = szam;
                        }
                    }
                }
                return "osztodo" + (maxSzamO + 1); // új név a következő Rovarhoz
                
            default:
                System.out.println("Nincs ilyen tipus: " + c);
                break;
        }
        
        return null;
    }

    /**
     * @brief Generál egy új gombafonal nevet
     * @return Az új gombafonal neve
     */
    public String ujGombafonalNev(){
        int maxGombafonalSzam = 0;
        for (String kulcs : objektumok.keySet()) {
            if (kulcs.matches("gombafonal\\d+")) {
                String szamResz = kulcs.substring(10); // levágjuk az "gombafonal"-t
                int szam = Integer.parseInt(szamResz);
                if (szam > maxGombafonalSzam) {
                    maxGombafonalSzam = szam;
                }
            }
        }
        return "gombafonal" + (maxGombafonalSzam + 1); // új név a következő Rovarhoz
    }

    /**
     * @brief Újraindítja a projektet, törli az összes adatot a tesztekhez
     */
    public void renewProject(){ 
        this.objektumok.clear();
        this.objektumokbolString.clear();
        this.jatekosok.clear();
        this.gombaszok.clear();
        this.rovaraszok.clear();
        this.jatekter = new Jatekter();
        this.aktivJatekos = null;
    }

    /**
     * @brief Frissíti a HashMap-eket az aktuális állapot szerint. Ellenőrzi az összes objektumot a játékban, és frissíti a HashMap-eket, hogy azok mindig a legfrissebb állapotot tükrözzék
     */
    public void updateHashMaps(){
        /*
            Végigmenni a
            Jatektér, Rovarasz, Gombafaj objektumokon és listáikon. 
            Innen kiírni egy temp listába az objektumokat. 

            Ezekkel az objektumokkal összehasonlítani a HashMap-et. 
            Ha valami nincs benne a temp listben de a HashMapben igen-->töröl
            Valami benne van a temp listben de a HashMapben nincs--> belerakjuk
        */

        //Rovarasz vizsgalat
        //ha benne van a HashMapben de a Rovarasz listájában nincs-->törölni kell a HashMapből
        List<Rovar> rovarokAHashMapben = new ArrayList<>();
        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("rovar\\d+")) {
                Rovar rovar = (Rovar)objektumok.get(kulcs);
                rovarokAHashMapben.add(rovar);
            }
        }
        List<Rovar> osszesRovar = new ArrayList<>();
        for(Rovarasz rovarasz : rovaraszok) {
            List<Rovar> rovarok = rovarasz.getRovarok();
            osszesRovar.addAll(rovarok);
            for(Rovar rovar : rovarok) {
                if(!objektumokbolString.containsKey(rovar)) {
                    //ha nincs benne a HashMap-ben, de a Rovarasz listájában igen.
                    String ujRovarNev = ujRovarNev(); //rovar neve

                    objektumok.put(ujRovarNev, rovar); //Rovar neve alapjan mentjuk el a Rovarokat a Map-en
                    objektumokbolString.put(rovar, ujRovarNev);
                    //graf osztaly létrehozasa
                    GRovar gRovar = new GRovar(getRovarKepByColor(rovarasz.getSzin()));
                    grafika.getRovarok().put(rovar,gRovar);
                }
            }
        }

        //ha benne van a HashMapben de a Rovarasz listájában nincs-->törölni kell a HashMapből
        for (Rovar rovarAHashMapben : rovarokAHashMapben) {
            if (!osszesRovar.contains(rovarAHashMapben)) {
                //ha nincs benne a Rovarasz listájában, de a HashMapben igen.
                objektumok.remove(objektumokbolString.get(rovarAHashMapben)); //eltávolítjuk a HashMapből
                objektumokbolString.remove(rovarAHashMapben); //eltávolítjuk a HashMapből

                //grafikus objektum eltávolítása a hashMapből
                grafika.getRovarok().remove(rovarAHashMapben);
            }   
        }
        
            //Gombasz vizsgalat: Gombatestek, gombafonalak, 
            List<Gombatest> osszesGombatestek = new ArrayList<>();
            List<Gombafonal> osszesGombafonalak = new ArrayList<>();

            List<Gombatest> gombatestekAHashMapben = new ArrayList<>();
            List<Gombafonal> gombafonalakAHashMapben = new ArrayList<>();

            for(String kulcs : objektumok.keySet()) {
                if (kulcs.matches("gombatest\\d+")) {
                    Gombatest gombatest = (Gombatest)objektumok.get(kulcs);
                    gombatestekAHashMapben.add(gombatest);
                }
                if (kulcs.matches("gombafonal\\d+")) {
                    Gombafonal gombafonal = (Gombafonal)objektumok.get(kulcs);
                    gombafonalakAHashMapben.add(gombafonal);
                }
            }

            for (Gombasz gombasz : gombaszok) {
                Gombafaj gombaszFaja = gombasz.getGombafaj();
                List<Gombatest> fajGombatestei = gombaszFaja.getGombaTestek();
                List<Gombafonal> fajGombafonalai = gombaszFaja.getGombafonalhalozat();

                //Ha a Hashmapben nincs Gombatest, de a Gombasz listájában van-->hozzáadni a HashMaphez
                for (Gombatest fajGombateste : fajGombatestei) {
                    if (!gombatestekAHashMapben.contains(fajGombateste)) {
                        String ujGombatestNev = ujGombatestNev(); //gombatest neve
                        objektumok.put(ujGombatestNev, fajGombateste); //Gombatest neve alapjan mentjuk el a Gombatestet a Map-en
                        objektumokbolString.put(fajGombateste, ujGombatestNev);

                        GGombatest gGt = new GGombatest(getGombatestKepByFaj(gombaszFaja));
                        grafika.getGombatestek().put(fajGombateste, gGt);
                    }
                }
                //Ha a HashMapben van Gombatest, de a Gombasz listájában nincs-->törölni kell a HashMapből
                osszesGombatestek.addAll(fajGombatestei);


                //Ha a Hashmapben nincs Gombafonal, de a Gombasz listájában van-->hozzáadni a HashMaphez
                for (Gombafonal fajGombafonala : fajGombafonalai) {
                    if (!gombafonalakAHashMapben.contains(fajGombafonala)) {
                        String ujGombafonalNev = ujGombafonalNev(); //gombatest neve
                        objektumok.put(ujGombafonalNev, fajGombafonala); //Gombatest neve alapjan mentjuk el a Gombatestet a Map-en
                        objektumokbolString.put(fajGombafonala, ujGombafonalNev);

                        GGombafonal gGf = new GGombafonal(getColorKepByFaj(gombaszFaja));
                        grafika.getGombafonalak().put(fajGombafonala, gGf);
                    }
                }
                //Ha a HashMapben van Gombafonal, de a Gombasz listájában nincs-->törölni kell a HashMapből
                osszesGombafonalak.addAll(fajGombafonalai);
                

            }
            //Ha a HashMapben van Gombatest, de a Gombaszok listájában nincs-->törölni kell a HashMapből
            for(Gombatest hashMapGombatest : gombatestekAHashMapben) {
                if(!osszesGombatestek.contains(hashMapGombatest)){
                    //ha nincs benne a Gombaszok listájában, de a HashMapben igen.
                    objektumok.remove(objektumokbolString.get(hashMapGombatest)); //eltávolítjuk a HashMapből
                    objektumokbolString.remove(hashMapGombatest); //eltávolítjuk a HashMapből

                    //grafikus objektum eltávolítása a hashMapből
                    grafika.getGombatestek().remove(hashMapGombatest);
                }
            }

            //Ha a HashMapben van Gombafonal, de a Gombasz listájában nincs-->törölni kell a HashMapből
            for(Gombafonal hashmapGombafonal : gombafonalakAHashMapben) {
                if(!osszesGombafonalak.contains(hashmapGombafonal)){
                    //ha nincs benne a Gombaszok listájában, de a HashMapben igen.
                    objektumok.remove(objektumokbolString.get(hashmapGombafonal)); //eltávolítjuk a HashMapből
                    objektumokbolString.remove(hashmapGombafonal); //eltávolítjuk a HashMapből

                    //grafikus objektum eltávolítása a hashMapből
                    grafika.getGombafonalak().remove(hashmapGombafonal);
                }
            }


        //Jatekter vizsgalat: tektonok, sporak
        List<Tekton> tekotonokAJatekteren = jatekter.getTektonok();
        List<Spora> osszesSpora = new ArrayList<>();

        List<Tekton> tektonokAHashMapben = new ArrayList<>();
        List<Spora> sporakAHashMapben = new ArrayList<>();

        for(String kulcs : objektumok.keySet()) {
                if (kulcs.matches("tekton\\d+")) {
                    Tekton tekton = (Tekton)objektumok.get(kulcs);
                    tektonokAHashMapben.add(tekton);
                }else if (kulcs.matches("puritekton\\d+")) {
                    Tekton puriTekton = (Puritekton)objektumok.get(kulcs);
                    tektonokAHashMapben.add(puriTekton);
                }else if (kulcs.matches("disszolator\\d+")) {
                    Tekton disszolatorTekton = (Disszolator)objektumok.get(kulcs);
                    tektonokAHashMapben.add(disszolatorTekton);
                }else if (kulcs.matches("monotekton\\d+")) {
                    Tekton monoTekton = (Monotekton)objektumok.get(kulcs);
                    tektonokAHashMapben.add(monoTekton);
                }else if (kulcs.matches("infinator\\d+")) {
                    Tekton infiratorTekton = (Infinator)objektumok.get(kulcs);
                    tektonokAHashMapben.add(infiratorTekton);
                }

                if (kulcs.matches("osztodo\\d+")) {
                    Spora osztodoSpora = (Osztodo)objektumok.get(kulcs);
                    sporakAHashMapben.add(osztodoSpora);
                }else if (kulcs.matches("benito\\d+")) {
                    Spora benitoSpora = (Benito)objektumok.get(kulcs);
                    sporakAHashMapben.add(benitoSpora);
                }else if (kulcs.matches("vagaskeptelenito\\d+")) {
                    Spora vagaskeptelenitoSpora = (VagasKeptelenito)objektumok.get(kulcs);
                    sporakAHashMapben.add(vagaskeptelenitoSpora);
                }else if (kulcs.matches("lassito\\d+")) {
                    Spora lassiSpora = (Lassito)objektumok.get(kulcs);
                    sporakAHashMapben.add(lassiSpora);
                }else if (kulcs.matches("gyorsito\\d+")) {
                    Spora gyorsitSpora = (Gyorsito)objektumok.get(kulcs);
                    sporakAHashMapben.add(gyorsitSpora);
                }
            }

        for (Tekton tektonJatekteren : tekotonokAJatekteren) {
            List<Spora> sporak = tektonJatekteren.getSporak();
            osszesSpora.addAll(sporak);

            for (Spora spora : sporak) {
                if (!sporakAHashMapben.contains(spora)) {
                    //ha nincs benne a HashMap-ben, de a Tekton listájában igen.
                    String nev = spora.getClass().getSimpleName().toLowerCase();
                    char sporaTipus= nev.charAt(0); //spora tipus
                    String ujSporaNev = ujSporaNev(sporaTipus); //spora neve
                    objektumok.put(ujSporaNev, spora); //spora neve alapjan mentjuk el a Sporakat a Map-en
                    objektumokbolString.put(spora, ujSporaNev);

                    //graf osztaly létrehozasa
                    GSpora gSpora = new GSpora(getColorKepByFaj(spora.getGombafaj()));
                    grafika.getSporak().put(spora,gSpora);
                }
            }

            if(!tektonokAHashMapben.contains(tektonJatekteren)) {
                //ha nincs benne a HashMap-ben, de a Tekton listájában igen.
                String nev = tektonJatekteren.getClass().getSimpleName().toLowerCase();
                char tektonTipus = nev.charAt(0); //tekton tipus
                String ujTektonNev = ujTektonNev(tektonTipus); //tekton neve
                objektumok.put(ujTektonNev, tektonJatekteren); //tekton neve alapjan mentjuk el a Tektonokat a Map-en
                objektumokbolString.put(tektonJatekteren, ujTektonNev);

                //graf osztaly létrehozasa
                GTekton gTekton = new GTekton(getColorTektonByName(tektonTipus));
                grafika.getTektonok().put(tektonJatekteren,gTekton);
            }
        }
            //Ha a HashMapben van Spora, de az osszSpora listájában nincs-->törölni kell a HashMapből
            for(Spora hashmapSpora : sporakAHashMapben) {
                if(!osszesSpora.contains(hashmapSpora)){
                    //ha nincs benne a Tektonok listáiban, de a HashMapben igen.
                    objektumok.remove(objektumokbolString.get(hashmapSpora)); //eltávolítjuk a HashMapből
                    objektumokbolString.remove(hashmapSpora); //eltávolítjuk a HashMapből

                    //grafikus objektum eltávolítása a hashMapből
                    grafika.getSporak().remove(hashmapSpora);
                }
            }
            //System.out.println("Objektumok: " + objektumok.size() + "\n ObjektumokbolString: " + objektumokbolString.size() +"\n jatekter: " + jatekter.getTektonok().size() + "\n Rovaraszok: " + rovaraszok.size() + "\n Gombaszok: " + gombaszok.size() +"\n jatekosok: "+jatekosok.size() +"\n grafTekton: " + grafika.getTektonok().size());
    }

    /**
     * @brief Létrehoz egy új tektont a megadott típus alapján
     * @param tektonTipus A tekton típusa (t, m, d, p, i)
     * @return Az új Tekton objektum, vagy null hibás típus esetén
     */
    public Tekton tektonLetrehoz(char tektonTipus){
        switch (tektonTipus) {
            case 't':
                return new Tekton();
                
            case 'm':
                return new Monotekton();
                
            case 'd':
                return new Disszolator();
                
            case 'p':
                return new Puritekton();
                
            case 'i':
                return new Infinator();
                
            default:
                System.out.println("Nincs ilyen tipus: " + tektonTipus);
                break;
        }
        
        return null;
    }

    /**
     * @brief Létrehoz egy új spórat a megadott típus alapján
     * @param sporaTipus A spóra típusa (b, g, l, v, o)
     * @param gf A spórához tartozó gombafaj
     * @param mennyiseg A spóra mennyisége
     * @return Az új Spora objektum, vagy null hibás típus esetén
     */
    public Spora sporaLetrehoz(char sporaTipus, Gombafaj gf, int mennyiseg){
        switch (sporaTipus) {
            case 'b':
                return new Benito(mennyiseg, gf);
                
            case 'g':
                return new Gyorsito(mennyiseg, gf);
                
            case 'l':
                return new Lassito(mennyiseg, mennyiseg, gf);
                
            case 'v':
                return new VagasKeptelenito(mennyiseg, gf);
                
            case 'o':
                return new Osztodo(mennyiseg, gf);
                
            default:
                System.out.println("Nincs ilyen tipus: " + sporaTipus);
                break;
        }
        
        return null;
    }

    //arrange Parancsok
    
    //assert parancsok
    /**
     * @brief Kiírja egy gombafonal állapotát
     * @param output A kimeneti író
     * @param fonalNev A gombafonal neve
     */
    public void showFonalAllapot(PrintWriter output, String fonalNev){
        Gombafonal fonal = (Gombafonal)objektumok.get(fonalNev);
        if(fonal == null){
            output.println("Nem letezik: " + fonalNev);
            return;
        }
        switch (fonal.getAllapot()) {
            case Ep:
                output.println("Ep");
                break;
            case Haldoklo:
                output.println("Haldoklo");
                break;
            case UtolsoEsely:
                output.println("UtolsoEsely");
                break;
            default:
                break;
        }
    }
    
    /**
     * @brief Kilistázza az összes gombafonalat
     * @param output A kimeneti író
     */
    public void listGf(PrintWriter output){
        List<String> nevek = new ArrayList<>();
        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("gombafonal\\d+")) {
                nevek.add(kulcs);
            }
        }
        if(nevek.isEmpty()){
            output.println("Nincs gombafonal a palyan");
            return;
        }
        szamszeruSort(nevek);
        for(String nev : nevek) {
            output.println(nev);
        }
    }

    /**
     * @brief Kilistázza az összes gombatestet
     * @param output A kimeneti író
     */
    public void listGt(PrintWriter output){
        List<String> nevek = new ArrayList<>();
        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("gombatest\\d+")) {
                nevek.add(kulcs);
            }
        }
        if(nevek.isEmpty()){
            output.println("Nincsen gombatest a palyan");
            return;
        }
        szamszeruSort(nevek);
        for(String nev : nevek) {
            output.println(nev);
        }
    }

    /**
     * @brief Kilistázza az összes rovart és tartozkodasi helyet
     * @param output A kimeneti író
     */
    public void listRovarok(PrintWriter output){
        List<String> rovarNevek = new ArrayList<>();
        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("rovar\\d+")) {
                rovarNevek.add(kulcs);
            }
        }
        if(rovarNevek.isEmpty()){
            output.println("Nincs rovar a palyan");
            return;
        }
        
        szamszeruSort(rovarNevek);

        for(String nev : rovarNevek) {
            Rovar rovar = (Rovar)objektumok.get(nev);
            Tekton tartozkodas = rovar.getTartozkodas();
            String tektonNev = objektumokbolString.get(tartozkodas);

            output.println(nev+" "+tektonNev);
        }
    }

    /**
     * @brief Kilistázza az összes tektont
     * @param output A kimeneti író
     */
    public void listTektonok(PrintWriter output){
        List<String> nevek = new ArrayList<>();
        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("tekton\\d+") || kulcs.matches("monotekton\\d+") || kulcs.matches("puritekton\\d+") || kulcs.matches("disszolator\\d+") || kulcs.matches("infinator\\d+")) {
                nevek.add(kulcs);
            }
        }
        if(nevek.isEmpty()){
            return;
        }

        szamszeruSort(nevek);
        for(String nev : nevek) {
            output.println(nev);
        }
    }

    /**
     * @brief Kilistázza az összes spórát
     * @param output A kimeneti író
     */
    public void listSpora(PrintWriter output){
        List<String> benitonevek = new ArrayList<>();
        List<String> osztodonevek = new ArrayList<>();
        List<String> vagasKeptelenitonevek = new ArrayList<>();
        List<String> lassitonevek = new ArrayList<>();
        List<String> gyorsitonevek = new ArrayList<>();

        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("osztodo\\d+")) {
                osztodonevek.add(kulcs);
            }else if (kulcs.matches("benito\\d+")) {
                benitonevek.add(kulcs);
            }else if (kulcs.matches("vagasKeptelenito\\d+")) {
                vagasKeptelenitonevek.add(kulcs);
            }else if (kulcs.matches("lassito\\d+")) {
                lassitonevek.add(kulcs);
            }else if (kulcs.matches("gyorsito\\d+")) {
                gyorsitonevek.add(kulcs);
            }
        }
        if(osztodonevek.isEmpty() && benitonevek.isEmpty() && vagasKeptelenitonevek.isEmpty() && lassitonevek.isEmpty() && gyorsitonevek.isEmpty()){
            output.println("Nincs spora a palyan");
            return;
        }

        szamszeruSort(osztodonevek);
        szamszeruSort(benitonevek);
        szamszeruSort(vagasKeptelenitonevek);
        szamszeruSort(lassitonevek);
        szamszeruSort(gyorsitonevek);

        for(String nev : benitonevek) {
            Spora spora = (Benito)objektumok.get(nev);
            String tektonNev = melyikTektononVanRajtaASpora(spora);
            output.println(nev +" "+spora.getMennyiseg()+" "+tektonNev);
        }
        for(String nev : gyorsitonevek) {
            Spora spora = (Gyorsito)objektumok.get(nev);
            String tektonNev = melyikTektononVanRajtaASpora(spora);
            output.println(nev +" "+spora.getMennyiseg()+" "+tektonNev);        }
        for(String nev : lassitonevek) {
            Spora spora = (Lassito)objektumok.get(nev);
            String tektonNev = melyikTektononVanRajtaASpora(spora);
            output.println(nev +" "+spora.getMennyiseg()+" "+tektonNev);
        }
        for(String nev : osztodonevek) {
            Spora spora = (Osztodo)objektumok.get(nev);
            String tektonNev = melyikTektononVanRajtaASpora(spora);
            output.println(nev +" "+spora.getMennyiseg()+" "+tektonNev);
        }
        for(String nev : vagasKeptelenitonevek) {
            Spora spora = (VagasKeptelenito)objektumok.get(nev);
            String tektonNev = melyikTektononVanRajtaASpora(spora);
            output.println(nev +" "+spora.getMennyiseg()+" "+tektonNev);
        }
    }

    /**
     * @brief Megkeresi, hogy egy spóra melyik tektonon van rajta
     * @param spora A keresett spóra
     * @return A tekton neve, amely tartalmazza a spórát
     */
    private String melyikTektononVanRajtaASpora(Spora spora){
        String tetkonNev="";

        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("tekton\\d+")) {
                Tekton tekton = (Tekton)objektumok.get(kulcs);
                if(tekton.getSporak().contains(spora)){
                    tetkonNev = kulcs;
                    break;
                }
            }else if (kulcs.matches("disszolator\\d+")) {
                Tekton tekton = (Disszolator)objektumok.get(kulcs);
                if(tekton.getSporak().contains(spora)){
                    tetkonNev = kulcs;
                    break;
                }
            }else if (kulcs.matches("puritekton\\d+")) {
                Tekton tekton = (Puritekton)objektumok.get(kulcs);
                if(tekton.getSporak().contains(spora)){
                    tetkonNev = kulcs;
                    break;
                }
            }else if (kulcs.matches("monotekton\\d+")) {
                Tekton tekton = (Monotekton)objektumok.get(kulcs);
                if(tekton.getSporak().contains(spora)){
                    tetkonNev = kulcs;
                    break;
                }
                
            }else if (kulcs.matches("infinator\\d+")) {
                Tekton tekton = (Infinator)objektumok.get(kulcs);
                if(tekton.getSporak().contains(spora)){
                    tetkonNev = kulcs;
                    break;
                }
                
            }
        }

        return tetkonNev;
    }

    /**
     * @brief Kilistázza a játékosokat
     * @param output A kimeneti író
     */
    public void listJatekosok(PrintWriter output){
        List<String> nevek = new ArrayList<>();
        List<String> Rovarasznevek = new ArrayList<>();

        if(jatekosok.isEmpty()){
            return;
        }

        for(Rovarasz rovarasz : rovaraszok) {
            Rovarasznevek.add(rovarasz.getNev());
            nevek.add(rovarasz.getNev());
        }
        for(Gombasz gombasz : gombaszok) {
            nevek.add(gombasz.getNev());
        }

        Collections.sort(nevek);
        for(String nev : nevek) {
            if(Rovarasznevek.contains(nev)) {
                output.println(nev+" rovarasz");
            }else{
                output.println(nev+" gombasz");
            }
        }
    }

    /**
     * @brief Kilistázza a gombászokat
     * @param output A kimeneti író
     */
    public void listGombaszok(PrintWriter output){
        List<String> nevek = new ArrayList<>();
        for(Gombasz gombasz : gombaszok) {
            nevek.add(gombasz.getNev());
        }
        if(nevek.isEmpty()){
            return;
        }
        Collections.sort(nevek);
        for(String nev : nevek) {
            output.println("Gombasz "+nev);
        }
    }

    /**
     * @brief Kilistázza a rovarászokat
     * @param output A kimeneti író
     */
    public void listRovaraszok(PrintWriter output){
        List<String> nevek = new ArrayList<>();
        for(Rovarasz rovarasz : rovaraszok) {
            nevek.add(rovarasz.getNev());
        }
        if(nevek.isEmpty()){
            return;
        }
        Collections.sort(nevek);
        for(String nev : nevek) {
            output.println("Rovarasz "+nev);
        }
    }

    /**
     * @brief Kilistázza a egy tekton fonalakkal osszekotott szomszedait
     * @param output A kimeneti író
     * @param tektonNev A tekton neve
     */
    public void listGFSzomszedok(PrintWriter output, String tektonNev){
        List<String> nevek = new ArrayList<>();
       Tekton szurtTekton = parancsTektonCast(tektonNev.charAt(0), tektonNev);
       if(szurtTekton.getFonalak().isEmpty()){
            output.println("Nincs rajta gombafonal");
       }
        List<Gombafonal> fonalak = szurtTekton.getFonalak();
        for(Gombafonal fonal : fonalak) {
            if(!szurtTekton.equals(fonal.getVegpont1())) {
                nevek.add(objektumokbolString.get(fonal.getVegpont1()));

            }else if(!szurtTekton.equals(fonal.getVegpont2())) {
                nevek.add(objektumokbolString.get(fonal.getVegpont2()));
            }
        }

        for(String nev : nevek) {
            output.println("Szomszed "+nev);
        }
    }

    /**
     * @brief Kilistázza a tekton szomszédait
     * @param output A kimeneti író
     * @param tektonNev A tekton neve
     */
    public void listTektonSzomszedok(PrintWriter output, String tektonNev){
        List<String> nevek = new ArrayList<>();
        Tekton szurtTekton = parancsTektonCast(tektonNev.charAt(0), tektonNev);
        if(szurtTekton == null){
            output.println("Nem letezik "+tektonNev);
            return;
        }
        List<Tekton> szomszedok = szurtTekton.getSzomszedok();
        for(Tekton szomszed : szomszedok) {
            nevek.add(objektumokbolString.get(szomszed));
        }
        if(nevek.isEmpty()){
            return;
        }
        szamszeruSort(nevek);
        for(String nev : nevek) {
            output.println(nev);
        }

    }

    /**
     * @brief kiírja a rovar állapotát
     * @param output A kimeneti író
     * @param rovarNev A rovar neve
     */
    public void showRovarAllapot(PrintWriter output, String rovarNev){
        Rovar rovar = (Rovar)objektumok.get(rovarNev);
        if(rovar == null) {
            output.println("Nem letezik "+rovarNev);
            return;
        }

        switch (rovar.getAllapot()) {
            case Alap:
                 output.println("Alap hatas ervenyesul "+rovarNev +" rovarra");
                break;
            case Benitott:
                output.println("Benito hatas ervenyesul "+rovarNev +" rovarra");
                break;
            case VagasKeptelen:
                output.println("VagasKeptelenito hatas ervenyesul "+rovarNev +" rovarra");
                break;
            case Lassitott:
                output.println("Lasito hatas ervenyesul "+rovarNev +" rovarra");
                break;
            case Gyorsitott:
                output.println("Gyorsito hatas ervenyesul "+rovarNev +" rovarra");
                break;
            default:
                throw new AssertionError();
        }
       
    }

    /**
     * @brief Számok szerint rendezi a listát
     * @param nevek A rendezendő lista
     */
    public void szamszeruSort(List<String> nevek) {
        nevek.sort((a, b) -> {
            int numA = Integer.parseInt(a.replaceAll("\\D+", ""));
            int numB = Integer.parseInt(b.replaceAll("\\D+", ""));
            return Integer.compare(numA, numB);
        });
    }

    //csakis játék módban fut le, a játékosok felvételére szolgál.
    /**
     * @brief Játékosok felvétele a játék kezdetekor. Interaktíven kéri a játékosok adatait, és hozzáadja őket a játékhoz
     */
    public void jatekosokFelvetele() {
        //Jatekosok felvetele a jatekosok listájába.
        //Jatekosok felvetele a HashMap-be.
        //Jatekosok felvetele a gombaszok vagy rovaraszok listájába.
        boolean felvenni = true;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter systemOut = new PrintWriter(System.out, true);
        System.out.println("Jatekos felvetele: ");
        while (felvenni) { 
            try {
                String parancs = r.readLine();
                //játékos: Rovarász + 1Rovar létrehozása
                if (parancs.matches("/arrange -j \\S+ -r -t \\S+")) {
                    rovaraszFelvetel(parancs, systemOut);

                }else if (parancs.matches("/arrange -j \\S+ -g \\S+")) { //Gombasz + 1Gombatest létrehozása
                    gombaszFelvetelJatekKezdetekor(parancs, r);
                }

            } catch (Exception e) { //hibás játékos felvétel esetén kivételt kapunk
                System.out.println(e.getMessage());
            }

            System.out.println("Szeretne még egyszer játékost felvenni? (Igen/Nem)");
            try {
                String valasz = r.readLine();
                if (valasz.equalsIgnoreCase("nem")) {
                    felvenni = false;
                } else if (valasz.equalsIgnoreCase("igen")) {
                    felvenni = true;
                } else {
                    System.out.println("Hibás válasz, kérem válasszon: Igen/Nem");
                }
            } catch (Exception e) {
                System.out.println("Hiba a jatekosok felvetelekor: "+e.getMessage());
            }
        }

    }

    /**
     * @brief Gombász és egy gombatest felvétele a játék kezdetekor
     * @param parancs A felvételi parancs
     * @param r A bemeneti olvasó
     */
    public void gombaszFelvetelJatekKezdetekor(String parancs, BufferedReader r){
        //gombasz létrehozasa játék kezdetekor
                    String gombaszNev = parancs.split(" ")[2];
                    String gombafajSporaNev = parancs.split(" ")[4];
                    Gombafaj ujGombafaj = createGombafajBySpora(gombafajSporaNev.charAt(0));
                    
                        if (vanGombafajAzObjektumokban(gombafajSporaNev)) {
                        System.out.println("A"+gombafajSporaNev+" szerep mar foglalt. Valassz mast");
                        return;
                        }

                        Gombasz gombasz = new Gombasz(parancs.split(" ")[2],ujGombafaj);
                        if (objektumok.containsKey(gombaszNev)) {
                            System.out.println("Már van ilyen nevű játékos");
                            return;
                        }
                    
                    

                    objektumok.put(gombafajSporaNev, ujGombafaj);                               //Spora neve alapjan mentjuk el a gombafajt a Map-en
                    objektumokbolString.put(ujGombafaj, gombafajSporaNev);

                    gombaszok.add(gombasz);
                    jatekosok.add(gombasz);
                    objektumok.put(gombaszNev, gombasz);                               //Gombasz neve alapjan mentjuk el a gombaszt a Map-en
                    objektumokbolString.put(gombasz, gombaszNev); 

                    //1db gomba lehelyezése, parancsból
                    System.out.println("tegye le a kezdő Gombatestet egy tektonra paranccsal!");
                    String gombatestParancs="";
                    try {
                        gombatestParancs = r.readLine();

                    } catch (Exception e) {
                        System.out.println("Hiba a gombatest parancs beolvasásakor: "+e.getMessage());
                    }
                    

                    boolean siker = false;
                    boolean sikeresGombatest = false;
                    while(!siker)
                    {
                        try {
                            while(!gombatestParancs.matches("/arrange -gt \\S+ -t \\S+")) { 
                                System.out.println("Hibás parancs, kérem adja meg újra: /arrange -gt <gombafaj tipusa> -t <tekton neve>");
                                gombatestParancs = r.readLine();
                            }

                            
                            Tekton testHelye = parancsTektonCast(gombatestParancs.split(" ")[4].charAt(0), gombatestParancs.split(" ")[4]); //tekton helye
                            //ellenorzes hogy van-e a tektonon már gombatest
                            while (!sikeresGombatest) { 
                                testHelye = parancsTektonCast(gombatestParancs.split(" ")[4].charAt(0), gombatestParancs.split(" ")[4]);

                                for(String kulcs : objektumok.keySet()) {
                                    if (kulcs.matches("gombatest\\d+")) {
                                        Gombatest gombatest = (Gombatest)objektumok.get(kulcs);
                                        if(gombatest.getTekton().equals(testHelye)){
                                            System.out.println("Mar van gombatest a tektonon!");
                                            gombatestParancs = r.readLine();

                                            sikeresGombatest = false;
                                            break;
                                        }
                                    }
                                }
                                sikeresGombatest = true; //ha nem talalunk gombatestet a tektonon, akkor sikeres a gombatest elhelyezese
                            }
                            
                           
                            ujGombafaj.testNovesztes(testHelye, false);                                 //kivételt dobhat, ha foglalt a helye
                            //Gombatest ujGombatest = ujGombafaj.getGombaTestek().getLast();
                            Gombatest ujGombatest = ujGombafaj.getGombaTestek().get(ujGombafaj.getGombaTestek().size() - 1);
                            
                            String ujGombatestNev = ujGombatestNev();                                  //gombatest neve
                            objektumok.put(ujGombatestNev, ujGombatest);                               //Gombatest neve alapjan mentjuk el a gombatestet a Map-en
                            objektumokbolString.put(ujGombatest, ujGombatestNev);
                            siker = true; // sikeres elhelyezés
                            System.out.println("Hozzaadva "+ujGombatestNev);
                            
                        /*
                        String gombaTipus = gombatestParancs.split(" ")[2];
                        //char gombaTip = gombaTipus.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(gombaTipus);

                        if(gf == null){
                            System.out.println("Nem letezik: " + gombaTipus);
                            return;
                        }

                        String tekton1 = gombatestParancs.split(" ")[4];
                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);

                        if(Tekton1 == null){
                            System.out.println("Nem letezik: " + tekton1);
                            return;
                        }

                        Gombatest ujGombatest = new Gombatest(gf, Tekton1);
                        gf.addTest(ujGombatest);
                        String ujNev = ujGombatestNev();
                        objektumok.put(ujNev, ujGombatest);
                        objektumokbolString.put(ujGombatest, ujNev);

                        System.out.println("Hozzaadva " + ujNev);
                        siker = true; // sikeres elhelyezés
                        */
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
        System.out.println("Hozzaadva "+gombaszNev +" "+gombafajSporaNev+" gombasz");

    }

    /**
     * @brief Ellenőrzi, hogy egy gombafaj szerepel-e az objektumok között
     * @param gombafajNeve A gombafaj neve
     * @return true, ha a gombafaj már szerepel, false egyébként
     */
    public boolean vanGombafajAzObjektumokban(String gombafajNeve) {
        return objektumok.containsKey(gombafajNeve);
    }

   

    /**
     * @brief Létrehoz egy gombafajt a spóra típusa alapján
     * @param sporaTipus A spóra típusa (o, b, v, l, g)
     * @return Az új Gombafaj objektum
     */
    public Gombafaj createGombafajBySpora(char sporaTipus){
            String nev="";
            int termelesIdeje=0;
            int eddigNovesztettTestekSzama=0;
            int gombatestFejlettsegIdo=0;
            int gombatestEletSzama=0;
            int gombafonalEletSzama=0;
         switch (sporaTipus) {
                        case 'o':
                            nev = "Foltos püffeteg";
                            termelesIdeje = 4;
                            eddigNovesztettTestekSzama = 0;
                            gombatestFejlettsegIdo = 2;
                            gombatestEletSzama = 3;
                            gombafonalEletSzama = 3;
                            break;
                        case 'b':
                            nev = "Légyölő galóca";
                            termelesIdeje = 4;
                            eddigNovesztettTestekSzama = 0;
                            gombatestFejlettsegIdo = 2;
                            gombatestEletSzama = 3;
                            gombafonalEletSzama = 4;
                            break;

                        case 'v':
                            nev = "Szegfűgomba";
                            termelesIdeje = 3;
                            eddigNovesztettTestekSzama = 0;
                            gombatestFejlettsegIdo = 2;
                            gombatestEletSzama = 4;
                            gombafonalEletSzama = 3;
                            break;

                        case 'l':
                            nev= "Vargánya gomba";
                            termelesIdeje = 2;
                            eddigNovesztettTestekSzama = 0;
                            gombatestFejlettsegIdo = 3;
                            gombatestEletSzama = 6;
                            gombafonalEletSzama = 2;
                            break;

                        case 'g':
                            nev= "Csiperke gomba";
                            termelesIdeje = 2;
                            eddigNovesztettTestekSzama = 0;
                            gombatestFejlettsegIdo = 3;
                            gombatestEletSzama = 6;
                            gombafonalEletSzama = 2;
                            break;

                        default:
                            System.out.println("Nincs ilyen gombafaj: " + sporaTipus);
                            break;
        }
        return new Gombafaj(nev, termelesIdeje, eddigNovesztettTestekSzama, gombatestFejlettsegIdo, gombatestEletSzama, gombafonalEletSzama);
    }

    public Spora parancsSporaCast(char sporaTipus, String sporaNeve) {
        switch (sporaTipus) {
                        case 'o':
                            return (Osztodo)objektumok.get(sporaNeve);
                            
                        case 'b':
                            return (Benito)objektumok.get(sporaNeve);
                            
                        case 'v':
                            return (VagasKeptelenito)objektumok.get(sporaNeve);
                            
                        case 'l':
                            return (Lassito)objektumok.get(sporaNeve);
                            
                        case 'g':
                            return (Gyorsito)objektumok.get(sporaNeve);
                            
                        default:
                            System.out.println("Nincs ilyen spora: " + sporaNeve);
                            break;
        }
        return null;
    }

    /**
     * @brief Gombász felvétele
     * @param parancs A felvételi parancs
     * @param output A kimeneti író
     * @throws Exception Ha hiba történik a felvétel során
     */
    public void gombaszFelvetele(String parancs, PrintWriter output) throws Exception{
        String gombaszNev = parancs.split(" ")[2];
        String gombafajSporaNev = parancs.split(" ")[4];
        Gombafaj ujGombafaj = createGombafajBySpora(gombafajSporaNev.charAt(0));
        if (vanGombafajAzObjektumokban(gombafajSporaNev)) {
            output.println("A "+gombafajSporaNev+" szerep mar foglalt. Valassz mast");
            return;
        }

        Gombasz gombasz = new Gombasz(parancs.split(" ")[2],ujGombafaj);
        if (objektumok.containsKey(gombaszNev)) {
            output.println("Mar van ilyen nevu jatekos");
            return;
        }

        objektumok.put(gombafajSporaNev, ujGombafaj);                               //Spora neve alapjan mentjuk el a gombafajt a Map-en
        objektumokbolString.put(ujGombafaj, gombafajSporaNev);

        gombaszok.add(gombasz);
        jatekosok.add(gombasz);
        objektumok.put(gombaszNev, gombasz);                               //Gombasz neve alapjan mentjuk el a gombaszt a Map-en
        objektumokbolString.put(gombasz, gombaszNev); 
        output.println("Hozzaadva "+gombaszNev +" "+gombafajSporaNev+" gombasz");
    }

    /**
     * @brief Rovarász felvétele
     * @param parancs A felvételi parancs
     * @param output A kimeneti író
     */
    public void rovaraszFelvetel(String parancs, PrintWriter output){
        Rovarasz rovarasz = new Rovarasz(parancs.split(" ")[2]);
        if (objektumok.containsKey(rovarasz.getNev())) {
            output.println("Mar van ilyen nevu jatekos");
        }
        jatekosok.add(rovarasz);
        rovaraszok.add(rovarasz);
        objektumok.put(rovarasz.getNev(), rovarasz);
        objektumokbolString.put(rovarasz, rovarasz.getNev());

        //Rovar léterhozása
        String tektonNeve = parancs.split(" ")[5];
        char tektonTipus = tektonNeve.charAt(0);
        Tekton tartozkodas = parancsTektonCast(tektonTipus, tektonNeve);
        Rovar ujRovar = new Rovar(tartozkodas,rovarasz);

        //Rovar kezelése
        rovarasz.addRovar(ujRovar);
        String ujRovarNev = ujRovarNev(); //rovar neve
        objektumok.put(ujRovarNev, ujRovar);
        objektumokbolString.put(ujRovar, ujRovarNev);

        output.println("Hozzaadva "+rovarasz.getNev() +" rovarasz "+ujRovarNev);
    }

    /**
     * @brief Generál egy új gombatest nevet
     * @return Az új gombatest neve
     */
    public String ujGombatestNev(){
        int maxGtSzam = 0;
        for (String kulcs : objektumok.keySet()) {
            if (kulcs.matches("gombatest\\d+")) {
                String szamResz = kulcs.substring(9); // levágjuk a "gombatest"-t
                int szam = Integer.parseInt(szamResz);
                if (szam > maxGtSzam) {
                    maxGtSzam = szam;
                }
            }
        }
        return "gombatest" + (maxGtSzam + 1); // új név a következő Gombatesthez
    }    

    /**
     * @brief Generál egy új rovar nevet
     * @return Az új rovar neve
     */
    public String ujRovarNev(){
        int maxRovarSzam = 0;
        for (String kulcs : objektumok.keySet()) {
            if (kulcs.matches("rovar\\d+")) {
                //String szamResz = kulcs.substring(1); // levágjuk az "r"-t
                String szamResz = kulcs.substring(5); // levágjuk az "r"-t
                int szam = Integer.parseInt(szamResz);
                if (szam > maxRovarSzam) {
                    maxRovarSzam = szam;
                }
            }
        }
        return "rovar" + (maxRovarSzam + 1); // új név a következő Rovarhoz
    }

    //névből és típusból kinyeri a HashMap-ből a tektont, majd cast-olja a megfelelő típusra.
    /**
     * @brief Név és típus alapján visszaad egy tektont a HashMap-ből
     * @param tektonTipus A tekton típusa (t, m, d, p, i)
     * @param tektonNeve A tekton neve
     * @return A Tekton objektum, vagy null ha nem található
     */
    public Tekton parancsTektonCast(char tektonTipus, String tektonNeve) {
        switch (tektonTipus) {
                        case 't':
                            return (Tekton)objektumok.get(tektonNeve);
                            
                        case 'm':
                            return (Monotekton)objektumok.get(tektonNeve);
                            
                        case 'd':
                            return (Disszolator)objektumok.get(tektonNeve);
                            
                        case 'p':
                            return (Puritekton)objektumok.get(tektonNeve);
                            
                        case 'i':
                            return (Infinator)objektumok.get(tektonNeve);
                            
                        default:
                            System.out.println("Nincs ilyen tekton: " + tektonNeve);
                            break;
        }
        return null;
    }

    /**
     * @brief A program fő belépési pontja
     * @param args Parancssori argumentumok
     * @details Kezeli a program módját (test/játék), és indítja el a megfelelő műveleteket
     */
    public static void main(String[] args){
            //kiválasztja a program módját. 
            //test/jatek mód kiválasztása
            //test: elindítja a tesztekInditasa() metódust, lefuttatja a teszteket.
            //tesztek feltöltik a HashMap-et a szükséges objektumokkal minden teszt esetén.

            //jatek: jatekosok felvetele. Nagy pálya betöltése
            //létrehozza a jatekosokat, a jatekteret, a jatekosok listáját act/arrange parancsokkal.
            //megkérdezi hogy akar-e még játékost felvenni? Nem-->elindítja a ...
            //elindítja a jatekInditasa() metódust, elindítja a játékot

            /*Parancskezelok parancskezelo = new Parancskezelok();
            Jatek jatek = new Jatek(parancskezelo, parancskezelo.getJatekter());
            
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            String mode= "";
            boolean helyesMode= false;
        while(!helyesMode){
                try {
                    mode= r.readLine();
                } catch (Exception e) {
                    System.err.println("Hiba a main mode beolvasásában: "+e.getMessage());
                }
            
            
            
            switch (mode) {
                case "/mode -test": 
                    System.out.println("Testmode bekapcsolva");
                    helyesMode = true;
                    parancskezelo.setParancsAllapot(parancsAllapot.Test);
                    jatek.tesztekInditasa(parancskezelo.getJatekosok());
                    break;
                case "/mode -game":
                    System.out.println("Jatekmode bekapcsolva");
                    helyesMode = true;

                    //pálya alkotás
                    parancskezelo.setParancsAllapot(parancsAllapot.Test);
                    jatek.jatekPalyaAlkotasa();
                    System.out.println("Pálya elkészült!");
                    parancskezelo.setParancsAllapot(parancsAllapot.Game);

                    //játékosok felvétele
                    parancskezelo.setParancsAllapot(parancsAllapot.Game);
                    parancskezelo.jatekosokFelvetele();
                    jatek.jatekInditasa(parancskezelo.getJatekosok(), parancskezelo.getGombaszok(), parancskezelo.getRovaraszok());
                    break;
                default:
                    System.out.println("Nincs ilyen mód: " + mode);
                }
        }
            */
            //Parancskezelok pk=new Parancskezelok();
            //Jatekter jatekter=new Jatekter();
            //Jatek jt=new Jatek(pk,jatekter);
            
            /*SwingUtilities.invokeLater(()->{
                //JatekAblak jatek=new JatekAblak(pk,jt);
                //jatek.setVisible(true);
                NevFajSzin nfsz=new NevFajSzin(pk,2,3,jt);
                nfsz.setVisible(true);
            });*/
            SwingUtilities.invokeLater(()->{
                //JatekAblak jatek=new JatekAblak(pk,jt);
                //jatek.setVisible(true);
                //NevFajSzin nfsz=new NevFajSzin(pk,2,3,jt);
                //nfsz.setVisible(true);
                JatekInditasaAblak jti=new JatekInditasaAblak();
                jti.setVisible(true);
            });
    }
}
