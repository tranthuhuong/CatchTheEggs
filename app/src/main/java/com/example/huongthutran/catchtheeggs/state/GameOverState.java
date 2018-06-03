package com.example.huongthutran.catchtheeggs.state;

import android.view.MotionEvent;

import com.example.huongthutran.catchtheeggs.main.Assets;
import com.example.huongthutran.catchtheeggs.main.GameMainActivity;
import com.example.huongthutran.catchtheeggs.utility.Painter;
import com.example.huongthutran.catchtheeggs.utility.UIButton;
import com.example.huongthutran.catchtheeggs.utility.UILabel;

public class GameOverState extends State {

    private int playerScore;
    private UIButton continueButton;
    private UIButton exitButton;
    private UILabel labelScore,labelCongratulations;

    public GameOverState(int playerScore) {
        this.playerScore = playerScore;
        labelScore = new UILabel( playerScore + " score", GameMainActivity.GAME_WIDTH / 2, 480);
        labelScore.setSize(130);

        labelCongratulations=new UILabel("",GameMainActivity.GAME_WIDTH / 2,600);
        labelCongratulations.setSize(100);

        this.exitButton = new UIButton(1050, 700, 1400, 850, Assets.quitButton);
        this.continueButton = new UIButton(600, 700, 950, 850, Assets.continueButton);
        /*if(playerScore>GameMainActivity.user.getBestScores()){
            GameMainActivity.saveHighScore(playerScore);
            labelCongratulations.setText("* Best Score *");
        }*/
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.gameoverbackground, GameMainActivity.GAME_WIDTH/5, -200, GameMainActivity.GAME_WIDTH/5*3, GameMainActivity.GAME_HEIGHT-100);
        continueButton.render(g, false);
        exitButton.render(g, false);
        labelScore.render(g);
        labelCongratulations.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            continueButton.onTouchDown(scaledX, scaledY);
            exitButton.onTouchDown(scaledX, scaledY);

        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (continueButton.isPressed(scaledX, scaledY)) {
                exitButton.cancel();
                setCurrentState(new PlayState());
            } else if (exitButton.isPressed(scaledX, scaledY)) {
                exitButton.cancel();
                setCurrentState(new MenuState());
            }
        }
        return true;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

}

















