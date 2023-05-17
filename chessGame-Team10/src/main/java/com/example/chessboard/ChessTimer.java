package com.example.chessboard;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class ChessTimer {
    private String timerColor;
    public Timeline timeline;
    private GameDataModel newGameData;
    private  final int GAME_DURATION_MINUTES = 15;
    private static final int SECONDS_PER_MINUTE = 60;

    private Label timerLabel;
    private int remainingTime;


    //Set the timer of each Player
    public void setTimerColor(String color) {
        this.timerColor =color;
    }

    public String getTimerColor(){
        return timerColor;
    }
    public void setTimerLabel(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    public void startTimer() {
        remainingTime = GAME_DURATION_MINUTES * SECONDS_PER_MINUTE;

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    remainingTime--;

                    if (remainingTime <= 0) {
                        // Time's up! The current player loses the game.
                        stopTimer();
                        handleTimeUp();
                    } else {
                        updateTimerLabel();
                    }
                })
        );

        timeline.play();
    }

    public void pauseAndPlay(String playerColor) {
        if(!timerColor.equals(playerColor))
            timeline.play();
        else timeline.pause();

    }

    public void stopTimer() {
        timeline.stop();
    }

    private void handleTimeUp() {
        // Handle the case when the time is up for a player
        // You can implement the necessary logic here
    }
    public Timeline getTilmeline(){
        return this.timeline;
    }
    private void updateTimerLabel() {
        int minutes = remainingTime / SECONDS_PER_MINUTE;
        int seconds = remainingTime % SECONDS_PER_MINUTE;

        String formattedTime = String.format("%02d:%02d", minutes, seconds);
        timerLabel.setText(formattedTime);
    }
}