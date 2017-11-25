package invent.integrasisosialmedia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button logout;
    ImageView bmImage;
    TextView tvModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayout();
        assignListener();

        displayProfile();
        checkModelDevice();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                showDialogShare();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeLayout() {
        logout = (Button) findViewById(R.id.logout);
        bmImage = (ImageView) findViewById(R.id.profileImage);
        tvModel = (TextView) findViewById(R.id.tv_model);
    }

    private void assignListener() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });
    }

    private void displayProfile() {
        Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
        String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();

        TextView nameView = (TextView)findViewById(R.id.nameAndSurname);
        nameView.setText("" + name + " " + surname);

        new DownloadImage(bmImage).execute(imageUrl);
    }

    private void showDialogShare() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String link = "https://play.google.com/store/search?q=mobile%20legend&c=apps";
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, link);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void checkModelDevice() {
        String model = Build.MANUFACTURER
                + " \n " + Build.MODEL
                + " \n " + Build.VERSION.RELEASE
                + " \n " + Build.VERSION_CODES.class.getFields()[Build.VERSION.SDK_INT].getName()
                + " \n " + Build.DEVICE
                + " \n " + Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        @SuppressLint("WifiManagerLeak") WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        tvModel.setText(model +"\n"+ ip);
    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
