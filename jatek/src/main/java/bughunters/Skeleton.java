package bughunters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Skeleton {
    private static Skeleton instance;
    private String testCase;

    public static Skeleton getInstance() {
        if (instance == null) {
            instance = new Skeleton();
        }
        return instance;
    }

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    private Skeleton() {
        testCase = "";
    }

    public boolean Kerdes(String kerdes) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(kerdes);
        String valasz;
        try {
            valasz = r.readLine();
            valasz.toLowerCase();
            while (valasz.equals("igen") || valasz.equals("nem")) {
                if (valasz.equals("igen")) {
                    return true;
                } else if (valasz.equals("nem")) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Konkrét tesztesetekké írni őket.
    //Test-case diagrammok alapján kibővíteni a tesztelést és a KOMM. DIAGRAMMOKAT IS


    public void teszt13(){
        Tekton t1 = new Tekton();
        Tekton t2= new Tekton();
        Rovar r1 = new Rovar();
        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1 = new Gombafonal();

        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        r1.setTartozkodas(t1);
        t1.addFonal(gf1);
        t2.addFonal(gf1);
        g1.addFonal(gf1);
        gf1.setVegpont1(t1);
        gf1.setVegpont2(t2);
        gf1.setGombafaj(g1);

        try {
            r1.maszik(t2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void teszt14(){
        Tekton t1 = new Tekton();
        Tekton t2= new Tekton();
        Rovar r1 = new Rovar();
        Gombafaj g1 = new Gombafaj();

        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        r1.setTartozkodas(t1);

        try {
            r1.maszik(t2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void teszt15(){
        Tekton d1 = new Disszolator();
        Tekton t1 = new Tekton();
        Gombafonal gf1 = new Gombafonal();
        Gombafaj g1 = new Gombafaj();

        d1.addSzomszed(t1);
        t1.addSzomszed(d1);
        d1.addFonal(gf1);
        g1.addFonal(gf1);
        gf1.setVegpont1(d1);
        gf1.setVegpont2(t1);
        gf1.setGombafaj(g1);
        t1.addFonal(gf1);

        try {
            d1.gombafonalFelszivasa();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void teszt16(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        Gombatest gt1 = new Gombatest();
        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1_1 = new Gombafonal();
        Gombafonal gf1_2 = new Gombafonal();
        Rovar r1 = new Rovar();

       t1.addSzomszed(t2);
       t1.addFonal(gf1_1);

       t2.addSzomszed(t1);
       t2.addSzomszed(t3);
       t2.addFonal(gf1_1);
       t2.addFonal(gf1_2);

       t3.addSzomszed(t2);
       t3.addFonal(gf1_2);

       gt1.setTekton(t1);

       g1.addTest(gt1);
       g1.addFonal(gf1_1);
       g1.addFonal(gf1_2);

       gf1_1.setVegpont1(t1);
       gf1_1.setVegpont2(t2);
       gf1_2.setVegpont1(t2);
       gf1_2.setVegpont2(t3);

       gf1_1.setGombafaj(g1);
       gf1_2.setGombafaj(g1);

       r1.setTartozkodas(t2);

       try {
            r1.vag(gf1_1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void teszt17(){
        Rovar r1 = new Rovar();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Spora bs1 = new Benito();
        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1 = new Gombafonal();
        Gombatest gt1 = new Gombatest();
    
        g1.addTest(gt1);
        g1.addFonal(gf1);

        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t1.addFonal(gf1);
        t2.addFonal(gf1);

        gf1.setVegpont1(t1);
        gf1.setVegpont2(t2);

        bs1.setGombafaj(g1);

        gt1.setTekton(t1);

        t2.addSpora(bs1);

        r1.setTartozkodas(t2);

        gf1.setGombafaj(g1);

        try {
            r1.eszik(bs1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void teszt18(){
        Jatekter jt1 = new Jatekter();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();

        Gombafaj g1 = new Gombafaj();
        Gombatest gt1 = new Gombatest();
        Gombafonal gf1 = new Gombafonal();
        Gombafonal gf2 = new Gombafonal();
        

        jt1.tektonAdd(t1);
        jt1.tektonAdd(t2);
        jt1.tektonAdd(t3);

        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t2.addSzomszed(t3);
        t3.addSzomszed(t2);

        t1.addFonal(gf1);
        t2.addFonal(gf1);
        t2.addFonal(gf2);
        t3.addFonal(gf2);

        g1.addFonal(gf1);
        g1.addFonal(gf2);

        gf1.setVegpont1(t1);
        gf1.setVegpont2(t2);

        gf2.setVegpont1(t2);
        gf2.setVegpont2(t3);

        g1.addTest(gt1);

        gt1.setTekton(t2);

        gf1.setGombafaj(g1);

        try {
            jt1.tores(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void teszt19(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        Gombatest gt1 = new Gombatest();
        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1 = new Gombafonal();
        Gombafonal gf2 = new Gombafonal();

        t1.addSzomszed(t2);

        t2.addSzomszed(t1);
        t2.addSzomszed(t3);

        t3.addSzomszed(t2);

        t2.addFonal(gf1);
        t3.addFonal(gf1);

        gt1.setTekton(t1);

        g1.addTest(gt1);

        gf1.setVegpont1(t2);
        gf1.setVegpont2(t3);
        gf1.setGombafaj(g1);

        g1.addFonal(gf1);

        gf2.setVegpont1(t1);
        gf2.setVegpont2(t2);
        gf2.setGombafaj(g1);

        g1.addFonal(gf2);

        try {
            g1.lastChance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void teszt20(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        Gombatest gt1 = new Gombatest();
        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1 = new Gombafonal();

        t1.addSzomszed(t2);

        t2.addSzomszed(t1);
        t2.addSzomszed(t3);

        t3.addSzomszed(t2);

        t2.addFonal(gf1);
        t3.addFonal(gf1);

        gt1.setTekton(t1);

        g1.addTest(gt1);

        gf1.setVegpont1(t2);
        gf1.setVegpont2(t3);
        gf1.setGombafaj(g1);

        g1.addFonal(gf1);

        try {
            g1.lastChance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
