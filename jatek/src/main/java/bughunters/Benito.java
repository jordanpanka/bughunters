package bughunters;

/**
 * @brief A Benito osztály a Spora osztály egyik utódja.
 * Ez a spóra képes bénító hatást gyakorolni a rovarokra.
 */
public class Benito extends Spora {

    /**
     * @brief Benito osztály konstuktora
     */
    public Benito( int m, Gombafaj g)
    {
        super( m, g);
        tapertek=30;
       
        
    }

    public Benito()
    {
        super();
        tapertek=30;
    }

    /**
     * @brief A spóra hatást fejt ki a megadott rovarra, és lebénítja azt.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r) {
       r.setAllapot(rovarAllapot.Benitott);
       r.setAllapotIdeje(0);
        
    }
}