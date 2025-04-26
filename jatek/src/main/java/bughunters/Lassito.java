package bughunters;


/**
 * @brief A Lassito osztály a Spora osztály egyik egyik utódja.
 * Ez a spóra képes lassító hatást gyakorolni a rovarokra.
 */
public class Lassito extends Spora {
    
    /**
     * @brief Lassito osztály konstuktora
     */
    public Lassito(int t, int m, Gombafaj g)
    {
        super( m, g);
        tapertek=15;
    }

    public Lassito()
    {
        super( );
        tapertek=15;
    }

    /**
     * @brief A spóra hatást fejt ki a megadott rovarra, és felgyorsítja azt.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r)
    {
        r.setAllapot(rovarAllapot.Lassitott);
        r.setAllapotIdeje(0);
    }
    @Override
    public void szorasTortent() {
        mennyiseg+=2;
    }
}
 