package com.example.simon.doodleproject;

import android.content.Context;
import android.gesture.GestureLibrary;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simon on 11/6/2016.
 */
public class DoodleView extends View {

    private Paint _paintDoodle = new Paint();
    private Path _path = new Path();
    private ArrayList<Path> _pathList = new ArrayList<Path>();
    private ArrayList<Path> _redoList = new ArrayList<Path>();
    private HashMap<Path, Paint> _map = new HashMap<Path, Paint>();
    private HashMap<Path, Paint> _redoMap = new HashMap<Path, Paint>();

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int opacity = 255;
    private float strokeWidth = 2;

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Path p: _pathList) {
            canvas.drawPath(p, _map.get(p));
        }
        canvas.drawPath(_path, _paintDoodle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        _paintDoodle.setARGB(opacity, red, green, blue);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);
        _paintDoodle.setStrokeWidth(strokeWidth);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                _path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                _path.lineTo(touchX, touchY);
                Path copyPath = new Path(_path);
                Paint copyPaint = new Paint(_paintDoodle);
                _map.put(copyPath, copyPaint);
                _pathList.add(copyPath);

                _paintDoodle = new Paint();
                _path = new Path();
                _paintDoodle.setARGB(opacity, red, green, blue);
                _paintDoodle.setAntiAlias(true);
                _paintDoodle.setStyle(Paint.Style.STROKE);
                _paintDoodle.setStrokeWidth(strokeWidth);
                break;
        }

        invalidate();
        return true;
    }


    public void clear() {
        _pathList.clear();
        invalidate();
    }

    public float get_size() {
        return strokeWidth;
    }

    public void set_size(float width) {
        strokeWidth = width;
        _paintDoodle.setStrokeWidth(strokeWidth);
    }

    public void set_opacity(int alpha) {
        opacity = alpha;
        _paintDoodle.setARGB(opacity, red, green, blue);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getOpacity() {
        return opacity;
    }
    public void set_color(int a, int r, int g, int b) {
        opacity = a;
        red = r;
        green = g;
        blue = b;
        _paintDoodle.setARGB(a, r, g, b);
    }

    public void undo() {
        if (_pathList.size() > 0) {
            Path temp = _pathList.remove(_pathList.size() - 1);
            Paint temp2 = _map.remove(temp);

            _redoList.add(temp);
            _redoMap.put(temp, temp2);
            invalidate();
        }
    }

    public void redo() {
        if (_redoList.size() > 0) {
            Path temp = _redoList.remove(_redoList.size() - 1);
            Paint temp2 = _redoMap.remove(temp);

            _pathList.add(temp);
            _map.put(temp, temp2);
            invalidate();
        }
    }

}