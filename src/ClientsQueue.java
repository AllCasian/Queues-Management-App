import javax.swing.*;
import java.awt.*;

public class ClientsQueue extends JPanel {
    private final Server server;

    public ClientsQueue(int N, int tMax) {
        this.setPreferredSize(new Dimension(700, 30));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(20, 20));
        panel.setBackground(Color.black);
        this.add(panel);

        server = new Server(N, tMax, this);
    }

    public Server getServer() {
        return server;
    }
}
