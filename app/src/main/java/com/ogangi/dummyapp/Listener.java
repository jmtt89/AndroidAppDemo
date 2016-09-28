package com.ogangi.dummyapp;


import android.location.Location;

import com.google.android.gms.location.Geofence;
import com.ogangi.messangi.android.sdk.MessangiListener;
import com.ogangi.messangi.android.sdk.Workspace;
import com.ogangi.messangi.android.sdk.vo.MessageVO;

/**
 * Created by jmtt on 9/27/16.
 */
public class Listener implements MessangiListener {
    private static Listener intance;

    public static Listener getIntance() {
        if(intance == null){
            intance = new Listener();
        }
        return intance;
    }

    @Override
    public void pushReceived(MessageVO messageVO, Workspace workspace) {

    }

    @Override
    public void updateFencesStatus(Geofence geofence, int i, Location location, Workspace workspace) {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void onLocationChange(Location location) {

    }

    @Override
    public void onGeofenceUpdate(String s, String s1, Workspace workspace) {

    }

    @Override
    public void onBeaconUpdate(String s, String s1, Workspace workspace) {

    }
}
