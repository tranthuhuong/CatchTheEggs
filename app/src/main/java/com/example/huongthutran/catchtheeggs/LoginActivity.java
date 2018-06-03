package com.example.huongthutran.catchtheeggs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huongthutran.catchtheeggs.Fragment.FragmentLogin;
import com.example.huongthutran.catchtheeggs.NetWork.ApiType;
import com.example.huongthutran.catchtheeggs.NetWork.CallApi;
import com.example.huongthutran.catchtheeggs.NetWork.CallBackData;
import com.example.huongthutran.catchtheeggs.NetWork.HttpParam;
import com.example.huongthutran.catchtheeggs.main.GameMainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements CallBackData{
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setFragment(FragmentLogin.newInstance());

    }

    @Override
    public void Callback(ApiType apiType, String json) {

    }
    public void setFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.idFragmentLogin, fragment);
        transaction.commit();
        Log.d("test","2");
    }
}
