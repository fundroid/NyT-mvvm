package com.ny.times.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.test.InstrumentationRegistry;

import com.ny.times.utils.NetworkUtils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NetworkUtilsTest {

    private NetworkUtils myUtil;

    @Before
    public void setUp() throws Exception {
        myUtil = new NetworkUtils();
    }

    @Test
    public void isOnline() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        ConnectivityManager cm = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        assertNotNull(cm);
    }

}
