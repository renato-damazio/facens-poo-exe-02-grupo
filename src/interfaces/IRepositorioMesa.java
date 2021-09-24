package interfaces;

import entidades.Mesa;

import java.util.List;

public interface IRepositorioMesa {
    boolean cadastrarMesa(Mesa objMesa);
    boolean abrirMesa(Mesa objMesa, int intQtdePessoa);
    boolean fazerPedido(Mesa objMesa, double dblTotalPedido);
    boolean fecharConta(Mesa objMesa, double dblValorDesconto);
    void liberarMesa(Mesa objMesa);
    Mesa consultarMesa(int intNumMesa);
    Mesa consultarMesa(int intNumMesa, boolean bolStatus);
    List<Mesa> listarMesas(boolean bolStatus);
}
