package com.example.huongthutran.catchtheeggs.GameState;

import android.view.MotionEvent;
import com.example.huongthutran.catchtheeggs.util.*;
import com.example.huongthutran.catchtheeggs.MainActivity;

/**
 * Created by Huong Thu Tran on 4/20/2018.
 */

public abstract class State {

    public void setCurrentState(State newState) {
        MainActivity.sGame.setCurrentState(newState);
    }

    public abstract void init();

    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);

    public void onPause(){}

    public void onResume() {}

    public void load() {}

    public void unload() {}
}
