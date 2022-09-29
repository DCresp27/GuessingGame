package com.mintedtech.guessing_game;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class GuessingGame {
    private int winningNumber;

    public GuessingGame() {
        startNewGame();
    }

    public void startNewGame() {
        winningNumber = (int) Math.ceil(Math.random() * 3);
    }

    public int getWinningNumber() {
        return winningNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuessingGame that = (GuessingGame) o;
        return winningNumber == that.winningNumber;
    }

    @Override
    public int hashCode() {
        return winningNumber;
    }

    /**
     * toString method returns the object name and winning number
     *
     * @return Object name and winning number
     */
    @Override
    @NonNull
    public String toString() {
        return "GuessingGame{" +
                "winningNumber=" + winningNumber +
                '}';
    }

    public String getJSONStringFromThis() {
        return new Gson().toJson(this);
    }

    public static GuessingGame getGuessingGameObjectFromJSONString(String jsonString) {
        return (GuessingGame) new Gson().fromJson(jsonString, GuessingGame.class);
    }
}