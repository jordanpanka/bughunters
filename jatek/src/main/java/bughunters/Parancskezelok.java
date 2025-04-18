package bughunters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
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

                }
            }
        }

        //ha benne van a HashMapben de a Rovarasz listájában nincs-->törölni kell a HashMapből
        for (Rovar rovarAHashMapben : rovarokAHashMapben) {
            if (!osszesRovar.contains(rovarAHashMapben)) {
                //ha nincs benne a Rovarasz listájában, de a HashMapben igen.
                objektumok.remove(objektumokbolString.get(rovarAHashMapben)); //eltávolítjuk a HashMapből
                objektumokbolString.remove(rovarAHashMapben); //eltávolítjuk a HashMapből
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
                }
            }

            //Ha a HashMapben van Gombafonal, de a Gombasz listájában nincs-->törölni kell a HashMapből
            for(Gombafonal hashmapGombafonal : gombafonalakAHashMapben) {
                if(!osszesGombafonalak.contains(hashmapGombafonal)){
                    //ha nincs benne a Gombaszok listájában, de a HashMapben igen.
                    objektumok.remove(objektumokbolString.get(hashmapGombafonal)); //eltávolítjuk a HashMapből
                    objektumokbolString.remove(hashmapGombafonal); //eltávolítjuk a HashMapből
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
                    Spora benitoSpora = (Benito)objektumok.get(kulcs);
                    sporakAHashMapben.add(benitoSpora);
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
                }
            }

            if(!tektonokAHashMapben.contains(tektonJatekteren)) {
                //ha nincs benne a HashMap-ben, de a Tekton listájában igen.
                String nev = tektonJatekteren.getClass().getSimpleName().toLowerCase();
                char tektonTipus = nev.charAt(0); //tekton tipus
                String ujTektonNev = ujTektonNev(tektonTipus); //tekton neve
                objektumok.put(ujTektonNev, tektonJatekteren); //tekton neve alapjan mentjuk el a Tektonokat a Map-en
                objektumokbolString.put(tektonJatekteren, ujTektonNev);
            }
        }
            //Ha a HashMapben van Spora, de az osszSpora listájában nincs-->törölni kell a HashMapből
            for(Spora hashmapSpora : sporakAHashMapben) {
                if(!osszesSpora.contains(hashmapSpora)){
                    //ha nincs benne a Tektonok listáiban, de a HashMapben igen.
                    objektumok.remove(objektumokbolString.get(hashmapSpora)); //eltávolítjuk a HashMapből
                    objektumokbolString.remove(hashmapSpora); //eltávolítjuk a HashMapből
                }
            }

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

                    //arrange parancsok
                    if (action.matches("/arrange -t \\S+")) {
                        String tektonTipus = action.split(" ")[2];
                        char tektonTip = tektonTipus.charAt(0);
                        Tekton ujTekton = tektonLetrehoz(tektonTip);
                        jatekter.tektonAdd(ujTekton);
                        objektumok.put(ujTektonNev(tektonTip), ujTekton);
                        objektumokbolString.put(ujTekton, ujTektonNev(tektonTip));
                    }
                    if(action.matches("/arrange -j \\S+ -r -t \\S+")){
                        rovaraszFelvetel(action);
                    }
                    if(action.matches("/arrange -szomszed -t \\S+ -t \\S+")){
                        String tekton1 = action.split(" ")[3];
                        String tekton2 = action.split(" ")[5];

                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);
                        Tekton Tekton2 = (Tekton)objektumok.get(tekton2);
                        Tekton1.addSzomszed(Tekton2);
                        Tekton2.addSzomszed(Tekton1);
                    }
                    if(action.matches("/arrange -j \\S+ -g \\S+")){
                        gombaszFelvetele(action);
                    }
                    if(action.matches("/arrange -gf -t1 \\S+ -t2 \\S+ -g \\S+")){
                        String gombafaj = action.split(" ")[7];
                        //char gombaf = gombafaj.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(gombafaj);

                        String tekton1 = action.split(" ")[3];
                        String tekton2 = action.split(" ")[5];

                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);
                        Tekton Tekton2 = (Tekton)objektumok.get(tekton2);

                        Gombafonal ujGombafonal = new Gombafonal(gf, Tekton1, Tekton2);
                        gf.addFonal(ujGombafonal);
                        Tekton1.addFonal(ujGombafonal);
                        Tekton2.addFonal(ujGombafonal);
                        objektumok.put(ujGombafonalNev(), ujGombafonal);
                        objektumokbolString.put(ujGombafonal, ujGombafonalNev());
                    }
                    if(action.matches("/arrange -s \\S+ -t \\S+ \\d+")){
                        String sporaTipus = action.split(" ")[2];
                        char sporaTip = sporaTipus.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(sporaTipus);

                        String tekton1 = action.split(" ")[4];
                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);

                        String szamResz = action.split(" ")[5];
                        int szam = Integer.parseInt(szamResz);

                        Spora ujSpora = sporaLetrehoz(sporaTip, gf, szam);
                        Tekton1.addSpora(ujSpora);
                        objektumok.put(ujSporaNev(sporaTip), ujSpora);
                        objektumokbolString.put(ujSpora, ujSporaNev(sporaTip));
                    }
                    if(action.matches("/arrange -gt \\S+ -t \\S+")){
                        String gombaTipus = action.split(" ")[2];
                        //char gombaTip = gombaTipus.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(gombaTipus);

                        String tekton1 = action.split(" ")[4];
                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);

                        Gombatest ujGombatest = new Gombatest(gf, Tekton1);
                        gf.addTest(ujGombatest);
                        objektumok.put(ujGombatestNev(), ujGombatest);
                        objektumokbolString.put(ujGombatest, ujGombatestNev());
                    }

                    //act parancsok
                    if(action.matches("/act -eszik -r \\S+ -g \\S+ -t \\S+")){
                        //Kérdés: Itt kell-e lekezelni, hogy ha a tekton és a rovar tartozkodása nem egyezik meg, akkor nem tud enni?
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        String tekton1 = action.split(" ")[7];
                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);

                        List<Spora> sporak = Tekton1.getSporak();
                        
                        String gombaTipus = action.split(" ")[5];
                        char gombaTip = gombaTipus.charAt(0);

                        for(Spora spora : sporak) {
                            String sporaTipus = objektumokbolString.get(spora);
                            char sporaTip = sporaTipus.charAt(0);
                            if(sporaTip == gombaTip) {
                                rovar.eszik(spora);
                                break;
                            }
                        }
                    }
                    if(action.matches("/act -vag -r \\S+ -gf \\S+")){
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        String gombafonalStr = action.split(" ")[5];
                        Gombafonal gombafonal = (Gombafonal)objektumok.get(gombafonalStr);

                        rovar.vag(gombafonal);
                    }
                    if(action.matches("/act -maszik -r \\S+ -t \\S+")){
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        String tektonStr = action.split(" ")[5];
                        Tekton tekton = (Tekton)objektumok.get(tektonStr);

                        rovar.maszik(tekton);
                    }
                    if(action.matches("/act -reszik -r \\S+")){
                        String rovarStr = action.split(" ")[3];
                        Rovar rovar = (Rovar)objektumok.get(rovarStr);

                        Gombasz gombasz = (Gombasz)aktivJatekos;
                        gombasz.rovarEves(rovar);
                    }
                    if(action.matches("/act -gfnov -t \\S+ -t \\S+ -g \\S+")){
                        String gombafaj = action.split(" ")[7];
                        //char gombaf = gombafaj.charAt(0);
                        Gombafaj gf = (Gombafaj)objektumok.get(gombafaj);

                        String tekton1 = action.split(" ")[3];
                        String tekton2 = action.split(" ")[5];

                        Tekton Tekton1 = (Tekton)objektumok.get(tekton1);
                        Tekton Tekton2 = (Tekton)objektumok.get(tekton2);

                        gf.fonalNov(Tekton1, Tekton2);
                    }
                    if(action.matches("/act -spszor -gt \\S+")){
                        String gombatestStr = action.split(" ")[3];
                        Gombatest gombatest = (Gombatest)objektumok.get(gombatestStr);

                        Tekton tartozkodas = gombatest.getTekton();
                        
                        Gombasz gombasz = (Gombasz)aktivJatekos;
                        gombasz.sporaSzoras(tartozkodas, gombatest);
                    }
                    if(action.matches("/act -tores -t \\S+")){
                        String tektonStr = action.split(" ")[3];
                        Tekton tekton = (Tekton)objektumok.get(tektonStr);

                        jatekter.tores(1, tekton, true);
                    }
                    if(action.matches("/act -gt -t \\S+")){
                        String tektonStr = action.split(" ")[3];
                        Tekton tekton = (Tekton)objektumok.get(tektonStr);

                        Gombasz gombasz = (Gombasz)aktivJatekos;
                        gombasz.testNovesztes(tekton, true);
                    }
                    if(action.matches("/act -gt \\S+ -fejlett")){
                        String gombatestStr = action.split(" ")[3];
                        Gombatest gombatest = (Gombatest)objektumok.get(gombatestStr);

                        int gtFejlettsegIdo = gombatest.getGombafaj().getGombatestFejlettsegIdo();

                        gombatest.setKor(gtFejlettsegIdo);
                    }
                    if(action.matches("/act -aktivJatekos \\S+")){
                        for(int i = 0; i < jatekosok.size(); i++){
                            if(jatekosok.get(i).getNev().equals(action.split(" ")[2])){
                                aktivJatekos = jatekosok.get(i);
                                break;
                            }
                        }
                    }
                    if(action.matches("/act -endTurn")){
                        aktivJatekos.korVege();
                    }
                }
            } catch (Exception e) {
                throw e;
            }

    }


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
                    if (kulcs.matches("vagaskeptelenio\\d+")) {
                        String szamResz = kulcs.substring(15); // levágjuk az "tekton"-t
                        int szam = Integer.parseInt(szamResz);
                        if (szam > maxSzamV) {
                            maxSzamV = szam;
                        }
                    }
                }
                return "vagaskeptelenio" + (maxSzamV + 1); // új név a következő Rovarhoz
                
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
    public void listGf(PrintStream output){
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

    public void listGt(PrintStream output){
        List<String> nevek = new ArrayList<>();
        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("gombatest\\d+")) {
                nevek.add(kulcs);
            }
        }
        if(nevek.isEmpty()){
            output.println("Nincs gombatest a palyan");
            return;
        }
        szamszeruSort(nevek);
        for(String nev : nevek) {
            output.println(nev);
        }
    }

    public void listRovarok(PrintStream output){
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

    public void listTektonok(PrintStream output){
        List<String> tektonNevek = new ArrayList<>();
        List<String> puritektonNevek = new ArrayList<>();
        List<String> disszolatorNevek = new ArrayList<>();
        List<String> monotektonNevek = new ArrayList<>();
        List<String> infinatorNevek = new ArrayList<>();

        for(String kulcs : objektumok.keySet()) {
            if (kulcs.matches("tekton\\d+")) {
                tektonNevek.add(kulcs);
            }
            if (kulcs.matches("puritekton\\d+")) {
                    puritektonNevek.add(kulcs);
            }
            if (kulcs.matches("disszolator\\d+")) {
                    disszolatorNevek.add(kulcs);
            }
            if (kulcs.matches("monotekton\\d+")) {
                    monotektonNevek.add(kulcs);
            }
            if (kulcs.matches("infinator\\d+")) {
                    infinatorNevek.add(kulcs);
            }
        }
        if(tektonNevek.isEmpty() && puritektonNevek.isEmpty() && disszolatorNevek.isEmpty() && monotektonNevek.isEmpty() && infinatorNevek.isEmpty()){
            return;
        }

        szamszeruSort(tektonNevek);
        szamszeruSort(puritektonNevek);
        szamszeruSort(disszolatorNevek);
        szamszeruSort(monotektonNevek);
        szamszeruSort(infinatorNevek);

        for(String nev : disszolatorNevek) {
            output.println(nev);
        }
        for(String nev : infinatorNevek) {
            output.println(nev);
        }
        for(String nev : monotektonNevek) {
            output.println(nev);
        }
        for(String nev : puritektonNevek) {
            output.println(nev);
        }
        for(String nev : tektonNevek) {
            output.println(nev);
        }
    }

    // /act SPORASZOR-nal ha sikeres a lefutás akkor a Sporat fel kell venni a HashMap-be. Megvizsgálni hogy élettartalma lejár-e, ha igen a testet is kivenni a HashMap-ből.
    // /act fonalvagas sikeres lefutása esetén megkeressük az elvágott fonalat a HashMap-ben és eltávolítjuk.
    // /act gombatestnovesztese sikeres lefutása esetén megkeressük a gombatestet és hozzáadjuk a HashMap-hez.
    // ha elhal egy fonal idővel a HashMapből el kell távolítani (hogy a retekbe?)
    // /act tores-nél megkeressük az új tektont és hozzáadjuk a HashMap-hez.

    public void listSpora(PrintStream output){
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
            }else if (kulcs.matches("vagaskeptelenito\\d+")) {
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

    public void listJatekosok(PrintStream output){
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

    public void listGombaszok(PrintStream output){
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

    public void listRovaraszok(PrintStream output){
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

    public void listGFSzomszedok(PrintStream output, String tektonNev){
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

    public void listTektonSzomszedok(PrintStream output, String tektonNev){
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

    public void showRovarAllapot(PrintStream output, String rovarNev){
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

    public void szamszeruSort(List<String> nevek) {
        nevek.sort((a, b) -> {
            int numA = Integer.parseInt(a.replaceAll("\\D+", ""));
            int numB = Integer.parseInt(b.replaceAll("\\D+", ""));
            return Integer.compare(numA, numB);
        });
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
                        throw new Exception("Mar van ilyen nevu jatekos");
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

    

    public Gombafaj createGombafajBySpora(char sporaTipus){
            String nev="";
            int termelesIdeje=0;
            int eddigNovesztettTestekSzama=0;
            int gombatestFejlettsegIdo=0;
            int gombatestEletSzama=0;
            int gombafonalEletSzama=0;
         switch (sporaTipus) {
                        case 'o':
                            nev = "Foltos pöffeteg";
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
                            nev = "Szegfűgomba";
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


    public void gombaszFelvetele(String parancs) throws Exception{
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

    public String ujRovarNev(){
        int maxRovarSzam = 0;
        for (String kulcs : objektumok.keySet()) {
            if (kulcs.matches("rovar\\d+")) {
                String szamResz = kulcs.substring(5); // levágjuk az "r"-t
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
