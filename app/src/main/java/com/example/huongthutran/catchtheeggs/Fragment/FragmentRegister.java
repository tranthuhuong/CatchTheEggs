package com.example.huongthutran.catchtheeggs.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huongthutran.catchtheeggs.LoginActivity;
import com.example.huongthutran.catchtheeggs.NetWork.ApiType;
import com.example.huongthutran.catchtheeggs.NetWork.CallApi;
import com.example.huongthutran.catchtheeggs.NetWork.CallBackData;
import com.example.huongthutran.catchtheeggs.NetWork.HttpParam;
import com.example.huongthutran.catchtheeggs.R;
import com.example.huongthutran.catchtheeggs.main.GameMainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentRegister extends android.support.v4.app.Fragment implements CallBackData {
    View rootView;
    Button btnLogin,btnRegister;
    EditText edtUsername,edtPass,edtName;
    private List<HttpParam> ls=new ArrayList<>();
    static FragmentRegister kt=new FragmentRegister();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_register, container, false);
        CallApi.getInstance().SetcallBack(this);
        btnLogin=rootView.findViewById(R.id.btn_Login);
        btnRegister=rootView.findViewById(R.id.btn_Register);
        edtPass=rootView.findViewById(R.id.edt_Pass);
        edtName=rootView.findViewById(R.id.edt_Name);
        edtUsername=rootView.findViewById(R.id.edt_Username);

        CallApi.getInstance().SetcallBack(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtName.getText().toString();
                String pass=edtPass.getText().toString();
                String username=edtUsername.getText().toString();
                CallApi.getInstance().setU("");
                JSONObject json = new JSONObject();
                try {
                    json.put("user_name", username);
                    json.put("nam", name);
                    json.put("password", pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CallApi.getInstance().CallapiServer(ApiType.POST_USER, null, json);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(FragmentLogin.newInstance());
            }
        });
        return rootView;
    }
    public void setFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction transaction = ((LoginActivity) getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.idFragmentLogin, fragment);
        transaction.commit();
    }
    public static FragmentRegister newInstance() {
        if(kt == null){
            kt = new FragmentRegister();
        }
        return kt;
    }
    @Override
    public void Callback(ApiType apiType, String json) {
        if (apiType == ApiType.POST_USER) {
            try {
                JSONObject jsonObject =new JSONObject(json);
                String username=jsonObject.getString("user_name");
                JSONObject jsonScore = new JSONObject();
                try {
                    jsonScore.put("user_name", username);
                    jsonScore.put("score1", 0);
                    jsonScore.put("date", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CallApi.getInstance().CallapiServer(ApiType.POST_SCORE, null, jsonScore);
                setFragment(FragmentLogin.newInstance());
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(),"User Name tồn tại",Toast.LENGTH_LONG).show();
            }
        }
    }
}
