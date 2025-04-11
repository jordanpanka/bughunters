package bughunters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Jatek {
    private List<Jatekos> jatekosok;
    private List<Gombasz> gombaszok;
    private List<Rovarasz> rovaraszok;
    private Jatekter jatekter;
    private int korSzam;
    private HashMap<String, Object> objektumok;

    Jatek() {
        this.korSzam = 1;
        this.objektumok = new HashMap<>();
        this.jatekosok = new ArrayList<>();
        this.gombaszok = new ArrayList<>();
        this.rovaraszok = new ArrayList<>();
        this.jatekter = new Jatekter();
    }

    public int getKorSzam() {
        return korSzam;
    }

    public void setKorSzam(int korSzam) {
        this.korSzam = korSzam;
    }

    public List<Jatekos> getJatekosok() {
        return jatekosok;
    }

    public void setJatekosok(List<Jatekos> jatekosok) {
        this.jatekosok = jatekosok;
    }

    public void bemenetAkcio(String action, Jatekos aktivJatekos){
            //megkapja a bemeneti stringet, azalapján eldönti melyik akció fut le.
            // (likelihood ellenőrzés) %-r eves%
            //elindul az akció.
            //akció lehet create() is, ebben az esetben HashMap-be belekerülnek az új objektumok.
            //create() féle akció nem csak constructor, de mondjuk osztodas(), gombatest novesztes esetén is lehetséges.
            //Kinyeri a fontos objektumokat név/string alapján a HashMapből
            //végrehajtja az akciót a kinyert objektumokkal.
    }

    public void tesztekInditasa(){
            //Minden, a Teszt mappában lévő tesztet lefuttatja.
            //Teszt mappában, Test1, Test2 ... névvel ellátott mappában lesznek elhelyezve a különböző tesztek fájlai.
            //Teszt1 mappa tartalma: arrange.txt, act.txt, assert.txt 
            //tesztek feltöltik a HashMap-et a szükséges objektumokkal minden teszt esetén az arrange.txt-ből.
            //act.txt alapján végrehajtja a tesztesetet bemenetAkcio() metódussal, itt nem fogyhatnak el az akcióPontok, jatekos.setAkciopont(3) minden sikeres parancs után.
            //eredmenyeket kiírja actual.txt-be. 
            //Tester külön cmd-ben összehasonlítja az actual.txt tartalmát az assert.txt tartalmával.
    }

    public void jatekInditasa(){
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String parancs= "";

        while (true) { 
            //Egy kör a játékban, minden játékos cselekszik
            for (Jatekos jatekos : jatekosok) {
                
                //Jatekos köre, amíg van akciópontja
                while(jatekos.getakcioSzama() > 0){
                    try {
                        parancs = r.readLine();
                        parancs = parancs.toLowerCase();
                        bemenetAkcio(parancs, jatekos);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                
            }
            
            //Kör végén minden játékos akciópontja visszaáll 3-ra
            try {
                korVegiCselekedetek();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void korVegiCselekedetek(){
        try{
            //kör számláló növelése
            korSzam++;

            //kör végén minden játékos akciópontja visszaáll 3-ra
            for (Jatekos jatekos : jatekosok) {
                jatekos.akciopontAlapbaallit();
            }

            //töres mind 5.ik körben egy egyre növekvő random számmal
            if (korSzam % 5 == 0) {
                jatekter.tores(randomSzamToreshez());
            }

            //Rovar állapot idejének növelése
            for (Rovarasz rovarasz : rovaraszok) {
                rovarasz.rovarokAllapotIdejenekNovelese();
                rovarasz.rovarokAlapallapotbaHelyezese();
            }

            //lastChance futtatása
            //Gombatestek sporaraktárának növelése
            //Gombafonal "miota" növelése
            for (Gombasz gombasz : gombaszok) {
                gombasz.korVegiCselekedetekRun();            
            }
            
        }
        catch (Exception e){
            throw e;        
        }
    }

    // random szám generálás a töréshez a körszám alapján egyre növekvő tartományban.
    private int randomSzamToreshez() {
        Random random = new Random();
        int max = korSzam / 5; // Scale the range based on korSzam
        return random.nextInt(max) + 1; // Generate a number between 1 and max (inclusive)
    }

    //incomplite i think
    public void skipTurn(){
        //egy teljes kör kihagyása, minden játékos köre kihagyásra kerül, a kör végi cselekedetek végrehajtódnak.
        korVegiCselekedetek();
    }
    
    //Why is this here??
    public static void main(){
            //kiválasztja a program módját. 
            //test/jatek mód kiválasztása
            //test: elindítja a tesztekInditasa() metódust, lefuttatja a teszteket.
            //tesztek feltöltik a HashMap-et a szükséges objektumokkal minden teszt esetén.

            //jatek: jatekosok felvetele. Nagy pálya betöltése
            //létrehozza a jatekosokat, a jatekteret, a jatekosok listáját act/arrange parancsokkal.
            //megkérdezi hogy akar-e még játékost felvenni? Nem-->elindítja a ...
            //elindítja a jatekInditasa() metódust, elindítja a játékot
    }
}
