import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Client extends JPanel {
    private final int id, tArrival;
    private final AtomicInteger tService;
    private final JLabel currSec;

    public Client(int id, int tArrival, int tService) {
        this.id = id;
        this.tArrival = tArrival;
        this.tService = new AtomicInteger(tService);

        this.setPreferredSize(new Dimension(20, 20));
        this.setBackground(Color.RED);
        currSec = new JLabel(String.valueOf(tService));

        this.add(currSec);

    }

    public int getTService() {
        return tService.get();
    }

    public int updateClientTime() {
        int curr = tService.decrementAndGet();
        currSec.setText(String.valueOf(curr));
        return curr;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + tArrival + ", " + tService + "); ";
    }

}
