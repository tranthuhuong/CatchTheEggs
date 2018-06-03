package com.example.huongthutran.catchtheeggs.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String user_name;
    private String name;
    private int BestScores;
    private int idScore;

    public User(){

    }
    public User(String user_name, String name, int bestScores, int idScore) {
        this.user_name = user_name;
        this.name = name;
        BestScores = bestScores;
        this.idScore = idScore;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBestScores() {
        return BestScores;
    }

    public void setBestScores(int bestScores) {
        BestScores = bestScores;
    }

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }
}
