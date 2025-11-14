package feifood;

import feifood.dao.PedidoDAO;
import feifood.model.Alimento;
import feifood.model.Pedido;

public class TestePedido {
    public static void main(String[] args) {
        Pedido pedido = new Pedido();
        pedido.setIdUsuario(1); // coloca um usuário que existe no BD

        // adiciona um alimento de ID existente (por exemplo, 1 = Hambúrguer)
        Alimento a1 = new Alimento();
        a1.setId(1);
        pedido.adicionarItem(a1);

        PedidoDAO dao = new PedidoDAO();
        boolean sucesso = dao.cadastrarPedido(pedido);

        System.out.println("Resultado: " + sucesso);
    }
}
