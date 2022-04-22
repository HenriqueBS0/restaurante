package entidades;

public class Mesa {
    private boolean ocupada = false;
    private Pessoa pessoa;
    private int numero;

    public Mesa(int numero) {
        this.numero = numero;
    }

    public void ocuparMesa(Pessoa pessoa) {
        this.pessoa = pessoa;
        ocupada = true;
    }   

    public Pessoa desocupar() {
        Pessoa pessoa = this.pessoa;
        this.pessoa = null;
        ocupada = false;
        return pessoa;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isOcupada() {
        return ocupada;
    }
}
