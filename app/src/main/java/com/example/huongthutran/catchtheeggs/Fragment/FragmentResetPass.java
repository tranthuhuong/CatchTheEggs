package com.example.huongthutran.catchtheeggs.Fragment;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentResetPass extends android.support.v4.app.Fragment implements CallBackData {
    View rootView;
    Button btnLogin,btnReset;
    EditText edtUsername,edtPass,edtRePass;
    private List<HttpParam> ls=new ArrayList<>();
    static FragmentResetPass kt=new FragmentResetPass();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_reset_pass, container, false);
        CallApi.getInstance().SetcallBack(this);
        btnLogin=rootView.findViewById(R.id.btnLogin);
        btnReset=rootView.findViewById(R.id.btnReset);
        edtPass=rootView.findViewById(R.id.edtPassNew);
        edtRePass=rootView.findViewById(R.id.edtRePassNew);
        edtUsername=rootView.findViewById(R.id.edtUsername);

        CallApi.getInstance().SetcallBack(this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=edtPass.getText().toString();
                String rePass=edtRePass.getText().toString();
                String username=edtUsername.getText().toString();
                if(rePass.equals(pass)){
                    CallApi.getInstance().setU("");
                    JSONObject json = new JSONObject();
                    try {
                        json.put("user_name", username);
                        json.put("nam", "");
                        json.put("password", pass);
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                    CallApi.getInstance().setU(username);
                    CallApi.getInstance().CallapiServer(ApiType.PUT_USER, null, json);
                }


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
    public static FragmentResetPass newInstance() {
        if(kt == null){
            kt = new FragmentResetPass();
        }
        return kt;
    }
    @Override
    public void Callback(ApiType apiType, String json) {
        if (apiType == ApiType.PUT_USER) {
            try {
                JSONObject jsonObject =new JSONObject(json);
                if (json==null){
                    Toast.makeText(getContext(),"User Name Không chính xác",Toast.LENGTH_LONG).show();
                }
                String username=jsonObject.getString("user_name");
                setFragment(FragmentLogin.newInstance());
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(),"User Name Không chính xác",Toast.LENGTH_LONG).show();
            }
        }
    }
}
