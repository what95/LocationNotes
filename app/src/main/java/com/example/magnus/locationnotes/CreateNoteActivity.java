package com.example.magnus.locationnotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magnus.locationnotes.model.Kort;
import com.google.android.gms.maps.model.LatLng;

public class CreateNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme2);
        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);

        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("My Own Title");

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        setContentView(R.layout.activity_create_note);
    }



    public void lagreNotat(View view){
        EditText editTekst = (EditText)findViewById(R.id.tekstFelt);
        String notat = editTekst.getText().toString();
        try{
            LatLng lokasjon = MapsActivity.hentLokasjonMarkor();
            new Kort(notat, lokasjon);
        }

        catch (NullPointerException e){
            e.printStackTrace();
        }


        //toast
        Context context = getApplicationContext();
        CharSequence text = "Notat lagret!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        startActivity(new Intent(this, MainActivity.class));

    }

    public void leggTilLokasjon(View v){
        startActivity(new Intent(this, MapsActivity.class));
    }

}
