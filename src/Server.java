import javax.swing.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread {
    private final BlockingQueue<Client> clients;
    private final AtomicInteger timeWaited;
    private int totalTime = 0;

    private int currentTime;
    private int totalServiceTime;
    private final JPanel panel;

    public Server(int N, int tMax, JPanel panel) {
        clients = new ArrayBlockingQueue<>(N);
        timeWaited = new AtomicInteger(tMax);
        this.panel = panel;

        this.start();
    }

    public void place(Client client) {
        totalTime += client.getTService();
        clients.add(client);
        panel.add(client);
        totalServiceTime += client.getTService();
    }

    public void remove(Client client) {
        panel.remove(client);
        panel.revalidate();
        panel.repaint();
        clients.remove();
    }

    public int getSize() {
        return clients.size();
    }

    public int getTotalServiceTime(){
        return totalServiceTime;
    }

    public int getRemainingTime() {
        return timeWaited.get();
    }

    public int getCurrentTime(){
        return currentTime;
    }

    public int getTotalTime(){
        return totalTime;
    }

    @Override
    public void run() {
        try {
            do {
                Thread.sleep(1000);

                if (totalTime != 0) {
                    //currentTime = timeWaited.get() - totalTime;
                    totalTime--;

                    Client curr = clients.peek();
                    if (curr != null)
                        if(curr.updateClientTime() == 0) {
                            remove(curr);
                        }
                }
            } while (timeWaited.decrementAndGet() != 0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Client client : clients) {
            res.append(client);
        }
        return res.toString();
    }

}
