package bughunters;

public class Rovar {
    //private String szin;
    private Tekton tartozkodas;

    public Rovar() {
        //this.szin = "";
        this.tartozkodas = null;
    }

    public Rovar(/*String szin,*/ Tekton tartozkodas) {
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
    public Tekton getTartozkodas() {
        System.out.println("Meghívódott a Rovar getTartozkodas metódusa.");

        return tartozkodas;
    }
    public void setTartozkodas(Tekton t){ 
        System.out.println("Meghívódott a Rovar setTartozkodas metódusa.");

        this.tartozkodas = t; 
    }

    public void alapAllapot(){
        System.out.println("Meghívódott a Rovar alapAllapot metódusa.");
    }
    public void lassito(){
        System.out.println("Meghívódott a Rovar lassito metódusa.");
    }
    public void gyorsito(){
        System.out.println("Meghívódott a Rovar gyorsito metódusa.");
    }
    public void benit(){
        System.out.println("Meghívódott a Rovar benit metódusa.");
    }
    public void vagaskeptelen(){
        System.out.println("Meghívódott a Rovar vagaskeptelen metódusa.");
    }

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
