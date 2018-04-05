package com.example.magnus.locationnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.magnus.locationnotes.adapter.NotatRecycleAdapter;
import com.example.magnus.locationnotes.filehandlers.WriteToFile;
import com.example.magnus.locationnotes.filehandlers.readFromFile;
import com.example.magnus.locationnotes.model.Kort;
import com.example.magnus.locationnotes.services.MyIntentService;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public static boolean rompe = true;

    public ArrayList<String> getMyStrings() {
        return myStrings;
    }

    private ArrayList<String> myStrings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpRecycle();
        if (rompe) {
            new readFromFile(getFilesDir().getPath() + "/notes1.json");
            startServicen();
            rompe = false;
        }



        lagreStuff();
    }

    public void startServicen(){
        Intent serviceEn = new Intent(this, MyIntentService.class);
        String tag = "LocationStartService";
        Log.e(tag, "Starter servicen");
        startService(serviceEn);
    }

    //Setter opp recycleview med manager
    public void setUpRecycle(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleViewet);
        NotatRecycleAdapter adapter = new NotatRecycleAdapter(this, Kort.getData());
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManagerVertical);
    }

    //Åpner nyNote activity
    public void nyNote(View view){
        startActivity(new Intent(this, CreateNoteActivity.class));
        lagreStuff();
    }

   public void lagreStuff(){
        System.out.println("Prøver å lagre!");
        File filen = new File(getFilesDir() + "/notes1.json");


        ArrayList<Kort> tempList = new ArrayList<Kort>();
        for(Kort k : Kort.getData()){
            tempList.add(k);
        }
        new WriteToFile(filen, tempList, this);

    }

}
