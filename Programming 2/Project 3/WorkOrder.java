//Work Orders
public class WorkOrder implements Printable {
	
	//Assigns employee to order
	private Employee employee;

	//Work order ticket
    private Ticket ticket;

	//Date/time
    private String createdAt; 
    

    public WorkOrder(Employee employee, Ticket ticket, String createdAt) {
        this.employee = employee;
        this.ticket = ticket;
        this.createdAt = createdAt;
       
    }


	//Once again returns file data as strings
    public String getFileData() {
        
        return ticket.getFileData() + "," + createdAt + "," + employee.getFileData();
        
    }

	//Gets ticket associated with order
	public Ticket getTicket() {
		return ticket;
	}

	//Assigns ticket to order
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	//Date/time
	public String getCreatedAt() {
		return createdAt;
	}

	//Assigns date/time
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

    //Gets the employee who has been assigned to order
	public Employee getEmployee() {
		return employee;
	}

	//Assigns employee to order
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}

//Ends the work order class
