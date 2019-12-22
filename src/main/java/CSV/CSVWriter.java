package CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CSVWriter {
    public void writeToCsv(String path, String message) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("ID", "Name", "Designation", "Company"));
            csvPrinter.print(message);
            csvPrinter.flush();
        } catch (Exception exception) {
        }
    }

    public void apprendToCsv(String path, List<String> appendMessage) {
        try (FileWriter fw = new FileWriter(path, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String message : appendMessage) {
                out.println(message);
            }
        } catch (Exception e) {
            //exception handling left as an exercise for the reader
        }
    }
}
