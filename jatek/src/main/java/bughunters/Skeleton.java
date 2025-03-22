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

    //Gombafonal növesztése Gombafonalból
    //Gombafonal növesztése Tektonra Gombafonalból, SIKERES
    public void teszt1(){
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

        try {
            g1.fonalNov(t2, t3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Gombafonal növesztése Gombafonalból
    //Gombafonal növesztése Tektonra Gombafonalból, SIKERTELEN: nem szomszédosak
    public void teszt2(){
        Tekton t1= new Tekton();
        Tekton t2= new Tekton();
        Tekton t3 = new Tekton();
        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1 = new Gombafonal();

        t1.addSzomszed(t2);
        t1.addFonal(gf1);

        t2.addSzomszed(t1);
        t2.addFonal(gf1);

        g1.addFonal(gf1);

        gf1.setGombafaj(g1);
        gf1.setVegpont1(t1);
        gf1.setVegpont2(t2);

        try {
            g1.fonalNov(t2, t3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Gombafonal növesztése Gombafonalból
    //Gombafonal növesztése Tektonra Gombafonalból, SIKERTELEN: nincs kezdeti gombafonal
    public void teszt3(){
        Tekton t1= new Tekton();
        Tekton t2= new Tekton();
        Tekton t3 = new Tekton();
        Gombafaj g1 = new Gombafaj();

        t1.addSzomszed(t2);
        t1.addSzomszed(t3);

        t2.addSzomszed(t1);
        t2.addSzomszed(t3);

        t3.addSzomszed(t1);
        t3.addSzomszed(t2);

        try {
            g1.fonalNov(t2, t3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Gombafonal növesztése Monotektonra Gombafonalból, SIKERES
    //Gombafonal növesztése Monotektonra Gombafonalból
    public void teszt4(){
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

        try {
            g1.fonalNov(t2, m1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //Gombafonal növesztése Monotektonra Gombafonalból, SIKERTELEN: m1-el jelen van másik gombafaj fonala
    //Gombafonal növesztése Monotektonra Gombafonalból
    public void teszt5(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton m1 = new Monotekton();

        Gombafaj g1 = new Gombafaj();
        Gombafaj g2 = new Gombafaj();
        Gombafonal gf1 = new Gombafonal();
        Gombafonal gf2 = new Gombafonal();

        t1.addSzomszed(t2);
        t1.addSzomszed(m1);
        t1.addFonal(gf1);

        t2.addSzomszed(t1);
        t2.addSzomszed(m1);
        t2.addFonal(gf1);

        m1.addSzomszed(t1);
        m1.addSzomszed(t2);

        g1.addFonal(gf1);
        g2.addFonal(gf2);

        gf1.setVegpont1(t1);
        gf1.setVegpont2(t2);
        gf1.setGombafaj(g1);

        gf2.setVegpont1(t2);
        gf2.setVegpont2(m1);
        gf2.setGombafaj(g2);

        try {
            g1.fonalNov(t2, m1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Gombafonal növesztése Tektonra Gombatestből, SIKERES
    //Gombafonal növesztése Gombatestből
    public void teszt6(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Gombafaj g1 = new Gombafaj();
        Gombatest gt1 = new Gombatest();

        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        g1.addTest(gt1);
        gt1.setTekton(t1);

        try {
            g1.fonalNov(t1, t2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Gombafonal növesztése Tektonra Gombatestből, SIKERTELEN
    //Gombafonal növesztése Gombatestből
    public void teszt7(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Gombafaj g1 = new Gombafaj();

        t1.addSzomszed(t2);
        t2.addSzomszed(t1);

        try {
            g1.fonalNov(t1, t2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    //Gombatest növesztése Tektonra
    //Gombatest növesztés Tektonra
    public void teszt8(){
        Gombafaj g1 = new Gombafaj();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Spora s1 = new Gyorsito();
        Gombafonal gf1 = new Gombafonal();
       

       g1.addFonal(gf1);

       s1.setGombafaj(g1);
       t1.addSzomszed(t2);

       t2.addSzomszed(t1);

       t1.addFonal(gf1);
       t2.addFonal(gf1);

       gf1.setVegpont1(t1);
       gf1.setVegpont2(t2);

       t1.addSpora(s1);

       gf1.setGombafaj(g1);

         try {
            g1.testNovesztes(t1);
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
    }

    //Gombatest növesztése Tektonra, SIKERTELEN: nincs g1 típusú spóra a test növesztésére
    //Gombatest növesztés Tektonra
    public void teszt9(){
        Gombafaj g1 = new Gombafaj();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Gombafonal gf1 = new Gombafonal();
        

       g1.addFonal(gf1);

       t1.addSzomszed(t2);

       t2.addSzomszed(t1);

       t1.addFonal(gf1);
       t2.addFonal(gf1);

       gf1.setVegpont1(t1);
       gf1.setVegpont2(t2);

       gf1.setGombafaj(g1);

        try {
            g1.testNovesztes(t1);
         } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Gombatest növesztése Tektonra, SIKERTELEN: van már gombatest t1 tektonon
    //Gombatest növesztés Tektonra
    public void teszt10(){
        Gombafaj g1 = new Gombafaj();
        Gombafaj g2 = new Gombafaj();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Gombafonal gf1 = new Gombafonal();
        Gombatest gt1 = new Gombatest();

        Spora s1 = new Gyorsito();
     

       g1.addFonal(gf1);

       t1.addSzomszed(t2);

       t2.addSzomszed(t1);

       t1.addFonal(gf1);
       t2.addFonal(gf1);

       gf1.setVegpont1(t1);
       gf1.setVegpont2(t2);

       gt1.setTekton(t1);
       g2.addTest(gt1);
       gf1.setGombafaj(g1);

        s1.setGombafaj(g1);
       t1.addSpora(s1);

       try {
            g1.testNovesztes(t1);
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
    }

    //Gombatest növesztése Puritektonra
    //Gombatest növesztése Puritektonra
    public void teszt11(){
        Tekton t1 = new Tekton();
        Tekton p1 = new Puritekton();
        Gombafaj g1 = new Gombafaj();
        Gombafonal gf1_1 = new Gombafonal();
        Spora b1 = new Benito();

       t1.addSzomszed(p1);
       t1.addFonal(gf1_1);
        t1.addSpora(b1);

       p1.addSzomszed(t1);
       p1.addFonal(gf1_1);
        p1.addSpora(b1);

       g1.addFonal(gf1_1);

       gf1_1.setVegpont1(t1);
       gf1_1.setVegpont2(p1);
       gf1_1.setGombafaj(g1);

       b1.setGombafaj(g1);
        try {
           g1.testNovesztes(p1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
    }

    //Spóra szórása még nem létező Spórával
    //Fejlett Spora Szoras
    public void teszt12(){
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

        try {
            g1.sporaSzoras(t1, gt1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
        Disszolator d1 = new Disszolator();
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
            d1.gombafonalFelszivas();
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
