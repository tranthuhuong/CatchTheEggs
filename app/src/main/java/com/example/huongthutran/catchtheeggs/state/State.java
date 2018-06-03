package com.example.huongthutran.catchtheeggs.state;

import android.view.MotionEvent;

import com.example.huongthutran.catchtheeggs.main.GameMainActivity;
import com.example.huongthutran.catchtheeggs.utility.Painter;

public abstract class State {

    public void setCurrentState(State newState) {
        GameMainActivity.gameView.setCurrentState(newState);
    }

    public void setPauseGame() {
        GameMainActivity.gameView.setPause();
    }

    public void setResumeGame(){
        GameMainActivity.gameView.setResume();
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

