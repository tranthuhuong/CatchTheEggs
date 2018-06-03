package com.example.huongthutran.catchtheeggs.model;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.huongthutran.catchtheeggs.main.Assets;
import com.example.huongthutran.catchtheeggs.utility.Painter;

public class Player {
    private float x, y;
    private int width, height, velY;
    private Rect rect;
    private float yGround;
    private int pressedCount;
    private Bitmap basket=Assets.basket;;

    private static final int ACCEL_GRAVITY = 1800;

    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rect = new Rect((int) x, (int) y, (int) (x + width), (int) (y + height));
        yGround = y + height;
        pressedCount = 0;
    }

    public void update(float delta) {
        if (isGrounded()) {
            velY = 0;
            pressedCount = 0;
        } else {
            velY += ACCEL_GRAVITY * delta;
        }

        y += velY * delta;
        updateRect();
    }
    public void render(Painter g) {
        g.drawImage(basket, (int)x, (int)y, width, height);
    }
    public void changeLocation(float a) {
        x=a;

    }


    public void onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            changeLocation((e.getX()-width/2));
        }
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            changeLocation(e.getX()-width/2);
        }
    }

    public void updateRect() {
        rect.set((int) x, (int) y, (int) x + width, (int) y + height);
    }

    public boolean isGrounded() {
        if (rect.top + rect.height() >= yGround) {
            return true;
        }
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rect getRect() {
        return new Rect(rect.left + 20, rect.top + 30, rect.right - 20, rect.bottom - 35);
    }

}
















