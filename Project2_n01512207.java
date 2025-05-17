/*
	Noah Sibley
	COP 3503
	Project 2 Input/Output
	Due 10/30/23
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;


//Program's purpose is to preprocess data from a given text input file
public class Project2_n01512207 {

    public static ArrayList<String> dates = new ArrayList<String>();
    public static ArrayList<String> times = new ArrayList<String>();
    public static ArrayList<Double> sensor2278 = new ArrayList<Double>();
    public static ArrayList<Double> sensor3276 = new ArrayList<Double>();
    public static ArrayList<Double> sensor4689 = new ArrayList<Double>();
    public static ArrayList<Double> sensor5032 = new ArrayList<Double>();
    public static ArrayList<Double> section1Diff = new ArrayList<Double>();
    public static ArrayList<Double> section2Diff = new ArrayList<Double>();
    public static ArrayList<Double> totalAvg = new ArrayList<Double>();


    // Reads data from file, returns bufferedReader
    public static BufferedReader readData(String input) throws FileNotFoundException {
        //Opens Buffered Reader for given file
        BufferedReader br = new BufferedReader(new FileReader(input));
        return br;
    }
    //Method that calcs avg sensor value
    public static void findAvg() {
        //Start average calculations
        double sensor2278Sum = 0;
        for (double d : sensor2278) {
            sensor2278Sum += d;
        }

        double sensor2278Avg = sensor2278Sum / sensor2278.size();

        double sensor3276Sum = 0;
        for (double d : sensor3276) {
            sensor3276Sum += d;
        }

        double sensor3276Avg = sensor3276Sum / sensor3276.size();

        double sensor4689Sum = 0;
        for (double d : sensor4689) {
            sensor4689Sum += d;
        }

        double sensor4689Avg = sensor4689Sum / sensor4689.size();

        double sensor5032Sum = 0;
        for (double d : sensor5032) {
            sensor5032Sum += d;
        }

        double sensor5032Avg = sensor5032Sum / sensor5032.size();


        //Add calculations to an array
        totalAvg.add(sensor2278Avg);
        totalAvg.add(sensor3276Avg);
        totalAvg.add(sensor4689Avg);
        totalAvg.add(sensor5032Avg);
    }
    //Method that writes processed data to a new file
    public static void writeToFile(String input) throws IOException {
        //Generate new filename based on user input
        String fileName = input.replace(".csv", "_Difference.csv");
        //Opens a file writer for the newly created file
        FileWriter fw = new FileWriter(fileName);

        //This writes the header for the new file
        fw.write("Date, Time, Sensor_2278, Sensor_3276, Sensor_4689, Sensor_5032, Section1_Diff, Section2_Diff, Total_Avg \n");

        //This converts the objects into arrays so the program can iterate through
        Object[] datesArray = dates.toArray();
        Object[] timesArray = times.toArray();
        Object[] sensor2278Array = sensor2278.toArray();
        Object[] sensor3276Array = sensor3276.toArray();
        Object[] sensor4689Array = sensor4689.toArray();
        Object[] sensor5032Array = sensor5032.toArray();
        Object[] section1DiffArray = section1Diff.toArray();
        Object[] section2DiffArray = section2Diff.toArray();
        Object[] totalAvgArray = totalAvg.toArray();
        Object[] totalAvgCopyArray = Arrays.copyOf(totalAvgArray, datesArray.length);


        //Here it writes the data to the new file
        for (int i = 0; i < datesArray.length; i++) {
            fw.write(datesArray[i] + ",");
            fw.write(timesArray[i] + ",");
            fw.write(sensor2278Array[i] + ",");
            fw.write(sensor3276Array[i] + ",");
            fw.write(sensor4689Array[i] + ",");
            fw.write(sensor5032Array[i] + ",");
            fw.write(section1DiffArray[i] + ",");
            fw.write(section2DiffArray[i] + ",");
            fw.write(totalAvgCopyArray[i] + "\n");
        }

        fw.close();
    }

    //Main program function where user interacts
    public static void main(String[] args) {

        System.out.println("Project 2 Data Preprocessing\n");

        String userChoice = "0";
        Scanner scnr = new Scanner(System.in);
        BufferedReader br = null;

        //Main loop for user input and processing the data
        do {
            System.out.println("Enter file name and location");
            String fileNameInput = scnr.nextLine();

            boolean projectDone = true;

            try {
                System.out.println("Reading data from file " + fileNameInput);

                br = readData(fileNameInput);

                String line;
                String dl = ",";
                String[] reader;

                line = br.readLine();

                //This processes the data line-by-line
                while ((line = br.readLine()) != null) {
                    reader = line.split(dl);

                    try {
                        //This will parse and format the date values
                        Date date = new SimpleDateFormat("MM/dd/yyy").parse(reader[0]);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

                        dates.add(formatter.format(date));
                    } catch (ParseException e) {
                        System.out.println("Converting dates from MM/DD/YYYY to YYYY/MM/DD");
                        System.out.println("Bad date in CSV file");
                        System.out.println("Check CSV file data and try again");
                        projectDone = false;
                    }

                    times.add(reader[1]);

                    //Adding rest of data to array lists
                    sensor2278.add(Double.parseDouble(reader[2]));
                    sensor3276.add(Double.parseDouble(reader[3]));
                    sensor4689.add(Double.parseDouble(reader[4]));
                    sensor5032.add(Double.parseDouble(reader[5]));

                    section1Diff.add(Double.parseDouble(reader[2]) - Double.parseDouble(reader[3]));
                    section2Diff.add(Double.parseDouble(reader[4]) - Double.parseDouble(reader[5]));


                }

                findAvg();

            //Catch statements for .csv file error exceptions
            } catch (NumberFormatException | FileNotFoundException nfe) {
                System.out.println("Bad number data in CSV file");
                System.out.println("Check CSV file data and try again");
                projectDone = false;
            } catch (IOException ioe) {
                System.out.println("Error reading the file");
                projectDone = false;
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (projectDone) {
                System.out.println("Converting dates from MM/DD/YYYY to YYYY/MM/DD");
                System.out.println("Calculating speed difference");
                System.out.println("Calculating speed average");

                try {
                    //Finally writing processed data to a new file
                    writeToFile(fileNameInput);
                    System.out.println("Done, exiting program");
                    System.exit(0);
                } catch (IOException ioe) {
                    System.out.println("Error writing to file");
                    System.out.println("Check file permissions and try again");
                }

            }

        } while (!userChoice.equals("1"));

        scnr.close();
    }
}

//Exit program