package com.example.user.cursoradapterexam;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);*/

        // 뷰
        GridView photoListView = (GridView) findViewById(R.id.photo_list);
        // 사진 데이터
        Cursor cursor = getContentResolver().query
                (MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // From 절
                null,
                null,
                null,
                MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"); // Order By 절
        // 어댑터
        MyCursorAdapter adapter = new MyCursorAdapter(this, cursor);
        photoListView.setAdapter(adapter);
        // 클릭 이벤트 처리
        photoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 부분의 cursor 데이타
                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                Toast.makeText(MainActivity.this, "사진 경로 : " + path, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
