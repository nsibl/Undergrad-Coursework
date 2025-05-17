import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileHandler {
    private String surveyFile = "survey_results.csv";
    private FileWriter fileOutput;
    private PrintWriter printWriter;

    public FileHandler() {
        try {
            String header = "DateTime,FirstName,LastName,PhoneNumber,Email,Gender,Water,Meals,Wheat,sugar,Dairy,Miles,Weight\n";
            printWriter = new PrintWriter(new FileWriter(surveyFile, true));

            printWriter.write(header);

            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResults(String surveyData) {
        try {
            fileOutput = new FileWriter(surveyFile, true);

            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();

            String newDate = formatter.format(date);

            fileOutput.append(newDate+","+surveyData+"\n");

            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}