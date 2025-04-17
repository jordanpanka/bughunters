package bughunters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

enum parancsAllapot {
    Game,               // Játék állapotban van a parancskezelő, nem fér hozzá Arrange parancsokhoz.
    Test                // Teszt állapotban van a parancskezelő, minden parancshoz hozzáfér.
}


public class Parancskezelok {
    private List<Jatekos> jatekosok;
    private List<Gombasz> gombaszok;
    private List<Rovarasz> rovaraszok;
    private HashMap<String, Object> objektumok;
    private HashMap<Object,String> objektumokbolString;
    private Jatekter jatekter;
    private parancsAllapot allapot;

    Parancskezelok() {
        this.objektumok = new HashMap<>();
        this.objektumokbolString = new HashMap<>();
        this.jatekosok = new ArrayList<>();
        this.gombaszok = new ArrayList<>();
        this.rovaraszok = new ArrayList<>();
        this.jatekter = new Jatekter();
        
    }

    public void setParancsAllapot(parancsAllapot allapot) {
        this.allapot = allapot;
    }
    public parancsAllapot getParancsAllapot() {
        return allapot;
    }

    public Jatekter getJatekter() {
        return jatekter;
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


    //A játékosok a játék indítása után nem férhetnek hozzá Arrange parancsokhoz, de a tesztek mindenhez hozzáférnek
    public void bemenetAkcio(String action, Jatekos aktivJatekos) throws Exception {
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

                    
                }else if (allapot == parancsAllapot.Test) {
                    //teszteléshez szükséges akciók - minden parancs

                   
                }
            } catch (Exception e) {
                throw e;
            }

            
    }

    //csakis játék módban fut le, a játékosok felvételére szolgál.
    public void jatekosokFelvetele() {
        //Jatekosok felvetele a jatekosok listájába.
        //Jatekosok felvetele a HashMap-be.
        //Jatekosok felvetele a gombaszok vagy rovaraszok listájába.
        boolean felvenni = true;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Jatekos felvetele: ");
        while (felvenni) { 
            try {
                String parancs = r.readLine();
                //játékos: Rovarász + 1Rovar létrehozása
                if (parancs.matches("/arrange -j \\S+ -r -t \\S+")) {
                    rovaraszFelvetel(parancs);

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

    public void gombaszFelvetelJatekKezdetekor(String parancs, BufferedReader r) throws Exception{
        //gombasz létrehozasa játék kezdetekor
                    String gombaszNev = parancs.split(" ")[2];
                    String gombafajSporaNev = parancs.split(" ")[4];
                    Gombafaj ujGombafaj = createGombafajBySpora(gombafajSporaNev.charAt(0));
                    if (vanGombafajAzObjektumokban(gombafajSporaNev)) {
                        throw new Exception("A"+gombafajSporaNev+" szerep mar foglalt. Valassz mast");
                    }

                    Gombasz gombasz = new Gombasz(parancs.split(" ")[2],ujGombafaj);
                    if (objektumok.containsKey(gombaszNev)) {
                        throw new Exception("Már van ilyen nevu jatekos");
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
                    while(!siker)
                    {
                        try {
                            while(!gombatestParancs.matches("/arrange -gt \\S+ -t \\S+")) { 
                                System.out.println("Hibás parancs, kérem adja meg újra: /arrange -gt <gombafaj tipusa> -t <tekton neve>");
                                gombatestParancs = r.readLine();
                            }

                            Tekton testHelye = parancsTektonCast(gombatestParancs.split(" ")[4].charAt(0), gombatestParancs.split(" ")[4]);
                            ujGombafaj.testNovesztes(testHelye, false);                                 //kivételt dobhat, ha foglalt a helye
                            Gombatest ujGombatest = ujGombafaj.getGombaTestek().getLast();
                            String ujGombatestNev = ujGombatestNev();                                  //gombatest neve
                            objektumok.put(ujGombatestNev, ujGombatest);                               //Gombatest neve alapjan mentjuk el a gombatestet a Map-en
                            objektumokbolString.put(ujGombatest, ujGombatestNev);
                            siker = true; // sikeres elhelyezés
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
    }

    public boolean vanGombafajAzObjektumokban(String gombafajNeve) {
        return objektumok.containsKey(gombafajNeve);
    }

    public void createGombatestByGombafaj(String parancs){
        String tektonNeve= parancs.split(" ")[4];
        String gombafajNeve = parancs.split(" ")[2];

    }

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

                        case 'b':
                            nev = "Légyölő galóca";
                            termelesIdeje = 4;
                            eddigNovesztettTestekSzama = 0;
                            gombatestFejlettsegIdo = 2;
                            gombatestEletSzama = 3;
                            gombafonalEletSzama = 4;

                        case 'v':
                            nev = " Szegfűgomba";
                            termelesIdeje = 3;
                            eddigNovesztettTestekSzama = 0;
                            gombatestFejlettsegIdo = 2;
                            gombatestEletSzama = 4;
                            gombafonalEletSzama = 3;
                            
                        case 'l':
                            nev= "Vargánya gomba";
                            termelesIdeje = 2;
                            eddigNovesztettTestekSzama = 0;
                            gombatestFejlettsegIdo = 3;
                            gombatestEletSzama = 6;
                            gombafonalEletSzama = 2;
                            
                        case 'g':
                            nev= "Csiperke gomba";
                            termelesIdeje = 2;
                            eddigNovesztettTestekSzama = 0;
                            gombatestFejlettsegIdo = 3;
                            gombatestEletSzama = 6;
                            gombafonalEletSzama = 2;
                            
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
                            System.out.println("Nincs ilyen tekton: " + sporaNeve);
                            break;
        }
        return null;
    }

    public void rovaraszFelvetel(String parancs) throws Exception {
        Rovarasz rovarasz = new Rovarasz(parancs.split(" ")[2]);
        if (objektumok.containsKey(rovarasz.getNev())) {
            throw new Exception("A " + rovarasz.getNev() + " név már foglalt.");
        }
        jatekosok.add(rovarasz);
        rovaraszok.add(rovarasz);
        objektumok.put(rovarasz.getNev(), rovarasz);
        objektumokbolString.put(rovarasz, rovarasz.getNev());

        //Rovar léterhozása
        String tektonNeve = parancs.split(" ")[4];
        char tektonTipus = tektonNeve.charAt(0);
        Tekton tartozkodas = parancsTektonCast(tektonTipus, tektonNeve);
        Rovar ujRovar = new Rovar(tartozkodas,rovarasz);

        //Rovar kezelése
        rovarasz.addRovar(ujRovar);
        String ujRovarNev = ujRovarNev(); //rovar neve
        objektumok.put(ujRovarNev, ujRovar);
        objektumokbolString.put(ujRovar, ujRovarNev);
    }

    public String ujGombatestNev(){
        int maxGtSzam = 0;
        for (String kulcs : objektumok.keySet()) {
            if (kulcs.matches("gt\\d+")) {
                String szamResz = kulcs.substring(2); // levágjuk a "gt"-t
                int szam = Integer.parseInt(szamResz);
                if (szam > maxGtSzam) {
                    maxGtSzam = szam;
                }
            }
        }
        return "gt" + (maxGtSzam + 1); // új név a következő Gombatesthez
    }    

    public String ujRovarNev(){
        int maxRovarSzam = 0;
        for (String kulcs : objektumok.keySet()) {
            if (kulcs.matches("r\\d+")) {
                String szamResz = kulcs.substring(1); // levágjuk az "r"-t
                int szam = Integer.parseInt(szamResz);
                if (szam > maxRovarSzam) {
                    maxRovarSzam = szam;
                }
            }
        }
        return "r" + (maxRovarSzam + 1); // új név a következő Rovarhoz
    }

    //névből és típusból kinyeri a HashMap-ből a tektont, majd cast-olja a megfelelő típusra.
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

    //assert + arrange Parancsokként metódusok maybee
    
    public static void main(String[] args){
            //kiválasztja a program módját. 
            //test/jatek mód kiválasztása
            //test: elindítja a tesztekInditasa() metódust, lefuttatja a teszteket.
            //tesztek feltöltik a HashMap-et a szükséges objektumokkal minden teszt esetén.

            //jatek: jatekosok felvetele. Nagy pálya betöltése
            //létrehozza a jatekosokat, a jatekteret, a jatekosok listáját act/arrange parancsokkal.
            //megkérdezi hogy akar-e még játékost felvenni? Nem-->elindítja a ...
            //elindítja a jatekInditasa() metódust, elindítja a játékot

            Parancskezelok parancskezelo = new Parancskezelok();
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
                case "-test": 
                    System.out.println("Testmode bekapcsolva");
                    helyesMode = true;
                    parancskezelo.setParancsAllapot(parancsAllapot.Test);
                    jatek.tesztekInditasa();
                    break;
                case "-game":
                    System.out.println("Jatekmode bekapcsolva");
                    helyesMode = true;

                    //pálya alkotás
                    parancskezelo.setParancsAllapot(parancsAllapot.Test);
                    jatek.jatekPalyaAlkotasa();
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
            
    }
}
