package invent.integrasisosialmedia;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.FacebookSdk;

/**
 * Created by LIMS on 22/11/2017.
 */

public class IntegrationSocialMediaApp extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
