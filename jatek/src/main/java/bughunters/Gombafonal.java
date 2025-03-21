package bughunters;

/**
 * A fonalAllapot enum a gombafonal állapotait reprezentálja.
 */
enum fonalAllapot {
    Ep,           // Az egészséges állapot
    Haldoklo,     //  haldokló állapot, még 2 körön belül helyrehozható
    UtolsoEsely   // Az utolsó esély állapot, még 1 körön belül helyrehozható
}

/**
 * A Gombafonal osztály egy gomba fonalát reprezentálja, amely két Tekton végpont között húzódik.
 * A fonal egy adott gombafajhoz tartozik, és különböző állapotokba kerülhet az idő múlásával.
 */
public class Gombafonal {

    /** A fonal aktuális állapota. Kezdetben ép. */
    private fonalAllapot allapot = fonalAllapot.Ep;

    /** Az időtartam, mióta a fonal létezik. */
    private int miota;

    /** A fonalhoz tartozó gombafaj. */
    private Gombafaj gombafaj;

    /** A fonal egyik végpontja. */
    private Tekton vegpont1;

    /** A fonal másik végpontja. */
    private Tekton vegpont2;

    /**
     * Megváltoztatja a fonal állapotát.
     * - Ha az állapot "Ép", akkor "Haldokló" lesz.
     * - Ha az állapot "Haldokló", akkor "UtolsóEsély" lesz.
     */
    public void allapotvalt() {
        if (allapot == fonalAllapot.Haldoklo) {
            allapot = fonalAllapot.UtolsoEsely;
        } else if (allapot == fonalAllapot.Ep) {
            allapot = fonalAllapot.Haldoklo;
        }
    }

    /**
     * Növeli az időtartamot, mióta a fonal létezik.
     * Ezt a metódust a control hívja.
     */
    public void tartozkodasNov() {
        miota += 1;
    }

    /**
     * Törli a fonalat a gombafaj és a végpontok közül.
     * A gombafaj és a két végpont értesül a fonal megszűnéséről.
     */
    public void vegpontTorles() {
        gombafaj.fonalSzakad(this);
        vegpont1.fonalSzakad(this);
        vegpont2.fonalSzakad(this);
    }
}
