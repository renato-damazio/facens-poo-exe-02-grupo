import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner;
        int menu;
        Crud crud = new Crud();

        do {

            System.out.println("+--------------------+----------------+------------------+------------------+--------------------+------------------+------------------+");
            System.out.println("| 1 - Cadastrar Mesa | 2 - Abrir Mesa | 3 - Fazer Pedido | 4 - Fechar Conta | 5 - Consultar Mesa | 6 - Listar Mesas | 7 - Sair Sistema |");
            System.out.println("+--------------------+----------------+------------------+------------------+--------------------+------------------+------------------+");

            scanner = new Scanner(System.in);
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    crud.cadastrarMesa();
                    break;
                case 2:
                    crud.abrirMesa();
                    break;
                case 3:
                    crud.fazerPedido();
                    break;
                case 4:
                    crud.fecharConta();
                    break;
                case 5:
                    crud.consultarMesa();
                    break;
                case 6:
                    crud.listarMesas();
                    break;
            }

        } while (menu != 7);

    }

}
