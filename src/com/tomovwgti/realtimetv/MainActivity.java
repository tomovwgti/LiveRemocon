
package com.tomovwgti.realtimetv;

import java.util.Map.Entry;
import java.util.TreeMap;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tomovwgti.realtimetv.json.Ranking;

public class MainActivity extends Activity {
    static final String TAG = MainActivity.class.getSimpleName();

    private PendingIntent mService;
    private static Context mContext;
    private AlarmManager mAlerm;

    private static final Handler mHandler = new Handler() {
        TreeMap<Integer, Ranking> map = new TreeMap<Integer, Ranking>();

        @Override
        public void dispatchMessage(Message msg) {
            Ranking rank = (Ranking) msg.obj;
            rank.setChannel(msg.what);
            Log.i(TAG, msg.what + "CH " + "Ikioi " + rank.getIkioi());
            map.put(Integer.parseInt(rank.getIkioi()), rank);
            // 取得完了
            Log.i(TAG, "Map Size " + map.size());
            if (map.size() == 6) {
                Entry<Integer, Ranking> selectMap = map.lastEntry();
                Ranking channel = selectMap.getValue();
                int ikioi = selectMap.getKey();
                ShowChannel(channel, ikioi);
                map.clear();
            }
        }
    };

    public static Handler getHandler() {
        return mHandler;
    }

    public static void ShowChannel(Ranking channel, int ikioi) {
        Toast.makeText(mContext,
                channel.getChannel() + " ch, 勢い: " + ikioi + " 番組: " + channel.getTitle(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mAlerm = (AlarmManager) getSystemService(ALARM_SERVICE);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetService.class);
                mService = PendingIntent.getService(MainActivity.this, 0, intent, 0);

                long first = SystemClock.elapsedRealtime();
                long interval = 1 * 1000 * 60;
                mAlerm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, first, interval, mService);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAlerm.cancel(mService);
    }
}
