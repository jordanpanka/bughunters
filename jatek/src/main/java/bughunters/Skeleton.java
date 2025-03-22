package bughunters;

import java.awt.PaintContext;
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

    public void teszt1_setup(){
        Tekton t1= new Tekton();
        Tekton t2= new Tekton();
        Tekton t3 = new Tekton();
        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1 = new Gombafonal();

        t1.addSzomszed(t2);
        t1.addSzomszed(t3);
        t1.addFonal(gf1);

        t2.addSzomszed(t1);
        t2.addSzomszed(t3);
        t2.addFonal(gf1);

        t3.addSzomszed(t1);
        t3.addSzomszed(t2);

        g1.addFonal(gf1);

        gf1.setGombafaj(g1);
        gf1.setVegpont1(t1);
        gf1.setVegpont2(t2);
    }

    public void teszt2_setup(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Gombafaj g1 = new Gombafaj();
        Gombatest gt1 = new Gombatest();

        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        g1.addTest(gt1);
        gt1.setTekton(t1);
    }

    public void teszt3_setup(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton m1 = new Monotekton();

        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1 = new Gombafonal();

        t1.addSzomszed(t2);
        t1.addSzomszed(m1);
        t1.addFonal(gf1);

        t2.addSzomszed(t1);
        t2.addSzomszed(m1);
        t2.addFonal(gf1);

        m1.addSzomszed(t1);
        m1.addSzomszed(t2);

        g1.addFonal(gf1);

        gf1.setVegpont1(t1);
        gf1.setVegpont2(t2);
        gf1.setGombafaj(g1);

    }

    public void teszt4_setup(){
        Gombafaj g1 = new Gombafaj();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Spora s1 = new Gyorsito();
        Gombafonal gf1 = new Gombafonal();
        Gombatest gt1 = new Gombatest();

       g1.addFonal(gf1);

       s1.setGombafaj(g1);
       t1.addSzomszed(t2);

       t2.addSzomszed(t1);

       t1.addFonal(gf1);
       t2.addFonal(gf1);

       gf1.setVegpont1(t1);
       gf1.setVegpont2(t2);

       t2.addSpora(s1);

       gt1.setTekton(t1);
       g1.addTest(gt1);
       gf1.setGombafaj(g1);
    }

    public void teszt5_setup(){
        Tekton t1 = new Tekton();
        Tekton p1 = new Puritekton();
        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1_1 = new Gombafonal();
        Spora b1 = new Benito();

       t1.addSzomszed(p1);
       t1.addFonal(gf1_1);

       p1.addSzomszed(t1);
       p1.addFonal(gf1_1);

       g1.addFonal(gf1_1);

       gf1_1.setVegpont1(t1);
       gf1_1.setVegpont2(p1);
       gf1_1.setGombafaj(g1);

       b1.setGombafaj(g1);
    }

    public void teszt6_setup(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Gombafaj g1 = new Gombafaj();
        Gombatest gt1 = new Gombatest();
        Spora b1 = new Benito();

        t1.addSzomszed(t2);

        t2.addSzomszed(t1);
        t2.addSpora(b1);

        g1.addTest(gt1);

        gt1.setTekton(t1);

        b1.setGombafaj(g1);
    }

    public void teszt7_setup(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Gombafaj g1 = new Gombafaj();
        Gombatest gt1 = new Gombatest();

        t1.addSzomszed(t2);

        t2.addSzomszed(t1);

        g1.addTest(gt1);

        gt1.setTekton(t1);
    }

    public void teszt8_setup(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        Gombafaj g1 = new Gombafaj();
        Gombatest gt1 = new Gombatest();

        t1.addSzomszed(t2);

        t2.addSzomszed(t1);
        t2.addSzomszed(t3);

        t3.addSzomszed(t2);

        g1.addTest(gt1);

        gt1.setTekton(t1);
    }

    public void teszt9_setup(){
        //ROVAR MÁSZIK FONAL NINCS-->KOMM.DIAGRAM
        Tekton t1 = new Tekton();
        Tekton t2= new Tekton();
        Rovar r1 = new Rovar();

        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        r1.setTartozkodas(t1);
    }

    public void teszt10_setup(){
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
    }

    public void teszt11_setup(){
        Jatekter jt = new Jatekter();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();

        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1 = new Gombafonal();
        Gombafonal gf2 = new Gombafonal();
        Gombatest gt1 = new Gombatest();

        jt.tektonAdd(t1);
        jt.tektonAdd(t2);
        jt.tektonAdd(t3);

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

    }

    public void teszt12_setup(){
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

       t3.addSzomszed(t2);
       
       t2.addFonal(gf1_1);
       t2.addFonal(gf1_2);

       t3.addFonal(gf1_2);

       gt1.setTekton(t1);

       g1.addTest(gt1);

       gf1_1.setVegpont1(t1);
       gf1_1.setVegpont2(t2);
       gf1_2.setVegpont1(t2);
       gf1_2.setVegpont2(t3);

       gf1_1.setGombafaj(g1);
       gf1_2.setGombafaj(g1);

       r1.setTartozkodas(t2);

       g1.addFonal(gf1_1);
       g1.addFonal(gf1_2);

    }

    public void teszt13_setup(){
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

    }

    public void teszt14_setup(){
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
    }
}






