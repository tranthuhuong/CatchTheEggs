package com.example.huongthutran.catchtheeggs.state;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.example.huongthutran.catchtheeggs.main.Assets;
import com.example.huongthutran.catchtheeggs.main.GameMainActivity;
import com.example.huongthutran.catchtheeggs.utility.Painter;
import com.example.huongthutran.catchtheeggs.utility.UIButton;

public class MenuState extends State {

    private UIButton playButton;
    private UIButton scoreButton;
    private UIButton exitButton;
    private static final String TAG = "MenuState";

    @Override
    public void init() {
        playButton = new UIButton(800, 300, 1100, 420, Assets.playButton);
        scoreButton = new UIButton(800, 450, 1100, 570, Assets.scorebutton);
        exitButton = new UIButton(800, 600, 1100, 720, Assets.quitButton);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
         g.drawImage(Assets.menuBackground, 0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
         playButton.render(g, false);
         scoreButton.render(g, false);
         exitButton.render(g, false);
    }

    @Override
    public void load() {

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            playButton.onTouchDown(scaledX, scaledY);
            scoreButton.onTouchDown(scaledX, scaledY);
            exitButton.onTouchDown(scaledX, scaledY);
        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (playButton.isPressed(scaledX, scaledY)) {
                playButton.cancel();
                setCurrentState(new PlayState());
            } else if (scoreButton.isPressed(scaledX, scaledY)) {
                scoreButton.cancel();
                setCurrentState(new ScoreState());
            }
            else if (exitButton.isPressed(scaledX, scaledY)) {
                exitButton.cancel();

            }
        }
        return true;
    }
}








