package estruturas;

public class ListaEncadeada<T> {
    private Nodo<T> inicio = null;

    public void setInicio(Nodo<T> nodo) {
        inicio = nodo;
    }

    public Nodo<T> getInicio() {
        return inicio;
    }

    public void inserirInicio(T dado) {
        Nodo<T> nodo = new Nodo<T>(dado);
        nodo.setProximo(inicio);
        setInicio(nodo);
    }

    public void inserirFinal(T dado) {
        Nodo<T> inicio = getInicio();

        if(inicio == null) {
            setInicio(new Nodo<T>(dado));
            return;
        }

        Nodo<T> nodo = inicio;

        while(nodo.getProximo() != null) {
            nodo = nodo.getProximo();
        }

        nodo.setProximo(new Nodo<T>(dado));
    }

    public void removerInicio() {
        if(getInicio() == null) return;
        setInicio(getInicio().getProximo());;
    }

    public void removerFinal() {
        if(getInicio() == null) return;

        Nodo<T> nodo = getInicio();

        if(nodo.getProximo() == null) {
            setInicio(null);
            return;
        }
        
        while(nodo.getProximo().getProximo() != null) {
            nodo = nodo.getProximo();
        }

        nodo.setProximo(null);
    }

    public int numeroElementos() {
        int numeroElementos = 0;

        Nodo<T> nodo = getInicio();

        while(nodo != null) {
            numeroElementos++;
            nodo = nodo.getProximo();
        }

        return numeroElementos;
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
