package com.ogangi.dummyapp.Notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jmtt on 22/10/15.
 * Basic Controller for use Notification more Easy
 */
public class Controller {
    /**
     * An array of Notifications.
     */
    private static List<Notification> Notifications = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    private static Map<String, Notification> Notifications_map = new HashMap<>();

    static {
        // Add 25 sample items.

        for (int i = 0; i < 25; i++) {
            String value = String.valueOf(i+1);
            addItem(new Notification(value,"Item "+value,"<div>Content Item "+value+"</div>",new Date()));
        }
    }

    public static List<Notification> getNotifications() {
        return Notifications;
    }

    public static void addItem(Notification notify) {
        Notifications.add(notify);
        Notifications_map.put(notify.id, notify);
    }

    public static Notification getNotification(String id){
        return Notifications_map.get(id);
    }

    public static Notification getNotification(int index){
        return Notifications.get(index);
    }
}
