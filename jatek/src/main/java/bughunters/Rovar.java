package bughunters;

enum rovarAllapot {
    Alap,               // Az alapvető állapota a rovarnak
    Lassitott,          // A rovar csak fele olyan gyorsan képes haladni
    Gyorsitott,         // A rovar kétszer olyan gyorsan képes haéadni
    VagasKeptelen,      // A rovar nem képes fonalat vágni
    Benitott            // A rovar cselekvés képtelen
}


public class Rovar {
    private Tekton tartozkodas;
    private String szin;
    private int allapotIdeje;
    private rovarAllapot allapot;
    private Rovarasz rs; // Rovarász objektum, amely a rovar viselkedését irányítja

    /**
     * @brief Paraméter nélküli konstruktor.
     */
    public Rovar() {
        //System.out.println("Létrejött egy új Rovar.");

        //this.szin = "";
        this.tartozkodas = null;
        allapot = rovarAllapot.Alap;
        allapotIdeje = 0;
    }

    /**
     *  @brief Paraméteres konstruktor.
     * @param tartozkodas A Tekton ahol a Rovar tartózkodik.
     */
    public Rovar(/*String szin,*/ Tekton tartozkodas, Rovarasz rs) {
        //System.out.println("Létrejött egy új Rovar.");

        //this.szin = szin;
        this.rs = rs;
        this.tartozkodas = tartozkodas;
        allapot = rovarAllapot.Alap;
        allapotIdeje = 0;
    }

    public void torolRovar(){
        rs.removeRovar(this);
    }
    
    public String getSzin() { 
        //System.out.println("Meghívódott a Rovar GetSzin metódusa.");

        return szin; 
    }
    public void setSzin(String szin) { 
        //System.out.println("Meghívódott a Rovar setSzin metódusa.");

        this.szin = szin; 
    }
    

    /**
     *  @brief Visszaadja a Rovar tartózkodását.
     * @return A jelenlegi Tekton ahol a Rovar tartózkodik.
    */
    public Tekton getTartozkodas() {
        return tartozkodas;
    }

    /**
     *   @brief Beállítja a Rovar tartózkodását egy Tektonra.
     * @param t Az új Tekton ahova beállítjuk a Rovar tartózkodását.
     */
    public void setTartozkodas(Tekton t){ 
        this.tartozkodas = t; 
       // System.out.println("Meghívódik a Rovar setTartozkodas() metodusa.");
    }

    /**
 
    * @brief Beállítja, hogy a rovar mennyi ideje van a jelenlegi állapotában.
    * @param ido Az idő (lépések száma), amelyet beállítunk a rovar állapotához.
    */
    public void setAllapotIdeje(int ido){
        allapotIdeje=ido;
    }

    /**
    * @brief Visszaadja, hogy a rovar mennyi ideje van a jelenlegi állapotában.
    * @return Az idő (lépések száma), amelyet a rovar a jelenlegi állapotában töltött.
    */
    public int getAllapotIdeje(){
        return allapotIdeje;
    }


    /**
     * @brief Visszaadja a rovar jelenlegi állapotát.
     * @return A rovar aktuális állapota.
     */
    public rovarAllapot getAllapot() {
        return allapot;
    }

    /**
     * @brief Beállítja a rovar állapotát.
     * @param allapot Az új állapot, amelyet beállítunk a rovarnak.
     */
    public void setAllapot(rovarAllapot allapot) {
        this.allapot = allapot;
        allapotIdeje = 0; // Az állapot idejét alaphelyzetbe állítjuk
    }


    /**
     *  @brief Alapállapotba állítja a Rovart.
     */
    public void alapAllapot(){
        allapot = rovarAllapot.Alap;
        allapotIdeje = 0;
        //System.out.println("Meghívódik a Rovar alapAllapot metódusa.");

    }


    /**
     *  @brief A Rovar elvágja a Gombafonalat, ha nincsen lebénítva vagy nem vágásképtelen.
     * @param g A Gombafonal, amit el kell vágnia a Rovarnak.
     * @throws Exception Hogyha a Rovar le van bénítva vagy vágásképtelen.
     */
    public void vag(Gombafonal g) throws Exception {
        //System.out.println("Meghívódik a Rovar vag metódusa.");

        //boolean valasz = Skeleton.getInstance().Kerdes("Vágásképtelen vagy Bénított állapotban van a rovar?");
        try {
            if (this.allapot == rovarAllapot.VagasKeptelen || this.allapot == rovarAllapot.Benitott) {
                throw new Exception("Vágásképtelen vagy Bénított állapotban van a rovar.");
            }else{
                g.vegpontTorles();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *  @brief A Rovar átmászik egy Tektonról egy másikra, ha van út és nincs lebénítva.
     * @param hova A Tekton, ahova a Rovar átmászik.
     * @throws Exception Ha a Rovar lebénítva van, vagy nincsen út a Tektonok között.
     */
    public void maszik(Tekton hova) throws Exception {
        //System.out.println("Meghívódik a Rovar maszik metódusa.");
        //boolean valasz = Skeleton.getInstance().Kerdes("Bénított állapotban van a rovar?");

        try {
            if (this.allapot == rovarAllapot.Benitott) {
            throw new Exception("Benitott allapotban van a rovar.");
            }else{

                if(tartozkodas.vanUt(hova)){
                setTartozkodas(hova);
                }else{
                    throw new Exception("Nincs ut ket tekton kozott.");
                }

            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *  @brief A Rovar eszik egy Sporaból
     * @param s Melyik Sporaból egyen
     * @throws Exception Ha a Rovar nem tud enni.
     */
    public void eszik(Spora s) throws Exception {
        //System.out.println("Meghívódik a Rovar eszik metódusa.");
        try {
            if (this.allapot == rovarAllapot.Benitott) {
                throw new Exception("Benitott allapotban van a rovar.");
            }
            else{
                tartozkodas.eszik(s, this);
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public void osztodik(){
        //System.out.println("Meghívódik a Rovar osztodik metódusa.");

        Rovar ujRovar = new Rovar(this.tartozkodas, this.rs);
        ujRovar.szin = this.szin;
        ujRovar.allapot = rovarAllapot.Alap;
        ujRovar.allapotIdeje = 0;
        rs.addRovar(ujRovar);
    }

}
