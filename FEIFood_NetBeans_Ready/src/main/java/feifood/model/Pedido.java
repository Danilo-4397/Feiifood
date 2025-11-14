package feifood.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private int idUsuario;
    private LocalDate dataPedido;
    private List<Alimento> itens;
    private Integer notaAvaliacao;
    private String comentario;

    public Pedido() {
        itens = new ArrayList<>();
        dataPedido = LocalDate.now();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public LocalDate getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }

    public List<Alimento> getItens() { return itens; }
    public void setItens(List<Alimento> itens) { this.itens = itens; }

    public void adicionarItem(Alimento a) { itens.add(a); }

    public Integer getNotaAvaliacao() { return notaAvaliacao; }
    public void setNotaAvaliacao(Integer notaAvaliacao) { this.notaAvaliacao = notaAvaliacao; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}

