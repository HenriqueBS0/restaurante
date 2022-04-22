package estruturas;

public class Fila<T> {
    private Nodo<T> inicio = null;
    private Nodo<T> fim = null;
    private int limite = 0;
    private int tamanho = 0;

    public Fila(int limite) {
        setLimite(limite);
    }

    private void setInicio(Nodo<T> nodo) {
        inicio = nodo;
    }

    private Nodo<T> getInicio() {
        return inicio;
    }

    private void setFim(Nodo<T> nodo) {
        fim = nodo;
    }

    private Nodo<T> getFim() {
        return fim;
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

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getLimite() {
        return limite;
    }

    public boolean estaVazia() {
        return getTamanho() == 0;
    }

    private boolean temEspaco() {
        return getTamanho() < getLimite();
    }

    public void inserir(T dado) {
        if(!temEspaco()) return;

        Nodo<T> nodo = new Nodo<T>(dado);

        if(estaVazia()) {
            setInicio(nodo);
            setFim(nodo);            
        }
        else {
            nodo.setAnterior(getFim());
            getFim().setProximo(nodo);
            setFim(nodo);
        }

        incrementaTamanho();
        return;
    }

    public T remover() {
        if(estaVazia()) return null;

        Nodo<T> nodoRemovido = getInicio();

        setInicio(getInicio().getProximo());

        decrementaTamanho();
        return nodoRemovido.getDado();
    }

    public T getDadoInicio() {
        if(estaVazia()) return null;
        return getInicio().getDado();
    }

    public int numeroElementos() {
        return getTamanho();
    }

    @Override
    public String toString() {
        String lista = "";

        Nodo<T> nodo = getInicio();
        

        while(nodo != null) {
            lista+= nodo.getDado() + "\n";
            nodo = nodo.getProximo();
        }

        return lista;
    }
}
