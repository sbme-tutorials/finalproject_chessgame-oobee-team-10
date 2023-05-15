package com.example.chessboard;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChessTimer {
    private GameDataModel newGameData;
    private  final int GAME_DURATION_MINUTES = 15;
    private static final int SECONDS_PER_MINUTE = 60;

    private Label timerLabel;
    private int remainingTime;

    public ChessTimer(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    public void startTimer() {
        remainingTime = GAME_DURATION_MINUTES * SECONDS_PER_MINUTE;

        Timeline timeline = new Timeline();
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

    public void stopTimer() {
        // Stop the timer if needed
        // You can implement the necessary logic here
    }

    private void handleTimeUp() {
        // Handle the case when the time is up for a player
        // You can implement the necessary logic here
    }

    private void updateTimerLabel() {
        int minutes = remainingTime / SECONDS_PER_MINUTE;
        int seconds = remainingTime % SECONDS_PER_MINUTE;

        String formattedTime = String.format("%02d:%02d", minutes, seconds);
        timerLabel.setText(formattedTime);
    }
}