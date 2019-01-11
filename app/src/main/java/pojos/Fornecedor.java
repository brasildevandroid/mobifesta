package pojos;

public class Fornecedor {

    String nome;
    int numeroAvaliacoes;
    private double avaliacao;

    public int getNumeroAvaliacoes() {
        return numeroAvaliacoes;
    }

    public void setNumeroAvaliacoes(int numeroAvaliacoes) {
        this.numeroAvaliacoes = numeroAvaliacoes;
    }



    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }





    public Fornecedor(){}


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
