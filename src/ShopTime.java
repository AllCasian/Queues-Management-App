import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ShopTime extends JPanel {
    private final JLabel label = new JLabel("0");
    private final AtomicInteger time = new AtomicInteger(0);
    private final int tMax;

    private final Scheduler scheduler;
    private final ClientsRoom clientsRoom;

    public ShopTime(int tMax, Scheduler scheduler, ClientsRoom clientsRoom) {
        this.tMax = tMax;
        this.scheduler = scheduler;
        this.clientsRoom = clientsRoom;

        label.setFont(new Font("arial", Font.BOLD, 30));

        this.setPreferredSize(new Dimension(1400, 50));
        this.setLayout(new FlowLayout());
        this.add(label);

        startTimer();
    }

    private void startTimer() {
        Thread thread = new Thread(() -> {
            try {
                FileOutputStream writer = new FileOutputStream("LogFile.txt");
                do {
                    Thread.sleep(1000);

                    writer.write(("Time " + time.get() + "\n").getBytes());
                    writer.write((clientsRoom.toString() + "\n").getBytes());
                    writer.write((scheduler.toString() + "\n").getBytes());

                    ArrayList<Client> nowClients = clientsRoom.getNowClients(time.get());
                    if (nowClients != null) {
                        for (Client client : nowClients) {
                            scheduler.strategy(client);
                        }
                    }
                    updateTime();

                    if (scheduler.finish() & clientsRoom.finish())
                        break;
                } while (time.get() != tMax);
                writer.write(("\nEnd Test").getBytes());
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    void updateTime() {
        label.setText(String.valueOf(time.incrementAndGet()));
    }

}
