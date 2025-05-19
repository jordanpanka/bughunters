package bughunters.Grafika;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Grafika extends JPanel {
    
    private HashMap<Tekton, GTekton> tektonok;

    public HashMap<Tekton, GTekton> getTektonok() {
        return tektonok;
    }
    public void setTektonok(HashMap<Tekton, GTekton> tektonok) {
        this.tektonok = tektonok;
    }
    private HashMap<Gombatest,GGombatest> gombatestek;
    public HashMap<Gombatest, GGombatest> getGombatestek() {
        return gombatestek;
    }
    public void setGombatestek(HashMap<Gombatest, GGombatest> gombatestek) {
        this.gombatestek = gombatestek;
    }
    private HashMap<Gombafonal,GGombafonal> gombafonalak;
    public HashMap<Gombafonal, GGombafonal> getGombafonalak() {
        return gombafonalak;
    }
    public void setGombafonalak(HashMap<Gombafonal, GGombafonal> gombafonalak) {
        this.gombafonalak = gombafonalak;
    }
    private HashMap<Rovar, GRovar> rovarok;
    public HashMap<Rovar, GRovar> getRovarok() {
        return rovarok;
    }
    public void setRovarok(HashMap<Rovar, GRovar> rovarok) {
        this.rovarok = rovarok;
    }
    private HashMap<Spora,GSpora> sporak;

    public HashMap<Spora, GSpora> getSporak() {
        return sporak;
    }
    public void setSporak(HashMap<Spora, GSpora> sporak) {
        this.sporak = sporak;
    }
   
    public Grafika(){
        tektonok=new HashMap<>();
        sporak=new HashMap<>();
        rovarok=new HashMap<>();
        gombafonalak=new HashMap<>();
        gombatestek=new HashMap<>();
    }
    public void Draw(Tekton t, Graphics g){
        super.paintComponent(g);
        int szomszedokSzama = t.getSzomszedok().size();
        System.out.println("Szomszédok száma: " + szomszedokSzama);

        int R = 150;  // Kör sugara
        int cX = 680; // Középpont X koordinátája
        int cY = 250; // Középpont Y koordinátája

        // Középső tekton pozíció beállítása
        tektonok.get(t).setX(cX);
        tektonok.get(t).setY(cY);
        sporaElhelyezesKorben(t,g);
        
        System.out.println("Tekton középen: X=" + cX + ", Y=" + cY);

        // Szomszédok pozícióinak kiszámítása és beállítása

        System.out.println("Gombateste száma: " + gombatestek.size());

        if (szomszedokSzama != 0) {
            System.out.println("Vannak szomszédok.");
            double szogLepes = 2 * Math.PI / szomszedokSzama;  // Egyenlő elosztás a kör mentén

            for (int i = 0; i < szomszedokSzama; i++) {
                double szog = i * szogLepes;  // Minden szomszédnál léptetjük a szöget
                int szomszedX = (int) (cX + R * Math.cos(szog));
                int szomszedY = (int) (cY + R * Math.sin(szog));

                Tekton szomszed = t.getSzomszedok().get(i);
                GTekton gSzomszed = tektonok.get(szomszed);

                if (gSzomszed != null) {
                    gSzomszed.setX(szomszedX);
                    gSzomszed.setY(szomszedY);
                    gSzomszed.Draw(g);  // Azonnal ki is rajzoljuk
                    System.out.println("Szomszéd tekton rajzolva X=" + szomszedX + ", Y=" + szomszedY);
                    gombatestek.forEach((gombatest, gg) -> {
                            if (gombatest.getTekton().equals(szomszed)) {
                                gg.setX(szomszedX);
                                gg.setY(szomszedY);
                                gg.Draw(g);
                            }
                    });
                    
                    sporaElhelyezesKorben(szomszed,g);
                }

            }
        }
        tektonok.forEach((tekton,gtekton)->{System.out.println(" X"+gtekton.getX());});
        // Végül a középső tekton kirajzolása
        tektonok.get(t).Draw(g);
        fonalrajzol(t,g);
        //gombatestek beállítása csak a középső
        gombatestek.forEach((gombatest,gg)->{
            if(gombatest.getTekton().equals(t)){
                gg.setX(cX);
                gg.setY(cY);
                gg.Draw(g);
                System.out.println("Gombatest rajzolva X=" + cX + ", Y=" + cY +"nev: "+gombatest.getGombafaj().getNev());
            }
        });
        
        /*
        long count = rovarok.keySet().stream()
        .filter(rovar -> rovar.getTartozkodas().equals(t))
        .count();

        if (count == 0) return;

        double angleStep = 2 * Math.PI / count;
        int radius = 30;  // dinamikus sugár
        int[] i = {0};

        rovarok.forEach((rovar, gg) -> {
            if (rovar.getTartozkodas().equals(t)) {
                double angle = i[0] * angleStep - Math.PI / 2; // fentről induljon

                int x = (int)(cX + radius * Math.cos(angle));
                int y = (int)(cY + radius * Math.sin(angle));

                gg.setX(x);
                gg.setY(y);
                gg.Draw(g);

                i[0]++;
            }
        });
        */

        // Rovarok kirajzolása a középső és a szomszédos tektonokra is
        List<Tekton> mindenRajzolando = new ArrayList<>();
        mindenRajzolando.add(t); // középső
        mindenRajzolando.addAll(t.getSzomszedok()); // szomszédok

        for (Tekton tekton : mindenRajzolando) {
            long count = rovarok.keySet().stream()
                .filter(rovar -> rovar.getTartozkodas().equals(tekton))
                .count();

            if (count == 0) continue;

            GTekton gTekton = tektonok.get(tekton);
            int baseX = gTekton.getX();
            int baseY = gTekton.getY();

            double angleStep = 2 * Math.PI / count;
            int radius = 30;
            int i = 0;

            for (Map.Entry<Rovar, GRovar> entry : rovarok.entrySet()) {
                Rovar rovar = entry.getKey();
                GRovar gg = entry.getValue();

                if (rovar.getTartozkodas().equals(tekton)) {
                    double angle = i * angleStep;
                    int x = (int)(baseX + radius * Math.cos(angle));
                    int y = (int)(baseY + radius * Math.sin(angle));
                    gg.setX(x);
                    gg.setY(y);
                    gg.Draw(g);
                    i++;
                }
            }
        }
        fonalrajzol(t,g);
        tektonok.forEach((tekton,gtekton)->{System.out.println(" X"+gtekton.getX());});
    }
    public void sporaElhelyezesKorben(Tekton t, Graphics g) {
        int sugar =50;
        int cX=tektonok.get(t).getX();
        int cY=tektonok.get(t).getY();
    List<Spora> sporakList = t.getSporak();
    int sporaSzam = sporakList.size();

    if (sporaSzam == 0) return;  // Nincs mit elhelyezni

    double szogLepes = 2 * Math.PI / sporaSzam;  // Egyenlő elosztás a kör mentén

    for (int i = 0; i < sporaSzam; i++) {
        double szog = i * szogLepes;
        int sporaX = (int) (cX + sugar * Math.cos(szog));
        int sporaY = (int) (cY + sugar * Math.sin(szog));

        Spora spora = sporakList.get(i);
        GSpora gSpora = sporak.get(spora);

        if (gSpora != null) {
            gSpora.setX(sporaX);
            gSpora.setY(sporaY);
            gSpora.Draw(g);  // Kirajzolás
            System.out.println("Spóra rajzolva X=" + sporaX + ", Y=" + sporaY);
        }
        }
    }
    /*public void fonalrajzol(Tekton t, Graphics g){
        System.out.println("fonalrajzol");
        List<Tekton>tektonok2=t.getSzomszedok();
        tektonok2.add(t);
        int x1;
            int x2;
            int y1;
            int y2;
        List<Gombafonal>fonalak2=t.getFonalak();
        System.out.println("grafika"+gombafonalak.size());
        for(int i=0; i<fonalak2.size(); i++){
            Tekton t1=fonalak2.get(i).getVegpont1();
            Tekton t2=fonalak2.get(i).getVegpont2();
            Point t1p=new Point();
            Point t2p=new Point();
           
            
            tektonok.forEach((tekton,gtekton)->{
                if(tekton.equals(t1)){
                    x1=gtekton.getX();
                    y1=gtekton.getY();
                }
                if(tekton.equals(t2)){
                    x2=gtekton.getX();
                    y2=gtekton.getY();
                }
            });
            gombafonalak.get(fonalak2.get(i)).setX1(x1);
            System.out.println( gombafonalak.get(fonalak2.get(i)).getX1());
            gombafonalak.get(fonalak2.get(i)).setX2(x2);
             System.out.println( gombafonalak.get(fonalak2.get(i)).getY1());
            gombafonalak.get(fonalak2.get(i)).setY1(y1);
            System.out.println( gombafonalak.get(fonalak2.get(i)).getX2());
            gombafonalak.get(fonalak2.get(i)).setY2(y2);
            gombafonalak.get(fonalak2.get(i)).Draw(g);
        }
    }*/
    public void fonalrajzol(Tekton t, Graphics g) {
    System.out.println("fonalrajzol");

    List<Tekton> tektonok2 = new ArrayList<>(t.getSzomszedok());
    tektonok2.add(t);

    List<Gombafonal> fonalak2 = t.getFonalak();
    for(int i=0; i<t.getSzomszedok().size();i++){
        Tekton t1=t.getSzomszedok().get(i);
        for(int j=0; j<t1.getFonalak().size();j++){
            fonalak2.add(t1.getFonalak().get(j));
        }
    }
    System.out.println("grafika: " + gombafonalak.size());

    for (Gombafonal fonal : fonalak2) {
        Tekton t1 = fonal.getVegpont1();
        Tekton t2 = fonal.getVegpont2();
        if(tektonok2.contains(t1) && tektonok2.contains(t2)){System.out.println("Benne van");
        //else{System.out.println("Az élet szép");}
        GTekton g1 = tektonok.get(t2);
        GTekton g2 = tektonok.get(t1);

        if(t2.equals(t))System.out.println("JAj");

       /*  if (g1 == null || g2 == null) {
            continue; // Nincs grafikai információ az egyik végpontról, kihagyjuk
        }*/

        int x1 = g1.getX();
        System.out.println(g1.getX());
        int y1 = g1.getY();
         System.out.println(g1.getY());
        int x2 = g2.getX();
         System.out.println(tektonok.get(t1).getX());
        int y2 = g2.getY();
         System.out.println(g2.getY());
        GGombafonal gFonal = gombafonalak.get(fonal);
        if (gFonal != null) {
            gFonal.setX1(x1);
            gFonal.setY1(y1);
            gFonal.setX2(x2);
            gFonal.setY2(y2);

            System.out.println("Fonal koordináták: (" + x1 + "," + y1 + ") -> (" + x2 + "," + y2 + ")");
            
            gFonal.Draw(g);
        }
    }}
}

    // Kirajzolja a gombafonalakat a tektonok között

   /*  public void gombafonalakRajzolasa(Tekton t, Graphics g) {
=======
    public void gombafonalakRajzolasa(Tekton t, Graphics g) {
>>>>>>> c9a770f6362cd515695ba840230c2a7fda738f03
        Set<Set<Tekton>> kirajzoltFonalak = new HashSet<>();
        List<Tekton> kirajzoltTektonok = t.getSzomszedok();
        kirajzoltTektonok.add(t);

        for (Tekton t1 : kirajzoltTektonok) {
            GTekton g1 = tektonok.get(t1);
            int x1 = g1.getX();
            int y1 = g1.getY();

            List<Gombafonal> gombafonalak1 = t1.getFonalak();

            for (Tekton t2 : t1.getSzomszedok()) {
                // Elkerülés: ugyanaz a kapcsolat ne legyen kétszer kirajzolva
                Set<Tekton> par = new HashSet<>(Arrays.asList(t1, t2));
                if (kirajzoltFonalak.contains(par)) continue;

                GTekton g2 = tektonok.get(t2);
                if (g2 != null) {
                    int x2 = g2.getX();
                    int y2 = g2.getY();

                    List<Gombafonal> gombafonalak2 = t2.getFonalak();

                    Gombafonal gombafonal = null;

                    for (Gombafonal gf1 : gombafonalak1) {
                        for (Gombafonal gf2 : gombafonalak2) {
                            if (gf1.equals(gf2)) {
                                gombafonal = gf1;
                                break;
                            }
                        }
                        if (gombafonal != null) break;
                    }

                    GGombafonal fonal = gombafonalak.get(gombafonal);
                    fonal.setX1(x1);
                    fonal.setY1(y1);
                    fonal.setX2(x2);
                    fonal.setY2(y2);
                    fonal.Draw(g);
                    kirajzoltFonalak.add(par);
                }
<<<<<<< HEAD

=======
>>>>>>> c9a770f6362cd515695ba840230c2a7fda738f03
        GTekton g1 = tektonok.get(t);
        int x1 = g1.getX();
        int y1 = g1.getY();

        List<Gombafonal> fonalak = t.getFonalak();

        for (Gombafonal gombafonal : fonalak) {
            if(gombafonal.getVegpont1().equals(t)){
                GTekton g2 = tektonok.get(gombafonal.getVegpont2());
                int x2 = g2.getX();
                int y2 = g2.getY();

                GGombafonal fonal = gombafonalak.get(gombafonal);
                fonal.setX1(x1);
                fonal.setY1(y1);
                fonal.setX2(800);
                fonal.setY2(350);
                fonal.Draw(g);
                System.out.println("X1:"+x1);
                System.out.println("Y1:"+y1);
                System.out.println("X2:"+x2);
                System.out.println("Y2:"+y2);
            }
            else{
                GTekton g2 = tektonok.get(gombafonal.getVegpont1());
                int x2 = g2.getX();
                int y2 = g2.getY();

                GGombafonal fonal = gombafonalak.get(gombafonal);
                fonal.setX1(x1);
                fonal.setY1(y1);
                fonal.setX2(800);
                fonal.setY2(350);
                fonal.Draw(g);
                System.out.println("X1:"+x1);
                System.out.println("Y1:"+y1);
                System.out.println("X2:"+x2);
                System.out.println("Y2:"+y2);
<<<<<<< HEAD

=======
>>>>>>> c9a770f6362cd515695ba840230c2a7fda738f03
            }
        }
    }*/


    @Override
    public void paintComponent(Graphics g){

        System.out.println(tektonok.size());
        System.out.println(rovarok.size());
        System.out.println(gombatestek.size());
        

    }
    public Gombafonal fonalKeres(int x, int y){
        y=y-230;
        for (Map.Entry<Gombafonal, GGombafonal> entry : gombafonalak.entrySet()) {
            double X1=(double)entry.getValue().getX();
            double X2=(double)entry.getValue().getX2();
            double Y1=(double)entry.getValue().getY();
            double Y2=(double)entry.getValue().getY2();
            double dX=(double)entry.getValue().getX()-entry.getValue().getX2();
            double dY=(double)entry.getValue().getY()-entry.getValue().getY2();
            double t=((x-X1)*dX+(y-Y1)*dY)/(dX*dX+dY*dY);
            t=Math.max(0,Math.min(1,t));
            double projX = X1 + t * dX;
            double projY = Y1 + t * dY;
            double tav=Math.hypot(x - projX, y - projY);
            if(tav<=0.5){
                return entry.getKey();
            }
        }
        return null; // nem találtuk meg
    }
   
    public Gombatest gombatestKeres(int x, int y) {
        y=y-230;
        System.out.println("Képernyő"+x+y);

        for (Map.Entry<Gombatest, GGombatest> entry : gombatestek.entrySet()) {
            double xC =(double) entry.getValue().getX()+50;
            double yC = (double)entry.getValue().getY()+50;
            System.out.println("Aktuális tektin:"+xC+yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d <=35) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }
    public Tekton tektonKeres(int x, int y){
        y=y-230;
        for (Map.Entry<Tekton, GTekton> entry : tektonok.entrySet()) {
            double xC = (double)entry.getValue().getX()+50;
            double yC = (double)entry.getValue().getY()+50;
            //System.out.println("Aktuális tektin:"+xC+yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            //System.out.println("Táv:"+d);
            if (d <=50 ) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }
    public Rovar rovarKeres(int x, int y){
        //lehet nem jó
        y=y-230;
        System.out.println("Képernyő"+x+y);
        for (Map.Entry<Rovar, GRovar> entry : rovarok.entrySet()) {
            double xC = (double)entry.getValue().getX()+45;
            double yC = (double)entry.getValue().getY()+45;
            System.out.println("Aktuális tektin:"+xC+yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d < 22) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }
    public Spora sporaKeres(int x, int y){
        y=y-230;
         for (Map.Entry<Spora, GSpora> entry : sporak.entrySet()) {
            double xC = (double)entry.getValue().getX()+3;
            double yC = (double)entry.getValue().getY()+3;
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d <=3) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null;
    }

}
