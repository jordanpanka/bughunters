package bughunters;

public class Rovar {
    private String szin;
    private Tekton tartozkodas;

    public Rovar() {
        this.szin = "";
        this.tartozkodas = null;
    }

    public Rovar(String szin, Tekton tartozkodas) {
        this.szin = szin;
        this.tartozkodas = tartozkodas;
    }

    public String getSzin() { return szin; }
    public void setSzin(String szin) { this.szin = szin; }

    public Tekton getTartozkodas() {
        return tartozkodas;
    }
    public void setTartozkodas(Tekton t){ this.tartozkodas = t; }

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

    public void vag(Gombafonal g){
        g.vegpontTorles();
        System.out.println("Meghívódott a Rovar vag metódusa.");
    }

    public void maszik(Tekton hova) throws Exception {
        System.out.println("Meghívódott a Rovar maszik metódusa.");
        if(tartozkodas.vanUt(hova)){
            setTartozkodas(hova);
        }else{
            try {
                throw new Exception("Nincs ut ket tekton kozott.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
    }

    public void eszik(Spora s){
        System.out.println("Meghívódott a Rovar eszik metódusa.");
        try {
            tartozkodas.eszik(s, this);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

}
