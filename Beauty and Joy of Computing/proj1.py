#This program helps users to determine their final bill for Osprey Car Rentals
print("Welcome to Osprey Car Rentals.\n")

#Inform customer about inputs and then ask to continue.
print("\tAt the prompts,  please enter the following:")
print("\tCustomer's classification code (a character: BDW)")
print("\tNumber of days the vehicle was rented (int)")
print("\tOdometer reading at the start of the rental period (int)")
print("\tOdometer reading at the end of the rental period (int)\n")

#Ask if customer wants to continue and while loop for if customer wants to continue..
should_continue = input("Would you like to continue (Y/N) ?: ")
while should_continue == "y" or should_continue == "Y":
    customer_code = input("\nCustomer code (BDW): ")
    #This is while loop to weed out invalid customer codes.
    while customer_code != "B" and customer_code != "D" and customer_code != "W":
        #Display Error message.
        print("\n\t**** Invalid customer code. Try again with 'B', 'D', or 'W'. ****")
        #Need to reassign for new input.....
        customer_code = input("\nCustomer Code (BDW): ")
    #Customer prompts
    if customer_code == "W":
        weeks_rented = int(input("Enter number of weeks rented (in whole number or decimal form): "))
    elif customer_code == "B" or customer_code == "D":
        days_rented = int(input("\nEnter number of days rented: "))
    odometer_start = int(input("\nEnter odometer reading at rental start date: "))
    odometer_end = int(input("\nEnter odometer reading at rental end date: "))

    #If/else statement for code B
    if customer_code == "B":
        milesdriven = odometer_end - odometer_start
        base = 40 * days_rented
        mileage = .25 * milesdriven
        bill = base + mileage
        print("\nThe final bill for your rental with code 'B' is: $ ", '{:.2f}'.format(bill))
        # Customer Summary:
        print("\nCustomer Summary: ")
        print("\nDays rented: ", days_rented)
        print("\nOdometer starting reading: ", odometer_start)
        print("\nOdometer ending reading: ", odometer_end)
        print("\nMiles driven: ", '{:.1f}'.format(milesdriven))
        print("\nAmount billed: ", '{:.2f}'.format(bill))
        should_continue = input("Would you like to continue (Y/N) ?: ")

    #If/else statement for code D
    if customer_code == "D":
        milesdriven = odometer_end - odometer_start
        base = 60 * days_rented
        if milesdriven <= 100:
            mileage = 0
        elif milesdriven > 100:
             milesdriven = milesdriven - 100
             mileage = .25 * milesdriven
        bill = base + mileage
        print("The final bill for your rental with code 'D' is: $ ",'{:.2f}'.format(bill))
        # Customer Summary:
        print("\nCustomer Summary: ")
        print("\nDays rented: ", days_rented)
        print("\nOdometer starting reading: ", odometer_start)
        print("\nOdometer ending reading: ", odometer_end)
        print("\nMiles driven: ", '{:.1f}'.format(milesdriven))
        print("\nAmount billed: ", '{:.2f}'.format(bill))
        should_continue = input("Would you like to continue (Y/N) ?: ")

    #If/else statement for code W
    if customer_code == "W":
       milesdriven = odometer_end - odometer_start
       base = 190 * weeks_rented
       average_miles = milesdriven / weeks_rented
       if average_miles <= 900:
           mileage = 0
       elif average_miles > 900 <= 1500:
           mileage = 100 * weeks_rented
       elif average_miles > 1500:
           charged_miles = milesdriven - (1500 * weeks_rented)
           mileage = 200 * charged_miles
       bill = base + mileage
       print("The final bill for your rental with code 'W' is: $ ", '{:.2f}'.format(bill))
       #Customer Summary:
       print("\nCustomer Summary: ")
       print("\nWeeks rented: ", weeks_rented)
       print("\nOdometer starting reading: ", odometer_start)
       print("\nOdometer ending reading: ", odometer_end)
       print("\nMiles driven: ", '{:.1f}'.format(milesdriven))
       print("\nAmount billed: ", '{:.2f}'.format(bill))
       should_continue = input("Would you like to continue (Y/N) ?: ")



#Print goodbye message if the user doesn't want to continue...
print("Thank you for your loyalty!")
    

