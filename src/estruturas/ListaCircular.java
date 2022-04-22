package estruturas;

public class ListaCircular<T> {
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
        
        if(getInicio() == null) {
            inserirPrimeiroElemento(nodo);
            return;
        }

        nodo.setProximo(getInicio());
        nodo.setAnterior(getFim());

        getInicio().setAnterior(nodo);
        
        setInicio(nodo);

        getFim().setProximo(getInicio());
    }
    

    public void inserirFinal(T dado) {
        Nodo<T> nodo = new Nodo<T>(dado);

        if(getFim() == null) {
            inserirPrimeiroElemento(nodo);
            return;
        }

        nodo.setAnterior(getFim());
        nodo.setProximo(getInicio());

        getFim().setProximo(nodo);

        setFim(nodo);

        getInicio().setAnterior(getFim());
    }

    private void inserirPrimeiroElemento(Nodo<T> nodo) {
        setInicio(nodo);
        setFim(nodo);
        
        getInicio().setProximo(getFim());
        getInicio().setAnterior(getFim());

        getFim().setAnterior(getInicio());
        getFim().setProximo(getInicio());

    }

    public void removerInicio() {
        if(getInicio() == null) return;

        if(getInicio() == getFim()) {
            setInicio(null);
            setFim(null);
            return;
        }

        setInicio(getInicio().getProximo());
        
        getInicio().setAnterior(getFim());
        getFim().setProximo(getInicio());
    }

    public void removerFinal() {
        if(getFim() == null) return;

        if(getInicio() == getFim()) {
            setInicio(null);
            setFim(null);
            return;
        }

        setFim(getFim().getAnterior());
        
        getFim().setProximo(getInicio());
        getInicio().setAnterior(getFim());
    }


    public int numeroElementos() {
        if(getInicio() == null) return 0;

        int numeroElementos = 0;

        Nodo<T> nodo = getInicio();

        do {
            numeroElementos++;
            nodo = nodo.getProximo();
        } while (nodo != getInicio());


        return numeroElementos;
    }

    @Override
    public String toString() {
        if(getInicio() == null) return "";

        Nodo<T> nodo = getInicio();
        
        String lista = "";

        do {
            lista+= nodo.getDado() + "\n";
            nodo = nodo.getProximo();
        } while (nodo != getInicio());

        return lista;
    }
}
