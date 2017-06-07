package com.example.nobelz.employee;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.*;
import android.os.Handler;
import android.os.Message;
//import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Location extends AppCompatActivity implements LocationListener,View.OnClickListener {

    private TextView latitudePosition;
    private TextView longitudePosition;
    private TextView currentCity;
    private LocationManager locationManager;
    private android.location.Location location;
    private final int REQUEST_LOCATION = 200;
    private static final String TAG = "MainActivity";

    private Button buttonInsert;

    String locsend;

    private static final String INSERT_URI = "http://10.0.2.2/register.php";

    public static final String KEY_SEND="names";
    public static final String KEY_LATITUDE = "name";
    public static final String KEY_LONGITUDE = "email";
    public static final String KEY_ADDRESS = "address";


    public final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Intent itt=getIntent();
        locsend=itt.getStringExtra("send_garni");

        latitudePosition = (TextView) findViewById(R.id.latitude);
        longitudePosition = (TextView) findViewById(R.id.longitude);
        currentCity = (TextView) findViewById(R.id.city);
        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Location.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, this);
            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (location != null) {
                latitudePosition.setText(String.valueOf(location.getLatitude()));
                longitudePosition.setText(String.valueOf(location.getLongitude()));
                getAddressFromLocation(location, getApplicationContext(), new GeoCoderHandler());


            }
        } else {
            showGPSDisabledAlertToUser();
        }



        buttonInsert = (Button) findViewById(R.id.btnInsert);
        buttonInsert.setOnClickListener(this);


    }
    @Override
    public void onLocationChanged(android.location.Location location) {
        latitudePosition.setText(String.valueOf(location.getLatitude()));
        longitudePosition.setText(String.valueOf(location.getLongitude()));
        getAddressFromLocation(location, getApplicationContext(), new GeoCoderHandler());
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            showGPSDisabledAlertToUser();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }
    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    public static void getAddressFromLocation(final android.location.Location location, final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List<Address> list = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 1);
                    if (list != null && list.size() > 0) {
                        Address address = list.get(0);
                        // sending back first address line and locality
                        result = address.getAddressLine(0) + ", " + address.getLocality() + ", " +  address.getCountryName() ;
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Impossible to connect to Geocoder", e);
                } finally {
                    Message msg = Message.obtain();
                    msg.setTarget(handler);
                    if (result != null) {
                        msg.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        msg.setData(bundle);
                    } else
                        msg.what = 0;
                    msg.sendToTarget();
                }
            }
        };
        thread.start();
    }

    final private Runnable call = new Runnable() {
        public void run() {
            //This is where my sync code will be, but for testing purposes I only have a Log statement
            Log.v("test","this will run every minute");
            final String name = latitudePosition.getText().toString().trim();
            final String email = longitudePosition.getText().toString().trim();
            final String names = locsend.trim();
            final String address = currentCity.getText().toString().trim();
            Log.v("go","run");

           // currentCity.setText("fff");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, INSERT_URI,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Location.this, response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Location.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_LATITUDE, name);
                    params.put(KEY_LONGITUDE, email);
                    params.put(KEY_SEND, names);
                    params.put(KEY_ADDRESS, address);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(Location.this);
            requestQueue.add(stringRequest);

            handler.postDelayed(call,30*1000);
        }
    };
    @Override
    public void onClick(View v) {
        Sync sync = new Sync(call,30*1000);
    }




    private class GeoCoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String result;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    result = bundle.getString("address");
                    break;
                default:
                    result = null;
            }
              currentCity.setText(result);
        }
    }


    public class Sync {
        Runnable task;

        public Sync(Runnable task, long time) {
            this.task = task;
            handler.removeCallbacks(task);
            handler.postDelayed(task, time);
        }
    }
}
