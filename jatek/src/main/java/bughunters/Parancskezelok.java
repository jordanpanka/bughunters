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

                    switch (action) {
                    case :
                        
                        break;
                    default:
                        throw new Exception("Nincs ilyen akció: " + action);
                    }
                }else if (allapot == parancsAllapot.Test) {
                    //teszteléshez szükséges akciók - minden parancs

                    switch (action) {
                    case :
                        
                        break;
                    default:
                        throw new Exception("Nincs ilyen akció: " + action);
                    }
                }
            } catch (Exception e) {
                throw e;
            }

            
    }

    //csakis játék módban fut le, a játékosok felvételére szolgál.
    public void jatekosokFelvetele() {
        //Jatekosok felvetele a jatekosok listájába.
        //Jatekosok felvetele a jatekterre.
        //Jatekosok felvetele a HashMap-be.
        //Jatekosok felvetele a gombaszok és rovaraszok listájába.
        boolean felvenni = true;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Jatekos felvetele: ");
        while (felvenni) { 
            try {
                String parancs = r.readLine();
                if (parancs.matches("/arrange -j \\S+ -r -t \\S+")) {
                    
                }else if (parancs.matches("/arrange -j \\S+ -g \\S+")) {
                    //gombasz létrehozasa

                    //1db gomba lehelyezése, parancsból
                }

            } catch (Exception e) {
                System.err.println("Hiba a jatekosok felvetelekor: "+e.getMessage());
            }

            System.out.println("Szeretne még egy játékost felvenni? (Igen/Nem)");
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
                    helyesMode = true;
                    parancskezelo.setParancsAllapot(parancsAllapot.Test);
                    jatek.tesztekInditasa();
                    break;
                case "-game":
                    //játékosok felvétele
                    helyesMode = true;
                    parancskezelo.setParancsAllapot(parancsAllapot.Game);
                    jatek.jatekInditasa(parancskezelo.getJatekosok(), parancskezelo.getGombaszok(), parancskezelo.getRovaraszok());
                    break;
                default:
                    System.out.println("Nincs ilyen mód: " + mode);
                }
        }
            
    }
}
