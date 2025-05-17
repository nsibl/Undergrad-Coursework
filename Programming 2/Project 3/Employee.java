// Employee main class
public class Employee extends Person implements Printable {

	// Employee id
	private String employeeId;

	// when they clocked in for work
	private String clockedIn;

	// Date hired
	private String hiredDate;

	// Employee personal info
	public Employee(String firstName, String lastName, String address, String phoneNumber, String email, String employeeId, String clockedIn, String hiredDate) {
		super(firstName, lastName, address, phoneNumber, email);

		this.employeeId = employeeId;
		this.clockedIn = clockedIn;
		this.hiredDate = hiredDate;
		
	} // End employee class

	// Formats employee file data
	public String getFileData() {

		return employeeId + "," + super.getFileData() + "," + clockedIn ;
	}

	//Gets hire date
	public String getHiredDate() {
		return hiredDate;
	}

	//Sets hire date to employee
	public void setHiredDate(String hiredDate) {
		this.hiredDate = hiredDate;
	}

	// Gets time clocked in
	public String getClockedIn() {
		return clockedIn;
	}

	//Sets the time clocked in to the employee
	public void setClockedIn(String clockedIn) {
		this.clockedIn = clockedIn;
	}

	//Gets each uniqe employee id
	public String getEmployeeId() {
		return employeeId;
	}

	//Sets the employee id to the employee
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}



}

// Ends the class
