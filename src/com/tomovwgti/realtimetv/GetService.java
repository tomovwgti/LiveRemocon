
package com.tomovwgti.realtimetv;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class GetService extends Service {
    static final String TAG = GetService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "onStart");
        GetLive live = new GetLive();
        live.GetLiveChannel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
