package com.dimowner.audiorecorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.dimowner.audiorecorder.app.main.MainActivity;

public class AppLauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
