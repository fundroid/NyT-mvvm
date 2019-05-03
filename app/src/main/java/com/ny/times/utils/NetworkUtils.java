package com.ny.times.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtils {

    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
