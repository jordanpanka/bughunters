package bughunters;
public class Gombatest {
    /*private int sporaRaktar;
    private int kor;*/
    private Gombafaj gombafaj;
    private Tekton tekton;


    /**
     * @brief Alapértelmezett konstruktor, amely létrehoz egy új Gombatest példányt.
     */
    public Gombatest(){
        System.out.println("Létrejött egy Gombatest.");
    }

    /**
     * @brief Paraméteres konstruktor egy új Gombatest példány létrehozására.
     * 
     * @param sporaRaktar A gombatest spóra raktárának kezdeti értéke (jelenleg nem inicializált).
     * @param kor A gombatest életkora (jelenleg nem inicializált).
     * @param gombafaj A gombatesthez tartozó gombafaj.
     * @param tekton A Tekton, amin a gombatest elhelyezkedik.
     */
    public Gombatest(/*int sporaRaktar, int kor,*/ Gombafaj gombafaj, Tekton tekton) {
        /*this.sporaRaktar = sporaRaktar;
        this.kor = kor;*/
        this.gombafaj = gombafaj;
        this.tekton = tekton;
        System.out.println("Létrejött egy új Gombatest.");
    }

    /**
     * @brief Visszaadja, hogy a gombatest melyik Tektonon van jelen.
     * 
     * @return A Tekton, amin a gombatest elhelyezkedik.
     */
    public Tekton getTekton() {
        return tekton;
    }
    

    /**
     * @brief Beállítja, hogy a gombatest melyik Tektonon helyezkedik el.
     * 
     * @param tekton Az új Tekton.
     */
    public void setTekton(Tekton tekton) {
        this.tekton = tekton;
    }
    /* 
    public int getSporaRaktar() {
        return sporaRaktar;
    }
    
    public void setSporaRaktar(int sporaRaktar) {
        this.sporaRaktar = sporaRaktar;
    }
    
    public int getKor() {
        return kor;
    }
    
    public void setKor(int kor) {
        this.kor = kor;
    }*/


    /**
     * @brief Megpróbálja kiüríteni a gombatest spóra raktárát.
     * Ha nincs elég spóra, kivételt dob.
     * 
     * @throws Exception Ha nincs elég spóra a szóráshoz.
     */
    public void urit() throws Exception{
        System.out.println("Meghívódik a Gombatest urit metodusa.");
        if(Skeleton.getInstance().Kerdes("Van elég spóra a szóráshoz?") ){
        }
        else
        {
            throw new Exception("Nem tud spórát szórni.");
        }
    }

    /**
     * @brief Visszaadja a gombatesthez tartozó gombafajt.
     * 
     * @return A gombatest gombafaja.
     */
    public Gombafaj getGombafaj() {
        return gombafaj;
    }
    
    /**
     * @brief A gombatest a körök végén spórát gyűjt.
     */
    public void sporaGyujtes(){
        System.out.println("Meghívódik a Gombatest sporaGyujtes metodusa.");

    }
    
}
