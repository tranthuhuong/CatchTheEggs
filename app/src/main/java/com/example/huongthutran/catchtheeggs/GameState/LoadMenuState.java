package com.example.huongthutran.catchtheeggs.GameState;

import android.view.MotionEvent;
import com.example.huongthutran.catchtheeggs.util.*;
/**
 * Created by Huong Thu Tran on 4/20/2018.
 */

public class LoadMenuState extends State {
    @Override
    public void init() {
        load();
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Painter g) {
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

    @Override
    public void load() {
        Assets.menuBackground = Assets.loadBitmap("menuBackground.png", true);

    }
}
