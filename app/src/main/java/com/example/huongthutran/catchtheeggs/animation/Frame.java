package com.example.huongthutran.catchtheeggs.animation;

import android.graphics.Bitmap;

/**
 * Created by Huong Thu Tran on 4/20/2018.
 */

public class Frame {
    private Bitmap image; //hình hiển thị trên frams
    private double duration; //Thời gian chuyển đổi

    public Frame(Bitmap image, double duration) {
        this.image = image;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public Bitmap getImage() {
        return image;
    }
}
