/*
    Noah Sibley
    COP 3503
    12/04/23
    Project 3
*/

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


   // Project 3 class generates working order

public class Project3 {

	   // Employee data file path

	public static String employeeFile = "employee_data.csv";
	

	 //   Tier 1 Ticket CSV file path

	public static String tier1TicketFile = "tier1_ticket_data.csv";
	
	 //   Tier 2 Ticket CSV file path

    public static String tier2TicketFile = "tier2_ticket_data.csv";
    

     //   Work order data CSV file path

    public static String workOrderFileName = "workorder_data.csv";
    

    //    Employee objects list

    public static ArrayList<Employee> employeeList = new ArrayList<Employee>();
    
    public static Queue<Ticket> tier1TicketList;

    public static Queue<Ticket> tier2TicketList;
 
    public static ArrayList<WorkOrder> workOrderList = new ArrayList<WorkOrder>();
    

     //   Main method

    public static void main(String[] args) {
    	// prints title
    	System.out.println("Project 3 Work Order Generator\n");
    	

    	System.out.println("Loading Employee Data");
    	FileHandler.readEmployeeData(employeeFile);
    	

    	System.out.println("Loading Ticket Data");
    	tier1TicketList = FileHandler.readTicketData(tier1TicketFile);
    	tier2TicketList = FileHandler.readTicketData(tier2TicketFile);
    	
    	// calling method
    	System.out.println("Creating Work Orders");
    	createWorkOrders();
    	
    	// Writes work order data
    	System.out.println("Writing Work Order Data to File");
    	FileHandler.writeData(workOrderFileName);
    	
    	// Prints program ending
    	System.out.println("Work Order Created. Program Exiting");
    	
    } // End main

       // Create work orders, assign tickets, formate date, and write to file

    private static void createWorkOrders() {
    	// Formats the date
    	 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
 		
 			Date date = new Date();

	        
    	 while (!tier1TicketList.isEmpty() ||!tier2TicketList.isEmpty()){
    		 String currentCreatedAt;
    		 

    		 
    		 // Assigns tier 1 tickets to tier 1 employees
             if (!tier1TicketList.isEmpty()) {
            	 
                 Employee currentEmployee = employeeList.remove(0); 
                 
                 currentCreatedAt = dateFormat.format(date);
                 // Makes sure employee is right tier
                 if (!(currentEmployee instanceof Tier2Employee)) {
                     Ticket currentTicket = tier1TicketList.poll();
                     

                     WorkOrder workOrder = new WorkOrder (currentEmployee, currentTicket, currentCreatedAt);
                     
                     
                     workOrderList.add(workOrder);
                 }


                 employeeList.add(currentEmployee);
                 

                 
             }    

             // Once again checks if right tier and assigns tier 2
             else if (!tier2TicketList.isEmpty()) {
                 Employee currentEmployee = employeeList.remove(0);
                 
                 currentCreatedAt = dateFormat.format(date);

                 if (currentEmployee instanceof Tier2Employee) {
                     Ticket currentTicket = tier2TicketList.poll();
                     
                     WorkOrder workOrder = new WorkOrder(currentEmployee, currentTicket, currentCreatedAt);

                     workOrderList.add(workOrder);
                     
                 }


                 employeeList.add(currentEmployee);
                
             }

    	        
    	 }
    	 
    } //Ends create work orders method
}
// End program
