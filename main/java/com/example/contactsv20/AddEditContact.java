package com.example.contactsv20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddEditContact extends AppCompatActivity {
    Button btn_ok, btn_cancel, btn_delete, btn_text, btn_call, btn_email;
    EditText et_firstname, et_lastName, et_email, et_address, et_phoneNumber, et_birthdate, et_message;
    TextView tv_contactsId;
    List<PersonContact> contactlist;
    AddressBook contact = new AddressBook();
    ContactList contactList = new ContactList();
    int id;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        //ContactList contactList = new ContactList();
        contactlist = contactList.getContactlist();

        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);
        et_firstname = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        et_birthdate = findViewById(R.id.et_birthdate);
        et_message = findViewById(R.id.et_message);
        tv_contactsId = findViewById(R.id.tv_contactsId);
        btn_delete = findViewById(R.id.btn_delete);
        btn_call = findViewById(R.id.btn_call);
        btn_text = findViewById(R.id.btn_text);
        btn_email = findViewById(R.id.btn_email);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        PersonContact person = null;

        if(id >= 0)
        {
            //edit contact
            for(PersonContact c: contactlist){
                if(c.getId() == id){
                    person = c;
                }
            }
            et_firstname.setText(person.getFirstName());
            et_lastName.setText(person.getLastName());
            et_email.setText(person.getEmail());
            et_address.setText(person.getAddress());
            et_phoneNumber.setText(person.getPhoneNumber());
            et_birthdate.setText(person.getDateOfBirth());
            tv_contactsId.setText(Integer.toString(id));
        }


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AddEditContact.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact.removeContact(id);
                Toast.makeText(v.getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( AddEditContact.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(et_phoneNumber.getText().toString());
            }
        });

        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(et_phoneNumber.getText().toString(), et_message.getText().toString());
            }
        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = new String[1];
                addresses[0] = et_email.getText().toString();
                composeEmail(addresses, et_message.getText().toString());
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id >= 0)
                {
                    //update
                    try {
                        contact.editContact(id, et_firstname.getText().toString(), et_lastName.getText().toString(), et_address.getText().toString(), et_email.getText().toString(),
                                et_phoneNumber.getText().toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    //add a contact
                    try {
                        FileAccessService load = new FileAccessService(v.getContext());
                        int contactID = ContactList.getNextId();
                        contact.addContact(contactID, et_firstname.getText().toString(), et_lastName.getText().toString(), et_address.getText().toString(), et_email.getText().toString(),
                                et_phoneNumber.getText().toString(), "", "", et_birthdate.getText().toString());
                        load.writeList(contactlist, "contacts.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //Go back to main activity
                Intent intent = new Intent( AddEditContact.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeMmsMessage(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}