package bughunters;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void tesztekInditasa(List<Jatekos> jatekosok){
            //Minden, a Teszt mappában lévő tesztet lefuttatja.
            //Teszt mappában, Test1, Test2 ... névvel ellátott mappában lesznek elhelyezve a különböző tesztek fájlai.
            //Teszt1 mappa tartalma: arrange.txt, act.txt, assert.txt 
            //tesztek feltöltik a HashMap-et a szükséges objektumokkal minden teszt esetén az arrange.txt-ből.
            //act.txt alapján végrehajtja a tesztesetet bemenetAkcio() metódussal, 
            //itt nem fogyhatnak el az akcióPontok, jatekos.setAkciopont(3) minden sikeres parancs után.
            //eredmenyeket kiírja actual.txt-be. 
            //Tester külön cmd-ben összehasonlítja az actual.txt tartalmát az assert.txt tartalmával.
            
            try {
                Path tesztekMappaPath = Paths.get("tesztek").toAbsolutePath();
                
                File tesztekMappa = tesztekMappaPath.toFile();
                if (!tesztekMappa.exists()) {
                    System.out.println("A tesztek mappa nem található.");
                    return;
                }

                File[] tesztKatalogus = tesztekMappa.listFiles(File::isDirectory);
                if (tesztKatalogus == null) return;

                
                for(File teszt : tesztKatalogus){
                    System.out.println("Fut: " + teszt.getName());
                    parancskezelo.renewProject();
                    
                    File inputFajl = new File(teszt, "input.txt");
                    File outputFajl = new File(teszt, "actualOutput.txt"); 

                    BufferedReader r = new BufferedReader(new FileReader(inputFajl));
                    PrintWriter writer = new PrintWriter(new FileWriter(outputFajl));

                    String parancs;
                    Boolean elsoActLezajlott=false;
                    //parancskezelo.setAktivJatekos(jatekosok.get(0)); // Teszteléshez az első játékost állítjuk be aktívnak a legelején, amit parancsal lehet változtatni
                    while ((parancs = r.readLine()) != null) {
                        //ha elérünk az első /act parancshoz és csak 1 játékos van, akkor ő lesz az aktiv jatekos
                        if(parancs.matches("^/act .*") && jatekosok.size()==1 && !elsoActLezajlott) {
                                parancskezelo.setAktivJatekos(jatekosok.get(0));
                                elsoActLezajlott=true;
                        }
                        parancskezelo.bemenetAkcio(parancs, writer);
                        
                        //minden parancs utan 3-ra noveljuk az akciopontot a teszteknel
                        for (Jatekos jatekos : jatekosok) {
                            jatekos.akciopontAlapbaallit();
                        }
                    }

                    r.close();
                    writer.close();
                    // Fájl tartalom visszaolvasása és vágása
                        String tartalom = new String(Files.readAllBytes(outputFajl.toPath()));
                        tartalom = tartalom.trim(); // Ez levágja az utolsó \n-t is, ha van

                        // Újraírjuk a fájlt a trimmelt tartalommal
                        Files.write(outputFajl.toPath(), tartalom.getBytes());

                   
                }

                //Nincsenek körök kezelve egyáltalán. 
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
    }

    public void jatekInditasa(List<Jatekos> jatekosok, List<Gombasz> gombaszok, List<Rovarasz> rovaraszok) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String parancs;

        PrintWriter systemOut = new PrintWriter(System.out, true);

        while (korSzam <= 60) { //Játék 20 körig tart, ha nem ér véget előbb.
            //Egy kör a játékban, minden játékos cselekszik
            for (Jatekos jatekos : jatekosok) {
                parancskezelo.setAktivJatekos(jatekos); //Aktív játékos beállítása a parancskezelőben
                //Jatekos köre, amíg van akciópontja
                while(jatekos.getakcioSzama() > 0){
                    try {
                        System.out.println("Aktiv játékos: " + jatekos.getNev() + " | Kör: " + korSzam + " | Akciópont: " + jatekos.getakcioSzama());

                        parancs = r.readLine();
                        
                        parancskezelo.bemenetAkcio(parancs, systemOut);
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
            //végigfut a pálya txt-n, mindegyik során lefuttatja a parancskezelot.
            Path palyaMappaPath = Paths.get("palya").toAbsolutePath();
                
            File palyaMappa = palyaMappaPath.toFile();
            if (!palyaMappa.exists()) {
                System.out.println("A palya mappa nem található.");
                return;
            }

            File palyaFajl = new File(palyaMappa, "palya.txt"); 
            if (!palyaFajl.exists()) {
                System.out.println("A palya.txt fájl nem található a palya mappában.");
                return;
            }
                try {
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new ByteArrayOutputStream()));
                    BufferedReader r = new BufferedReader(new FileReader(palyaFajl));
                    String parancs;
                    while ((parancs = r.readLine()) != null) {
                        
                        parancskezelo.bemenetAkcio(parancs, writer);
                        
                    }
                    

                    r.close();
                    writer.close();
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }

  
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
                jatekter.tores(randomSzamToreshez(), null, false);
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
            
            for(Tekton tekton : jatekter.getTektonok()){
                tekton.gombafonalFelszivas();
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
