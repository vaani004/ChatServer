import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SampleClass {
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            File fileObj = new File("input.txt");
            fr = new FileReader(fileObj);
            br = new BufferedReader(fr);

            String data;
            int counter = 0;

            while ((data = br.readLine()) != null) {
                int l = 0, r = data.length() - 1;
                while (l < r) {
                    if (data.charAt(l) == data.charAt(r)) {
                        counter++;
                    }
                    l++;
                    r--;
                }
            }

            System.out.println("Counter: " + counter);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}

