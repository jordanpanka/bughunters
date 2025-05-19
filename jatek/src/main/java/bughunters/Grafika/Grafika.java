package bughunters.Grafika;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import bughunters.Gombafaj.Gombafaj;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Grafika extends JPanel {
    
    /**
     * A Tekton és a hozzá tartozó grafikus reprezentáció (GTekton) párosításait tároló HashMap.
     */
    private HashMap<Tekton, GTekton> tektonok;

    /**
     * Visszaadja a Tekton és GTekton objektumok párosításait tartalmazó térképet.
     *
     * @return a Tekton–GTekton párosításokat tartalmazó HashMap
     */
    public HashMap<Tekton, GTekton> getTektonok() {
        return tektonok;
    }

    /**
     * Beállítja a Tekton és GTekton objektumok párosításait.
     *
     * @param tektonok a Tekton–GTekton párosításokat tartalmazó HashMap
     */
    public void setTektonok(HashMap<Tekton, GTekton> tektonok) {
        this.tektonok = tektonok;
    }
    /**
     * A Gombatest és a hozzá tartozó grafikus reprezentáció (GGombatest) párosításait tároló HashMap.
     */
    private HashMap<Gombatest,GGombatest> gombatestek;

    /**
     * Visszaadja a Gombatest és GGombatest objektumok párosításait tartalmazó térképet.
     *
     * @return a Gombatest–GGombatest párosításokat tartalmazó HashMap
     */
    public HashMap<Gombatest, GGombatest> getGombatestek() {
        return gombatestek;
    }

    /**
     * Beállítja a Gombatest és GGombatest objektumok párosításait.
     *
     * @param gombatestek a Gombatest–GGombatest párosításokat tartalmazó HashMap
     */
    public void setGombatestek(HashMap<Gombatest, GGombatest> gombatestek) {
        this.gombatestek = gombatestek;
    }

    /**
     * A Gombafonal és a hozzá tartozó grafikus reprezentáció (GGombafonal) párosításait tároló HashMap.
     */
    private HashMap<Gombafonal,GGombafonal> gombafonalak;

    /**
     * Visszaadja a Gombafonal és GGombafonal objektumok párosításait tartalmazó térképet.
     *
     * @return a Gombafonal–GGombafonal párosításokat tartalmazó HashMap
     */
    public HashMap<Gombafonal, GGombafonal> getGombafonalak() {
        return gombafonalak;
    }

    /**
     * Beállítja a Gombafonal és GGombafonal objektumok párosításait.
     *
     * @param gombafonalak a Gombafonal–GGombafonal párosításokat tartalmazó HashMap
     */
    public void setGombafonalak(HashMap<Gombafonal, GGombafonal> gombafonalak) {
        this.gombafonalak = gombafonalak;
    }

    /**
     * A Rovar és a hozzá tartozó grafikus reprezentáció (GRovar) párosításait tároló HashMap.
     */
    private HashMap<Rovar, GRovar> rovarok;

    /**
     * Visszaadja a Rovar és GRovar objektumok párosításait tartalmazó térképet.
     *
     * @return a Rovar–GRovar párosításokat tartalmazó HashMap
     */
    public HashMap<Rovar, GRovar> getRovarok() {
        return rovarok;
    }

    /**
     * Beállítja a Rovar és GRovar objektumok párosításait.
     *
     * @param rovarok a Rovar–GRovar párosításokat tartalmazó HashMap
     */
    public void setRovarok(HashMap<Rovar, GRovar> rovarok) {
        this.rovarok = rovarok;
    }

    /**
     * A Spóra és a hozzá tartozó grafikus reprezentáció (GSpora) párosításait tároló HashMap.
     */
    private HashMap<Spora,GSpora> sporak;

    /**
     * Visszaadja a Spora és GSpora objektumok párosításait tartalmazó térképet.
     *
     * @return a Spora–GSpora párosításokat tartalmazó HashMap
     */
    public HashMap<Spora, GSpora> getSporak() {
        return sporak;
    }

    /**
     * Beállítja a Spora és GSpora objektumok párosításait.
     *
     * @param sporak a Spora–GSpora párosításokat tartalmazó HashMap
     */
    public void setSporak(HashMap<Spora, GSpora> sporak) {
        this.sporak = sporak;
    }
   
    /**
     * Létrehozza a {@code Grafika} objektumot, és inicializálja az összes entitás grafikus reprezentációját
     * tartalmazó HashMap-et üres példányokkal.
     */
    public Grafika(){
        tektonok=new HashMap<>();
        sporak=new HashMap<>();
        rovarok=new HashMap<>();
        gombafonalak=new HashMap<>();
        gombatestek=new HashMap<>();
    }

    /**
     * Visszaállítja az összes grafikus elem (Tekton, Gombatest, Gombafonal, Rovar, Spóra) koordinátáit (-1, -1) értékre.
     * Ez a metódus egy adott Tekton objektum kirajzolásának előkészítéséhez használható, de jelenleg
     * nem hajt végre tényleges kirajzolást.
     *
     * @param t a Tekton példány, amelyhez a kirajzolást készítjük elő
     * @param g a Graphics objektum, amelyre a rajzolás történne
     */
    public void Draw(Tekton t, Graphics g){
        tektonok.forEach((tek,gtek)->{
            gtek.setX(-1);
            gtek.setY(-1);
        });
        gombatestek.forEach((tek,gtek)->{
            gtek.setX(-1);
            gtek.setY(-1);
        });
        gombafonalak.forEach((tek,gtek)->{
            gtek.setX(-1);
            gtek.setY(-1);
            gtek.setX1(-1);
            gtek.setY1(-1);
            gtek.setX2(-1);
            gtek.setY2(-1);
        });
        rovarok.forEach((tek,gtek)->{
            gtek.setX(-1);
            gtek.setY(-1);
        });
        sporak.forEach((tek,gtek)->{
            gtek.setX(-1);
            gtek.setY(-1);
        });
        super.paintComponent(g);
        int szomszedokSzama = t.getSzomszedok().size();

        int R = 150;  // Kör sugara
        int cX = 680; // Középpont X koordinátája
        int cY = 250; // Középpont Y koordinátája

        // Középső tekton pozíció beállítása
        tektonok.get(t).setX(cX);
        tektonok.get(t).setY(cY);


        // Szomszédok pozícióinak kiszámítása és beállítása
        if (szomszedokSzama != 0) {
            //System.out.println("Vannak szomszédok.");
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

                    gombatestek.forEach((gombatest, gg) -> {
                            if (gombatest.getTekton().equals(szomszed)) {
                                gg.setX(szomszedX);
                                gg.setY(szomszedY);
                               // gg.Draw(g);
                            }
                    });
                    
                    //sporaElhelyezesKorben(szomszed,g);
                }

            }
        }


        // Végül a középső tekton kirajzolása
        fonalrajzol(t,g);
        tektonok.get(t).Draw(g);
         if (szomszedokSzama != 0) {

            for (int i = 0; i < szomszedokSzama; i++) {

                Tekton szomszed = t.getSzomszedok().get(i);
                GTekton gSzomszed = tektonok.get(szomszed);

                if (gSzomszed != null) {
                    gSzomszed.Draw(g);  // Azonnal ki is rajzoljuk
                    gombatestek.forEach((gombatest, gg) -> {
                            if (gombatest.getTekton().equals(szomszed)) {
                    
                                gg.Draw(g);
                            }
                    });
                    
                    sporaElhelyezesKorben(szomszed,g);

                }

            }
        }
        sporaElhelyezesKorben(t,g);
       
        //gombatestek beállítása csak a középső
        gombatestek.forEach((gombatest,gg)->{
            if(gombatest.getTekton().equals(t)){
                //System.out.println("kozepso tekton: "+t + "\n");
                gg.setX(cX);
                gg.setY(cY);
                gg.Draw(g);
            }
        });
        

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
                    //System.out.println("Rovar rajzolva X=" + baseX + ", Y=" + baseY+"tekton: "+rovar.getTartozkodas());
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
        
    }


    /**
     * A megadott Tektonhoz tartozó spórákat egyenletesen elhelyezi egy képzeletbeli kör mentén,
     * a Tekton középpontjától számított sugárral. Minden spóra grafikus reprezentációja (GSpora)
     * kirajzolásra kerül, valamint megjelenik a spóra mennyisége is.
     *
     * @param t a Tekton objektum, amelyhez tartozó spórákat kell kirajzolni
     * @param g a Graphics objektum, amelyre a rajzolás történik
     */
    public void sporaElhelyezesKorben(Tekton t, Graphics g) {
        int sugar =45;
        int cX=tektonok.get(t).getX();
        int cY=tektonok.get(t).getY();
        List<Spora> sporakList = t.getSporak();
        int sporaSzam = sporakList.size();

       // System.out.println("Spórák száma: " + sporaSzam);

        if (sporaSzam == 0) return;  // Nincs mit elhelyezni

        double szogLepes = 2 * Math.PI / sporaSzam;  // Egyenlő elosztás a kör mentén

        for (int i = 0; i < sporaSzam; i++) {
            double szog = i * szogLepes;
            int sporaX = (int) (cX + sugar * Math.cos(szog));
            int sporaY = (int) (cY + sugar * Math.sin(szog));

            Spora spora = sporakList.get(i);
            GSpora gSpora = sporak.get(spora);

            if (gSpora != null) {
                gSpora.setX(sporaX+44);
                gSpora.setY(sporaY+44);
                gSpora.Draw(g);  // Kirajzolás
                gSpora.szamKiir(g, spora.getMennyiseg());
                //System.out.println("Spóra rajzolva X=" + sporaX + ", Y=" + sporaY);
            }
        }

        
    }
    
    
    /**
     * A megadott Tekton-hoz tartozó gombafonalakat kirajzolja a képernyőre. A rajzolás során
     * figyelembe veszi a szomszédos Tektonokat is, és minden gombafonalat a hozzá tartozó
     * gombafaj szerint eltolt vonallal jelenít meg. Azok a fonalak, amelyek csak az egyik végpontban
     * kapcsolódnak a vizsgált Tektonhoz vagy szomszédaihoz, kifelé mutató, körszerűen elhelyezett
     * vonalakként kerülnek ábrázolásra.
     *
     * A különböző gombafajokhoz tartozó fonalak eltérő offset értéket kapnak, hogy vizuálisan
     * elkülöníthetők legyenek. A metódus csak az érintett Tektonok és fonalak kirajzolását végzi.
     *
     * @param t a Tekton objektum, amelyhez tartozó fonalakat kell kirajzolni
     * @param g a Graphics objektum, amelyre a rajzolás történik
     */
    public void fonalrajzol(Tekton t, Graphics g) {
        // Tektonok listája a vizsgált és szomszédos elemekkel 
        List<Tekton> tektonok2 = new ArrayList<>(t.getSzomszedok());
        tektonok2.add(t);

        // Egyedi Gombafonal objektumok gyűjtése Set-be
        Set<Gombafonal> fonalak2 = new HashSet<>(t.getFonalak());

        for (Tekton szomszed : t.getSzomszedok()) {
            fonalak2.addAll(szomszed.getFonalak());
        }

        // Gombafajok kigyűjtése
        Set<Gombafaj> gombafajok = new HashSet<>();
        for (Gombafonal fon : fonalak2) {
            gombafajok.add(fon.getGombafaj());
        }

        List<Gombafaj> gombafajLista = new ArrayList<>(gombafajok);

        // Pozíció offset-ek létrehozása gombafajonként
        HashMap<Gombafaj, Integer> fajok = new HashMap<>();
        if (!gombafajLista.isEmpty()) fajok.put(gombafajLista.get(0), 50);

        for (int i = 1; i < gombafajLista.size(); i++) {
            if (i % 2 == 0)
                fajok.put(gombafajLista.get(i), 50 - i * 10);
            else
                fajok.put(gombafajLista.get(i), 50 + i * 16);
        }



        int totalLines = 0; // vagy ahány gombafonal van
        int index = 0; // az aktuális vonal indexe
        int radius = 60; // hossza a vonalaknak, mehet nagyobbra is

        for (Gombafonal fonal : fonalak2) {
            Tekton t1 = fonal.getVegpont1();
            Tekton t2 = fonal.getVegpont2();
            if(tektonok2.contains(t1) || tektonok2.contains(t2)){
                totalLines++;
            }
        }


        // Fonalak kirajzolása
        for (Gombafonal fonal : fonalak2) {
            double angle = 2 * Math.PI * index / totalLines;

            Tekton t1 = fonal.getVegpont1();
            Tekton t2 = fonal.getVegpont2();

            if (tektonok2.contains(t1) && tektonok2.contains(t2)) {
                GTekton g1 = tektonok.get(t2);
                GTekton g2 = tektonok.get(t1);

                int x1 = g1.getX();
                int y1 = g1.getY();
                int x2 = g2.getX();
                int y2 = g2.getY();

                GGombafonal gFonal = gombafonalak.get(fonal);
                if (gFonal != null) {
                    int offset = fajok.get(fonal.getGombafaj());
                    gFonal.setX1(x1 + offset);
                    gFonal.setY1(y1 + offset);
                    gFonal.setX2(x2 + offset);
                    gFonal.setY2(y2 + offset);

                    gFonal.Draw(g);
                }
            }
            else{
                if(tektonok2.contains(t1)){
                    GTekton g1 = tektonok.get(t1);
                    int x1 = g1.getX();
                    int y1 = g1.getY();
                    GGombafonal gFonal = gombafonalak.get(fonal);
                    if (gFonal != null) {
                        int x_1 = x1+50;
                        int y_1 = y1+50;
                        int x_2 = (int)(x_1 + Math.cos(angle) * radius);
                        int y_2 = (int)(y_1 + Math.sin(angle) * radius);
 
                        gFonal.setX1(x_1);
                        gFonal.setY1(y_1);
                        gFonal.setX2(x_2);
                        gFonal.setY2(y_2);

                        gFonal.Draw(g);
                        index++;
                    }
                }
                else{
                    GTekton g2 = tektonok.get(t2);
                    int x2 = g2.getX();
                    int y2 = g2.getY();
                    GGombafonal gFonal = gombafonalak.get(fonal);
                    if (gFonal != null) {
                        int x_1 = x2+50;
                        int y_1 = y2+50;
                        int x_2 = (int)(x_1 + Math.cos(angle) * radius);
                        int y_2 = (int)(y_1 + Math.sin(angle) * radius);
                        gFonal.setX1(x_1);
                        gFonal.setY1(y_1);
                        gFonal.setX2(x_2);
                        gFonal.setY2(y_2);

                        gFonal.Draw(g);
                        index++;
                    }
                }
            }
        }
    }


    /**
     * Megkeresi és visszaadja azt a Gombafonal objektumot, amelyikhez tartozó grafikus vonal
     * közel van a megadott (x, y) koordinátához. A keresés során a függvény figyelembe veszi,
     * hogy a koordináta y értékéből 230 egységet levon, valószínűleg az eltolás miatt a
     * megjelenítés vagy konténer miatt.
     *
     * A keresés a tárolt GGombafonal objektumok vonalaihoz hasonlítja a megadott pontot,
     * és az isPointNearLine segédfüggvénnyel dönti el, hogy a pont elég közel van-e egy vonalhoz.
     * Amint talál ilyen vonalat, azonnal visszatér a hozzá tartozó Gombafonal objektummal.
     * Ha nincs találat, null értéket ad vissza.
     *
     * @param x a keresett pont vízszintes koordinátája (pixel)
     * @param y a keresett pont függőleges koordinátája (pixel)
     * @return a hozzá tartozó Gombafonal, ha van, egyébként null
     */
    public Gombafonal fonalKeres(int x, int y){
        y=y-230;
        //System.out.println("fonalkeres");
        for (Map.Entry<Gombafonal, GGombafonal> entry : gombafonalak.entrySet()) {
            double X1=(double)entry.getValue().getX1();
            double X2=(double)entry.getValue().getX2();
            double Y1=(double)entry.getValue().getY1();
            double Y2=(double)entry.getValue().getY2();
            boolean kozelVan = isPointNearLine(x, y, X1, Y1, X2, Y2);
            if(kozelVan){
                return entry.getKey();
            }
        }
        return null; // nem találtuk meg
    }


    /**
     * Ellenőrzi, hogy a megadott pont (px, py) elég közel van-e az (x1, y1)-(x2, y2) vonalszakaszhoz.
     * A közeliség egy előre definiált küszöbértéken (jelen esetben 10 pixel) alapul.
     *
     * @param px a pont x koordinátája
     * @param py a pont y koordinátája
     * @param x1 a vonal kezdőpontjának x koordinátája
     * @param y1 a vonal kezdőpontjának y koordinátája
     * @param x2 a vonal végpontjának x koordinátája
     * @param y2 a vonal végpontjának y koordinátája
     * @return true, ha a pont 10 pixelen belül van a vonaltól, különben false
     */
    boolean isPointNearLine(double px, double py, double x1, double y1, double x2, double y2) {
        double distance = distanceToLine(px, py, x1, y1, x2, y2);
        //System.out.println("Táv: "+distance);
        return distance < 10; // Küszöb távolság: ha elég közel van, akkor igaz
    }


    /**
     * Kiszámítja a pont (px, py) és az (x1, y1)-(x2, y2) vonalszakasz közötti legkisebb távolságot.
     * Ha a vonalszakasz hossza 0 (pont), akkor a pont és a vonal kezdőpontja közti távolságot adja vissza.
     *
     * @param px a pont x koordinátája
     * @param py a pont y koordinátája
     * @param x1 a vonal kezdőpontjának x koordinátája
     * @param y1 a vonal kezdőpontjának y koordinátája
     * @param x2 a vonal végpontjának x koordinátája
     * @param y2 a vonal végpontjának y koordinátája
     * @return a pont és a vonalszakasz közötti legkisebb távolság
     */
    double distanceToLine(double px, double py, double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double lengthSquared = dx * dx + dy * dy;

        // Ha a szakasz hossza közel nulla, a távolság a kezdőponttól értendő
        if (lengthSquared == 0.0) {
            double dxp = px - x1;
            double dyp = py - y1;
            return Math.sqrt(dxp * dxp + dyp * dyp);
        }

        // Projekciós arány a vonalon belül
        double t = ((px - x1) * dx + (py - y1) * dy) / lengthSquared;

        // A legközelebbi pont a vonalon (ha t < 0 vagy > 1, akkor a végpontokhoz legközelebbi)
        double closestX = x1 + t * dx;
        double closestY = y1 + t * dy;

        double dxp = px - closestX;
        double dyp = py - closestY;

        return Math.sqrt(dxp * dxp + dyp * dyp);
    }


    /**
     * Keres egy Gombatest objektumot a megadott (x, y) koordináta közelében.
     * A keresésnél a y koordinátát 230 pixellel csökkenti (valószínűleg az eltolás miatt).
     * 
     * A keresés úgy történik, hogy minden Gombatesthez tartozó GGombatest középpontját
     * kiszámolja (az X és Y koordinátákhoz hozzáad 50-et), majd megnézi,
     * hogy a megadott pont legfeljebb 35 pixel távolságra van-e ettől a középponttól.
     *
     * @param x A vizsgált pont x koordinátája
     * @param y A vizsgált pont y koordinátája
     * @return A megtalált Gombatest, ha van 35 pixel távolságon belül, egyébként null
     */
    public Gombatest gombatestKeres(int x, int y) {
        y=y-230;
        //System.out.println("Képernyő"+x+y);

        for (Map.Entry<Gombatest, GGombatest> entry : gombatestek.entrySet()) {
            double xC =(double) entry.getValue().getX()+50;
            double yC = (double)entry.getValue().getY()+50;
            //System.out.println("Aktuális tektin:"+xC+yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d <=35) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }

    /**
     * Keres egy Tekton objektumot a megadott (x, y) koordináta közelében.
     * A y koordinátát 230 pixellel csökkenti (eltolás miatt).
     * 
     * Minden Tektonhoz tartozó GTekton pozíciójához hozzáad 50-et (offset),
     * majd kiszámolja a távolságot a megadott pont és a GTekton középpontja között.
     * Ha a távolság legfeljebb 50 pixel, akkor megtaláltnak tekinti.
     * 
     * @param x A vizsgált pont x koordinátája
     * @param y A vizsgált pont y koordinátája
     * @return A megtalált Tekton, vagy null ha nincs elég közel
     */
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


    /**
     * Keres egy Rovar objektumot a megadott (x, y) koordináta környékén.
     * A y koordinátát 230 pixellel eltolja (pl. GUI elhelyezkedés miatt).
     * A Rovar pozíciójához 45 pixeles offsetet ad hozzá mindkét koordinátára.
     * Ha a távolság a pont és a Rovar középpontja között kisebb, mint 22 pixel,
     * akkor megtaláltnak tekinti.
     * 
     * @param x A vizsgált pont x koordinátája
     * @param y A vizsgált pont y koordinátája
     * @return A megtalált Rovar, vagy null ha nincs elég közel
     */
    public Rovar rovarKeres(int x, int y){
        //lehet nem jó
        y=y-230;
        //System.out.println("Rovarkeres"+x+y);
        for (Map.Entry<Rovar, GRovar> entry : rovarok.entrySet()) {
            double xC = (double)entry.getValue().getX()+45;
            double yC = (double)entry.getValue().getY()+45;
            //System.out.println("Aktuális tektin:"+xC+yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d < 22) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }


    /**
     * Keres egy Spóra objektumot a megadott (x, y) koordináta közelében.
     * A y koordinátát 230 pixellel csökkenti (eltolás miatt).
     * 
     * Minden Spóra pozíciójához hozzáad 12-et (offset),
     * majd kiszámolja a távolságot a megadott pont és a GSpora középpontja között.
     * Ha a távolság legfeljebb 12 pixel, akkor megtaláltnak tekinti.
     * 
     * @param x A vizsgált pont x koordinátája
     * @param y A vizsgált pont y koordinátája
     * @return A megtalált Spóra, vagy null ha nincs elég közel
     */
    public Spora sporaKeres(int x, int y){
        y=y-230;
         for (Map.Entry<Spora, GSpora> entry : sporak.entrySet()) {
            double xC = (double)entry.getValue().getX()+12;
            double yC = (double)entry.getValue().getY()+12;
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d <=12) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null;
    }

}
