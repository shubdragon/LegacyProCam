package com.shubdragon.legacyprocam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class ShowPhotoActivity extends AppCompatActivity {

    ImageView imageview;
    Button btn_edit;

    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);

        imageview = findViewById(R.id.imageview);
        btn_edit = findViewById(R.id.btn_edit);

        path = getIntent().getExtras().getString("path");
        File imgfile = new File(path);

        if(imgfile.exists())
        {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());

            imageview.setImageBitmap(RotateBitmap(myBitmap, 90));
        }

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShowPhotoActivity.this, EditPhotoActivity.class);
                intent.putExtra("path", path);
                startActivity(intent);
            }
        });
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0,0,source.getWidth(),source.getHeight(),matrix,true);

    }

}