import java.util.Random;
import java.util.TreeMap;

public class State {

    String str;
    int count;
    TreeMap<Character,Integer> suffixes;
    Random rand;

    public State(String statestring) {
        this.str = statestring;
        this.count = 0;
        this.suffixes= new TreeMap<Character,Integer>();
        this.rand = new Random();
    }

    public void add() {
        count += 1;
    }

    public void add(char c) {

        if (suffixes.containsKey(c)) {
            suffixes.replace(c, suffixes.get(c) + 1);
        } else {
            suffixes.put(c, 1);
        }

    }


    public char generate() {

        int r = rand.nextInt(count);
        for (char c : suffixes.keySet()) {
            r -= suffixes.get(c);
            if (r < 0) {
                return c;
            }
        }
        return '#';

    }

    public String toString() {
        String s = String.format("%d %s:", count, str);
        for (Character ch : suffixes.keySet() )
            s += String.format(" (%c %d) ", ch, suffixes.get(ch));
        return s;
    }

}
