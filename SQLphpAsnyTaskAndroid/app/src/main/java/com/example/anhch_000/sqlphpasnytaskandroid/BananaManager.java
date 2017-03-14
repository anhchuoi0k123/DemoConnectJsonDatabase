package com.example.anhch_000.sqlphpasnytaskandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.example.anhch_000.sqlphpasnytaskandroid.Util.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by anhch_000 on 04/03/2017.
 */

public class BananaManager {
    private ArrayList<Banana> bananas;
    private OnGetDatasListener onGetDatasListener;


    public BananaManager() {
        bananas = new ArrayList<>();
    }

    public void getData() {
        class GetDataTask extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    onGetDatasListener.onGetDataSuccess();
                } else {
                    onGetDatasListener.onGetDataFail();
                }
                super.onPostExecute(aBoolean);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                String s = API.callService(API.GET_DATA_URL, null, null);
                Log.i(TAG, "doInBackground: "+s);
                if (!TextUtils.isEmpty(s)){
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("bang_1");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = (JSONObject) jsonArray.get(i);
                            Banana banana = new Banana();
                            banana.setId(object.getInt("id"));
                            banana.setName(object.getString("name"));
                            banana.setContent(object.getString("content"));

                            if (!object.getString("images").isEmpty()){
                                byte[] bytes = Base64.decode(object.getString("images"), Base64.DEFAULT);
                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                banana.setIamge(bitmap);
                            }
                            bananas.add(banana);
                        }
                        return true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        }

        new GetDataTask().execute();
    }

    public ArrayList<Banana> getBananas() {
        return bananas;
    }

    public void setBananas(ArrayList<Banana> bananas) {
        this.bananas = bananas;
    }

    public void setOnGetDatasListener(OnGetDatasListener onGetDatasListener) {
        this.onGetDatasListener = onGetDatasListener;
    }

    public interface OnGetDatasListener {
        void onGetDataSuccess();

        void onGetDataFail();
    }

}
