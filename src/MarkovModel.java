import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MarkovModel {

    HashMap<String,State> model;
    int K;

    public MarkovModel(int modelparameter, String fileName) {
        this.model = new HashMap<String,State>();
        K = modelparameter;
        train(fileName);
    }

    public void train(String fileName) {
        try {
            FileReader R = new FileReader(fileName);
            String s = "";
            for (int i = 0; i < K; i++) {
                int c = R.read();
                s += (char) c;
            }
            boolean done = false;
            while (!done) {
                int c = R.read();
                if (c == -1) {
                    done = true;
                } else {
                    if (model.containsKey(s) == true) {
                        State variable = model.get(s);
                        variable.add();
                        variable.add((char) c);

                    } else {
                        State x = new State(s);
                        x.add();
                        x.add((char) c);
                        model.put(s, x);
                    }
                    s += (char) c;
                    s = s.substring(1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Bad file name");
        } catch (IOException e) {
            System.out.println("IOEException");
        }
    }

    public String generateText(int M, String start) {
        String text = start;
        String s = start;
        while (text.length() < M) {
            if (model.containsKey(s)) {
                State f = model.get(s);
                char c = f.generate();
                text += c;
                s += c;
                s = s.substring(1);
            }else {
                s = start;
                State f = model.get(s);
                char c = f.generate();
                text += c;
                s += c;
                s = s.substring(1);
            }
        }
        return text;
    }

    public void printModel() {
        System.out.printf("%d distinct states:\n", model.size());
        for (String s : model.keySet())
              System.out.printf("   %s\n", model.get(s));
  }

}
                                  