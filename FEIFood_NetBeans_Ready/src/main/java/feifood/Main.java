package feifood;

import javax.swing.SwingUtilities;
import feifood.view.TelaLogin;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
