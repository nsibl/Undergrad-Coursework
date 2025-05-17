//Customer class
public class Customer extends Person  implements Printable{

	//customer id
	private String customerId;

	//Customer account number
	private String accountNumber;


	//Builds the customer
	public Customer(String firstName, String lastName, String address, String phoneNumber, String email, String customerId, String accountNumber) 
	{
		super(firstName, lastName, address, phoneNumber, email);
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		
	}

	//Returns file data as strings
	public String getFileData() 
	{
		
		return customerId + "," + super.getFileData();
		
	}

	//Gets account number
	public String getAccountNumber() {
		return accountNumber;
	}

	//Assigns account number
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	//Gets customer id
	public String getCustomerId() {
		return customerId;
	}

	//Assigns customer id
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}// end of class 
