package bughunters; 

/**
 *  @brief Az Spora osztály egy absztrakt ősosztály
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
     *  @brief Absztrakt metódus, amely meghatározza, hogy a spóra milyen hatást gyakorol a rovarra.
     * Az leszármazottaknak kell megvalósítaniuk ezt a metódust.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    public abstract void hatas(Rovar r);

     /**
     *  @brief Spora abasztrakt osztály konstruktora
     */
    public Spora(int t, int m, Gombafaj g)
    {
        tapertek =t;
        mennyiseg=m;
        gombafaj=g;
       
    }
    /**
     *  @brief A Spora osztály paraméter nélküli konstruktora
     */
    public Spora()
    {
        mennyiseg=1;
    }

    /**
     *  @brief Csökkenti a spóra mennyiségét a megadott mennyiséggel, amennyiben az elegendő.
     * Ha a spóra mennyisége nem elegendő a művelet végrehajtásához, kivételt dob.
     *
     * @param mennyit a fogyasztandó spóra mennyisége
     * @throws Exception ha nincs elegendő spóra
     */
    public void fogyaszt(int mennyit) throws Exception {

        Skeleton s = Skeleton.getInstance();
        System.out.println("Meghívódik a Spora osztaly fogyaszt metodusa.");
        boolean valasz= s.Kerdes("Van elég spóra?");

        if(valasz)
        {
            //Itt most írnom kell bármit, ha true?
        }
        else
        {
            throw new Exception("Nincs elég spóra az akció végrehajtására");
        }
        /* 
        if (mennyiseg - mennyit >= 0) {
            mennyiseg -= mennyit;
            
        } else {
            throw new Exception("Nincs elég spóra az akció végrehajtására");
        }
            */
    }

    /**
     *  @brief Csökkenti a spóra mennyiségét a megadott mennyiséggel, és hatást gyakorol egy adott rovarra.
     * Ha a spóra mennyisége nem elegendő, kivételt dob.
     *
     * @param mennyit a fogyasztandó spóra mennyisége
     * @param r a rovar, amelyre a spóra hatást fejt ki a fogyasztás során
     * @throws Exception ha nincs elegendő spóra
     */
    public void fogyaszt(int mennyit, Rovar r) throws Exception {
        System.out.println("Meghívódik a Spora osztaly fogyaszt metodusa.");
      

        if(mennyiseg>0)
        {
            hatas(r);
        }
        else
        {
            throw new Exception("Nincs elég spóra az akció végrehajtására");
        }
        /* 
        if (mennyiseg - mennyit >= 0) {
            mennyiseg -= mennyit;
            hatas(r);
        } else {
            throw new Exception("Nincs elég spóra az akció végrehajtására");
        }
            */
    }

    /**
     *  @brief Akkor hívódik meg ha már egy létező spórából több kerül az adott tektonra. Lekérdezi, hogy
     * az ő gombafajában mennyi spórát szórnak szét a gombatestek és azzal az értékkel növeli a mennyiseg attribútumát
     */
    public void szorasTortent()
    {
        System.out.println("Meghívódik a Spora osztaly szorasTortent metodusa.");
       // mennyiseg += gombafaj.getGombatestSzortSpora();
    }

     /**
     *  @brief tapertek attribútum gettere
     */
    public int getTapertek()
    {
        return tapertek;
    }

     /**
     *  @brief tapertek attribútum settere
     */
    public void setTapertek(int t)
    {
        tapertek= t;
    }

     /**
     *  @brief gombafaj attribútum gettere
     */
    public Gombafaj getGombafaj()
    {
        return gombafaj;
    }

    /**
     *  @brief  gombafaj attribútum settere
     */
    public void setGombafaj(Gombafaj g)
    {
        gombafaj=g;
    }

    /**
     *  @brief mennyiseg attribútum gettere
     */
    public int getMennyiseg()
    {
        return mennyiseg;
    }

     /**
     *  @brief mennyiseg attribútum settere
     */
    public void setMennyiseg(int db)
    {
        mennyiseg=db;
    }
}