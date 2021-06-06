package com.example.bitmapsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //getResources().getLayout() return a XmlResourceParser. So, we can't cast it to View/ViewGroup
//        XmlResourceParser xml = getResources().getLayout(R.layout.activity_main);

        setContentView(R.layout.activity_main);
//        LinearLayout mainLayout = findViewById(R.id.ll_main_layout);
        LinearLayout mainLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        LinearLayout ll_container = mainLayout.findViewById(R.id.fl_bitmap_container);
        BitmapView bitmapView = new BitmapView(this, BitmapView.Shape.CIRCLE);
        BitmapView bitmapView2 = new BitmapView(this, BitmapView.Shape.HEART);
        BitmapView bitmapView3 = new BitmapView(this, BitmapView.Shape.RECT);
        ll_container.addView(bitmapView);
        ll_container.addView(bitmapView2);
        ll_container.addView(bitmapView3);

        shapeDrawableImage(ll_container);

        ViewGroup parent = (ViewGroup)ll_container.getParent();
        parent.removeView(ll_container);
        mainLayout.addView(ll_container);
        setContentView(mainLayout);
    }

    private void shapeDrawableImage(ViewGroup view){
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.type3);
        Bitmap output = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0XFF000000);
        Path path = new PathProvider(3000.0f, 3000.0f).getPath(BitmapView.Shape.HEART);
        canvas.drawPath(path, paint);
        PorterDuff.Mode porterDuffMode = PorterDuff.Mode.SRC_IN;
        paint.setXfermode(new PorterDuffXfermode(porterDuffMode));
        canvas.drawBitmap(src, 0, 0, paint);

        ImageView iv = new ImageView(this);
        iv.setImageBitmap(output);
        view.addView(iv);
    }
}