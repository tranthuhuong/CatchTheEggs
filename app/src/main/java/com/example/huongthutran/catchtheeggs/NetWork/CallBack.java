package com.example.huongthutran.catchtheeggs.NetWork;

public interface CallBack<T> {
    public void onSuccess(T result);
    public void onFail(T result);
}
