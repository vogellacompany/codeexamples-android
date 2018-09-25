package com.vogella.android.androidnearby;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.Strategy;

public class MainActivity extends AppCompatActivity {

    private static final String[] REQUIRED_PERMISSIONS =
            new String[]{
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            };
    private static final int REQUEST_CODE_REQUIRED_PERMISSIONS = 1;

    private static final String TAG = "Logging";

    // Our handle to Nearby Connections
    private ConnectionsClient connectionsClient;

    // Callbacks for connections to other devices
    private final ConnectionLifecycleCallback connectionLifecycleCallback =
            new ConnectionLifecycleCallback() {
                @Override
                public void onConnectionInitiated(String endpointId, ConnectionInfo connectionInfo) {
                    connectionsClient.acceptConnection(endpointId, payloadCallback);
                    opponentName = connectionInfo.getEndpointName();
                }

                @Override
                public void onConnectionResult(String endpointId, ConnectionResolution result) {
                    if (result.getStatus().isSuccess()) {
                        Log.i(TAG, "onConnectionResult: connection successful");

                        connectionsClient.stopDiscovery();
                        connectionsClient.stopAdvertising();

                        opponentEndpointId = endpointId;
                        setOpponentName(opponentName);
                        setStatusText(getString(R.string.status_connected));
                        setButtonState(true);
                    } else {
                        Log.i(TAG, "onConnectionResult: connection failed");
                    }
                }

                @Override
                public void onDisconnected(String endpointId) {
                    Log.i(TAG, "onDisconnected: disconnected from the opponent");
                    resetGame();
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button connect = findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect();
            }
        });
        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });

        connectionsClient = Nearby.getConnectionsClient(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();
        if (!hasPermissions(this, REQUIRED_PERMISSIONS)) {
            requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_REQUIRED_PERMISSIONS);
        }
    }

    /** Returns true if the app was granted all the permissions. Otherwise, returns false. */
    private static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /** Handles user acceptance (or denial) of our permission request. */
    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != REQUEST_CODE_REQUIRED_PERMISSIONS) {
            return;
        }

        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Missing permissions", Toast.LENGTH_LONG).show();
                finish();
                return;
            }
        }
        recreate();
    }

    private void connect() {
        startAdvertising();
//        startDiscovery();
    }

    /** Broadcasts our presence using Nearby Connections so other players can find us. */
    private void startAdvertising() {
        // Note: Advertising may fail. To keep this demo simple, we don't handle failures.
        connectionsClient.startAdvertising(
                "mykey", getPackageName(), connectionLifecycleCallback, new AdvertisingOptions(Strategy.P2P_STAR));
    }

    private void send() {
    }
}
