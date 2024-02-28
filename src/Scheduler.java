import javax.swing.*;
import java.awt.*;

public class Scheduler extends JPanel {
    private final ClientsQueue[] queues;

    public Scheduler(int N, int Q, int tMax) {
        this.setPreferredSize(new Dimension(750, 750));
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        queues = new ClientsQueue[Q];
        for (int i = 0; i < Q; i++) {
            this.add(queues[i] = new ClientsQueue(N, tMax));
        }

    }

    /*void strategy(Client client) {
        int minIndex = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < queues.length; i++) {
            if (queues[i].getServer().getSize() < min) {
                min = queues[i].getServer().getSize();
                minIndex = i;
            }
        }
        queues[minIndex].getServer().place(client);
    }*/

    void strategy(Client client) {
        int minIndex = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < queues.length; i++) {
            //int totalServiceTime = queues[i].getServer().getTotalServiceTime();
            int timeUntilDone = queues[i].getServer().getTotalTime();
            if (timeUntilDone <= min) {
                min = timeUntilDone;
                minIndex = i;
            }
        }
        queues[minIndex].getServer().place(client);
    }

    public boolean finish() {
        for (ClientsQueue queue : queues) {
            if (queue.getServer().getSize() != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < queues.length; i++) {
            res.append("Queue ").append(i + 1).append(": ");
            if (queues[i].getServer().getSize() == 0) {
                res.append("Closed\n");
            } else {
                res.append(queues[i].getServer().toString()).append("\n");
            }
        }
        return res.toString();
    }

}
