package estruturas;

public class ListaDuplamenteEncadeada<T> {
    private Nodo<T> inicio = null;
    private Nodo<T> fim = null;

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

    public void inserirInicio(T dado) {
        Nodo<T> nodo = new Nodo<T>(dado) ;
        
        if(getInicio() != null) {
            nodo.setProximo(getInicio());
            getInicio().setAnterior(nodo);            
        }

        setInicio(nodo);

        if(getFim() == null) {
            setFim(nodo);
        }
    }
    

    public void inserirFinal(T dado) {
        Nodo<T> nodo = new Nodo<T>(dado) ;
        
        if(getFim() != null) {
            nodo.setAnterior(getFim());
            getFim().setProximo(nodo);            
        }

        setFim(nodo);

        if(getInicio() == null) {
            setInicio(nodo);
        }
    }

    public void removerInicio() {
        if(getInicio() == null) return;

        Nodo<T> nodoRemovido = getInicio();

        setInicio(getInicio().getProximo());

        if(getInicio() != null) {
            getInicio().setAnterior(null);
        }

        if(nodoRemovido == getFim()) {
            removerFinal();
        }
    }

    public void removerFinal() {
        if(getFim() == null) return;

        Nodo<T> nodoRemovido = getFim();

        setFim(getFim().getAnterior());

        if(getFim() != null) {
            getFim().setProximo(null);
        }

        if(nodoRemovido == getInicio()) {
            removerInicio();
        }
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
