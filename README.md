Getting Started - Android Integration
===================

Use this Android sample app to prove how Messangi SDK works, or add our library to your existing app following these steps. We use **tags** in the Github repository over key moments in the integration.

**Required**: The latest versions of Android Studio and Git installed on your system.

-----
##Get the project

Clone the repository in the directory you choose using git:

```shell
$ git clone https://github.com/jmtt89/AndroidAppSkeleton.git
```

The Base application, without MessangiSDK integration can be found over the tag **baseApp**. Hint: chekout it using git console, not using Android Studio repository menu.

```shell
cd AndroidAppSkeleton
$ git checkout tags/baseApp
```

Open Android Studio.

If you are in the Android Studio start screen, you can select *Open an existing Android Studio project*, browse where you cloned the repository and open it. If you are already in Android Studio, select **File > Open**, and use this same path.

-------
## Configure **Google Cloud Messaging**

Go to https://developers.google.com/mobile/add, select *Pick a platform*, then *Enable services for my Android App*, enter your **own App name** and your app's package name (for this example **com.ogangi.dummyapp**) . It's important to enter the package name your app is using. Make sure the **package name** in your **build.gradle** file matches the package name you entered. Press *Choose and configure services*, select *Cloud Messaging* and press *Enable Google Cloud messaging*, select *Generate configuration files* and press *Download google-services.json*. Once you have the json file you have all you needed from that page.

### Add the configuration file to your project

Copy the **google-services.json** file you just downloaded **into** the **app/ or mobile/ directory of your Android Studio project**. 

Open the Android Studio Terminal pane and use:

```shell
$ mv path-to-download/google-services.json app/
```

### Add Google Services Gradle Plugin

As part of enabling Google APIs in your Android application you have to add the google-services plugin to your project-level build.gradle file:

```Gradle
dependencies {
    classpath 'com.google.gms:google-services:3.0.0'
    // ...
}
```
Add dependencies for basic libraries required for the services you have enabled. To this, add the plugin to your app-level build.gradle (**at the BOTTOM of your app/build.gradle file** so no dependency collisions are introduced).

```gradle
apply plugin: 'com.google.gms.google-services' 
```
Sync your project with the changes on gradle.
-------
## Create Messangi Account

Messangi is a platform created to handle omnichannel communications, that allows companies to enhance their engagement with their customers. Messangi provides channels like sms, email and push notifications and also adds above them some value-added products, such as geofences, scratchcards, mobile wallets and more. If you want to take advantage of all the power of Messangi platform inside your Android mobile apps, you just need to download the Messangi SDK, add it to your app, set up your account and start coding.

### Messangi Account 

Go to https://messangi.com/messangi_mmc/ and click on **Sign up** to create a new account, **check your email** to get your **Messangi Credentials**.

### Request a GCM - Messangi Linking

Send an email to *support@ogangi.com* with your Messangi credentials you've received in your registration and attach to it the **google-services.json** file you've received from Google to request the registration of your app in Messangi platform. It's the way to authorize and enable your access to the platform *as developer*.

--------
## Configure Messangi SDK
To start in this section you can **checkout the tag GCMReady** with git console (OJO REVISAR)

### Add Messangi Module 

- [Download Messangi Library](https://github.com/jmtt89/AndroidAppSkeleton/tree/master/messangisdk)
- Go to File > New > New Module
- Select “Import JAR/AAR Package” then “next”
- Click the button “…” next to the “file name” field. 
- Select the file messangiSDK.aar from the directory you downloaded it and click “Finish”
- Wait Gradle synchronize.

### Add Dependencies

- Right Click on your application and select “Open Module Settings”
- Select app and go to the tab “Dependencies”
- If MessangiSDK is not a dependency, add it by clicking on “+”, select “module” and “MessangiSDK”.

Currently you have to manually add dependencies for MessangiSDK, the Gradle will not automatically add them. 

- Go to “Gradle Scripts” > “build.gradle(Module: app)”
- Add the lines below in the section dependencies before the line “compile project(‘:messangisdk’)”

```Gradle
compile 'com.android.support:support-annotations:24.2.1'
compile 'com.google.android.gms:play-services-base:9.4.0'
compile 'com.google.android.gms:play-services-location:9.4.0'
compile 'com.google.android.gms:play-services-gcm:9.4.0'
compile 'org.altbeacon:android-beacon-library:2.9.1'
```

### Add Broadcast Receiver

Android Studio provides an easy way to generate a Broadcast Receiver, only **right click over the package app**, select **New > Other > Broadcast Receiver** add the name you want (for the demo application you can use **MessangiReceiver**) and Android Studio will create automatically a class and add info to the Manifest file too. Change the values if they look diferent in AndroidManifest.xml file:

```Java
<receiver
    android:name=".MessangiReceiver"
    android:enabled="true"
    android:exported="false">
</receiver>
```

To work with Messangi SDK you need to **add an intent-filter** with name **com.ogangi.messangi.android.sdk.PUSH_NOTIFICATION**, the receiver in manifest looks like this:

```Java
<receiver
    android:name=".MessangiReceiver"
    android:enabled="true"
    android:exported="false">
    <intent-filter>
        <action android:name="com.ogangi.messangi.android.sdk.PUSH_NOTIFICATION" />
   </intent-filter>
</receiver>
```

The intent  delivers to you a receiver, a json representation of the message and, if you wish, can create an object MessangeVO from that json:

```Java
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
```

### Add Messangi Listener

Messangi Module uses the interface **MessangiListener** to notify when some event occurs, your only need to implement (in an external class or inside your main activity) this interface if you want to receive those notifications.

```java
import android.location.Location;
import com.google.android.gms.location.Geofence;
import com.ogangi.messangi.android.sdk.MessangiListener;
import com.ogangi.messangi.android.sdk.Workspace;
import com.ogangi.messangi.android.sdk.vo.MessageVO;
...
public class Listener implements MessangiListener{
...
}
``` 
The MessangiListener interface to provide a set of methods that need to be implemented, this method are for events updates on the Messangi Service

```Java
@Override
public void pushReceived(MessageVO messageVO, Workspace workspace) {
    // This method will be called every time the user receives a push notification via Messangi.
}

@Override
public void updateFencesStatus(Geofence geofence, int i, Location location, Workspace workspace) {
    // Use this method to handle any changes in the Geofences status. This is an
    // informational method that can be used to update any maps or other views
    // showing the geofences. The location parameter contains the triger location
}

@Override
public void postInit() {
    //This method is called after the configuration process will finished. 
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
```

### Configure Messangi Module

Messangi provides multiple ways to **include credentials**, but the easiest and straight forward way is including them into **onCreate** method of the **main activity**; if you're using an **Application** module as starting point, we support it too.

```Java
//Place here the Credentials sent to you in the registration Email
Messangi.getInstance().setAppName("App name on Email");
Messangi.getInstance().setClientId("Client Id on Email");
Messangi.getInstance().setApiClientPrivateKey("Secret Key on Email");

// GCM Credentials
Messangi.getInstance().setGcmApiKey(getString(R.string.gcm_api_key));
Messangi.getInstance().setGcmProjectId(getString(R.string.gcm_defaultSenderId));

Messangi.getInstance().init(this);
Messangi.getInstance().addMessangiListener(Listener.getIntance()); //you also can add an object instance of Listener
Messangi.getInstance().registerDialog(this, this);
```

When the **Registration process is completed**, the SDK calls a **postInit** method **on MessangiListener implementation**. 

After Messangi is already configured, it's strongly recommended to add a life cicle indicator, to notify when the app is in Background or Foreground. Add it in your main activity and in all activities where Messangi is invoked.

```Java
@Override
protected void onResume() {
    super.onResume();
    Messangi.getInstance().bindService();
}

@Override
protected void onPause() {
    Messangi.getInstance().unBindService();
    super.onPause();
}
```

The synchronization process is very important if you use Geofence, Beacons or Location-aware capabilities. This method synchronizes all information in Messangi System; the best moment to do this are on postInit method, is strongly recomended use a Background Thread to all Network calls.

```Java
@Override
public void postInit() {
    Messangi.getInstance().synchronize();
}
```

### Android Permissions

In Android 6 and above, the permission process for some functionalities needs to be accepted by the user in Runtime, if the user don't accept that, multiples internal functionalities won't work as expected. Use this in your main activity:

```Java
@Override
public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    try {
        Messangi.getInstance().onRequestPermissionsResult(requestCode,permissions,grantResults);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

## Using Messangi SDK

To start in this section you can **checkout the tag MessangiSDKReady**

This section will do the most basic interaction with the library, if you want to make things more complicated please see the section of [advanced topics]().

MessangiSDK support multiple workspaces enviroment, a workspace is equivalent to a Messangi Account, so if you want you can create multiple accounts and receive notification from all them in the same app, so to referencing all information stored in each workspace, we provide **Workspace** Class. 

This class is the entry point for every information stored in your workspace, accessed thorugh Messangi. MessangiSDK provides a easy way to get the default registered workspace. The default workspace is already set in the main register in configuration section above. When you want to get your default workspace use:

```Java
Workspace default = Messangi.getInstance().getDefaultWorkspace();
```

###  List stored messages

The main functionality of this example application is only to show stored messages, to start with that functionality **remove the package Notification**, change every reference to it for the class MessageVO provided by the SDK, and replace all Controller.getNotifications() with Messangi.getInstance().getDefaultWorkspace().getMessages().

To list stored messages you only need to call **getMessages()** from workspace.

```Java
default.getMessages();
```

To get a single message, you need pass the context and the messageID, this is the functionality that we use to show a detail view for the message.

```Java
default.getMessages(this,"Message ID");
```

### Synchronize unread messages

The most classic interaction on Android is pull to refresh gesture, so when pulling a list we want Messangi to update all messages with all new messages in the server. 

For easy integration of swipe to refresh, Android provides a **SwipeRefreshLayout**, only wrap up your listview layout or recycle view in layout file

```xml
<android.support.v4.widget.SwipeRefreshLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/swipeContainer"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    .... //your layout here
</android.support.v4.widget.SwipeRefreshLayout>
```
And place this in your activity where the list is on (for this example, NotificationListFragment in onViewCreated() method)

```Java
    swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
    swipeContainer.setOnRefreshListener(new OnRefreshListener() {
        @Override
            public void onRefresh() {
                Messangi.getInstance().getUnreadMessages(getContext());
                // Remember to CLEAR OUT old items before appending in the new ones
                mAdapter.clear();
                // ...the data has come back, add new items to your adapter...
                mAdapter.addAll(Messangi.getInstance().getDefaultWorkspace().getMessages());
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);    
            } 

        });
```


## Sending Push Messages
//>> Agregar aqui un caso de uso o ejemplo de funcionalidad basico utiliando el MMC, si creas un video tanto mejor.
