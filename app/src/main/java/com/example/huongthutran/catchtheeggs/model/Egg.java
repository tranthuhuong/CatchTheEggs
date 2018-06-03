package com.example.huongthutran.catchtheeggs.model;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.huongthutran.catchtheeggs.main.Assets;
import com.example.huongthutran.catchtheeggs.main.GameMainActivity;
import com.example.huongthutran.catchtheeggs.utility.Painter;

public class Egg {

        private float x, y;
        private int width, height;
        private Rect rect;
        private Bitmap egg;
        private int speed;
        private int count=0;
        private Boolean isPass = false;
        private Boolean isBreak=false;

        public Egg(float x, float y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            egg = Assets.eggwhite;
            speed = 35;

            rect = new Rect((int) x, (int) y, (int) (x + width), (int) (y + height));
        }

        public void update() {
            fly();
            updateRect();
        }

        public void updateRect() {
            rect.set((int) x, (int) y, (int) x + width, (int) y + height);
        }
        public void render(Painter g) {
            g.drawImage(egg, (int) x, (int) y, width, height);
        }

        public void onTouch(MotionEvent e, int scaledX, int scaledY) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {

            }
        }

        public void fly() {
            if(isPass){
                count=0;
                egg=Assets.eggwhite;
                y = - 100;
                //chưa bể
                isPass=false;
                isBreak=false;
                //random vị trí x, từ trên xuống dưới
                x = (int) Math.floor(Math.random() * (GameMainActivity.GAME_WIDTH-200)+100);
            } else {
                y += speed;
                if(y>=GameMainActivity.GAME_HEIGHT-200){
                    egg=Assets.eggwhitebroken;
                    isBreak=true;
                    if(count==0){
                        Assets.playSound(Assets.breakSoundId);
                    }
                    if(count<20){
                        //bị bể
                        count++;
                        y -= speed;
                    } else {

                    }
                }


                if (y >GameMainActivity.GAME_HEIGHT -200) {
                    count=0;
                    egg=Assets.eggwhite;
                    y = - 100;
                    //chưa bể
                    isBreak=false;
                    //random vị trí x, từ trên xuống dưới
                    x = (int) Math.floor(Math.random() * (GameMainActivity.GAME_WIDTH-200)+100);
                    isPass=false;
                }
            }



        }
    public Rect getRect() {
        return new Rect(rect.left+ 55, rect.top + 55, rect.right - 55, rect.bottom - 55);
    }
    public Boolean isBroken(){
            return isBreak;
    }
    public void isBroken(Boolean a){
        isBreak=a;
    }
    public void setPass(Boolean a){
        isPass=a;
    }
    public Boolean isPass(){
        return isPass;
    }
}
