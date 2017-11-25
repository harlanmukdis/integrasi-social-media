package invent.integrasisosialmedia;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;


/**
 * Created by Inv-PC on 09/02/2017.
 */

public class MRuntimePermission {

    //    private Context context;
    private static AlertDialog.Builder alertBuilder;
    private static AlertDialog dialog;

    public static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;
    public static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 101;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 200;
    public static final int PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 201;
    public static final int PERMISSIONS_REQUEST_CAMERA = 300;
    public static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 400;

//    public MRuntimePermission(Context context) {
//        this.context = context;
//    }

    //External Storage Permission Region
    //Fragment
    public static boolean checkWriteExternalStoragePermission(Context context, final Fragment fragmentHost) {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(false);
                    alertBuilder.setTitle("Perizinan");
                    alertBuilder.setMessage("Dibutuhkan Penyimpanan External untuk melanjutkan!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(DialogInterface dialog, int which) {
                            fragmentHost.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    fragmentHost.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean checkWriteExternalStoragePermission(final Activity activityHost) {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(activityHost, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(activityHost, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) activityHost, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    alertBuilder = new AlertDialog.Builder(activityHost);
                    alertBuilder.setCancelable(false);
                    alertBuilder.setTitle("Perizinan");
                    alertBuilder.setMessage("Dibutuhkan Penyimpanan External untuk melanjutkan!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activityHost, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(activityHost, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    //End Region

    //Location Permission Region
    //Fragment
    public static boolean checkLocationAccessPermission(Context context, final Fragment fragmentHost) {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(false);
                    alertBuilder.setTitle("Perizinan");
                    alertBuilder.setMessage("Dibutuhkan Penyimpanan External untuk melanjutkan!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(DialogInterface dialog, int which) {
                            fragmentHost.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    fragmentHost.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean checkLocationAccessPermission(final Activity activityHost) {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {


            if (ActivityCompat.checkSelfPermission(activityHost, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(activityHost, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    alertBuilder = new AlertDialog.Builder(activityHost);
                    alertBuilder.setCancelable(false);
                    alertBuilder.setTitle("Perizinan");
                    alertBuilder.setMessage("Dibutuhkan Penyimpanan External untuk melanjutkan!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activityHost, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(activityHost, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    //End Region


    //Camera Permission Region
    //Fragment
    public static boolean checkCameraPermission(Context context, final Fragment fragmentHost) {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
                    alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(false);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Camera permission is needed!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(DialogInterface dialog, int which) {
                            fragmentHost.requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    fragmentHost.requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }

    }

    //Activity
    public static boolean checkCameraPermission(final Activity activityHost) {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {


            if (ActivityCompat.checkSelfPermission(activityHost, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(activityHost, Manifest.permission.CAMERA)) {
                    alertBuilder = new AlertDialog.Builder(activityHost);
                    alertBuilder.setCancelable(false);
                    alertBuilder.setTitle("Perizinan");
                    alertBuilder.setMessage("Dibutuhkan izin Kamera untuk melanjutkan!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activityHost, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(activityHost, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    //end region

    //PHONE State Permission Region
    //Fragment
    public static boolean checkReadPhoneStatePermission(Context context, final Fragment fragmentHost) {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_PHONE_STATE)) {

                    showAlertDialog(context, "Phone State permission is needed!", new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(DialogInterface dialog, int which) {
                            fragmentHost.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSIONS_REQUEST_READ_PHONE_STATE);
                        }
                    });
                } else {
                    fragmentHost.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSIONS_REQUEST_READ_PHONE_STATE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    //Activity
    public static boolean checkReadPhoneStatePermission(final Activity activityHost) {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {


            if (ActivityCompat.checkSelfPermission(activityHost, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(activityHost, Manifest.permission.READ_PHONE_STATE)) {
                    alertBuilder = new AlertDialog.Builder(activityHost);
                    alertBuilder.setCancelable(false);
                    alertBuilder.setTitle("Perizinan");
                    alertBuilder.setMessage("Dibutuhkan izin Kamera untuk melanjutkan!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activityHost, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSIONS_REQUEST_READ_PHONE_STATE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(activityHost, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSIONS_REQUEST_READ_PHONE_STATE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private static void showAlertDialog(Context context, String msg, DialogInterface.OnClickListener okListener) {
        if (dialog == null || !dialog.isShowing()) {

            alertBuilder = new AlertDialog.Builder(context);
            alertBuilder.setCancelable(false);
            alertBuilder.setTitle("Permission Required");
            alertBuilder.setMessage(msg);
            alertBuilder.setPositiveButton(android.R.string.yes, okListener);

            dialog = alertBuilder.create();
            dialog.show();
        }
    }
}
