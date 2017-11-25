package invent.integrasisosialmedia;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by LIMS on 22/11/2017.
 */

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private String fbToken, deviceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);

        initializeLoginButton(loginButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Facebook login
//        accessTokenTracker.stopTracking();
//        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MRuntimePermission.PERMISSIONS_REQUEST_READ_PHONE_STATE:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        getUuId();
                        break;
                    }
                }
        }

    }

    public void initializeLoginButton(LoginButton loginButton) {
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
                //track the mToken
//                if (newToken != null) {
//                    fbToken = newToken.getToken();
//                    SharedPreferences prefs = getSharedPreferences(Globalvars.PREFS_ADSVOKAT, Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = prefs.edit();
//                    editor.putString(Globalvars.PREFS_TAG_FB_ACCESS_TOKEN, newToken.getToken());
//                    editor.apply();
//                    if (profile == null) {
//                        profile = new User();
//                        profile.setFbAccessToken(fbToken);
//                    } else {
//                        profile.setFbAccessToken(fbToken);
//                    }
//                } else {
//                    fbToken = null;
//                }
            }
        };

        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                accessTokenTracker = new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
//                        if (newToken != null) {
//                            fbToken = newToken.getToken();
//                            SharedPreferences prefs = getSharedPreferences(Globalvars.PREFS_ADSVOKAT, Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = prefs.edit();
//                            editor.putString(Globalvars.PREFS_TAG_FB_ACCESS_TOKEN, newToken.getToken());
//                            editor.apply();
//                            if (profile == null) {
//                                profile = new User();
//                                profile.setFbAccessToken(fbToken);
//                            } else {
//                                profile.setFbAccessToken(fbToken);
//                            }
//                        } else {
//                            fbToken = null;
//                        }
                    }
                };
                accessTokenTracker.startTracking();

                if (Profile.getCurrentProfile() == null) {
                    profileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile old, Profile newProfile) {
                            Profile fbProfile = newProfile;

//                            if (fbProfile != null) {
//                                profile = new User();
//                                profile.setFbId(fbProfile.getId());
//                                profile.setFirstName(fbProfile.getFirstName());
//                                profile.setLastName(fbProfile.getLastName());
//                                profile.setMiddleName(fbProfile.getLastName());
//                                profile.setName(fbProfile.getName());
//                                profile.setLinkUri(fbProfile.getLinkUri());
//
//                                profile.setFbAccessToken(loginResult.getAccessToken().getToken());
//                                getFbProfileData(profile, loginResult);
//                            }
                            profileTracker.stopTracking();
                        }
                    };
                    profileTracker.startTracking();
                } else {
                    Profile fbProfile = Profile.getCurrentProfile();

//                    if (fbProfile != null) {
//                        profile = new User();
//                        profile.setFbId(fbProfile.getId());
//                        profile.setFirstName(fbProfile.getFirstName());
//                        profile.setLastName(fbProfile.getLastName());
//                        profile.setMiddleName(fbProfile.getLastName());
//                        profile.setName(fbProfile.getName());
//                        profile.setLinkUri(fbProfile.getLinkUri());
//
//                        profile.setFbAccessToken(loginResult.getAccessToken().getToken());
//                        getFbProfileData(profile, loginResult);
//                    }
                }

            }

            @Override
            public void onCancel() {
                //canceled
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        };
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, callback);
    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
            main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
            startActivity(main);
        }
    }

    private void getUuId() {
        try {
            final TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId(); //line 82
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            String deviceId = deviceUuid.toString();
            Log.d("Device Id", deviceId);

            this.deviceId = deviceId;

        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
        }
    }

}
