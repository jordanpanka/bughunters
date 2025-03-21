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
    private fonalAllapot allapot;

    /** Az időtartam, mióta a fonal létezik. */
    private int miota;

    /** A fonalhoz tartozó gombafaj. */
    private Gombafaj gombafaj;

    /** A fonal egyik végpontja. */
    private Tekton vegpont1;

    /** A fonal másik végpontja. */
    private Tekton vegpont2;

    public Gombafonal(Gombafaj gf, Tekton veg1, Tekton veg2)
    {
        allapot=fonalAllapot.Ep;
        miota=0;
        vegpont1=veg1;
        vegpont2=veg2;
    }

    /**
     * Megváltoztatja a fonal állapotát.
     * - Ha az állapot "Ép", akkor "Haldokló" lesz.
     * - Ha az állapot "Haldokló", akkor "UtolsóEsély" lesz.
     */
    public void allapotvalt() {
        System.out.println("Meghívódik a gombafonal osztaly allapotvalt() metodusa.");
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
        System.out.println("Meghívódik a gombafonal osztaly tartozkodasNov() metodusa.");
    }

    /**
     * Törli a fonalat a gombafaj és a végpontok közül.
     * A gombafaj és a két végpont értesül a fonal megszűnéséről.
     */
    public void vegpontTorles() {
        System.out.println("Meghívódik a gombafonal osztaly vegpontTorles() metodusa.");
        gombafaj.fonalSzakad(this);
        vegpont1.fonalSzakad(this);
        vegpont2.fonalSzakad(this);
        
    }

     /**
     * Visszaadja, hogy mióta él a gombafonal
     */
    public int getMiota()
    {
            return miota;
    }

     /**
     * Visszaadja a gombafonal aktuális állapotát
     */
    public fonalAllapot getAllapot()
    {
        return allapot;
    }

    /**
     *  allapot attribútum settere
     */
    public void setAllapot(fonalAllapot uj)
    {
        allapot=uj;
    }

    /**
     *  gombafaj attribútum gettere
     */
    public Gombafaj getGombafaj()
    {
        return gombafaj;
    }

    /**
     *  vegpont1 attribútum gettere
     */
    public Tekton getVegpont1()
    {
        return vegpont1;
    }

    /**
     *  vegpont1 attribútum settere
     */
    public void setVegpont1(Tekton t)
    {
        vegpont1=t;
    }

     /**
     *  vegpont2 attribútum gettere
     */
    public Tekton getVegpont2()
    {
        return vegpont2;
    }

    /**
     *  vegpont2 attribútum settere
     */
    public void setVegpont2(Tekton t)
    {
        vegpont2=t;
    }
}
