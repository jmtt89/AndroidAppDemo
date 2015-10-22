package com.ogangi.dummyapp.Notification;

import android.text.Html;

import java.util.Date;

/**
 * Created by jmtt on 22/10/15.
 * Basic class of Notification
 */
public class Notification {
    public String id;
    public String title;
    public String content;
    public String html;
    public Date date;
    public String type;
    public boolean selected;

    public Notification(String id, String title, String html, Date date) {
        this.id      = id;
        this.title   = title;
        this.content = Html.fromHtml(html).toString();
        this.selected = false;
        this.html    = "<!DOCTYPE html>" +
                "<HTML lang=\"en\">" +
                "   <HEAD>" +
                "      <meta charset=\"utf-8\">"+
                "      <TITLE>" +
                "         Notification" +
                "      </TITLE>" +
                "   </HEAD>" +
                "<BODY>" +
                html +
                "</BODY>" +
                "</HTML>";
        this.date    = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return content;
    }
}
