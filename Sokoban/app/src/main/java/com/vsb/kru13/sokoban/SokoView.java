package com.vsb.kru13.sokoban;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by kru13 on 12.10.16.
 */
public class SokoView extends View{

    Bitmap[] bmp;

    int lx = 10;
    int ly = 10;

    int width;
    int height;

    int heroX = 6;
    int heroY = 4;

    private int level[] = {
            1,1,1,1,1,1,1,1,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,4,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0
    };

    private int levelClear[] = {
            1,1,1,1,1,1,1,1,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,0,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0
    };

    public SokoView(Context context) {
        super(context);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        bmp = new Bitmap[6];

        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.empty);
        bmp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
        bmp[2] = BitmapFactory.decodeResource(getResources(), R.drawable.box);
        bmp[3] = BitmapFactory.decodeResource(getResources(), R.drawable.goal);
        bmp[4] = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        bmp[5] = BitmapFactory.decodeResource(getResources(), R.drawable.boxok);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

                float xP = event.getX();
                float yP = event.getY();
                int lastHeroPos = heroY * 10 + heroX;

                //right
                if (xP > screenWidth / 1.5 && yP > screenHeight / 4) {
                    if (level[heroY * 10 + heroX + 1] == 0 || level[heroY * 10 + heroX + 1] == 3) {

                        level[heroY * 10 + heroX + 1] = 4;
                        level[heroY * 10 + heroX] = 0;
                        heroX++;

                    } else if ((level[heroY * 10 + heroX + 1] == 2) &&
                            (level[heroY * 10 + heroX + 2] == 0 || level[heroY * 10 + heroX + 2] == 3)) {

                        level[heroY * 10 + heroX + 2] = 2;
                        level[heroY * 10 + heroX + 1] = 4;
                        level[heroY * 10 + heroX] = 0;
                        heroX++;
                    }
                }

                //left
                else if (xP < screenWidth / 2.5 && yP > screenHeight / 4) {
                    if (level[heroY * 10 + heroX - 1] == 0 || level[heroY * 10 + heroX - 1] == 3) {

                        level[heroY * 10 + heroX - 1] = 4;
                        level[heroY * 10 + heroX] = 0;
                        heroX--;

                    } else if ((level[heroY * 10 + heroX - 1] == 2) &&
                            (level[heroY * 10 + heroX - 2] == 0 || level[heroY * 10 + heroX - 2] == 3)) {

                        level[heroY * 10 + heroX - 2] = 2;
                        level[heroY * 10 + heroX - 1] = 4;
                        level[heroY * 10 + heroX] = 0;
                        heroX--;
                    }
                }

                //top
                else if (yP < screenHeight / 4) {
                    if (level[(heroY - 1) * 10 + heroX] == 0 || level[(heroY - 1) * 10 + heroX] == 3) {

                        level[(heroY - 1) * 10 + heroX] = 4;
                        level[heroY * 10 + heroX] = 0;
                        heroY--;

                    } else if ((level[(heroY - 1) * 10 + heroX] == 2) &&
                            (level[(heroY - 2) * 10 + heroX] == 0 || level[(heroY - 2) * 10 + heroX] == 3)) {

                        level[(heroY - 2) * 10 + heroX] = 2;
                        level[(heroY - 1) * 10 + heroX] = 4;
                        level[heroY * 10 + heroX] = 0;
                        heroY--;
                    }
                }

                //bottom
                else if (yP > screenHeight / 1.5) {
                    if (level[(heroY + 1) * 10 + heroX] == 0 || level[(heroY + 1) * 10 + heroX] == 3) {

                        level[(heroY + 1) * 10 + heroX] = 4;
                        level[heroY * 10 + heroX] = 0;
                        heroY++;

                    } else if ((level[(heroY + 1) * 10 + heroX] == 2) &&
                            (level[(heroY + 2) * 10 + heroX] == 0 || level[(heroY + 2) * 10 + heroX] == 3)) {

                        level[(heroY + 2) * 10 + heroX] = 2;
                        level[(heroY + 1) * 10 + heroX] = 4;
                        level[heroY * 10 + heroX] = 0;
                        heroY++;
                    }
                }
                if (levelClear[lastHeroPos] == 3 && lastHeroPos != heroY * 10 + heroX) {

                    level[lastHeroPos] = 3;
                }
                invalidate();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = h / lx;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[level[i*10 + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
            }
        }

    }
}
