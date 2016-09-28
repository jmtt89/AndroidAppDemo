package com.ogangi.dummyapp;

import android.app.Activity;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.ogangi.messangi.android.sdk.Messangi;
import com.ogangi.messangi.android.sdk.vo.MessageVO;

/**
 * A fragment representing a single Notification detail screen.
 * This fragment is either contained in a {@link NotificationListActivity}
 * in two-pane mode (on tablets) or a {@link NotificationDetailActivity}
 * on handsets.
 */
public class NotificationDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "NOTIFICATION_ID";

    /**
     * The dummy content this fragment is presenting.
     */
    private MessageVO notify;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotificationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            notify = Messangi.getInstance().getDefaultWorkspace().getMessage(getContext(),getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(notify.getSubject());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification_detail, container, false);

        String data;
        if(notify != null) {
            data = notify.getText();
        }else{
            data = "<h3>An error has been occurred, please try again</h3>";
        }


        WebView webview = (WebView)rootView.findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadData(data, "text/html; charset=utf-8", "UTF-8");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        return rootView;
    }
}
