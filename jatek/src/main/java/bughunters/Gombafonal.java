package bughunters;

/**
 * @brief A fonalAllapot enum a gombafonal állapotait reprezentálja.
 */
enum fonalAllapot {
    Ep,           // Az egészséges állapot
    Haldoklo,     //  haldokló állapot, még 2 körön belül helyrehozható
    UtolsoEsely   // Az utolsó esély állapot, még 1 körön belül helyrehozható
}

/**
 *   @brief A Gombafonal osztály egy gomba fonalát reprezentálja, amely két Tekton végpont között húzódik.
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

    /**
     *  @brief a gombafonal osztály konstruktora
     */
    public Gombafonal(Gombafaj g, Tekton veg1, Tekton veg2)
    {
        allapot=fonalAllapot.Ep;
        miota=0;
        vegpont1=veg1;
        vegpont2=veg2;
        gombafaj=g;
        System.out.println("Létrejött egy új Gombafonal:" );
        
    }
    /**
     *  @brief a Gombafonal osztály paraméter nélküli konstruktora
     */
    public Gombafonal()
    {
        System.out.println("Létrejött egy új Gombafonal:" );
    }

    /**
     * @brief  Megváltoztatja a fonal állapotát.
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
     *  @brief Növeli az időtartamot, mióta a fonal létezik.
     * Ezt a metódust a control hívja.
     */
    public void tartozkodasNov() {
       // miota += 1;
        System.out.println("Meghívódik a gombafonal osztaly tartozkodasNov() metodusa.");
    }

    /**
     *  @brief Törli a fonalat a gombafaj és a végpontok közül.
     * A gombafaj és a két végpont értesül a fonal megszűnéséről.
     */
    public void vegpontTorles() {
        System.out.println("Meghívódik a gombafonal osztaly vegpontTorles() metodusa.");
        gombafaj.fonalSzakad(this);
        vegpont1.fonalSzakad(this);
        vegpont2.fonalSzakad(this);  
    }

     /**
     *  @brief Visszaadja, hogy mióta él a gombafonal
     */
    public int getMiota()
    {
            return miota;
    }

    /**
     *  @brief miota attribútum settere
     */
    public void setMiota(int m)
    {
        miota =m;
    }
     /**
     *  @brief Visszaadja a gombafonal aktuális állapotát
     */
    public fonalAllapot getAllapot()
    {
        return allapot;
    }

    /**
     *   @brief allapot attribútum settere
     */
    public void setAllapot(fonalAllapot uj)
    {
        allapot=uj;
    }

    /**
     *   @brief gombafaj attribútum gettere
     */
    public Gombafaj getGombafaj()
    {
        return gombafaj;
    }

    /**
     *   @brief gombafaj attribútum settere
     */
    public void setGombafaj(Gombafaj g)
    {
        gombafaj=g;
    }

    /**
     *  @brief  vegpont1 attribútum gettere
     */
    public Tekton getVegpont1()
    {
        return vegpont1;
    }

    /**
     *  @brief  vegpont1 attribútum settere
     */
    public void setVegpont1(Tekton t)
    {
        vegpont1=t;
    }

     /**
     *   @brief vegpont2 attribútum gettere
     */
    public Tekton getVegpont2()
    {
        return vegpont2;
    }

    /**
     *   @brief vegpont2 attribútum settere
     */
    public void setVegpont2(Tekton t)
    {
        vegpont2=t;
    }
}
