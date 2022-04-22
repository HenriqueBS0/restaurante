package entidades;

import estruturas.Fila;
import estruturas.ListaEncadeada;
import estruturas.Nodo;
import estruturas.Pilha;

public class Restaurante {

    final float VALOR_ALMOCO = 20;

    private Fila<Pessoa>         filaAlmocar;
    private Fila<Pessoa>         filaBuffet;
    private Fila<Pessoa>         filaCaixa;
    private Pilha<Prato>         pratosDisponiveis;
    private ListaEncadeada<Mesa> mesas;
    private Pilha<Prato>         pratosParaRepor;
    private int                  pessoasAtendidas = 0;
    private float                valorEmCaixa = 0;

    public Restaurante(int numeroPratos, int numeroMesas) {
        filaAlmocar = new Fila<Pessoa>(0);
        filaBuffet = new Fila<Pessoa>(0);
        filaCaixa = new Fila<Pessoa>(0);
        pratosDisponiveis = getPilhaPratosDisponiveis(numeroPratos);
        mesas = getListaMesas(numeroMesas);
        pratosParaRepor = new Pilha<Prato>(numeroPratos);
    }

    private Pilha<Prato> getPilhaPratosDisponiveis(int numeroPratos) {
        Pilha<Prato> pratos = new Pilha<Prato>(numeroPratos);

        for (int i = 0; i < numeroPratos; i++) {
            pratos.push(new Prato());
        }

        return pratos;
    }

    private ListaEncadeada<Mesa> getListaMesas(int numeroMesas) {
        ListaEncadeada<Mesa> mesas = new ListaEncadeada<Mesa>();

        for (int i = 1; i <= numeroMesas; i++) {
            mesas.inserirFinal(new Mesa(i));
        }

        return mesas;
    }

    public void incluirPessoaFilaAlmocar(Pessoa pessoa) {
        filaAlmocar.setLimite(filaAlmocar.getLimite() + 1);
        filaAlmocar.inserir(pessoa);
    }

    private Pessoa removerPessoaFilaAlmocar() {
        Pessoa pessoaRemovida = filaAlmocar.remover();
        filaAlmocar.setLimite(filaAlmocar.getLimite() - 1);
        return pessoaRemovida;
    }

    public boolean temPratosDisponiveis() {
        return !pratosDisponiveis.estaVazia();
    }

    public boolean temPessoasFilaAlmoco() {
        return filaAlmocar.numeroElementos() > 0;
    }

    public void incluirPessoaFilaBuffed() {
        if(!temPratosDisponiveis()) {
            System.out.println("Sem pratos disponíveis.");
            return;
        }

        if(!temPessoasFilaAlmoco()) {
            System.out.println("Sem pessoas na fila para almoçar.");
            return;
        }

        Pessoa pessoa = removerPessoaFilaAlmocar();
        pessoa.pegarPrato(pratosDisponiveis.pop());

        filaBuffet.setLimite(filaBuffet.getLimite() + 1);
        filaBuffet.inserir(pessoa);
    }

    private Pessoa removerPessoaFilaBuffet() {
        Pessoa pessoaRemovida = filaBuffet.remover();
        filaAlmocar.setLimite(filaAlmocar.getLimite() - 1);
        return pessoaRemovida;
    }

    public ListaEncadeada<Mesa> getMesasDisponiveis() {
        
        ListaEncadeada<Mesa> mesas = new ListaEncadeada<Mesa>();

        Nodo<Mesa> nodo = this.mesas.getInicio();

        while(nodo != null) {
            Mesa mesa = nodo.getDado();

            if(!mesa.isOcupada()) {
                mesas.inserirFinal(mesa);
            }

            nodo = nodo.getProximo();
        }

        return mesas;
    }

    public String listarMesasDisponiveis() {
        String mesasDisponiveis = "";

        Nodo<Mesa> nodo = getMesasDisponiveis().getInicio();

        while(nodo != null) {
            Mesa mesa = nodo.getDado();

            if(!mesa.isOcupada()) {
                mesasDisponiveis += nodo.getDado().getNumero() + "\n";
            }

            nodo = nodo.getProximo();
        }

        return mesasDisponiveis;
    } 

    public boolean mesaDisponivel(int numeroMesa) {
        Nodo<Mesa> nodo = getMesasDisponiveis().getInicio();

        while(nodo != null) {
            Mesa mesa = nodo.getDado();

            if(mesa.getNumero() == numeroMesa) {
                return true;
            }

            nodo = nodo.getProximo();
        }

        return false;
    }

    public boolean temPessoasNaFilaBuffet() {
        return filaBuffet.numeroElementos() > 0;
    }

    public void ocuparMesa(int numeroMesa) {
        if(!mesaDisponivel(numeroMesa)) {
            return;
        }

        Nodo<Mesa> nodo = mesas.getInicio();

        while(nodo != null) {
            Mesa mesa = nodo.getDado();

            if(mesa.getNumero() == numeroMesa) {
                mesa.ocuparMesa(removerPessoaFilaBuffet());
                return;
            }

            nodo = nodo.getProximo();
        }
    }

    public ListaEncadeada<Mesa> getMesasOcupadas() {
        
        ListaEncadeada<Mesa> mesas = new ListaEncadeada<Mesa>();

        Nodo<Mesa> nodo = this.mesas.getInicio();

        while(nodo != null) {
            Mesa mesa = nodo.getDado();

            if(mesa.isOcupada()) {
                mesas.inserirFinal(mesa);
            }

            nodo = nodo.getProximo();
        }

        return mesas;
    }

    public String listarMesasOcupadas() {
        String mesasOcupadas = "";

        Nodo<Mesa> nodo = getMesasOcupadas().getInicio();

        while(nodo != null) {
            Mesa mesa = nodo.getDado();

            if(mesa.isOcupada()) {
                mesasOcupadas += nodo.getDado().getNumero() + "\n";
            }

            nodo = nodo.getProximo();
        }

        return mesasOcupadas;
    } 

    public boolean mesaOcupada(int numeroMesa) {
        Nodo<Mesa> nodo = getMesasOcupadas().getInicio();

        while(nodo != null) {
            Mesa mesa = nodo.getDado();

            if(mesa.getNumero() == numeroMesa) {
                return true;
            }

            nodo = nodo.getProximo();
        }

        return false;
    }

    public void desocuparMesa(int numeroMesa) {

        Nodo<Mesa> nodo = mesas.getInicio();

        while(nodo != null) {
            
            Mesa mesa = nodo.getDado();

            if(mesa.getNumero() == numeroMesa) {
                Pessoa pessoa = mesa.desocupar();
                pratosParaRepor.push(pessoa.soltarPrato());
                filaCaixa.setLimite(filaCaixa.getLimite() + 1);
                filaCaixa.inserir(pessoa);
                break;
            }

            nodo = nodo.getProximo();
        }
    }

    public boolean filaCaixaVazia() {
        return filaCaixa.estaVazia();
    }

    public void realizarPagamento() {
        if(filaCaixaVazia()) return;

        filaCaixa.remover();
        filaCaixa.setLimite(filaCaixa.getLimite() - 1);

        pessoasAtendidas++;
        valorEmCaixa += VALOR_ALMOCO;
    }

    public int numeroPratosParaRepor() {
        return pratosParaRepor.numeroElementos();
    }

    public void reporPratos(int numeroPratos) {
        for (int i = 0; i < numeroPratos; i++) {
            pratosDisponiveis.push(pratosParaRepor.pop());
        }
    }

    public String relatorio() {
        String relatorio = "";
        
        relatorio += "Número de pessoas na fila para almoçar: "     + filaAlmocar.numeroElementos()        + "\n";
        relatorio += "Número de pessoas na fila do caixa: "         + filaCaixa.numeroElementos()          + "\n";
        relatorio += "Número de pessoas no restaurante almoçando: " + getMesasOcupadas().numeroElementos() + "\n";
        relatorio += "Número de pessoas atendidas: "                + pessoasAtendidas                     + "\n";
        relatorio += "Valor em caixa: "                             + valorEmCaixa                         + "\n";
        relatorio += "Número de pratos na pilha: "                  + pratosDisponiveis.numeroElementos()  + "\n";
        relatorio += "Número de mesas livres: "                     + getMesasDisponiveis().numeroElementos();
        
        return relatorio;
    }

    @Override
    public String toString() {
        String restaurante = "";

        restaurante += "Pratos Dispíveis:\n" + pratosDisponiveis;
        restaurante += "Pratos Reposição:\n" + pratosParaRepor;
        restaurante += "Fila para almoçar:\n" + filaAlmocar;
        restaurante += "Fila buffet:\n" + filaBuffet;
        restaurante += "Mesas:\n" + mesas;


        return restaurante;
    }
}