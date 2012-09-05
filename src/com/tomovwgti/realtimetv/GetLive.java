
package com.tomovwgti.realtimetv;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tomovwgti.realtimetv.json.Ranking;

public class GetLive {
    static final String TAG = GetLive.class.getSimpleName();

    private Handler mHandler;

    public GetLive() {
        mHandler = MainActivity.getHandler();
    }

    public void GetLiveChannel() {
        callNhk();
        callEtv();
        callNtv();
        callAsahi();
        callTbs();
        callTx();
        callFuji();
    }

    private void callNhk() {
        final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("board", "livenhk");

        client.get(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                int end = result.length() - 2;
                // Log.i(TAG, result.toString().substring(9, end));
                ByteArrayInputStream input = new ByteArrayInputStream(result.toString()
                        .substring(9, end).getBytes());
                Ranking[] rank = null;

                try {
                    rank = JSON.decode(input, Ranking[].class);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.i(TAG, "TITLE " + rank[0].getTitle());
                Log.i(TAG, "ikioi " + rank[0].getIkioi());

                Message msg = mHandler.obtainMessage(CH_NHK, rank[0]);
                mHandler.sendMessage(msg);
            }
        });
    }

    private void callEtv() {
        final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("board", "liveetv");

        client.get(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                int end = result.length() - 2;
                // Log.i(TAG, result.toString().substring(9, end));
                ByteArrayInputStream input = new ByteArrayInputStream(result.toString()
                        .substring(9, end).getBytes());
                Ranking[] rank = null;

                try {
                    rank = JSON.decode(input, Ranking[].class);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.i(TAG, "TITLE " + rank[0].getTitle());
                Log.i(TAG, "ikioi " + rank[0].getIkioi());

                Message msg = mHandler.obtainMessage(CH_ETV, rank[0]);
                mHandler.sendMessage(msg);
            }
        });
    }

    private void callNtv() {
        final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("board", "liventv");

        client.get(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                int end = result.length() - 2;
                // Log.i(TAG, result.toString().substring(9, end));
                ByteArrayInputStream input = new ByteArrayInputStream(result.toString()
                        .substring(9, end).getBytes());
                Ranking[] rank = null;

                try {
                    rank = JSON.decode(input, Ranking[].class);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.i(TAG, "TITLE " + rank[0].getTitle());
                Log.i(TAG, "ikioi " + rank[0].getIkioi());

                Message msg = mHandler.obtainMessage(CH_NTV, rank[0]);
                mHandler.sendMessage(msg);
            }
        });
    }

    private void callTbs() {
        final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("board", "livetbs");

        client.get(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                int end = result.length() - 2;
                // Log.i(TAG, result.toString().substring(9, end));
                ByteArrayInputStream input = new ByteArrayInputStream(result.toString()
                        .substring(9, end).getBytes());
                Ranking[] rank = null;

                try {
                    rank = JSON.decode(input, Ranking[].class);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.i(TAG, "TITLE " + rank[0].getTitle());
                Log.i(TAG, "ikioi " + rank[0].getIkioi());

                Message msg = mHandler.obtainMessage(CH_TBS, rank[0]);
                mHandler.sendMessage(msg);
            }
        });
    }

    private void callFuji() {
        final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("board", "livecx");

        client.get(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                int end = result.length() - 2;
                // Log.i(TAG, result.toString().substring(9, end));
                ByteArrayInputStream input = new ByteArrayInputStream(result.toString()
                        .substring(9, end).getBytes());
                Ranking[] rank = null;

                try {
                    rank = JSON.decode(input, Ranking[].class);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.i(TAG, "TITLE " + rank[0].getTitle());
                Log.i(TAG, "ikioi " + rank[0].getIkioi());

                Message msg = mHandler.obtainMessage(CH_FUJI, rank[0]);
                mHandler.sendMessage(msg);
            }
        });
    }

    private void callAsahi() {
        final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("board", "liveanb");

        client.get(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                int end = result.length() - 2;
                // Log.i(TAG, result.toString().substring(9, end));
                ByteArrayInputStream input = new ByteArrayInputStream(result.toString()
                        .substring(9, end).getBytes());
                Ranking[] rank = null;

                try {
                    rank = JSON.decode(input, Ranking[].class);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.i(TAG, "TITLE " + rank[0].getTitle());
                Log.i(TAG, "ikioi " + rank[0].getIkioi());

                Message msg = mHandler.obtainMessage(CH_ASAHI, rank[0]);
                mHandler.sendMessage(msg);
            }
        });
    }

    private void callTx() {
        final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("board", "livetx");

        client.get(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                int end = result.length() - 2;
                // Log.i(TAG, result.toString().substring(9, end));
                ByteArrayInputStream input = new ByteArrayInputStream(result.toString()
                        .substring(9, end).getBytes());
                Ranking[] rank = null;

                try {
                    rank = JSON.decode(input, Ranking[].class);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.i(TAG, "TITLE " + rank[0].getTitle());
                Log.i(TAG, "ikioi " + rank[0].getIkioi());

                Message msg = mHandler.obtainMessage(CH_TX, rank[0]);
                mHandler.sendMessage(msg);
            }
        });
    }

    private static final String URL = "http://2ch-ranking.net/ranking.json";

    private static final int CH_NHK = 1;
    private static final int CH_ETV = 2;
    private static final int CH_NTV = 4;
    private static final int CH_TBS = 6;
    private static final int CH_FUJI = 8;
    private static final int CH_ASAHI = 5;
    private static final int CH_TX = 7;
}
