import entidades.Mesa;
import repositorios.RepositorioMesa;

import java.util.Scanner;

public class Crud {

    private RepositorioMesa repositorioMesa = new RepositorioMesa();
    private Util util = new Util();
    private Scanner scanner = null;
    private int cont = 1;
    private int menu = 0;

    public Crud() {

    }

    public void cadastrarMesa() {

        if (cont < 31) {
            Mesa objMesa = new Mesa(cont);

            if (repositorioMesa.cadastrarMesa(objMesa)) {
                System.out.println("\n\nMesa cadastrada com sucesso!");
                System.out.println("--------------------------------------------------------------------------");
                this.imprimirMesa(objMesa);
                cont++;
            } else {
                System.out.println("\n\nErro ao cadastrar a mesa!");
            }
        }else {
            System.out.println("\n\nLimite maximo de 30 mesas atingido!");
        }

    }

    public void abrirMesa() {

        if (!repositorioMesa.listarMesas(false).isEmpty()) {

            System.out.println("\n\nInforme o numero de uma mesa na lista abaixo para acomodar o(s) cliente(s)");

            this.listarMesas(false);

            scanner = new Scanner(System.in);

            Mesa objMesa = repositorioMesa.consultarMesa(scanner.nextInt(), false);

            if (objMesa != null) {

                System.out.println("\n\nInforme a quantidade de pessoa(s) para acomodar?");

                scanner = new Scanner(System.in);

                if (repositorioMesa.abrirMesa(objMesa, scanner.nextInt())) {
                    System.out.println("\n\nMesa aberta com sucesso e cliente(s) acomodado(s)");
                    System.out.println("--------------------------------------------------------------------------");
                    imprimirMesa(objMesa);
                    System.out.println("Deseja fazer o pedido agora? (S/N): ");
                    scanner = new Scanner(System.in);
                    String opcao = scanner.nextLine();
                    if (opcao.equals("s") || opcao.equals("S")) fazerPedido(objMesa);
                } else {
                    System.out.println("\n\nNão foi possível abrir a mesa informada!");
                }

            } else {
                System.out.println("\n\nNumero da mesa informada nao está disponivel no momento!");
            }
        }else {
            System.out.println("\n\nNao existem mesas disponiveis para acomodacao de cliente(s)!");
        }

    }

    public void fazerPedido() {

        if (!repositorioMesa.listarMesas(true).isEmpty()) {

            System.out.println("Informe o numero da mesa para iniciar o pedido:");

            scanner = new Scanner(System.in);

            Mesa objMesa = repositorioMesa.consultarMesa(scanner.nextInt(), true);

            if (objMesa != null) {
                fazerPedido(objMesa);
            } else {
                System.out.println("\n\nDesculpe, nao e possivel realizar pedido para a mesa informada");
            }
        }else {
            System.out.println("\n\nNao existem mesas disponiveis para realizar pedidos no momento!");
        }

    }

    public void fazerPedido(Mesa objMesa){
        double dblTotalPedido = 0;

        do {

            System.out.println("\n\nInforme o codigo do produto desejado?");

            System.out.println("1 - Refrigerante - R$ 2,00");
            System.out.println("2 - Cachorro Quante - R$ 5,00");
            System.out.println("3 - Batata Frita - R$ 4,00");
            System.out.println("4 - Confirmar e Finalizar Pedido");

            scanner = new Scanner(System.in);
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    System.out.println("Informe a quantidade de refrigerante: ");
                    scanner = new Scanner(System.in);
                    dblTotalPedido += scanner.nextDouble() * 2.00d;
                    break;
                case 2:
                    System.out.println("Informe a quantidade de cachorro quente: ");
                    scanner = new Scanner(System.in);
                    dblTotalPedido += scanner.nextDouble() * 5.00d;
                    break;
                case 3:
                    System.out.println("Informe a quantidade de batata frita: ");
                    scanner = new Scanner(System.in);
                    dblTotalPedido += scanner.nextDouble() * 4.00d;
                    break;
            }

        } while (menu != 4);

        if (repositorioMesa.fazerPedido(objMesa, dblTotalPedido)) {
            System.out.println("\n\nPedido adicionado com sucesso para a mesa atual!");
            System.out.println("--------------------------------------------------------------------------");
            imprimirMesa(objMesa);
        } else {
            System.out.println("\n\nOcorreu um erro ao tentar finalizar o pedido da mesa!");
        }
    }

    public void fecharConta() {

        if (!repositorioMesa.listarMesas(true).isEmpty()) {

            double dblDesconto = 0;

            System.out.println("\n\nInforme o numero de uma mesa na lista abaixo para fechar a conta");

            this.listarMesas(true);

            Mesa objMesa = repositorioMesa.consultarMesa(scanner.nextInt(), true);

            if (objMesa != null) {

                if (objMesa.getDblTotalConta() > 0.01d) {

                    System.out.println("--------------------------------------------------------------------------");
                    this.imprimirMesa(objMesa);

                    do {

                        System.out.println("\n\nInforme o tipo de pagamento: ");

                        System.out.println("1 - Dinheiro | Desconto de 10%");
                        System.out.println("2 - Cheque   | Sem Desconto");
                        System.out.println("3 - Cartão   | Desconto de 5%");

                        scanner = new Scanner(System.in);
                        menu = scanner.nextInt();

                        switch (menu) {
                            case 1:
                                dblDesconto = 10.0d;
                                break;
                            case 2:
                                break;
                            case 3:
                                dblDesconto = 5.0d;
                                break;
                        }

                    } while (menu != 1 && menu != 2 && menu != 3);

                    if (repositorioMesa.fecharConta(objMesa, dblDesconto)) {

                        System.out.println("\n\nFechamento da conta realizado e mesa " + util.retornaCodigo(objMesa.getIntNumMesa()) + " liberada!");
                        System.out.println("---------------------------------------------------------------------------");
                        System.out.println("TOTAL COM DESCONTO: " + util.retornaMoeda(objMesa.getDblTotalConta()) + "     |     TOTAL POR PESSOA: " + util.retornaMoeda(objMesa.getDblTotalConta() / objMesa.getIntQtdePessoa()));
                        System.out.println("---------------------------------------------------------------------------");


                    }
                }else {
                    System.out.println("\n\nFechamento da conta realizado e mesa " + util.retornaCodigo(objMesa.getIntNumMesa()) + " liberada!");
                }

                repositorioMesa.liberarMesa(objMesa);

            } else {
                System.out.println("Nenhuma mesa localizada pelo numero informado!");
            }
        }else {
            System.out.println("\n\nNao existem mesas disponiveis para fechamento de conta!");
        }
    }

    public void consultarMesa(){

        System.out.println("\n\nInforme o numero da mesa que deseja consultar?");

        scanner = new Scanner(System.in);

        Mesa objMesa = repositorioMesa.consultarMesa(scanner.nextInt());

        if (objMesa != null){

            System.out.println("--------------------------------------------------------------------------");

            this.imprimirMesa(objMesa);

        }else {
            System.out.println("\n\nNenhuma mesa localizada pelo numero informado!");
        }

    }

    public void listarMesas() {
        do {

            System.out.println("\n\nInforme o status da mesa que deseja listar");

            System.out.println("1 - Mesa(s) Livre(s)");
            System.out.println("2 - Mesa(s) Ocupada(s)");

            scanner = new Scanner(System.in);
            menu = scanner.nextInt();

            if (menu == 1) listarMesas(false);
            if (menu == 2) listarMesas(true);

        } while (menu != 1 && menu != 2 && menu != 3);
    }

    public void listarMesas(boolean bolStatus) {

        if (!repositorioMesa.listarMesas(bolStatus).isEmpty()){
            System.out.println("--------------------------------------------------------------------------");
            for (Mesa mesa : repositorioMesa.listarMesas(bolStatus)) {
                imprimirMesa(mesa);
            }
        }else {
            System.out.println("\n\nNao existem mesas com o status informado!");
        }

    }

    public void imprimirMesa(Mesa objMesa) {
        String status = objMesa.isBolStatus() ? "Ocupada" : "Livre";
        System.out.println("MESA: " + util.retornaCodigo(objMesa.getIntNumMesa()) +
                " | STATUS: " + status +
                " | QTDE PESSOAS: " + objMesa.getIntQtdePessoa() +
                " | TOTAL CONTA: " + util.retornaMoeda(objMesa.getDblTotalConta())
        );
        System.out.println("--------------------------------------------------------------------------");
    }

}
