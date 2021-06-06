package com.example.bitmapsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import androidx.appcompat.widget.AppCompatImageView;

public class BitmapView extends AppCompatImageView {
    private Paint paint;
    private Path path;
    private Canvas canvas;
    private Shape shape;

    public enum Shape{
        RECT,
        HEART,
        CIRCLE
    }

    public BitmapView(Context context, Shape shape){
        super(context);
        this.path = new Path();
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.shape = shape;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = Bitmap.createBitmap(300, 500, Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.WHITE);
        this.canvas = new Canvas(bitmap);

        switch (this.shape){
            case RECT:
                paint.setColor(Color.BLACK);
                canvas.drawRect(20.0f, 20.0f, 280, 180, paint);
                break;
            case HEART:
                paint.setShader(null);
                float width = getContext().getResources().getDimension(R.dimen.heart_width);
                float height = getContext().getResources().getDimension(R.dimen.heart_height);
                path = new PathProvider(width, height).getPath(Shape.HEART);
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawPath(path, paint);
                break;
            case CIRCLE:
                paint.setColor(Color.BLUE);
                paint.setStyle(Paint.Style.STROKE);
                paint.setAntiAlias(true);
                canvas.drawCircle(getWidth() - 150.0f, getHeight() - 150.0f, 100, paint);
                break;
        }
//        Bitmap bitmap = Bitmap.createBitmap("bitmap_src")
        setImageBitmap(bitmap);
    }
}
