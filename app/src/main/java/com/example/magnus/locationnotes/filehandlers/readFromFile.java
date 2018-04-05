package com.example.magnus.locationnotes.filehandlers;

import android.util.Log;

import com.example.magnus.locationnotes.model.Kort;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by magnus on 07.05.2017.
 */

public class readFromFile {
    private String path;
    private static final Type REVIEW_TYPE = new TypeToken<List<Kort>>() {
    }.getType();
    private String tag = "LocationNotes";

    public readFromFile(String path) {
        this.path = path;
        readFil();
    }

    private void readFil() {
        try{
            System.out.println("Prøver å laste inn fil");
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(path));
            Type collectionType = new TypeToken<Collection<Kort>>(){}.getType();
            Collection<Kort> enums = gson.fromJson(reader, collectionType);
            System.out.println(enums);
            reader.close();
            for(Iterator<Kort> iterator = enums.iterator(); iterator.hasNext();){
                Kort type = iterator.next();
                Kort.getData().add(type);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
