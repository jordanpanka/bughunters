package bughunters.Egyeb;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Rovarasz extends Jatekos {
    private List<Rovar> rovarok;
    Color szin;
    /***
     * @brief A rovarok listájának lekérdezése
     * @return A Rovarász által irányított rovarok listája
     */
    public List<Rovar> getRovarok() {
        return rovarok;
    }

     /***
     * @brief A rovarok listájának beállítása
     * @param rovarok A beállítandó rovarok lista
     */
    public void setRovarok(List<Rovar> rovarok) {
        this.rovarok = rovarok;
    }

    /***
     * @brief Konstruktor, amely inicializálja a Rovarász nevét és a rovarok listáját
     * @param nev A Rovarász neve
     */
    public Rovarasz(String nev){
        super(nev);
        rovarok = new ArrayList<>();
    }
   
    /***
     * @brief Konstruktor, amely inicializálja a Rovarász nevét és a rovarok listáját
     * @param nev A Rovarász neve
     * @param szin A Rovarász rovarjainak a szine
     */
    public Rovarasz(String nev, Color szin){
        super(nev);
        rovarok = new ArrayList<>();
        this.szin = szin;
    }

    public Color getSzin() {
        return szin;
    }

    /***
     * @brief Új rovar hozzáadása a Rovarászhoz
     * @param rovar A hozzáadandó rovar
     */
    public void addRovar(Rovar rovar) {
        rovarok.add(rovar);
    }

    /***
     * @brief Rovar eltávolítása a Rovarászból
     * @param rovar A törlendő rovar
     */
    public void removeRovar(Rovar rovar) {
        rovarok.remove(rovar);
    }

    /***
     * @brief A Rovarász által végzett vágás művelet
     * @param gf A Gombafonal, amelyet a rovar vág
     * @param r A rovar, amely végrehajtja a vágást
     * @throws Exception Ha a rovar nem tartozik a Rovarászhoz vagy nincs elegendő akciópont
     */
    public void vag(Gombafonal gf, Rovar r) throws Exception {
        try {
            if(!rovarok.contains(r)){
                throw new Exception("A rovar nem tartozik a Rovarászhoz.");
            }
            if (getakcioSzama() < 1) {
                throw new Exception("Nincs elegendő akciópont.");
            }

            r.vag(gf);
            akciopontCsokkentes(1);
        } catch (Exception e) {
            throw e;
        }
        
    }

    /***
     * @brief A Rovarász mászó művelete, amely a rovarok állapotától függ
     * @param hova A célpont (Tekton), ahova a rovar mászik
     * @param r A mászó rovar
     * @throws Exception Ha a rovar nem tartozik a Rovarászhoz vagy nincs elegendő akciópont
     */
    public void maszik(Tekton hova, Rovar r) throws Exception {
       try {
            if(!rovarok.contains(r)){
                throw new Exception("A rovar nem tartozik a Rovarászhoz.");
            }

            switch (r.getAllapot()) {
                case Gyorsitott:
                    if (getakcioSzama() < 0.5) {
                        throw new Exception("Nincs elegendő akciópont.");
                    }
                    r.maszik(hova);
                    akciopontCsokkentes(0.5);
                    break;
                case Lassitott:
                    if (getakcioSzama() < 2) {
                        throw new Exception("Nincs elegendő akciópont.");
                    }
                    r.maszik(hova);
                    akciopontCsokkentes(2);
                    break;
                default:
                    if (getakcioSzama() < 1) {
                        throw new Exception("Nincs elegendő akciópont.");
                    }
                    r.maszik(hova);
                    akciopontCsokkentes(1);
                    break;
            }
           
        } catch (Exception e) {
            throw e;
        }
    }

     /***
     * @brief A Rovarász eszik művelete, ahol a rovar eszik egy spórát
     * @param sp A spóra, amit a rovar eszik
     * @param r A rovar, amely eszik
     * @throws Exception Ha a rovar nem tartozik a Rovarászhoz vagy nincs elegendő akciópont
     */
    public void eszik(Spora sp, Rovar r) throws Exception {
        try {
            if(!rovarok.contains(r)){
                throw new Exception("A rovar nem tartozik a Rovarászhoz.");
            }
            if (getakcioSzama() < 1) {
                throw new Exception("Nincs elegendő akciópont.");
            }

            r.eszik(sp);
            akciopontCsokkentes(1);
            gyozelmiPontokNovelese(sp.getTapertek());
        } catch (Exception e) {
            throw e;
        }
    }

    /***
     * @brief A rovarok állapotidejének növelése minden egyes rovar számára
     */
    public void rovarokAllapotIdejenekNovelese(){
        for (Rovar rovar : rovarok) {
            int ideiglenes = rovar.getAllapotIdeje();
            rovar.setAllapotIdeje(ideiglenes++);
        }
    }

    /***
     * @brief A rovarok visszaállítása alapállapotba, ha az állapotidejük nagyobb vagy egyenlő mint 1
     */
    public void rovarokAlapallapotbaHelyezese(){
        for (Rovar rovar : rovarok) {
            if(rovar.getAllapotIdeje()>=1){
                rovar.alapAllapot();
            }
        }
    }

    /***
     * @brief A kör végén végrehajtott műveletek
     * Az akciópontok 0-ra állítása, valamint a rovarok állapotidejének növelése és alapállapotba helyezése
     */
    @Override
    public void endTurnForTests(){
        this.setakcioSzama(0);
        rovarokAllapotIdejenekNovelese();
        rovarokAlapallapotbaHelyezese();
    }
}
