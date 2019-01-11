package pojos;

public class Avaliacao {

    String nome;
    String urlFoto;
    String comentario;
    String tipoFesta;
    double numeroAvaliacao;


    public Avaliacao(){


    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getCometario() {
        return comentario;
    }

    public void setCometario(String cometario) {
        this.comentario = cometario;
    }

    public String getTipoFesta() {
        return tipoFesta;
    }

    public void setTipoFesta(String tipoCliente) {
        this.tipoFesta = tipoCliente;
    }

    public double getNumeroAvaliacao() {
        return numeroAvaliacao;
    }

    public void setNumeroAvaliacao(double numeroAvaliacao) {
        this.numeroAvaliacao = numeroAvaliacao;
    }
}
