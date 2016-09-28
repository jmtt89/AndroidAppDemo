package com.ogangi.dummyapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ogangi.messangi.android.sdk.vo.MessageVO;

public class MessangiReceiver extends BroadcastReceiver {
    private static final String TAG = "MessangiReceiver";

    public MessangiReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String json = intent.getStringExtra("message");
        MessageVO message = null;
        try {
            message = MessageVO.fromJson(json);
            // Here you can do anything you want with last received notification, at moment our only print it in console
            Log.d(TAG, "onReceive: "+message.toString());
        }catch(Exception _e){
            _e.printStackTrace();
            Log.e(TAG, "onReceive: "+ _e.getLocalizedMessage(), _e);
        }
    }
}
