package bughunters;

public class Rovar {
    //private String szin;
    private Tekton tartozkodas;


    /**
     * @brief Paraméter nélküli konstruktor.
     */
    public Rovar() {
        System.out.println("Létrejött egy új Rovar.");

        //this.szin = "";
        this.tartozkodas = null;
    }

    /**
     *  @brief Paraméteres konstruktor.
     * @param tartozkodas A Tekton ahol a Rovar tartózkodik.
     */
    public Rovar(/*String szin,*/ Tekton tartozkodas) {
        System.out.println("Létrejött egy új Rovar.");

        //this.szin = szin;
        this.tartozkodas = tartozkodas;
    }

    /*
    public String getSzin() { 
        System.out.println("Meghívódott a Rovar GetSzin metódusa.");

        return szin; 
    }
    public void setSzin(String szin) { 
        System.out.println("Meghívódott a Rovar setSzin metódusa.");

        this.szin = szin; 
    }
    */

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
        System.out.println("Meghívódik a Rovar setTartozkodas() metodusa.");
    }

    /**
     *  @brief Alapállapotba állítja a Rovart.
     */
    public void alapAllapot(){
        System.out.println("Meghívódik a Rovar alapAllapot metódusa.");
    }

    /**
     *  @brief Lassítja a Rovart.
     */
    public void lassito(){
        System.out.println("Meghívódik a Rovar lassito metódusa.");
    }

    /**
     *  @brief Gyorsítja a Rovart.
     */
    public void gyorsito(){
        System.out.println("Meghívódik a Rovar gyorsito metódusa.");
    }

    /**
     *  @brief Lebénítja a Rovart.
     */
    public void benit(){
        System.out.println("Meghívódik a Rovar benit metódusa.");
    }

    /**
     *  @brief Vágásképtelenné teszi a Rovart.
     */
    public void vagaskeptelen(){
        System.out.println("Meghívódik a Rovar vagaskeptelen metódusa.");
    }

    /**
     *  @brief A Rovar elvágja a Gombafonalat, ha nincsen lebénítva vagy nem vágásképtelen.
     * @param g A Gombafonal, amit el kell vágnia a Rovarnak.
     * @throws Exception Hogyha a Rovar le van bénítva vagy vágásképtelen.
     */
    public void vag(Gombafonal g) throws Exception {
        System.out.println("Meghívódik a Rovar vag metódusa.");

        boolean valasz = Skeleton.getInstance().Kerdes("Vágásképtelen vagy Bénított állapotban van a rovar?");
        try {
            if (valasz) {
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
        System.out.println("Meghívódik a Rovar maszik metódusa.");
        boolean valasz = Skeleton.getInstance().Kerdes("Bénított állapotban van a rovar?");

        try {
            if (valasz) {
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
     * @param s ÍMelyik Sporaból egyen
     * @throws Exception Ha a Rovar nem tud enni.
     */
    public void eszik(Spora s) throws Exception {
        System.out.println("Meghívódik a Rovar eszik metódusa.");
        try {
            tartozkodas.eszik(s, this);

        } catch (Exception e) {
            throw e;
        }

    }

}
