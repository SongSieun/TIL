package com.sesong.imageretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 이 프로젝트에서는 서버에 이미지 파일을 업로드하고 가져와서 리사이클러뷰에 띄워주는 것을 연습한다.
public class MainActivity extends AppCompatActivity {
    public static final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVpZCI6MSwidXNlcm5hbWUiOiJ0bGRtcyJ9LCJpYXQiOjE1NDA4MjE2NDF9._aGlgVG9x3kPTQ5ACnxzG6H32AsceVX2kmcX2eK0qps";
    private String title, image, combination, type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void addMenu(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<JsonObject> response = retrofitService.combinations(token, title, image, combination, type);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 2000:
                    title = data.getStringExtra("title");
                    combination = data.getStringExtra("content");
                    image = data.getStringExtra("image");
                    type = data.getStringExtra("type");
                    Log.d("menuTitleMaIn ", title);
                    Log.d("menuContentMaIn ", combination);
                    Log.d("menuImageMaIn ", image);
                    Log.d("imageTypeMaIn ", type);
                    addMenu();

                    break;
            }
        }
    }
}
