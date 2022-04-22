package estruturas;

public class Nodo<T> {

    private Nodo<T> anterior = null;
    private Nodo<T> proximo = null;
    private T dado = null;

    public Nodo(T dado) {
        this.setDado(dado);
    }

    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }

    public Nodo<T> getAnterior() {
        return anterior;
    }

    public void setProximo(Nodo<T> proximo) {
        this.proximo = proximo;
    }

    public Nodo<T> getProximo() {
        return this.proximo;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public T getDado() {
        return dado;
    }
}
