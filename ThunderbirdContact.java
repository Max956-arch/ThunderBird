/******************************************************************************
 * Copyright (C) 2019 Eric Pogue.
 * 
 * This file is licensed under the BSD-3-Clause
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 *****************************************************************************/

class ThunderbirdContact extends HttpRequest implements Runnable {
    private String firstName;
    public String getFirstName() { return firstName; }

    private String lastName;
    public String getLastName() {return lastName; }
    
    private int seatLocation; 
    public int getSeat() { return seatLocation; }

    // MD: All of the additional ThunderbirdContact Fields
    private String preferredName;
    public String getPreferredName() { return preferredName; }

    private String emailAddress;
    public String getEmailAddress() { return emailAddress; }

    private String superhero;
    public String getSuperhero() { return superhero; }

    private String author;
    public String getAuthor() { return author; }

    //MD: adds additional fields into the constructor
    ThunderbirdContact(String urlIn) {
        super(urlIn);

        firstName = "";
        lastName = "";
        seatLocation = 0;

        // Todo: Add additional fields. 
        preferredName = "";
        emailAddress = "";
        superhero = "";
    }

    public Boolean Load() {
        Boolean returnValue = false;
        System.out.println("Loading: " + requestURL);
        if (super.readURL()) {
            Parse(); 
            returnValue = true;
        }

        return returnValue;
    }

    public void Parse() {
        for (String s : urlContent) {
            String[] subString = s.split("\"");

            // Todo: Parse for additional fields. 
            if (subString.length > 3) {
                if (subString[1].equals("firstName")) {
                    firstName = subString[3];
                }
                if (subString[1].equals("lastName")) {
                    lastName = subString[3];
                }
                // MD: Extracts additional information for each contact JSON file
                if (subString[1].equals("preferredName")) {
                    lastName = subString[3];
                }
                if (subString[1].equals("emailAddress")) {
                    lastName = subString[3];
                }
                if (subString[1].equals("superhero")) {
                    lastName = subString[3];
                }
                if (subString[1].equals("seatLocation")) {
                    try {
                        seatLocation = 0; 
                        if (!subString[3].equals("")) {
                            seatLocation = Integer.parseInt(subString[3]);
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("Exception: " + e);
                    }
                }
            }
        }    
    }

    public void Validate() {
        if (urlContent.size() < 1) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: No content loaded\n");
            return; // Returning from the middle of a method is controversial.
        }

        // Todo: Add author's name and email address to failed messages. 
        // MD: Added additional validational enhancements
        if (firstName.length() == 0) {
            System.out.println("Validating: " + requestURL);
            System.out.println("Author: " + author);
            System.out.println("Email: " + emailAddress);
            System.out.println("    **Failed**: First Name (\"firstName\") required but not found\n\n");
            System.out.println(this);
        } else if (lastName.length() == 0) {   
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: Last Name (\"lastName\") required but not found\n\n");
            System.out.println(this);          
        } else {
            System.out.println("Validating: " + requestURL + "... Passed!");
        }
    }

    public String toString() {
        // Todo: Add additional fields to returnString. 
        // MD: Added aditional fields and then prints all parsed contact information
        String returnString = "firstName: " + firstName + "\n";
        returnString = returnString + "lastName: " + lastName + "\n";
        returnString = returnString + "seatNumber: " + seatLocation + "\n";
        returnString = returnString + "preferredName: " + preferredName + "\n";
        returnString = returnString + "emailAddress: " + emailAddress + "\n";
        returnString = returnString + "superhero: " + superhero + "\n";
        returnString = returnString + super.toString();

        return returnString;
    }

    public void run() {
        Load();
    }
}