package com.sesong.imageretrofit;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;

public class FormActivity extends AppCompatActivity {
    String title, content, image, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button okBtn = findViewById(R.id.okBtn);
        Button searchImage = findViewById(R.id.searchImage);

        title = "title";
        content = "content";
        type = "food";

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormActivity.this, MainActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("image", image);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                Log.d("AddMenuIntent  ", String.valueOf(intent));
                startActivityForResult(intent, 7000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 7000:
                    image = sendPicture(data.getData()); //갤러리에서 가져오기 // NullPointer
                    Log.d("AddMenuIntentData  ", image);
                    break;
            }
        }
    }

    private String sendPicture(Uri imgUri) {
        String imagePath = getRealPathFromURI(imgUri); // path 경로
        Log.d("AddMenuRealPath  ", imagePath);
        //경로를 통해 비트맵으로 전환
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        Log.d("AddMenuBitmap  ", String.valueOf(bitmap));
        String imageString = getStringFromBitmap(bitmap);  // NullPointer
        Log.d("AddMenuLog  ", imageString);

        return imageString;
    }

    // 비트맵 -> 스트링
    private String getStringFromBitmap(Bitmap bitmapPicture) {
        Log.d("AddMenuBitmap ", String.valueOf(bitmapPicture));
        final int COMPRESSION_QUALITY = 100;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY, byteArrayBitmapStream); // NullPointer
        byte[] b = byteArrayBitmapStream.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    private String getRealPathFromURI(Uri contentUri) {
        int column_index = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }
        return cursor.getString(column_index);
    }
}
