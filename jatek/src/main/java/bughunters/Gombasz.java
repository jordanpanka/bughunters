package bughunters;

import java.util.ArrayList;

public class Gombasz extends Jatekos{

    private Gombafaj gombafaj;

    public Gombafaj getGombafaj() {
        return gombafaj;
    }

    public void setGombafaj(Gombafaj gombafaj) {
        this.gombafaj = gombafaj;
    }

    Gombasz(String nev,Gombafaj gfaj){
        super(nev);
        this.gombafaj=gfaj;
    }

    public void korVegiCselekedetekRun(){
        try {
            gombafaj.korVegiCselekedetek();
        } catch (Exception e) {
            throw e;
        }
    }

    public void fonalNov(Tekton t1, Tekton t2) throws Exception{
        try {
            if (getakcioSzama() < 1) {
                throw new Exception("Nincs elegendő akciópont.");
            }

            gombafaj.fonalNov(t1, t2);
            akciopontCsokkentes(1);
        } catch (Exception e) {
            throw e;
        }
    }

    public void testNovesztes(Tekton t, boolean sporaval) throws Exception{
        try {
            if (getakcioSzama() < 2) {
                throw new Exception("Nincs elegendő akciópont.");
            }

            gombafaj.testNovesztes(t, sporaval);
            akciopontCsokkentes(2);
            gyozelmiPontokNovelese(1);
        } catch (Exception e) {
            throw e;
        }
    }

    public void sporaSzoras(Tekton t, Gombatest gt) throws Exception{
        try {
            if (getakcioSzama() < 1) {
                throw new Exception("Nincs elegendő akciópont.");
            }

            gombafaj.sporaSzoras(t, gt);
            akciopontCsokkentes(1);
        } catch (Exception e) {
            throw e;
        }
    }

    public void rovarEves(Rovar rovar) throws Exception{
        try {
            if (getakcioSzama() < 1) {
                throw new Exception("Nincs elegendő akciópont.");
            }

            gombafaj.rovarEves(rovar);
            akciopontCsokkentes(1);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void endTurnForTests(){
        this.setakcioSzama(0);
        korVegiCselekedetekRun();
        
        ArrayList<Gombafonal> fonalak = gombafaj.getGombafonalhalozat();
        ArrayList<Tekton> tektonok = new ArrayList<>();
        for (Gombafonal gombafonal : fonalak) {
            Tekton vegpont1 = gombafonal.getVegpont1();
            Tekton vegpont2 = gombafonal.getVegpont2();
            if (!tektonok.contains(vegpont1)) {
                tektonok.add(vegpont1);
            }
            if (!tektonok.contains(vegpont2)) {
                tektonok.add(vegpont2);
            }
        }
        for (Tekton tekton : tektonok) {
            tekton.gombafonalFelszivas();
        }
    }
}
