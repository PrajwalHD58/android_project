package com.example.task_internship;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {
    List<Image_model> list;
    ImageAdapter imageAdapter;
    RecyclerView recyclerView;
    String urls = "https://api.unsplash.com/photos/?client_id=1yb_mWk6WfXe2ZcTTKLrMP1j6EY3GvursSqLFX82XQU&page=";
    int page = 1;
    String perPage = "&per_page=30";

    ProgressBar progressBar;
    View decorView;

    int currentItem,totalItem,scrollOut;
    boolean scrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_2);
        setTitle("Screen-1");
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        recyclerView = findViewById(R.id.cycle);
        progressBar = findViewById(R.id.pro);

        list = new ArrayList<>();
        imageAdapter = new ImageAdapter(this,list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);


        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    scrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItem = gridLayoutManager.getChildCount();
                totalItem = gridLayoutManager.getItemCount();
                scrollOut = gridLayoutManager.findFirstVisibleItemPosition();

                if(scrolling &&(currentItem+scrollOut == totalItem))
                {
                    scrolling = false;
                    fetchWallPaper();
                }


            }
        });

        fetchWallPaper();


    }

    private void fetchWallPaper() {
        urls=urls+page+perPage;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urls, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i =0;i<response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        JSONObject imageUrls = jsonObject.getJSONObject("urls");
                        String urls = imageUrls.getString("small");

                        Image_model imageModel = new Image_model(urls);
                        list.add(imageModel);

                    }
                    imageAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);


                }catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
