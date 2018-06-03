package com.example.huongthutran.catchtheeggs.GameState;
import com.example.huongthutran.catchtheeggs.util.*;
import android.view.MotionEvent;

/**
 * Created by Huong Thu Tran on 4/20/2018.
 */

public class MenuState extends State {
    @Override
    public void init() {
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        // g.drawImage(Assets.menuBackground, 0, 0, 1920, 1080);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
