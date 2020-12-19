package com.example.contactsv20;

import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public class ContactList {
    //global list defined
    public static List<PersonContact> contactlist = new ArrayList<>();
    public static List<PersonContact> contactlistFilterd = new ArrayList<>();
    public static int nextId = 0;

    public ContactList(List<PersonContact> contactlist){
        ContactList.contactlist = contactlist;}

    public List<PersonContact> getContactlist(){
        return contactlist;
    }
    @Override
    public String toString(){
        return "contactlist{" +
                "contactlist=" + contactlist +
                '}';
    }

    public ContactList(){
    }

    public void setContactList(List<PersonContact> contactlist){
        ContactList.contactlist = contactlist;}

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        ContactList.nextId = nextId;
    }

}
