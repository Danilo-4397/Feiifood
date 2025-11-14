package feifood.model;

public class Avaliacao {
    private int id;
    private int idPedido;
    private int nota;
    private String comentario;

    public Avaliacao() {}

    public Avaliacao(int idPedido, int nota, String comentario) {
        this.idPedido = idPedido;
        this.nota = nota;
        this.comentario = comentario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
