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

public class FragmentLogin  extends android.support.v4.app.Fragment implements CallBackData {
    View rootView;
    Button btnLogin,btnRegister,btnReset;
    EditText edtUsername,edtPass;
    private List<HttpParam> ls=new ArrayList<>();
    static FragmentLogin kt=new FragmentLogin();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_login, container, false);
        CallApi.getInstance().SetcallBack(this);
        btnLogin=rootView.findViewById(R.id.btnLogin);
        btnRegister=rootView.findViewById(R.id.btnRegister);
        edtPass=rootView.findViewById(R.id.edtPass);
        edtUsername=rootView.findViewById(R.id.edtUsername);
        btnReset=rootView.findViewById(R.id.btnResetPass);
        CallApi.getInstance().SetcallBack(this);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(FragmentResetPass.newInstance());
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(FragmentRegister.newInstance());
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallApi.getInstance().setU("");
                ls.clear();
                HttpParam httpParam1=new HttpParam();
                httpParam1.value= edtUsername.getText().toString()+"";
                httpParam1.param="username";
                ls.add(httpParam1);
                HttpParam httpParam2=new HttpParam();
                httpParam2.value=edtPass.getText().toString()+"" ;
                httpParam2.param="pass";
                ls.add(httpParam2);
                CallApi.getInstance().CallapiServer(ApiType.GET_USER, ls, null);

            }
        });
        return rootView;
    }
    public void setFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction transaction = ((LoginActivity) getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.idFragmentLogin, fragment);
        transaction.commit();
    }

    @Override
    public void Callback(ApiType apiType, String json) {
        if (apiType == ApiType.GET_USER) {
            try {
                JSONArray listJSON = new JSONArray(json);
                JSONObject jsonObject;
                if(listJSON.isNull(0)){
                    Toast.makeText(getContext(),"Thông tin chưa đúng",Toast.LENGTH_LONG);
                }
                jsonObject=listJSON.getJSONObject(0);

                String username=jsonObject.getString("user_name");
                Intent intent=new Intent(getContext(), GameMainActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("USER", username);
                intent.putExtra("USERLOGIN", mBundle);
                startActivity(intent);
                getActivity().finish();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(),"Thông tin chưa đúng",Toast.LENGTH_LONG).show();
            }
        }
    }
    public static FragmentLogin newInstance() {
        if(kt == null){
            kt = new FragmentLogin();
        }
        return kt;
    }
}
