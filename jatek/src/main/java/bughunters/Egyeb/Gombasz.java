package bughunters.Egyeb;

import java.util.ArrayList;

import bughunters.Gombafaj.Gombafaj;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Gombasz extends Jatekos{

    private Gombafaj gombafaj;

    /***
     * @brief Visszaadja a Gombász gombafaját.
     * @return Gombafaj
     */
    public Gombafaj getGombafaj() {
        return gombafaj;
    }
    
    /***
     * @brief Beállítja a Gombász gombafaját.
     * @param gombafaj A gombafaj objektum
     */
    public void setGombafaj(Gombafaj gombafaj) {
        this.gombafaj = gombafaj;
    }

    /***
     * @brief Gombasz konstruktor, amely névvel és gombafajjal inicializálja az objektumot.
     * @param nev A játékos neve
     * @param gfaj A gombafaj
     */
    Gombasz(String nev,Gombafaj gfaj){
        super(nev);
        this.gombafaj=gfaj;
    }

    /***
     * @brief Végrehajtja a gombafaj körvégi cselekedeteit.
     */
    public void korVegiCselekedetekRun(){
        try {
            gombafaj.korVegiCselekedetek();
        } catch (Exception e) {
            throw e;
        }
    }

    /***
     * @brief Gombafonal növesztése két tekton között.
     * @param t1 Az első tekton
     * @param t2 A második tekton
     * @throws Exception Ha nincs elég akciópont
     */
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

    /***
     * @brief Gombatest növesztése egy tektonon, opcionálisan spórával.
     * @param t A tekton, ahol növesztünk
     * @param sporaval Jelzi, hogy spórával történik-e a növesztés
     * @throws Exception Ha nincs elég akciópont
     */
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

    public void JatekEleiTestNovesztes(Tekton t, boolean sporaval) throws Exception{
        try {
            gombafaj.testNovesztes(t, sporaval);
        } catch (Exception e) {
            throw e;
        }
    }

    /***
     * @brief Spóra szórása egy tektonra adott gombatestre.
     * @param t A cél tekton
     * @param gt A cél gombatest
     * @throws Exception Ha nincs elég akciópont
     */
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

    /***
     * @brief Rovar megevése akció végrehajtása.
     * @param rovar A cél rovar
     * @throws Exception Ha nincs elég akciópont
     */
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

     /***
     * @brief Teszteléshez: a kör végén elvégzett műveletek.
     */
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
    @Override
    public String szerepKor(){
        return "Gombász";
    }
}
