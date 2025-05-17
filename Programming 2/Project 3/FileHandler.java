import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

//Provides methods for reading/writing to file

public class FileHandler {
	

	static ArrayList<WorkOrder> list = Project3.workOrderList;
	

	//Writing work order data to file
	public static void writeData(String workOrderFileName) {

		FileHandler.logger("Writing Work Order Data to File");
		
		try {

			FileOutputStream output = new FileOutputStream(workOrderFileName);
			
			try (PrintWriter writer = new PrintWriter(output)) {
				writer.println("customer_id,customer_first_name,customer_last_name,ticket_id,ticket_createdAt,workorder_createdAt,employee_id,employee_first_name,employee_last_name,clocked_in,certification");
				
				
				 for (WorkOrder workOrder : list) {
					writer.println(workOrder.getFileData());
					logger(workOrder.getFileData());
				}

					writer.close();
					try {
						output.close();
					} catch (IOException e) {
						
						e.printStackTrace();
					} 
			}
			
		} 
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		FileHandler.logger("Work Order file Created. Program Exiting");		
	}
	//Ends the write data method

	//Reads the ticket and returns a linked list
	public static LinkedList<Ticket> readTicketData(String TicketFileName) {

		FileHandler.logger("Loading Ticket Data");

		LinkedList<Ticket> ticketList = new LinkedList<>();

		ArrayList<String> customer_Id = new ArrayList<String>();
		ArrayList<String> first_name = new ArrayList<String>();
		ArrayList<String> last_name = new ArrayList<String>();
		ArrayList<String> email = new ArrayList<String>();
		ArrayList<String> address = new ArrayList<String>();
		ArrayList<String> phone_number = new ArrayList<String>();
		ArrayList<String> account_number = new ArrayList<String>();
		ArrayList<String> ticket_Id = new ArrayList<String>();
		ArrayList<String> createdAt = new ArrayList<String>();


		FileInputStream fileScanner;
		try {

			fileScanner = new FileInputStream(TicketFileName);

			Scanner scanner = new Scanner(fileScanner);

			scanner.nextLine();

			while(scanner.hasNext()) {

				String nextLine = scanner.nextLine();

				String[] divide = nextLine.split(",");


				customer_Id.add(divide[0]);
				first_name.add(divide[1]);
				last_name.add(divide[2]);
				email.add(divide[3]);
				address.add(divide[4]);
				phone_number.add(divide[5]);
				account_number.add(divide[6]);
				ticket_Id.add(divide[7]);
				createdAt.add(divide[8]);

			}


			for ( int i = 0 ; i < customer_Id.size()  ; i++) {
				Customer customerObject = new Customer (first_name.get(i),last_name.get(i),address.get(i),phone_number.get(i),email.get(i),customer_Id.get(i),account_number.get(i));
				Ticket ticketObject = new Ticket(customerObject,createdAt.get(i),ticket_Id.get(i));
				ticketList.add(ticketObject);
			}


			scanner.close(); // close scanner

		}
		catch (FileNotFoundException e) {

			e.printStackTrace();

		} //Ends try/catch

		return ticketList;

	} //Ends read ticket data method
	

	//Reads employee data and inputs into main list
	public static void readEmployeeData(String employeeFile) {
		
		FileHandler.logger("Loading Employee Data");
		
		ArrayList<String> employee_id = new ArrayList<String>();
		ArrayList<String> first_name = new ArrayList<String>();
		ArrayList<String> last_name = new ArrayList<String>();
		ArrayList<String> email = new ArrayList<String>();
		ArrayList<String> address = new ArrayList<String>();
		ArrayList<String> phone_number = new ArrayList<String>();
		ArrayList<String> clocked_in = new ArrayList<String>();
		ArrayList<String> date_hired = new ArrayList<String>();
		ArrayList<String> tier = new ArrayList<String>();
		ArrayList<String> certification = new ArrayList<String>();
		
		FileInputStream fileScanner;
		try {
			
			fileScanner = new FileInputStream(employeeFile);
			
			Scanner scanner = new Scanner(fileScanner);
			
			scanner.nextLine();
			
			while(scanner.hasNext()) {

				String nextLine = scanner.nextLine();

				String[] divide = nextLine.split(",");

				employee_id.add(divide[0]);
				first_name.add(divide[1]);
				last_name.add(divide[2]);
				email.add(divide[3]);
				address.add(divide[4]);
				phone_number.add(divide[5]);
				clocked_in.add(divide[6]);
				date_hired.add(divide[7]);
				tier.add(divide[8]);
				certification.add(divide[9]);
	
				
			}

			//Assigns employee tiers

			for (int i = 0 ; i <  employee_id.size(); i++) {

				if (tier.get(i).contains("tier2")) {
					Tier2Employee tier2Object = new Tier2Employee (first_name.get(i),last_name.get(i),address.get(i),phone_number.get(i),email.get(i),employee_id.get(i),clocked_in.get(i),date_hired.get(i),certification.get(i));
					Project3.employeeList.add(tier2Object);
				}

				else if(tier.get(i).contains("tier1")) {

					Employee tier1Object = new Employee (first_name.get(i),last_name.get(i),address.get(i),phone_number.get(i),email.get(i),employee_id.get(i),clocked_in.get(i),date_hired.get(i));
					Project3.employeeList.add(tier1Object);
				}
				
			}
			scanner.close();
			//End of the loop and closes the scanner
		} 
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
		//Ends the try/catch
		
	
	}
	//End of the read employee data method


	

	//Logs time date
	private static void logger(String log) {
		//Formats the date
		 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	 		
			Date date = new Date();
			
			
			try ( PrintWriter logFileWriter = new PrintWriter(new FileOutputStream("log.txt", true));) {
				//Prints the data to a log file
	             logFileWriter.println("log: " + dateFormat.format(date) + " : " + log);
	             logFileWriter.close();
	        } 
			catch (IOException e) {
	            e.printStackTrace();
	        }
 	    
    }
}

//Ends file handling class
 