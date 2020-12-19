package com.example.contactsv20;

public class BusinessContact extends BaseContact{
    //initialize extra variables
    private String businessUrl;
    private String businessHours;
    public BusinessContact(int id, String firstName, String lastName, String email, String address,
                           String phoneNumber, String businessUrl, String businessHours) {
        super(id, firstName, lastName, email, address, phoneNumber);
        this.businessUrl = businessUrl;
        this.businessHours = businessHours;
    }
    //getters and setters
    public String getBusinessUrl() {
        return businessUrl;
    }

    public void setBusinessUrl(String businessUrl) {
        this.businessUrl = businessUrl;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String busienessHours) {
        this.businessHours = busienessHours;
    }

    @Override
    public void viewContacts() {
        super.viewContacts();
        System.out.println( this.getBusinessUrl());
        System.out.println( this.getBusinessHours());
    }

    @Override
    public String toString()
    {
        return "first name: " + getFirstName() + ", last name: " + getLastName() +
                ", email: " + getEmail() + ", address: " + getAddress() + ", phone number: "
                + getPhoneNumber() + ", List of photos: " + getPhotoList() + ", Business Url: "
                + getBusinessUrl() + ", Business Hours: " + getBusinessHours();
    }
}
