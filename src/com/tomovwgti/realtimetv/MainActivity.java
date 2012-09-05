
package com.tomovwgti.realtimetv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tomovwgti.realtimetv.json.Ranking;

public class MainActivity extends Activity {
    static final String TAG = MainActivity.class.getSimpleName();

    private PendingIntent mService;
    private static Context mContext;
    private AlarmManager mAlerm;
    private static ArrayAdapter<String> mAdapter;
    private static ListView mListView;

    private static TreeMap<Integer, Ranking> mMap;
    private static List<Integer> mChannelList;

    private static final Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            Ranking rank = (Ranking) msg.obj;
            rank.setChannel(msg.what);
            Log.i(TAG, msg.what + "CH " + "Ikioi " + rank.getIkioi());
            mMap.put(Integer.parseInt(rank.getIkioi()), rank);
            // 取得完了
            Log.i(TAG, "Map Size " + mMap.size());
            synchronized (mContext) {
                if (mMap.size() == 7) {
                    mChannelList.clear();
                    mAdapter.clear();
                    Log.i(TAG, "List Clear " + mChannelList.size());
                    // リストに登録
                    for (Iterator i = mMap.entrySet().iterator(); i.hasNext();) {
                        Map.Entry entry = (Map.Entry) i.next();
                        Integer key = (Integer) entry.getKey();
                        Ranking value = (Ranking) entry.getValue();
                        System.out.println(key + " => " + value.getTitle());
                        mAdapter.add(value.getTitle());
                        mChannelList.add(value.getChannel());
                    }
                    // 勢いのあるチャンネルに変更する
                    sendRemocon(mChannelList.get(0));
                    mMap.clear();
                    Log.i(TAG, "Map Clear " + mMap.size());
                }
            }
        }
    };

    // 降順に並べるためのコンパレータ
    public class ExmComparator implements java.util.Comparator {
        public int compare(Object object1, Object object2) {
            return ((Comparable) object1).compareTo(object2) * -1;
        }
    }

    public static Handler getHandler() {
        return mHandler;
    }

    private static void sendRemocon(int channel) {
        Remocon remo = new Remocon();
        remo.channel = (byte) channel;
        remo.sendData();
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

        mMap = new TreeMap<Integer, Ranking>(new ExmComparator());
        mChannelList = new ArrayList<Integer>();

        mContext = this;
        mAlerm = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1);
        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(mAdapter);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetService.class);
                mService = PendingIntent.getService(MainActivity.this, 0, intent, 0);

                long first = SystemClock.elapsedRealtime();
                long interval = 5 * 1000 * 60;
                mAlerm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, first, interval, mService);
            }
        });

        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlerm.cancel(mService);
            }
        });

        // リストビューのアイテムがクリックされた
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックされたアイテムを取得します
                Toast.makeText(MainActivity.this, mChannelList.get(position) + "CH",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
