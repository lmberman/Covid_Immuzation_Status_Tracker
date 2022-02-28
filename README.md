# Covid_Immuzation_Status_Tracker
A Prototype of a covid immunization status tracker for local health care center

## Application Setup and Structure
A Java base web application using the Spring Framework for Web Mvc, H2 DB, and Unit Testing

- [ ] Use Cases of how the application is expected to be used
- [ ] How the application is not expected to be used
- [ ] What each stackholder will be able to do and how?
- [ ] How to mitigate miss use of attacks

### Requirements
- [ ] What information are we taking in?
  1. Username and password 
  2. Name of the Person (First Middle and Last) 
  3. Immunication Status (Yes or No)
  4. Type of Vaccine
  5. Dates of Vaccination
  6. Some proof like a picture of their vaccine card?
  7. Phone number, Address, Email (All PII and needs to be encrypted)

- [ ] What are we doing with that information?
  1. Storing this formation in a DB
- [ ] DB tables are:
  - [ ] 1. Customer Contact Information (Name, Address, Phone Number, and Email Address)
  - [ ] 2. Users of the application (CSRA, CEO, Employees, Customers)
   Three types of stakeholders 
	1. CSRA (Customer Support Representative Admin)
	2. Standard Users who will login and provide their information
	3. Employees and CEO of the Care Center
  - [ ] 3. Auditing of actions done within the system by all stakeholders
 
- [ ] What are we giving back to the customer to use?
  1. A List of person's vaccinated status

- [ ] How could the system become vunerable?
- [ ] What are the boundaries for our input? 
	1. User names will be strings and must not allow special characters and 	must have length limits
	2. Dates must have a specific format and should use a plugin that will 		allow the date to be selected and not manually input
	3. Type of Vaccine should be a drop down
	4. Picture should be restricted to certain types of image extensions
	5. Password must have an upper and lower bound and must be stored 		encrypted. 
	''' 
	1. Validation on this password must decrypt the password
	2. The key must be stored with a password or some kind of security 		standard to avoid it being accessed by a hacker
	''' 
