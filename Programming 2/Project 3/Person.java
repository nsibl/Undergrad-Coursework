/**
 * Person class represents a person.
 * Implements the Printable interface.
 */
public class Person implements Printable {

	/**
	 * the first name of the person
	 */
	private String firstName;
	/**
	 * the last name of the person
	 */
	private String lastName;
	/**
	 * the address of the person
	 */
	private String address;
	/**
	 * the phone number of the person
	 */
	private String phoneNumber;
	/**
	 * the email of the person
	 */
	private String email;

	/**
	 * Constructs a new Person with personal information.
	 * @param firstName the person's first name
	 * @param lastName the person's last name
	 * @param address the person's address 
	 * @param phoneNumber the person's phone number 
	 * @param email the person's email
	 */
	public Person(String firstName, String lastName, String address, String phoneNumber, String email) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;

	} // end of Person 

	/**
	 * gets a formatted string containing the data.
	 * @return formatted file data for the person.
	 */
	public String getFileData() 
	{
		return firstName + "," + lastName ;
	} // end of getFileData

	/**
	 * Gets the first name of the person
	 * @return firstName the first name of the person
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 *  Sets the first name of the person
	 * @param firstName the first name of the person
	 */
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the person
	 * @return lastName the last name of the person
	 */
	public String getLastName() 
	{
		return lastName;
	}

	/**
	 * Sets the last name of the person
	 * @param lastName the last name of the person
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Gets  the address of the person
	 * @return address the address of the person
	 */
	public String getAddress() 
	{
		return address;
	}

	/**
	 * Sets the address of the person
	 * @param address the address of the person
	 */
	public void setAddress(String address) 
	{
		this.address = address;
	}

	/**
	 * Gets the phone number of the person
	 * @return phoneNumber the phone number of the person
	 */
	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	/**
	 * Sets the phone number of the person
	 * @param phoneNumber the phone number of the person
	 */
	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the email of the person
	 * @return email the email of the person
	 */
	public String getEmail() 
	{
		return email;
	}

	/**
	 * Sets the email of the person
	 * @param email the email of the person
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}



} // end of class
