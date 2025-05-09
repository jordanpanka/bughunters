package bughunters;

/**
 * A Gyorsito osztály a Spora osztály egyik egyik utódja.
 * Ez a spóra képes gyorsito hatást gyakorolni a rovarokra.
 */
public class Gyorsito extends Spora {
    

    /**
     * @brief Gyorsito osztály konstuktora
     */
    public Gyorsito( int m, Gombafaj g)
    {
        super( m, g);
        tapertek=10;
    }

    /**
     * @brief Gyorsito osztály paraméter nélküli konstuktora
     */
    public Gyorsito()
    {
        super( );
        tapertek=10;
    }

     /**
     * @brief A spóra hatást fejt ki a megadott rovarra, és gyorsítja  azt.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r)
    {
        
        r.setAllapot(rovarAllapot.Gyorsitott);
        r.setAllapotIdeje(0);
    }
    @Override
    public void szorasTortent() {
        mennyiseg+=2;
    }
}

