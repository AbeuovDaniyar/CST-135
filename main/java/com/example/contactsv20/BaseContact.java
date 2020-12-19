package com.example.contactsv20;

import androidx.annotation.NonNull;

public abstract class BaseContact implements Comparable<BaseContact>{
    //initializing var
    private int id = 0;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String photoList;
    //constructor
    public BaseContact(int id, String firstName, String lastName, String email,
                       String address, String phoneNumber)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public BaseContact() {

    }

    //setters and getters
    public int getId() {
        return this.id;
    }
    public void incrementId() {
        this.id = id++;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoList() {
        return this.photoList;
    }

    public void setPhotoList(String photoList) {
        this.photoList = photoList;
    }
    //displays contact info
    public void viewContacts() {
        System.out.println("First Name: "+  this.getFirstName());
        System.out.println("Last Name: "+ this.getLastName());
        System.out.println("Email Name: "+ this.getEmail());
        System.out.println("Address Name: "+ this.getAddress());
        System.out.println("Phone Number: "+ this.getPhoneNumber());
        System.out.println("Photo List: "+ this.getPhotoList());
    }

    //equals method for the search function
    @Override
    public boolean equals(Object other) {
        //if it is compared with itself return true
        if(other == this) {
            return true;
        }
        //if it is compared to null return false
        if(other == null) {
            return false;
        }

        if (getClass() != other.getClass()) {
            return false;
        }

        //Typecast other to BaseContact to compare data members
        BaseContact obj = (BaseContact)other;
        return (this.firstName == obj.firstName && this.lastName == obj.lastName);
    }
    //compareTo method for search function
    @Override
    public int compareTo(BaseContact p) {
        return this.firstName.compareToIgnoreCase(p.getFirstName());
    }

    @Override
    public String toString() {
        return "BaseContact{" +
                "First Name=" + firstName +
                ", Last Name=" + lastName +
                ", email=" + email +
                ", address=" + address +
                ", phone number=" + phoneNumber +
                ", List of photos=" + photoList + '\'' +
                '}';
    }
}
