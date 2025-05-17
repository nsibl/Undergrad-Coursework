//Tier 2 employees
public class Tier2Employee extends Employee implements Printable {

	//Ensures employee is tier 2
	private String certification;


	//Builds a file with the given parameters
	public Tier2Employee(String firstName, String lastName, String address, String phoneNumber, String email, String employeeId, String clockedIn, String hiredDate, String certification) {
		super(firstName, lastName, address, phoneNumber, email, employeeId, clockedIn, hiredDate);
		this.certification = certification;
		
	}

	//Returns file data as strings
	@Override
	public String getFileData() {
		
		return super.getFileData() + "," + certification;
		
	}

	//Gets employee certification
	public String getCertification() {
		return certification;
	}

	//Assigns certification
	public void setCertification(String certification) {
		this.certification = certification;
	}

}

//Ends the tier 2 employee class