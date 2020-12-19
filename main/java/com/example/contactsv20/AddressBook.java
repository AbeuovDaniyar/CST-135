package com.example.contactsv20;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AddressBook extends Application{
    //create a list array
    List<PersonContact> contactlist = new ArrayList<>();
    ContactList contactList = new ContactList();
    public static int nextId = 0;
    //define a counter
    private int top = 0;
    public int x = 0;
    //contact id

    public void addContact(int contactId, String firstName, String lastName, String address, String email, String phone, String businessurl, String businesshours, String dateOfBirth) throws IOException {
        //FileAccessService load = new FileAccessService(context);
        if (businessurl == "" && businesshours == "") {
            PersonContact entry;
            entry = new PersonContact(nextId, firstName, lastName, email, address,
                    phone, dateOfBirth);
            nextId++;
            //add entry to list array
            contactList.getContactlist().add(entry);
        }
    }

    //removes element of the array
    public void removeContact(int index) {
        contactList.getContactlist().remove(index);
    }
    //sort contact list
    public void sortContactList() {
        Collections.sort(contactList.getContactlist());
        //contactList.setContactList(contactlist);
    }

    //displays Contact info
    //for loop to display all contact entries in the array
    public void view() throws FileNotFoundException {
        for (int index = 0; index < contactlist.size(); index++) {
            contactlist.get(index).viewContacts();
        }
    }

    //edit contact by id
    public void editContact(int contactId, String firstName, String lastName, String address, String email, String phone) throws IOException {
        // TODO Auto-generated method stub
            int id = contactId;
        contactList.getContactlist().get(id).setFirstName(firstName);
        contactList.getContactlist().get(id).setLastName(lastName);
        contactList.getContactlist().get(id).setEmail(email);
        contactList.getContactlist().get(id).setEmail(address);
        contactList.getContactlist().get(id).setPhoneNumber(phone);
    }


}
