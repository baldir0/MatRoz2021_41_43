import java.io.*;
import java.util.ArrayList;

public class FileController {
    public static String[] readFile(String fPath) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileReader File = new FileReader(fPath);
            BufferedReader reader = new BufferedReader(File);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.toArray(new String[0]);
    }

    public static void saveToFile(String fPath, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fPath));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
