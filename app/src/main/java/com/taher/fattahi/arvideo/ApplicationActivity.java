package com.taher.fattahi.arvideo;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.taher.fattahi.arvideo.VideoPlayback.app.VideoPlayback.VideoPlayback;

public class ApplicationActivity extends Activity {

    private static final int PERMISSION_REQUEST_CODE = 100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkPermission()) {
            requestPermission();
        }else{
            Intent intent = new Intent(this, VideoPlayback.class);
            startActivity(intent);
            finish();
        }

    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            Log.d("testtt", "checkPermission: ");
            return false;
        }
        return true;
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted Camera", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, VideoPlayback.class);
                    startActivity(intent);
                    finish();
                } else {
                    requestPermission();
                }
                break;
        }
    }
}
