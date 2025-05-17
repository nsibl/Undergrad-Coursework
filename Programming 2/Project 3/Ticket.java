//Customer's ticket
public class Ticket implements Printable {
	
	//Customer associated with said ticket
	private Customer customer;

	//When ticket was made
    private String createdAt;

	//Ticket's id
    private String ticketId;
    
    //Builds ticket
    public Ticket(Customer customer, String createdAt, String ticketId) {
        this.customer = customer;
        this.createdAt = createdAt;
        this.ticketId = ticketId;
     
        
    }

	//Formats into string
    public String getFileData() {
        return customer.getFileData()  + "," + ticketId + "," + createdAt;   
    }

	//Gets ticekt id
	public String getTicketId() {
		return ticketId;
	}

	//Assigns ticket id
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	//Gets date/time
	public String getCreatedAt() {
		return createdAt;
	}

	//Assigns date/time
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

   	//Gets customer
	public Customer getCustomer() {
		return customer;
	}

	//Assigns customer
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}

//Ends the ticket class