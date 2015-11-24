package ru.isu.mobile_2015.prj_05;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Matrix3f;
import android.view.View;
import android.widget.ImageView;

public class MyActivity extends Activity {
    private static final int TAKE_A_PHOTO = 1;
    private ImageView mImageView;
    private ImageView mImageView2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mImageView = (ImageView)findViewById(R.id.imageView);
        mImageView2 = (ImageView)findViewById(R.id.imageView2);
    }

    public void onTakePhotoClick(View v) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, TAKE_A_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_A_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imageBitmap = imageBitmap.copy(Bitmap.Config.ARGB_8888, true);

            applyFilter(imageBitmap, new Matrix3f(new float[]{
                    0.333f, 0.333f, 0.333f,
                    0.333f, 0.333f, 0.333f,
                    0.333f, 0.333f, 0.333f
            }));

            mImageView.setImageBitmap(imageBitmap);

            imageBitmap = imageBitmap.copy(Bitmap.Config.ARGB_8888, true);

            applyFilter(imageBitmap, new Matrix3f(new float[]{
                    .393f, .349f, .272f,
                    .769f, .686f, .534f,
                    .189f, .168f, .131f
            }));

            mImageView2.setImageBitmap(imageBitmap);
        }
    }


    private void applyFilter(Bitmap imageBitmap, Matrix3f m) {
        for(int x = 0; x < imageBitmap.getWidth(); x++) {
            for(int y = 0; y < imageBitmap.getHeight(); y++) {
                int color = imageBitmap.getPixel(x,y);
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);

                float dr = m.get(0,0) * r + m.get(1,0) * g + m.get(2,0) * b;
                float dg = m.get(0,1) * r + m.get(1,1) * g + m.get(2,1) * b;
                float db = m.get(0,2) * r + m.get(1,2) * g + m.get(2,2) * b;

                imageBitmap.setPixel(x, y, Color.rgb((int)dr, (int)dg, (int)db));
            }
        }
    }
}
