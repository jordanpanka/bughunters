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
        System.out.println("A rovar alap állapotba állt");
    }
    public void lassito(){
        System.out.println("A rovar lassított állapotba állt");
    }
    public void gyorsito(){
        System.out.println("A rovar gyorsított állapotba állt");
    }
    public void benit(){
        System.out.println("A rovar bénított állapotba állt");
    }
    public void vagaskeptelen(){
        System.out.println("A rovar vágásképtelen állapotba állt");
    }

    public void vag(Gombafonal g){
        g.vegpontTorles();
        System.out.println("A fonál elvágódott");
    }
    public void maszik(Tekton hova){

        if(tartozkodas.vanUt(hova);){
            setTartozkodas(hova);
        }else{

        }
    }
    public void eszik(Spora s){

    }

}
