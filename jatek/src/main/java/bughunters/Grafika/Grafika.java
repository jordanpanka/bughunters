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


    
   /**
 * A Grafika osztály felelős a játékbeli objektumok grafikus megjelenítéséért.
 * Tárolja a játék különböző entitásainak grafikus megfelelőit.
 */
public class Grafika extends JPanel{

    /** A tekton objektumokhoz tartozó grafikus elemek. */
    private HashMap<Tekton, GTekton> tektonok;

    /** 
     * Visszaadja a tektonok és grafikus megfelelőik mapjét.
     * @return a tektonok és GTekton objektumok map-je
     */
    public HashMap<Tekton, GTekton> getTektonok() {
        return tektonok;
    }

    /**
     * Beállítja a tektonokhoz tartozó grafikus objektumokat.
     * @param tektonok a Tekton → GTekton hozzárendelések map-je
     */
    public void setTektonok(HashMap<Tekton, GTekton> tektonok) {
        this.tektonok = tektonok;
    }

    /** A gombatestekhez tartozó grafikus objektumok. */
    private HashMap<Gombatest, GGombatest> gombatestek;

    /**
     * Visszaadja a gombatestek és grafikus megfelelőik mapjét.
     * @return a Gombatest → GGombatest map
     */
    public HashMap<Gombatest, GGombatest> getGombatestek() {
        return gombatestek;
    }

    /**
     * Beállítja a gombatestek grafikus megfelelőit.
     * @param gombatestek a Gombatest → GGombatest hozzárendelések
     */
    public void setGombatestek(HashMap<Gombatest, GGombatest> gombatestek) {
        this.gombatestek = gombatestek;
    }

    /** A gombafonalakhoz tartozó grafikus objektumok. */
    private HashMap<Gombafonal, GGombafonal> gombafonalak;

    /**
     * Visszaadja a gombafonalak és grafikus megfelelőik mapjét.
     * @return a Gombafonal → GGombafonal map
     */
    public HashMap<Gombafonal, GGombafonal> getGombafonalak() {
        return gombafonalak;
    }

    /**
     * Beállítja a gombafonalak grafikus objektumait.
     * @param gombafonalak a Gombafonal → GGombafonal hozzárendelések
     */
    public void setGombafonalak(HashMap<Gombafonal, GGombafonal> gombafonalak) {
        this.gombafonalak = gombafonalak;
    }

    /** A rovarokhoz tartozó grafikus objektumok. */
    private HashMap<Rovar, GRovar> rovarok;

    /**
     * Visszaadja a rovarok és grafikus megfelelőik mapjét.
     * @return a Rovar → GRovar map
     */
    public HashMap<Rovar, GRovar> getRovarok() {
        return rovarok;
    }

    /**
     * Beállítja a rovarok grafikus objektumait.
     * @param rovarok a Rovar → GRovar hozzárendelések
     */
    public void setRovarok(HashMap<Rovar, GRovar> rovarok) {
        this.rovarok = rovarok;
    }

    /** A spórákhoz tartozó grafikus objektumok. */
    private HashMap<Spora, GSpora> sporak;

    /**
     * Visszaadja a spórák és grafikus megfelelőik mapjét.
     * @return a Spora → GSpora map
     */
    public HashMap<Spora, GSpora> getSporak() {
        return sporak;
    }

    /**
     * Beállítja a spórák grafikus objektumait.
     * @param sporak a Spora → GSpora hozzárendelések
     */
    public void setSporak(HashMap<Spora, GSpora> sporak) {
        this.sporak = sporak;
    }

    /**
     * Konstruktor. Inicializálja az összes grafikus objektumtérképet.
     */
    public Grafika() {
        tektonok = new HashMap<>();
        sporak = new HashMap<>();
        rovarok = new HashMap<>();
        gombafonalak = new HashMap<>();
        gombatestek = new HashMap<>();
    }
    public void Draw(Tekton t, Graphics g){
        //minden grafikus objektum alapállapotba állítása
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
                //System.out.println("Szomszéd tekton: " + szomszed);
                GTekton gSzomszed = tektonok.get(szomszed);

                if (gSzomszed != null) {
                    gSzomszed.setX(szomszedX);
                    gSzomszed.setY(szomszedY);

                    gombatestek.forEach((gombatest, gg) -> {
                            if (gombatest.getTekton().equals(szomszed)) {
                                gg.setX(szomszedX);
                                gg.setY(szomszedY);
                            }
                    });
                    
                }

            }
        }
        
        // Végül a középső tekton kirajzolása
        //fonalak kirajzolása
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
                    //spórák kirajzolása
                    sporaElhelyezesKorben(szomszed,g);

                }

            }
        }
        sporaElhelyezesKorben(t,g);
       
        //gombatestek beállítása csak a középső
        gombatestek.forEach((gombatest,gg)->{
            if(gombatest.getTekton().equals(t)){
               
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
     * Egy adott {@link Tekton} középpontja köré helyezi el az ahhoz tartozó {@link Spora} objektumokat egyenletesen
     * egy kör mentén, majd kirajzolja őket a megadott {@link Graphics} objektumra.
     *
     * <p>Amennyiben a tektonhoz nem tartozik spóra, a függvény azonnal visszatér.</p>
     * <p>A kirajzolás során minden spóra pozíciója kiszámításra kerül, eltolással (offset) kerül megjelenítésre,
     * valamint a mennyiségük is megjelenik a {@code szamKiir()} hívás által.</p>
     *
     * @param t a {@link Tekton}, amelyhez a spórák tartoznak
     * @param g a {@link Graphics} objektum, amire a spórák kirajzolása történik
     */
    public void sporaElhelyezesKorben(Tekton t, Graphics g) {
        int sugar =45;
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
                gSpora.setX(sporaX+44);
                gSpora.setY(sporaY+44);
                gSpora.Draw(g);  // Kirajzolás
                gSpora.szamKiir(g, spora.getMennyiseg());
                //System.out.println("Spóra rajzolva X=" + sporaX + ", Y=" + sporaY);
            }
        }

        
    }
    /**
     * Kirajzolja a megadott {@link Tekton} és annak közvetlen szomszédai között húzódó {@link Gombafonal}
     * kapcsolatokat, vizuálisan megjelenítve a fonalakat a megfelelő grafikai objektumokon keresztül.
     *
     * <p>A metódus összegyűjti a tektonhoz és szomszédaihoz tartozó összes fonalat, csoportosítja őket
     * a hozzájuk tartozó {@link Gombafaj} alapján, majd egyedi eltolásokkal (offset) pozicionálja őket,
     * hogy vizuálisan elkülönüljenek egymástól. Azokat a fonalakat is megjeleníti, amelyek csak egyik
     * végpontjukon kapcsolódnak a kirajzolt tektonokhoz — ezek egy félkörív vagy sugár irányába mutató
     * vonalként jelennek meg.</p>
     *
     * @param t a középső {@link Tekton}, amelyhez képest a fonalakat kirajzoljuk
     * @*/
            
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
            //System.out.println("Szog: " + angle);

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
                //System.out.println("angle: "+angle + "radius: "+radius);
                if(tektonok2.contains(t1)){
                    GTekton g1 = tektonok.get(t1);
                    int x1 = g1.getX();
                    //System.out.println(g1.getX());
                    int y1 = g1.getY();
                    //System.out.println(g1.getY());
                    GGombafonal gFonal = gombafonalak.get(fonal);
                    if (gFonal != null) {
                        int x_1 = x1+50;
                        int y_1 = y1+50;
                        int x_2 = (int)(x_1 + Math.cos(angle) * radius);
                        int y_2 = (int)(y_1 + Math.sin(angle) * radius);
                        
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
                    //System.out.println(g1.getX());
                    int y2 = g2.getY();
                    //System.out.println(g1.getY());
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
     * Megkeresi, hogy az egérkattintás (x, y) pozíciója közel esik-e valamelyik kirajzolt
     * {@link Gombafonal} vonalhoz, és ha igen, visszaadja az ahhoz tartozó {@link Gombafonal} objektumot.
     *
     * <p>A keresés során minden grafikus fonalvonalat (GGombafonal) vizsgál a {@code gombafonalak}
     * térképből, és ellenőrzi, hogy a megadott pont közel van-e a vonalhoz a 
     * {@code isPointNearLine()} segédfüggvény alapján.</p>
     *
     * <p>A y koordináta 230 pixellel csökkentésre kerül, hogy illeszkedjen a grafikai eltoláshoz
     * (valószínűleg UI eltolás miatt).</p>
     *
     * @param x az egérkattintás X koordinátája
     * @param y az egérkattintás Y koordinátája (módosítva lesz -230 pixellel)
     * @return a megtalált {@link Gombafonal} objektum, ha a pont közel esik valamelyik fonalhoz; különben {@code null}
     * */
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
     * Eldönti, hogy egy adott pont (px, py) a megadott szakaszhoz (x1, y1) - (x2, y2)
     * elég közel van-e egy előre meghatározott küszöbtávolság alapján.
     *
     * <p>A távolságot a {@code distanceToLine} segédfüggvény számítja ki, majd
     * összeveti egy fix, 10 pixeles küszöbértékkel.</p>
     * 
     * @param px a vizsgált pont X koordinátája (pl. egér pozíció)
     * @param py a vizsgált pont Y koordinátája
     * @param x1 a vonalszakasz kezdőpontjának X koordinátája
     * @param y1 a vonalszakasz kezdőpontjának Y koordinátája
     * @param x2 a vonalszakasz végpontjának X koordinátája
     * @param y2 a vonalszakasz végpontjának Y koordinátája
     * @return {@code true}, ha a pont a vonalhoz 10 pixelnél közelebb van; különben {@code false}
 */
    boolean isPointNearLine(double px, double py, double x1, double y1, double x2, double y2) {
        double distance = distanceToLine(px, py, x1, y1, x2, y2);
        //System.out.println("Táv: "+distance);
        return distance < 10; // Küszöb távolság: ha elég közel van, akkor igaz
    }
    /**
     * Kiszámítja egy pont (px, py) és egy vonalszakasz (x1, y1) - (x2, y2) közötti 
     * legrövidebb távolságot. A távolság mindig a szakaszra vetített merőleges
     * vagy a végpontokhoz mért távolság (ha a vetület a szakaszon kívül esik).
     *
     * <p>Hasznos például grafikus felületeken, ahol azt szeretnénk eldönteni,
     * hogy a felhasználó egy vonalszakaszra "kattintott"-e.</p>
     *
     * @param px a vizsgált pont X koordinátája
     * @param py a vizsgált pont Y koordinátája
     * @param x1 a szakasz kezdőpontjának X koordinátája
     * @param y1 a szakasz kezdőpontjának Y koordinátája
     * @param x2 a szakasz végpontjának X koordinátája
     * @param y2 a szakasz végpontjának Y koordinátája
     * @return a pont és a vonalszakasz közötti legrövidebb távolság
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

        double t = ((px - x1) * dx + (py - y1) * dy) / lengthSquared;

        double closestX = x1 + t * dx;
        double closestY = y1 + t * dy;

        double dxp = px - closestX;
        double dyp = py - closestY;

        return Math.sqrt(dxp * dxp + dyp * dyp);
    }
    /**
     * Megkeresi, hogy az egérkattintás (x, y) koordinátái alapján melyik {@link Gombatest}
     * objektumra kattintott a felhasználó. Ha van olyan gombatest, amelyhez a pont 35 pixelen
     * belül esik (kör alakú területen belül), akkor visszatér azzal.
     *
     * <p>A keresés során a grafikus Gombatestek (GGombatest) pozícióját is figyelembe veszi, 
     * és egy 230 pixeles függőleges eltolással kompenzálja az UI eltolását .</p>
     *
     * @param x az egér X koordinátája (képernyőpozíció)
     * @param y az egér Y koordinátája (képernyőpozíció, 230 pixellel csökkentve)
     * @return a megtalált {@link Gombatest} objektum, ha van a közelben; különben {@code null}
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
     * Megkeresi, hogy az adott (x, y) koordinátájú pont (pl. egérkattintás) melyik 
     * {@link Tekton} objektum grafikus reprezentációjához (GTekton) van elég közel.
     *
     * <p>Ez akkor hasznos, ha például az egérrel rá akarunk kattintani egy tektonra
     * a játékfelületen. A pozíció-eltolás (y - 230) valószínűleg a GUI felső paneljei
     * (pl. menüsáv) miatt szükséges.</p>
     *
     * @param x az egér X koordinátája
     * @param y az egér Y koordinátája (módosításra kerül -230 pixellel)
     * @return a megtalált {@link Tekton} objektum, ha van 50 pixelen belül; különben {@code null}
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
