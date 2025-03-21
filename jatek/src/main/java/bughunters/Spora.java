package bughunters; 

/**
 * Az Spora osztály egy absztrakt ősosztály
 * A spóra rendelkezik tápértékkel és mennyiséggel, valamint hatással lehet rovarokra.
 * 
 * Az osztály kétféle fogyasztási metódust biztosít:
 * - Egy általános fogyasztási metódust, amely csökkenti a spóra mennyiségét, ezt használjuk gombatest növesztés esetén
 * - Egy második verziót, amely egy rovarra is hatást gyakorol a fogyasztás során.
 */
abstract class Spora {
    private int tapertek;
    private int mennyiseg;
    private Gombafaj gombafaj;

    /**
     * Absztrakt metódus, amely meghatározza, hogy a spóra milyen hatást gyakorol a rovarra.
     * Az leszármazottaknak kell megvalósítaniuk ezt a metódust.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    public abstract void hatas(Rovar r);

    /**
     * Csökkenti a spóra mennyiségét a megadott mennyiséggel, amennyiben az elegendő.
     * Ha a spóra mennyisége nem elegendő a művelet végrehajtásához, kivételt dob.
     *
     * @param mennyit a fogyasztandó spóra mennyisége
     * @throws Exception ha nincs elegendő spóra
     */
    public void fogyaszt(int mennyit) throws Exception {
        if (mennyiseg - mennyit >= 0) {
            mennyiseg -= mennyit;
        } else {
            throw new Exception("Nincs elég spóra az akció végrehajtására");
        }
    }

    /**
     * Csökkenti a spóra mennyiségét a megadott mennyiséggel, és hatást gyakorol egy adott rovarra.
     * Ha a spóra mennyisége nem elegendő, kivételt dob.
     *
     * @param mennyit a fogyasztandó spóra mennyisége
     * @param r a rovar, amelyre a spóra hatást fejt ki a fogyasztás során
     * @throws Exception ha nincs elegendő spóra
     */
    public void fogyaszt(int mennyit, Rovar r) throws Exception {
        if (mennyiseg - mennyit >= 0) {
            mennyiseg -= mennyit;
            hatas(r);
        } else {
            throw new Exception("Nincs elég spóra az akció végrehajtására");
        }
    }
}