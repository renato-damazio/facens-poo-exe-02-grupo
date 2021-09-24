package entidades;

public class Mesa {

    //Atributos privados
    private int intNumMesa;
    private int intQtdePessoa;
    private boolean bolStatus;//false=LIVRE | true=OCUPADA
    private double dblTotalConta;

    //Construtor sem parametros
    public Mesa() {

    }

    //Cobrecarga construtor com 1 parametro
    public Mesa(int intNumMesa){
        this.intNumMesa = intNumMesa;
        this.intQtdePessoa = 0;
        this.bolStatus = false;
        this.dblTotalConta = 0;
    }

    //Métodos Getters and Setters
    public int getIntNumMesa() {
        return intNumMesa;
    }

    public void setIntNumMesa(int intNumMesa) {
        this.intNumMesa = intNumMesa;
    }

    public int getIntQtdePessoa() {
        return intQtdePessoa;
    }

    public void setIntQtdePessoa(int intQtdePessoa) {
        this.intQtdePessoa = intQtdePessoa;
    }

    public boolean isBolStatus() {
        return bolStatus;
    }

    public void setBolStatus(boolean bolStatus) {
        this.bolStatus = bolStatus;
    }

    public double getDblTotalConta() {
        return dblTotalConta;
    }

    public void setDblTotalConta(double dblTotalConta) {
        this.dblTotalConta = dblTotalConta;
    }

}
