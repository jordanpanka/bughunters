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

    public void bemenetAkcio(String action){

    }

    public void jatekInditasa(){
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println(kerdes);
        String parancs= "";

        while (true) { 
            //Egy kör a játékban, minden játékos cselekszik
            for (Jatekos jatekos : jatekosok) {
                
                //Jatekos köre, amíg van akciópontja
                while(jatekos.getakcioSzama() > 0){
                    try {
                        parancs = r.readLine();
                        parancs = parancs.toLowerCase();
                        bemenetAkcio(parancs);
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

            //lastChance futtatása
            for (Gombasz gombasz : gombaszok) {
                gombasz.lastChanceRun();
            }

            //Rovar állapot idejének növelése
            for (Rovarasz rovarasz : rovaraszok) {
                rovarasz.rovarokAllapotIdejenekNovelese();
            }

            //Gombatestek sporaraktárának növelése
            
            //Gombafonal "miota" növelése
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
        
        korVegiCselekedetek();
    }
    
    //Why is this here??
    public static void main(){

    }
}
