import java.io.FileReader;
import java.io.IOException;

public class TextGenerator {

    public TextGenerator() {
        // TODO Auto-generated constructor stub
    }
    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        String filename = args[2];
        MarkovModel model = new MarkovModel(K,filename);
        model.train(filename);
        FileReader R = new FileReader(filename);
        String start = "";
        for (int i = 0; i < K; i++) {
            int c = R.read();
            start += (char) c;
        }
        System.out.println(model.generateText(M, start));
  }
}
