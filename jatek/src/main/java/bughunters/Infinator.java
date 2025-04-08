package bughunters;

public class Infinator extends Tekton{
    public void eletbenTartas(){
        for(Gombafonal gf: getFonalak()){
            gf.setAllapot(fonalAllapot.Ep);
        }
    }
}
