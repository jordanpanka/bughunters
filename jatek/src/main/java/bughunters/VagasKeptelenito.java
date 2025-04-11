package bughunters;


/**
 * @brief A VagasKeptelenito osztály a Spora osztály egyik egyik utódja.
 * Ez a spóra képes vagasKeptelenito hatást gyakorolni a rovarokra.
 */
public class VagasKeptelenito extends Spora {


    /**
     * @brief VagasKeptelenito osztály konstuktora
     */
    public VagasKeptelenito( int m, Gombafaj g)
    {
        super( m, g);
        tapertek=20;
    }

    public VagasKeptelenito()
    {
        super();
        tapertek=20;
    }
    /**
     * @brief A spóra hatást fejt ki a megadott rovarra, és nem engedi, hogy fonalat vágjon az adott rovar.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r)
    {
       r.setAllapot(rovarAllapot.VagasKeptelen);
       r.setAllapotIdeje(0);
    }
}
