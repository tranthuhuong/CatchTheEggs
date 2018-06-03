package com.example.huongthutran.catchtheeggs.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.huongthutran.catchtheeggs.NetWork.ApiType;
import com.example.huongthutran.catchtheeggs.NetWork.CallApi;
import com.example.huongthutran.catchtheeggs.NetWork.CallBack;
import com.example.huongthutran.catchtheeggs.NetWork.CallBackData;
import com.example.huongthutran.catchtheeggs.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class GameMainActivity extends Activity implements CallBackData{

    public  static final int GAME_WIDTH = 1920;
    public static final int GAME_HEIGHT = 1080;
    public static GameView gameView;
    public static AssetManager assets;
    public static User user=new User();
    String s="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        assets = getAssets();
        gameView = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
        setContentView(gameView);
        CallApi.getInstance().SetcallBack(this);
        Intent loginIntent=getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller=loginIntent.getBundleExtra("USERLOGIN");
        s=packageFromCaller.getString("USER");
        CallApi.getInstance().setU(s);
        CallApi.getInstance().CallapiServer(ApiType.GET_USER, null, null);

        //set full screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    /*public static void exit(){
        finish();
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        gameView.onResume();
    }

    @Override //onPause() is also run when the game is quit
    protected  void onPause() {
        super.onPause();
        gameView.onPause();
    }

    public static void saveHighScore(int highscore) {
        user.setBestScores(highscore);
        CallApi.getInstance().setU(user.getIdScore()+"");
        JSONObject json = new JSONObject();
        try {
            json.put("id", user.getIdScore());
            json.put("user_name", user.getUser_name());
            json.put("score1", highscore);
            json.put("time", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CallApi.getInstance().CallapiServer(ApiType.PUT_SCORE, null, json);

    }

    @Override
    public void Callback(ApiType apiType, String json) {
        if (apiType == ApiType.GET_USER) {
            try {
                user = parseUser(json);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("test callback 1",e.toString());
            }
        }
    }
    public User parseUser(String json) throws JSONException {
        User user=new User();
        JSONObject jsonObject = new JSONObject(json);
        user.setName(jsonObject.getString("name"));
        user.setUser_name(jsonObject.getString("user_name"));
        user.setBestScores(jsonObject.getInt("bestScore"));
        user.setIdScore(jsonObject.getInt("idScore"));
        Toast.makeText(getApplicationContext(),""+user.getBestScores(),Toast.LENGTH_LONG);
        return user;
    }
}
