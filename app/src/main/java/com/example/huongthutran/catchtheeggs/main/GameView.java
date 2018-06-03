package com.example.huongthutran.catchtheeggs.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.huongthutran.catchtheeggs.state.LoadState;
import com.example.huongthutran.catchtheeggs.state.PauseState;
import com.example.huongthutran.catchtheeggs.state.State;
import com.example.huongthutran.catchtheeggs.utility.InputHandler;
import com.example.huongthutran.catchtheeggs.utility.Painter;

public class GameView extends SurfaceView implements Runnable {

    private Bitmap gameImage;
    private Rect gameImageSrc;
    private Rect gameImageDst;
    private Canvas gameCanvas;
    public Painter graphics;

    private final String TAG = "GameView";

    private Thread gameThread;
    //trả về trạng thái đang chạy hay tam ngừng
    private volatile boolean running = false;
    //State hiện tại
    private volatile State currentState;
    private volatile State preState = null;

    private InputHandler inputHandler;

    public static final int FPS = 60;

    public GameView(Context context, int gameWidth, int gameHeight) {
        super(context);
        gameImage = Bitmap.createBitmap(gameWidth, gameHeight, Bitmap.Config.RGB_565);
        gameImageSrc = new Rect(0, 0, gameImage.getWidth(), gameImage.getHeight());
        gameImageDst = new Rect();
        gameCanvas = new Canvas(gameImage);
        graphics = new Painter(gameCanvas);

        SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initInput();
                if(currentState == null) {
                    setCurrentState(new LoadState());
                }
                initGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                pauseGame();
            }
        });
    }

    public GameView(Context context) {
        super(context);
    }

    //PAUSE AND RESUME GAME
    public void setPause() {
        System.gc();
        PauseState pauseState = new PauseState();
        pauseState.init();
        preState = currentState;
        currentState = pauseState;
        inputHandler.setCurrentState(currentState);
    }

    public void setResume() {
        if (preState != null) {
            currentState = preState;
            inputHandler.setCurrentState(currentState);
        }
    }

    public void setCurrentState(State newState) {
        System.gc();
        newState.init();
        currentState = newState;
        inputHandler.setCurrentState(currentState);
    }

    private void initInput() {
        if(inputHandler == null) {
            inputHandler = new InputHandler();
        }
        setOnTouchListener(inputHandler);
    }

    //Thread
    @Override
    public void run() {
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;

        //Game loop
        while(running) {

            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = sleepDurationMillis + updateDurationMillis;
            //Up date và Render
           updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) / 1000000L;
            sleepDurationMillis = Math.max(2, 17 - updateDurationMillis);

            try {
                Thread.sleep(sleepDurationMillis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //basically like a constructor for the game
    private void initGame() {
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    private void pauseGame() {
        running = false;
        while (gameThread.isAlive()) {
            try {
                gameThread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void onPause() {
        if (currentState != null) {
            currentState.onPause();
        }
    }

    public void onResume() {
        if (currentState != null) {
            currentState.onResume();
        }
    }

    private void updateAndRender(long delta) {

        currentState.update(delta / 1000f);
        currentState.render(graphics);
        //sau khi update thì sẽ đc ve lên Bitmap màn hình
        renderGameImage();
    }

    private void renderGameImage() {
        Canvas screen = getHolder().lockCanvas();
        if (screen != null) {
            screen.getClipBounds(gameImageDst);
            screen.drawBitmap(gameImage, gameImageSrc, gameImageDst, null);
            getHolder().unlockCanvasAndPost(screen);
        }
    }
}

