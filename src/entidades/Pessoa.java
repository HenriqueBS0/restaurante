package entidades;

public class Pessoa {
    private String nome;
    private Prato prato;

    public Pessoa(String nome) {
        setNome(nome);
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void pegarPrato(Prato prato) {
        this.prato = prato;
    }

    public Prato soltarPrato() {
        Prato prato = this.prato;
        prato = null;
        return prato;
    }

    @Override
    public String toString() {
        return nome;
    }
}
