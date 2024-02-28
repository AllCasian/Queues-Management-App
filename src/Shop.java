import javax.swing.*;
import java.awt.*;

public class Shop extends JFrame {

    public Shop(int N, int Q, int tMax, int[] tArrival, int[] tService) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Shop");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(new FlowLayout());

        Scheduler scheduler = new Scheduler(N, Q, tMax);
        ClientsRoom clientsRoom = new ClientsRoom(N, tArrival, tService);

        this.add(new ShopTime(tMax, scheduler, clientsRoom));
        this.add(scheduler);
        this.add(clientsRoom);

        this.setVisible(true);
    }

}
