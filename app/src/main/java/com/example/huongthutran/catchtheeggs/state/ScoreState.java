package com.example.huongthutran.catchtheeggs.state;

import android.view.MotionEvent;

import com.example.huongthutran.catchtheeggs.main.Assets;
import com.example.huongthutran.catchtheeggs.main.GameMainActivity;
import com.example.huongthutran.catchtheeggs.utility.Painter;
import com.example.huongthutran.catchtheeggs.utility.UIButton;
import com.example.huongthutran.catchtheeggs.utility.UILabel;

public class ScoreState extends State {
    private UIButton continueButton;
    private UILabel labelScore,labelCongratulations;
    @Override
    public void init() {
        this.continueButton = new UIButton(850, 800, 1200, 950, Assets.continueButton);
        labelScore = new UILabel( "Best Score: " + GameMainActivity.user.getBestScores(), GameMainActivity.GAME_WIDTH / 2, 480);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.scorebackground, GameMainActivity.GAME_WIDTH/3, 0, GameMainActivity.GAME_WIDTH/5*2, GameMainActivity.GAME_HEIGHT-200);
        continueButton.render(g, false);
        labelScore.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            continueButton.onTouchDown(scaledX, scaledY);

        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (continueButton.isPressed(scaledX, scaledY)) {
                setCurrentState(new MenuState());

            }
        }
        return true;
    }
}

