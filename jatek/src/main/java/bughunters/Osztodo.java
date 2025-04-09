package bughunters;

/**
 * @brief A Osztodo osztály a Spora osztály egyik egyik utódja.
 * Ez a spóra képes osztodo hatást gyakorolni a rovarokra.
 */
public class Osztodo extends Spora{
    
     /**
     * @brief Osztodo osztály konstuktora
     */
    public Osztodo( int m, Gombafaj g)
    {
        super( m, g);
        tapertek=10;
      
    }

    public Osztodo()
    {
        super( );
        tapertek=10;
    }

    /**
     * @brief A spóra hatást fejt ki a megadott rovarra, aki ennek hatásásra osztódni fog
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r) {
       r.osztodik();
        
    }
}
