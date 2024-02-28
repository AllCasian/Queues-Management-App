import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomClientGenerator extends HashMap<Integer, ArrayList<Client>> {

    public RandomClientGenerator(JPanel clientsRoom, int N, int[] tArrival, int[] tService, StringBuilder str) {
        Random random = new Random();
        AtomicInteger ids = new AtomicInteger(1);

        for (int i = 0; i < N; i++) {
            int arrive = random.nextInt(tArrival[0], tArrival[1] + 1);
            Client adder = new Client(ids.getAndIncrement(), arrive, random.nextInt(tService[0], tService[1] + 1));
            clientsRoom.add(adder);
            str.append(adder);

            if (this.containsKey(arrive)) {
                this.get(arrive).add(adder);
            } else {
                ArrayList<Client> temp = new ArrayList<>();
                temp.add(adder);
                this.put(arrive, temp);
            }
        }
    }

}
