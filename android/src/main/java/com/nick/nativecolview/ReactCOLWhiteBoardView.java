package com.nick.nativecolview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.util.DisplayMetrics;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/**
 * Created by nick on 7/31/16.
 */

final class WhiteBoardPointType {
    public static final int RedPenDown = -1;
    public static final int BluePenDown = -2;
    public static final int GreenPenDown = -3;
    public static final int BrownPenDown = -4;
    public static final int OrangePenDown = -5;
    public static final int PurplePenDown = -6;
    public static final int YelloPenDown = -7;
    public static final int BlackPenDown = -8;
    public static final int WideEraser = -9;
    public static final int NarrowEraser = -10;
    public static final int PenUp = -100;
    public static final int Clear = -200;
}

class WhiteBoardPoint {
    private int x;
    private int y;
    private int millisecond;



    public WhiteBoardPoint(ReadableMap map) {
        this.x = map.getInt("x");
        this.y = map.getInt("y");
        this.millisecond = map.getInt("millisecond");
        
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getMillisecond() {
        return this.millisecond;
    }

}

public class ReactCOLWhiteBoardView extends View {
    private int lastX = -1;
    private int lastY = -1;
    private int lineWidth = 2;
    

    private Paint paint;
    
    private int milsecond;
    private ReadableArray pointsAry;
    public ReactCOLWhiteBoardView(Context context) {
        super(context);

        this.lastX = -1;
        this.lastY = -1;
        this.lineWidth = 2;
        
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);

        
    }

    public void setPoints(ReadableArray newPointsAry) {
        if (newPointsAry != null) {
            pointsAry = newPointsAry;
            this.invalidate();
        }
    }
    
    public void setMilsecond(int newMilsecond) {
        this.milsecond = newMilsecond;
    }

    @Override
    protected  void onDraw(Canvas canvas) {
        if (pointsAry != null) {
            for(int idx = 0; idx < pointsAry.size(); idx++) {
                ReadableMap objMap = pointsAry.getMap(idx);
                WhiteBoardPoint point = new WhiteBoardPoint(objMap);
                drawPoint(point, canvas);
            }
        }
    }

    private void drawPoint(WhiteBoardPoint point, Canvas canvas) {
        float hscale = this.getWidth() / 960;
        float vscale = this.getHeight() / 480;

        switch (point.getX()) {
            case WhiteBoardPointType.BlackPenDown:
                paint.setColor(Color.BLACK);
                break;
            case WhiteBoardPointType.RedPenDown:
                paint.setColor(Color.RED);
                break;
            case WhiteBoardPointType.BluePenDown:
                paint.setColor(Color.BLUE);
                break;
            case WhiteBoardPointType.GreenPenDown:
                paint.setColor(Color.GREEN);
                break;
            case WhiteBoardPointType.BrownPenDown:
                paint.setColor(Color.rgb(210,105,30));
                break;
            case WhiteBoardPointType.OrangePenDown:
                paint.setColor(Color.rgb(255,215,0));
                break;
            case WhiteBoardPointType.PurplePenDown:
                paint.setColor(Color.rgb(128,43,226));
                break;
            case WhiteBoardPointType.YelloPenDown:
                paint.setColor(Color.YELLOW);
                break;
            case WhiteBoardPointType.WideEraser:
                paint.setColor(Color.WHITE);
                this.lineWidth = 390 / 12;
                break;
            case WhiteBoardPointType.NarrowEraser:
                paint.setColor(Color.WHITE);
                this.lineWidth = 390 / 48;
                break;
            case WhiteBoardPointType.PenUp:
                this.lineWidth = 2;
                this.lastX = -1;
                this.lastY = -1;

                break;
            case WhiteBoardPointType.Clear:
                paint.setColor(Color.WHITE);
                canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), paint);
                break;
            default:
                if (this.lastX > -1 && this.lastY > -1 && point.getX() < 65300 && this.lastY < 10000) {
                    paint.setStrokeWidth(this.lineWidth * hscale);

                    float startX = this.lastX * hscale / 12;
                    float startY = this.lastY * vscale / 12;
                    float endX = point.getX() * hscale / 12;
                    float endY = point.getY() * vscale / 12;

                    canvas.drawLine(startX, startY, endX, endY, paint);

                }
                this.lastX = point.getX();
                this.lastY = point.getY();
                break;
        }
    }
}
