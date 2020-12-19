package com.example.contactsv20;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;
import org.json.JSONObject;

import androidx.annotation.RequiresApi;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileAccessService {
    Context context;
    ObjectMapper om = new ObjectMapper();

    public FileAccessService(Context context) {
        this.context = context;
    }

    public void writeList(List<PersonContact> contactlist, String filename) {
        File path = context.getExternalFilesDir(null);
        File file = new File(path, filename);
        try {
            om.writerWithDefaultPrettyPrinter().writeValue(file, contactlist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ContactList readList(String fileName) {
        File path = context.getExternalFilesDir(null);
        File file = new File(path, fileName);
        ContactList contactlist = new ContactList();


        try {
            contactlist = om.readValue(file, ContactList.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactlist;
    }
}


