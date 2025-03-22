package bughunters;

public class Rovar {
    //private String szin;
    private Tekton tartozkodas;


    /**
     * @brief Default constructor for Rovar.
     */
    public Rovar() {
        System.out.println("Létrejött egy új Rovar.");

        //this.szin = "";
        this.tartozkodas = null;
    }

    /**
     *  @brief Constructor for Rovar with a specified Tekton.
     * @param tartozkodas The Tekton where the Rovar is located.
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
     *  @brief Gets the Tekton where the Rovar is currently located.
     * @return The current Tekton.
    */
    public Tekton getTartozkodas() {
        return tartozkodas;
    }

    /**
     *   @brief Sets the Tekton where the Rovar is located.
     * @param t The new Tekton.
     */
    public void setTartozkodas(Tekton t){ 
        this.tartozkodas = t; 
        System.out.println("Meghívódott a Rovar setTartozkodas() metodusa.");
    }

    /**
     *  @brief Resets the Rovar to its default state.
     */
    public void alapAllapot(){
        System.out.println("Meghívódott a Rovar alapAllapot metódusa.");
    }

    /**
     *  @brief Applies a slowing effect to the Rovar.
     */
    public void lassito(){
        System.out.println("Meghívódott a Rovar lassito metódusa.");
    }

    /**
     *  @brief Applies a speeding effect to the Rovar.
     */
    public void gyorsito(){
        System.out.println("Meghívódott a Rovar gyorsito metódusa.");
    }

    /**
     *  @brief Applies a paralyzing effect to the Rovar.
     */
    public void benit(){
        System.out.println("Meghívódott a Rovar benit metódusa.");
    }

    /**
     *  @brief Makes the Rovar unable to cut.
     */
    public void vagaskeptelen(){
        System.out.println("Meghívódott a Rovar vagaskeptelen metódusa.");
    }

    /**
     *  @brief Cuts a Gombafonal if the Rovar is not in a paralyzed or unable-to-cut state.
     * @param g The Gombafonal to be cut.
     * @throws Exception If the Rovar is in a paralyzed or unable-to-cut state.
     */
    public void vag(Gombafonal g) throws Exception {
        System.out.println("Meghívódott a Rovar vag metódusa.");

        boolean valasz = Skeleton.getInstance().Kerdes("Vágásképtelen vagy Bénított állapotban van a rovar?");
        try {
            if (valasz) {
                throw new Exception("Vágásképtelen vagy Bénított állapotban van a rovar.");
            }else{
                g.vegpontTorles();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     *  @brief Moves the Rovar to a specified Tekton if there is a path and the Rovar is not paralyzed.
     * @param hova The Tekton to move to.
     * @throws Exception If the Rovar is paralyzed or there is no path between the Tektons.
     */
    public void maszik(Tekton hova) throws Exception {
        System.out.println("Meghívódott a Rovar maszik metódusa.");
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
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     *  @brief Makes the Rovar eat a Spora.
     * @param s The Spora to be eaten.
     * @throws Exception If an error occurs during the eating process.
     */
    public void eszik(Spora s) throws Exception {
        System.out.println("Meghívódott a Rovar eszik metódusa.");
        try {
            tartozkodas.eszik(s, this);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

}
