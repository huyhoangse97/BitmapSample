package com.example.bitmapsample;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class PathProvider {
    private float width;
    private float height;

    public PathProvider(float w, float h){
        this.width = w;
        this.height = h;
    }

    public Path getPath(BitmapView.Shape shape){
        Path path = new Path();
        switch (shape){
            case HEART:
                // Starting point
                path.moveTo(width / 2, height / 5);
                // Upper left path
                path.cubicTo(5 * width / 14, 0,
                        0, height / 15,
                        width / 28, 2 * height / 5);
                // Lower left path
                path.cubicTo(width / 14, 2 * height / 3,
                        3 * width / 7, 5 * height / 6,
                        width / 2, height);
                // Lower right path
                path.cubicTo(4 * width / 7, 5 * height / 6,
                        13 * width / 14, 2 * height / 3,
                        27 * width / 28, 2 * height / 5);
                // Upper right path
                path.cubicTo(width, height / 15,
                        9 * width / 14, 0,
                        width / 2, height / 5);
                break;
            default:
                break;
        }
        return path;
    }
}
