package com.ogangi.dummyapp;


import android.location.Location;

import com.google.android.gms.location.Geofence;
import com.ogangi.messangi.android.sdk.Messangi;
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
        // This method will be called every time the user receives a push notification
        // via Messangi.
        // Use this method to display the content of the notification.
    }

    @Override
    public void updateFencesStatus(Geofence geofence, int i, Location location, Workspace workspace) {
        // Use this method to handle any changes in the Geofences status. This is an
        // informational method that can be used to update any maps or other views
        // showing the geofences. The location parameter contains the triger location
    }

    @Override
    public void postInit() {
        Messangi.getInstance().synchronize();
    }

    @Override
    public void onLocationChange(Location location) {
        // Use this method to handle any change in location.
    }

    @Override
    public void onGeofenceUpdate(String s, String s1, Workspace workspace) {
        // This method will be called every time the user receive a Geofence
        // create, update or delete event.
        // NOT when arrive a Notification of geofence Enter or Exit .
    }

    @Override
    public void onBeaconUpdate(String s, String s1, Workspace workspace) {
        // This method will be called every time the user receive a Beacon
        // create, update or delete event.
        // NOT when arrive a Notification of beacon Enter or Exit .
    }
}
