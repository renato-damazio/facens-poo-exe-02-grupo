package repositorios;

import entidades.Mesa;
import interfaces.IRepositorioMesa;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMesa implements IRepositorioMesa {

    private List<Mesa> mesas = new ArrayList<>();

    @Override
    public boolean cadastrarMesa(Mesa objMesa) {
        try {
            mesas.add(objMesa);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean abrirMesa(Mesa objMesa, int intQtdePessoa) {
        try {
            objMesa.setIntQtdePessoa(intQtdePessoa);
            objMesa.setBolStatus(true);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean fazerPedido(Mesa objMesa, double dblTotalPedido) {
        try {
            objMesa.setDblTotalConta(objMesa.getDblTotalConta() + dblTotalPedido);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean fecharConta(Mesa objMesa, double dblValorDesconto) {
        try {
            objMesa.setDblTotalConta(objMesa.getDblTotalConta() - ((objMesa.getDblTotalConta() * dblValorDesconto) / 100));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void liberarMesa(Mesa objMesa) {
        objMesa.setBolStatus(false);
        objMesa.setIntQtdePessoa(0);
        objMesa.setDblTotalConta(0);
    }

    @Override
    public Mesa consultarMesa(int intNumMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getIntNumMesa() == intNumMesa) return mesa;
        }
        return null;
    }

    @Override
    public Mesa consultarMesa(int intNumMesa, boolean bolStatus) {
        for (Mesa mesa : mesas) {
            if (mesa.getIntNumMesa() == intNumMesa && mesa.isBolStatus() && bolStatus) return mesa;
            if (mesa.getIntNumMesa() == intNumMesa && !mesa.isBolStatus() && !bolStatus) return mesa;
        }
        return null;
    }

    @Override
    public List<Mesa> listarMesas(boolean bolStatus) {

        List<Mesa> filtroMesas = new ArrayList<>();

        for (Mesa mesa : mesas) {
            if (bolStatus && mesa.isBolStatus()) filtroMesas.add(mesa);
            if (!bolStatus && !mesa.isBolStatus()) filtroMesas.add(mesa);
        }

        return filtroMesas;
    }
}
