package com.lrs.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.lrs.adapter.ProvinceAdapter;
import com.lrs.model.Province;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProvinceActivity extends AppCompatActivity {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    private final static String QUERY_URL = "http://guolin.tech/api/china";

    private static final String TAG = "ProvinceActivity";

    private ListView listView;

    private List<Province> provinceList = new ArrayList<>();

    private ProvinceAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);

        listView = findViewById(R.id.query_list);
        adapter = new ProvinceAdapter(ProvinceActivity.this, R.layout.province_item, provinceList);
        listView.setAdapter(adapter);

        Button queryBtn = findViewById(R.id.query_province);
        // 禁止在主线程中使用网络请求，NetworkOnMainThreadException
        queryBtn.setOnClickListener(view -> {

            new Thread(() -> {
                String res = get(QUERY_URL);
                Gson gson = new Gson();
                JsonArray jsonArray = new JsonParser().parse(res).getAsJsonArray();
                List<Province> list = new ArrayList<>(jsonArray.size());
                jsonArray.forEach(ele -> {
                    list.add(gson.fromJson(ele, Province.class));
                });

                Log.i(TAG, "onCreate: " + list);

//                ProvinceActivity.this.provinceList = list; 错误写法
                ProvinceActivity.this.provinceList.clear();
                ProvinceActivity.this.provinceList.addAll(list);
                // 需要在主线程中更新ui
                ProvinceActivity.this.runOnUiThread(() -> {

                    ProvinceActivity.this.adapter.notifyDataSetChanged();

                });

            }).start();


        });
    }


    /**
     * Post 请求
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    private String post(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.e(TAG, "post: post请求失败！", e);
        }
        return null;
    }

    /**
     * Get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    private String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.e(TAG, "get: get请求失败！", e);
        }
        return null;
    }

}
