package com.example.magnus.locationnotes.model;

/**
 * Created by magnus on 01.03.2017.
 */

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class Kort implements Serializable{
    private static ArrayList<Kort> KortListen = new ArrayList<Kort>();

    private String notatet;
    private LatLng lokasjonen;

    public Kort(String teksten, LatLng lokasjonen){
        this.teksten = teksten;
        this.lokasjonen = lokasjonen;

        KortListen.add(this);
    }

    public static ArrayList<Kort> getData(){
        return KortListen;
    }
    private String teksten;

    public String getTeksten() {
        return teksten;
    }
    public LatLng getLokasjonen(){
        return lokasjonen;
    }
    public void setLokasjonen(LatLng lokasjon){
        this.lokasjonen = lokasjon;
    }

    public void setTeksten(String teksten) {
        this.teksten = teksten;
    }


}
