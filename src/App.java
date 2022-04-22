import java.util.Scanner;

import entidades.Pessoa;
import entidades.Restaurante;

public class App {

    private static Scanner entrada = new Scanner(System.in);
    private static Restaurante restaurante = new Restaurante(20, 20);

    public static void main(String[] args) throws Exception {
        menuPrincipal();
    }   

    public static void menuPrincipal() {
        int acao = 0;

        do {
            clearConsole();
            String mensagem =
                "Ação:" + "\n" + 
                "1 - Entrar na fila" + "\n" +
                "2 - Entrar na fila do buffet" + "\n" +
                "3 - Escolher mesa" + "\n" +
                "4 - Entrar na fila do caixa" + "\n" +
                "5 - Realizar pagamento" + "\n" +
                "6 - Repor pratos" + "\n" +
                "7 - Relatório" + "\n" + 
                "8 - Finalizar" + "\n";
            acao = getInt(mensagem);

            if(acao == 8) return;

        } while (acao <= 0 || acao > 7);

        clearConsole();

        switch (acao) {
            case 1:
                entrarNaFila();
                break;
            case 2:
                entrarFilaBuffet();
                break;
            case 3:
                escolherMesa();
                break;
            case 4:
                entrarNaFilaDoCaixa();
                break;
            case 5:
                realizarPagamento();
                break;
            case 6:
                reporPratos();
                break;
            case 7:
                relatorio();
                break;
        }
    }

    public static void entrarNaFila() {
        restaurante.incluirPessoaFilaAlmocar(new Pessoa(getString("Informe o nome da pessoa: ")));
        menuPrincipal();
    }

    public static void entrarFilaBuffet() {
        if(!restaurante.temPratosDisponiveis()) {
            System.out.println("Sem pratos disponíveis!");
            continuar();
            menuPrincipal();
            return;
        }

        if(!restaurante.temPessoasFilaAlmoco()) {
            System.out.println("Sem pessoas na fila do almoço!");
            continuar();
            menuPrincipal();
            return;
        }

        restaurante.incluirPessoaFilaBuffed();
        System.out.println("Incluído no Buffet!");
        continuar();
        menuPrincipal();
    }

    public static void escolherMesa() {
        if(!restaurante.temPessoasNaFilaBuffet()) {
            System.out.println("Nenhuma pessoa na fila do buffet");
            continuar();
            menuPrincipal();
            return;
        }
        
        if(restaurante.getMesasDisponiveis().numeroElementos() <= 0) {
            System.out.println("Nenhuma mesa disponível");
            continuar();
            menuPrincipal();
            return;
        }

        int mesa;

        do {
            clearConsole();
            System.out.println("Mesas: ");
            System.out.println(restaurante.listarMesasDisponiveis());
            mesa = getInt("Informe o número da mesa: ");
        } while (!restaurante.mesaDisponivel(mesa));

        restaurante.ocuparMesa(mesa);
        clearConsole();
        System.out.println("Escolhida a mesa " + mesa + " com sucesso!");
        continuar();
        menuPrincipal();
        return;
    }

    public static void entrarNaFilaDoCaixa() {

        clearConsole();

        if(restaurante.getMesasOcupadas().numeroElementos() <= 0) {
            System.out.println("Nenhuma pessoa está almoçando!");
            continuar();
            menuPrincipal();
            return;
        }

        int mesa;

        do {
            clearConsole();
            System.out.println("Mesas ocupadas: ");
            System.out.println(restaurante.listarMesasOcupadas());

            mesa = getInt("Informe o número da mesa: ");
        } while (!restaurante.mesaOcupada(mesa));

        restaurante.desocuparMesa(mesa);
        clearConsole();
        System.out.println("Escolhida a mesa " + mesa + " com sucesso!");
        continuar();
        menuPrincipal();
        return;
    }

    public static void realizarPagamento() {
        clearConsole();

        if(restaurante.filaCaixaVazia()) {
            System.out.println("Ninguém na fila do caixa!");
            continuar();
            menuPrincipal();
            return;
        }
        
        restaurante.realizarPagamento();
        System.out.println("Pagamento realizado com sucesso!");
        continuar();
        menuPrincipal();
    }

    public static void reporPratos() {
        clearConsole();

        if(restaurante.numeroPratosParaRepor() == 0) {
            System.out.println("Nenhum prato para repor!");
            continuar();
            menuPrincipal();
            return;
        }   

        System.out.println("Número de pratos para repor: " + restaurante.numeroPratosParaRepor());
        int numeroPratos = getInt("Informe o número de pratos para repor: ");

        if(numeroPratos > restaurante.numeroPratosParaRepor()) {
            System.out.println("Quantidade Informada inválida!");
            continuar();
            menuPrincipal();
            return;
        }

        restaurante.reporPratos(numeroPratos);
        System.out.println("Os pratos foram repostos!");
        continuar();
        menuPrincipal();
    }

    public static void relatorio() {
        System.out.println(restaurante.relatorio());
        if(!getString("Digite F para finalizar: ").equals("F")) {
            menuPrincipal();
        }
    }

    public static int getInt(String mensagem) {
        System.out.print(mensagem);
        int resultado = entrada.nextInt();
        entrada.nextLine();
        return resultado;
    }

    public static String getString(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine();
    }

    public static double getDouble(String mensagem) {
        System.out.print(mensagem);
        double resultado = entrada.nextDouble();
        entrada.nextLine();
        return resultado;
    }

    public static float getFloat(String mensagem) {
        System.out.print(mensagem);
        float resultado = entrada.nextFloat();
        entrada.nextLine();
        return resultado;
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void continuar() {
        getString("Precione qualquer tecla para continuar\n");
    }
}
