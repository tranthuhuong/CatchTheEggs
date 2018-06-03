package com.example.huongthutran.catchtheeggs.state;

import android.view.MotionEvent;

import com.example.huongthutran.catchtheeggs.main.Assets;
import com.example.huongthutran.catchtheeggs.main.GameMainActivity;
import com.example.huongthutran.catchtheeggs.model.Boom;
import com.example.huongthutran.catchtheeggs.model.Egg;
import com.example.huongthutran.catchtheeggs.model.Player;
import com.example.huongthutran.catchtheeggs.utility.Painter;
import com.example.huongthutran.catchtheeggs.utility.UIButton;
import com.example.huongthutran.catchtheeggs.utility.UILabel;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {
    private Player player;
    private Egg egg1,egg2;
    private List<Boom> booms=new ArrayList<>();
    private UILabel labelScore;
    private UIButton pauseButton;
    private int score;
    private boolean isGameOver;
    @Override
    public void init() {
        isGameOver=false;
        score=0;
        pauseButton = new UIButton(GameMainActivity.GAME_WIDTH - 200, 50, GameMainActivity.GAME_WIDTH - 100, 150, Assets.pauseButton, Assets.pauseTouchButton);
        labelScore = new UILabel("0", GameMainActivity.GAME_WIDTH / 2 - 6, 150);
        player = new Player(GameMainActivity.GAME_WIDTH/2, GameMainActivity.GAME_HEIGHT - 280, 450, 250);
        egg1 = new Egg(300, -850, 220, 220);
        egg2 = new Egg(300, -50, 220, 220);
        for(int i=1;i<4;i++){
            Boom boom = new Boom(GameMainActivity.GAME_WIDTH+100, -250*i*2, 220, 220);
            booms.add(boom);
        }
    }

    @Override
    public void update(float delta) {
        player.update(delta);
        egg1.update();
        egg2.update();
        for(int i=0;i<3;i++){
           booms.get(i).update();
        }
        checkGameOver();
        if(!isGameOver){
            updateScore();
            labelScore.setText(score + "");
        }

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.gamebackground, 0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
        pauseButton.render(g, true);
        player.render(g);
        egg1.render(g);
        egg2.render(g);
        for(int i=0;i<3;i++){
            booms.get(i).render(g);
        }
        labelScore.render(g);
    }


    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        pauseButton.onTouchDown(scaledX, scaledY);
        if (!pauseButton.isButtonDown()) {
            player.onTouch(e, scaledX, scaledY);
        }
        if (pauseButton.isPressed(scaledX, scaledY)) {
            pauseButton.cancel();
            setPauseGame();
        }

        return true;
    }

    @Override
    public void load() {
        super.load();
    }
    private void updateScore() {
        int playerleft = player.getRect().left;
        int playerright = player.getRect().right;
        int playertop = player.getRect().top;
        int egg11eft = egg1.getRect().left;
        int egg1right = egg1.getRect().right;
        int egg1Bottom = egg1.getRect().bottom;
        int egg21eft = egg2.getRect().left;
        int egg2right = egg2.getRect().right;
        int egg2Bottom = egg2.getRect().bottom;

        if (egg1right < playerright &&egg11eft > playerleft && !egg1.isBroken() && egg1Bottom > playertop && !egg1.isPass()) {
            egg1.setPass(true);
            score++;
            Assets.playSound(Assets.wingSoundId);
        }
        if (egg2right < playerright &&egg21eft > playerleft && !egg2.isBroken() && egg2Bottom > playertop && !egg2.isPass()) {
            egg2.setPass(true);
            score++;
            Assets.playSound(Assets.wingSoundId);
        }

    }
    private void checkGameOver() {

        for (int i=0;i<3;i++){
            int playerleft = player.getRect().left;
            int playerright = player.getRect().right;
            int playertop = player.getRect().top;
            int boom1eft = booms.get(i).getRect().left;
            int boomright = booms.get(i).getRect().right;
            int boomBottom = booms.get(i).getRect().bottom;
            if (boomright < playerright &&boom1eft > playerleft && !booms.get(i).isBroken() && boomBottom > playertop && !booms.get(i).isPass()) {
                isGameOver = true;
                if(score>GameMainActivity.user.getBestScores()){
                    setCurrentState(new GameCompletionState(score));
                    Assets.playSound(Assets.wingSoundId);
                } else {
                    setCurrentState(new GameOverState(score));
                    Assets.playSound(Assets.hitSoundId);
                }

            }
        }
    }

}













