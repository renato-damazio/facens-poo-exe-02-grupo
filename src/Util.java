public class Util {

    public Util() {}

    public String retornaMoeda(double dblValor){
        return String.format("R$%10.2f", dblValor);
    }

    public String retornaCodigo(int intId){
        if (intId < 10){
            return "0" + intId;
        }
        return Integer.toString(intId);
    }

    public String retornaMaiusculo(String strTexto){
        return strTexto.toUpperCase();
    }

}
