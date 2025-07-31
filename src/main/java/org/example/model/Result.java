package org.example.model;

public class Result {
    private int userId;
    private int score;
    private int timeTaken;

    public Result(int userId, int score, int timeTaken) {
        this.userId = userId;
        this.score = score;
        this.timeTaken = timeTaken;
    }

    public int getUserId() { return userId; }
    public int getScore() { return score; }
    public int getTimeTaken() { return timeTaken; }
}

