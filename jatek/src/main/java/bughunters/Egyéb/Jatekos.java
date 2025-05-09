package bughunters;
public abstract class Jatekos {

    private double akcioSzama;
    private String nev;
    private int gyozelmiPontok;

    /***
     * @brief Konstruktor, amely beállítja a játékos nevét és alapértelmezett győzelmi pontokat
     * @param nev Játékos neve
     */
    public Jatekos(String nev){
        this.nev=nev;
        this.gyozelmiPontok=0;
    }

     /***
     * @brief Akciópontok számának beállítása
     * @param akcioSzama Az akciópontok száma
     */
    public void setakcioSzama(double akcioSzama){
        this.akcioSzama=akcioSzama;
    }
    
      /***
     * @brief Akciópontok számának lekérdezése
     * @return Az akciópontok aktuális száma
     */
    public double getakcioSzama(){
        return akcioSzama;
    }
   
    /***
     * @brief A játékos nevének lekérdezése
     * @return A játékos neve
     */
    public String getNev() {
        return nev;
    }
    
    /***
     * @brief A játékos nevének beállítása
     * @param nev A játékos neve
     */
    public void setNev(String nev) {
        this.nev = nev;
    }
    
    /***
     * @brief A játékos győzelmi pontjainak lekérdezése
     * @return A győzelmi pontok száma
     */
    public int getGyozelmiPontok() {
        return gyozelmiPontok;
    }
    
    /***
     * @brief A játékos győzelmi pontjainak beállítása
     * @param gyozelmiPontok A győzelmi pontok új értéke
     */
    public void setGyozelmiPontok(int gyozelmiPontok) {
        this.gyozelmiPontok = gyozelmiPontok;
    }
    
    /***
     * @brief A győzelmi pontok növelésére szolgáló függvény
     * @param novel A növelés mértéke
     */
    public void gyozelmiPontokNovelese(int novel) {
        gyozelmiPontok += novel;
    }

    /***
     * @brief Akciópontok csökkentése, ha nem kerül negatív értékre
     * @param minusz A csökkentés mértéke
     * @throws Exception Ha az akciópontok negatívvá válnak
     */
    public void akciopontCsokkentes(double minusz) throws Exception {
        double jelenlegiAkciopont = getakcioSzama();
        jelenlegiAkciopont = jelenlegiAkciopont - minusz;
        if (jelenlegiAkciopont < 0) {
            throw new Exception("Akciópont nem lehet negatív.");
        }
        setakcioSzama(jelenlegiAkciopont);
    }

    /***
     * @brief Az akciópontok visszaállítása alapértelmezett értékre (3)
     */
    public void akciopontAlapbaallit(){
        akcioSzama=3;
    }
    
    /***
     * @brief A kör végét jelző függvény, amely 0-ra állítja az akciópontokat
     */
    public void korVege(){
        akcioSzama=0;
    }

     /***
     * @brief A kör végét jelző tesztfüggvény (jelenleg üres)
     */
    public void endTurnForTests(){

    }
}
