package com.example.anhch_000.sqlphpasnytaskandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BananaManager.OnGetDatasListener {

    private RecyclerView recyclerView;
    private ArrayList<Banana> bananas;
    private Adapter adapter;
    private BananaManager bananaManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bananaManager = new BananaManager();
        bananaManager.setOnGetDatasListener(this);
        bananas = new ArrayList<>();
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.re_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onGetDataSuccess() {
        recyclerView.setAdapter(adapter = new Adapter(bananaManager.getBananas()));
    }

    @Override
    public void onGetDataFail() {
        Toast.makeText(this, "deo lay duoc du lieu", Toast.LENGTH_SHORT).show();
    }

    public void getDatas(View view) {
        if (view.getId() == R.id.btn_getdata) {
            bananaManager.getData();
        }
    }
}
