package com.example.contactsv20;

public class PersonContact extends BaseContact{
    //initialize var
    private String dateOfBirth;
    public PersonContact(int id, String firstName, String lastName, String email, String address, String phoneNumber,
                         String dateOfBirth) {
        super(id, firstName, lastName, email, address, phoneNumber);
        this.dateOfBirth = dateOfBirth;
    }
    //get and set functions
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public void viewContacts() {
        super.viewContacts();
        System.out.println("BirthDay: "+ this.dateOfBirth);
    }

    @Override
    public String toString() {
        return "BaseContact{" +
                "First Name=" + getFirstName() +
                ", Last Name=" + getLastName() +
                ", email=" + getEmail() +
                ", address=" + getAddress() +
                ", phone number=" + getPhoneNumber() +
                ", List of photos=" + getPhotoList() +
                ", Date Of birth=" + getDateOfBirth() + '\'' +
                '}';
    }
}
