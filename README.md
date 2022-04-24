# Covid_Immunization_Status_Tracker
The covid Immunization status and contract tracker is an academic project prototype for a fictional health
department in the united states used to maintain the covid test results and vaccination status of the health care department patients.
It is intended to provide stackholders of the application the ability to add, view, and update covid test and vaccine records for department patients.

## Stack Holders of the application
The application services three levels of the health department to perform certain tasks related to
system data including:
    
    1. The CEO of the department
        i. Able to view all patients of the system and their covid test and vaccination records
    2. The Employees
        i. Able to view all patients of the system and their covid test and vaccination records
    3. The Customer Support Representatives
        i. Able to view all patients of the system and their covid test and vaccination records
        ii. Able to view contact information for a given patient in order to contact them regarding their records
    4. The Patients
        i. Able to add their Covid Tests; whether pending result, positive, or negative and the date for which it was taken
        ii. Able to add their Covid Vaccine Shots and the date on which it was administer (Pfiser 1 and 2, Modern 1 and 2)
        iii. Able to update pending result Covid Tests in order to change their status

![Alt text](src/main/resources/static/TestResultsScreenshot.png?raw=true "Optional Title")
![Alt text](src/main/resources/static/VaccinationScreenshot.png?raw=true "Optional Title")

## How user access the application
To load the application, 
    
    1. Load it into a java IDE of your choice as a maven java project.
    2. This application uses Java 8 so make sure that version is install on your machine
    3. Create a run execution of the application using the Java Main Class CovidTrackerApplication.java in src/main/java.
    4. Access the application, open a browser and navigate to localhost:8080/, this will log the login screen.

### Logging In:

![Alt text](src/main/resources/static/loginScreenShot.png?raw=true "Optional Title")

#### Test Users:

    A user for each stackholder is provided in the import.sql file in this project for quick demo access. 
    Username and Password are provided in the file. 
    NOTE: All User passwords are stored using a Bcrypt Hash and so you can not use the password as stored in the file.
    Reference the files comments for the true password.

#### Signup
Alternately, you can click the signup button under the login and create a new user with a default role of Patient. 

Note: This can not be modified through the application and must be updated manually through H2 DB Access.
![Alt text](src/main/resources/static/NewUserSignupScreenshot.png?raw=true "Optional Title")

## Stackholders views and available actions
The application has four possible stack holders. 
    
    1. CEO
    2. CSRA
    3. EMPLOYEE
    4. PATIENT/CUSTOMER
The driver of the application is the PATIENT/CUSTOMER since they are in charge of

    1. Logging and updating their Test Results
    2. Logging their Vaccination Records

All other stackholders have only read abilities to the system data in order to perform their jobs
    
    1. CEO, EMPLOYEE see List of Patients and their Test Results and Vaccination Records
    2. CSRA see List of Patients and their Test Results and Vaccination Records and the Patients Contact information

Each user is greeted with a homepage when they successfully log in that corresponds with their Role.

For Customers/Patients, they are greeted with a list of their existing covid related records including test records, and vaccinations.
![Alt text](src/main/resources/static/UserHomeScreenshot.png?raw=true "Optional Title")

For Admin users (CEO, CSRA, Employee), they are greeted with a list of existing patients using the system and links to view their
covid related information.
![Alt text](src/main/resources/static/AdminNonCSRAHomeScreenshot.png?raw=true "Optional Title")

CSRAs have an additional ability to view contact information using a visible link specific to their ROLE.
![Alt text](src/main/resources/static/CSRAHomeScreenshot.png?raw=true "Optional Title")


## Security and Data Policy
This application uses an embedded H2 Database to store related information and Spring Security to manage Authentication/Authorization.

Each user of the system is given a ROLE based on the provided stakeholders of the system.
Each time a user interacts with the system via an API, like the login page, signup, or any other pages in the system;
their session is validated using a JSESSIONID to ensure their session is still active, 
their provided ROLE is validated to ensure they are Authorized to access the URL or perform the action they are
attempting to perform, and all data passed to an API via forms or parameters is validated to ensure it matches the structure 
of the system data and avoid injection attacks.

The H2 Database is embedded and is SQL dialect based. It can be accessed through the localhost:8080/h2-console and is secured using a password 
stored in the application.yaml. If this application was real and in project this password would be encrypted or stored in a key manager
but this suffices for the prototype concept.

All user passwords are stored and validated using a BCrypt hash to avoid this information being used by hackers to break into the application.

Passwords are required to be at least 8 characters long and must include a Capital letter, a LowerCase Letter, a Number, and a special character like !

The H2 database is destroyed each time the application is shut down and so all data created during your session will be lost
and only the records in the import.sql file will be regenerated upon system start up.

If a user fails to authenticate upon login then they are alerted with an "Invalid username or password" error and
redirected to the login screen.

If a user is unauthorized to perform an action or access a page (If they try to use the URL directly), 
they receive a generic '403 access denied' page and their request is rejected.

In a realworld scenario, passwords should be changed every 30 days, all PII should be encrypted, an all data older than 180 days should be
archived every 30 days.
