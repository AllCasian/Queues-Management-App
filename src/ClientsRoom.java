import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientsRoom extends JPanel {
    private final HashMap<Integer, ArrayList<Client>> clientHashMap;
    StringBuilder str = new StringBuilder();

    public ClientsRoom(int N, int[] tArrival, int[] tService) {
        this.setPreferredSize(new Dimension(750, 750));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        clientHashMap = new RandomClientGenerator(this, N, tArrival, tService, str);
    }

    public ArrayList<Client> getNowClients(int sec) {
        ArrayList<Client> res = clientHashMap.remove(sec);
        if (res == null) {
            return null;
        }

        for (Client client : res) {
            int startChar = str.indexOf(client.toString());
            int endChar = startChar + client.toString().length();
            str.delete(startChar, endChar);
            this.remove(client);
        }
        this.revalidate();
        this.repaint();

        return res;
    }

    public boolean finish() {
        return clientHashMap.isEmpty();
    }

    @Override
    public String toString() {
        return "Waiting Clients: " + str.toString();
    }

}
