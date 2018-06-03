package com.example.huongthutran.catchtheeggs.state;

import android.view.MotionEvent;


import com.example.huongthutran.catchtheeggs.main.Assets;
import com.example.huongthutran.catchtheeggs.main.GameMainActivity;
import com.example.huongthutran.catchtheeggs.utility.Painter;

public class LoadState extends State {
    @Override
    public void init() {
        load();
        setCurrentState(new MenuState());
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.gameloadingbackground, 0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

    @Override
    public void load() {
        Assets.load();
    }

    @Override
    public void unload() {

    }
}

