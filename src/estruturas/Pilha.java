package estruturas;

public class Pilha<T> {
    private Nodo<T> topo;
    private int tamanho = 0;
    private int limite;

    public Pilha(int limite) {
        setLimite(limite);
    }

    private void setTopo(Nodo<T> nodo) {
        topo = nodo;
    }

    private Nodo<T> getTopo() {
        return topo;
    }

    private void incrementaTamanho() {
        tamanho++;
    }

    private void decrementaTamanho() {
        tamanho--;
    }

    private int getTamanho() {
        return tamanho;
    }

    private void setLimite(int limite) {
        this.limite = limite;
    }

    private int getLimite() {
        return limite;
    }

    public boolean estaVazia() {
        return getTamanho() == 0;
    }

    private boolean temEspaco() {
        return  getTamanho() < getLimite();
    }

    public void push(T dado) {
        if(!temEspaco()) return;

        Nodo<T> nodo = new Nodo<T>(dado);

        nodo.setProximo(getTopo());
        setTopo(nodo);
        
        incrementaTamanho();
    }

    public T pop() {
        if(estaVazia()) return null;

        Nodo<T> nodoRemovido = getTopo();

        setTopo(getTopo().getProximo());

        decrementaTamanho();

        return nodoRemovido.getDado();
    }

    public T getDadoTopo() {
        if(getTopo() == null) return null;

        return getTopo().getDado();
    }

    public int numeroElementos() {
        return getTamanho();
    }

    @Override
    public String toString() {
        String pilha = "";

        Nodo<T> nodo = getTopo();

        while(nodo != null) {
            pilha+= nodo.getDado() + "\n";
            nodo = nodo.getProximo();
        }

        return pilha;
    }
}
