package com.example.magnus.locationnotes.filehandlers;

import android.content.Context;

import com.example.magnus.locationnotes.model.Kort;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by magnus on 07.05.2017.
 */

public class WriteToFile {
    private File path;
    private ArrayList<Kort> objList;
    private String filNavn;
    private Context context;

    public WriteToFile(File path, ArrayList<Kort> objList, Context context){
        this.path = path;
        this.objList = objList;
        this.context = context;

        writeToSerFile();
    }

    private void writeToSerFile(){
        try {
            //åpner støm til fil
            FileOutputStream outStream;// = new FileOutputStream(path);
            outStream = context.openFileOutput(path.getName(), context.MODE_PRIVATE);
            Gson g = new Gson();
            String json = g.toJson(objList);
            outStream.write(json.getBytes());
            outStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Filen finnes ikke");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Kan ikke åpne objektstrøm til fil");
        } catch (IndexOutOfBoundsException e){
            System.out.println("Var ikke noe objekt i listen");
        }

    }
}
