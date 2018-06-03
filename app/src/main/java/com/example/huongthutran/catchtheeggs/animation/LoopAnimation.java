package com.example.huongthutran.catchtheeggs.animation;
import com.example.huongthutran.catchtheeggs.util.*;
import android.graphics.Paint;

/**
 * Created by Huong Thu Tran on 4/20/2018.
 */
//class LoppAnimation dùng để lặp lại các hoạt động liên tiếp
public class LoopAnimation {
    private Frame[] frames; //mảng các hình để hiển thị
    private double[] frameEndTimes; //mảng chứa thời gian kết thúc 1 frame
    private int currentFrameIndex = 0; // chỉ số frams hiện tại trong mảng
    private double totalDuration = 0; //tổng thời gian hiển thị
    private double currentTime = 0;//thời gian hiện tại

    private boolean looping = true;

    public LoopAnimation(Boolean looping, Frame... frames) {
        this.looping = looping;
        this.frames = frames;
        frameEndTimes = new double[frames.length];
        for (int i = 0; i < frames.length; i++) {
            Frame frame = frames[i];
            totalDuration += frame.getDuration();
            frameEndTimes[i] = totalDuration;
        }
    }
    public synchronized void update(float increment) {
        currentTime += increment;
        if (currentTime > totalDuration && looping) {
            wrapAnimation();
        }
        if (currentFrameIndex < frameEndTimes.length) {
            while (currentTime > frameEndTimes[currentFrameIndex]) {
                currentFrameIndex++;
            }
        }
    }

    //restart the Animation
    private synchronized void wrapAnimation() {
        currentFrameIndex = 0;
        currentTime %= totalDuration;
    }

    public synchronized void render(Painter g, int x, int y) {
        g.drawImage(frames[currentFrameIndex].getImage(), x, y);
    }

    public synchronized void render(Painter g, int x, int y, int width, int height) {
        g.drawImage(frames[currentFrameIndex].getImage(), x, y, width, height);
    }
}
