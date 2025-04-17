package bughunters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class Jatek {
    
    private int korSzam;
    private Jatekter jatekter;
    private Parancskezelok parancskezelo;

    Jatek(Parancskezelok kapottParancskezelo, Jatekter kapottJatekter)  {
        this.korSzam = 1;
        this.parancskezelo = kapottParancskezelo;
        this.jatekter = kapottJatekter;
    }

    public int getKorSzam() {
        return korSzam;
    }

    public void setKorSzam(int korSzam) {
        this.korSzam = korSzam;
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

    public void jatekInditasa(List<Jatekos> jatekosok, List<Gombasz> gombaszok, List<Rovarasz> rovaraszok) {
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
                        parancskezelo.bemenetAkcio(parancs, jatekos);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                
            }
            
            //Kör végén minden játékos akciópontja visszaáll 3-ra
            try {
                korVegiCselekedetek(jatekosok, gombaszok, rovaraszok);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    //Nagy Uniform pálya a játékhoz, egy txt-ből beolvasása.
    public void jatekPalyaAlkotasa(){
            
    }

    public void korVegiCselekedetek(List<Jatekos> jatekosok, List<Gombasz> gombaszok, List<Rovarasz> rovaraszok){
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
    public void skipTurn(List<Jatekos> jatekosok, List<Gombasz> gombaszok, List<Rovarasz> rovaraszok){
        //egy teljes kör kihagyása, minden játékos köre kihagyásra kerül, a kör végi cselekedetek végrehajtódnak.
        korVegiCselekedetek(jatekosok, gombaszok, rovaraszok);
    }
}
